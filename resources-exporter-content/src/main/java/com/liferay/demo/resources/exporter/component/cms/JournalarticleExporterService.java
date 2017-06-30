
package com.liferay.demo.resources.exporter.component.cms;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.demo.resources.exporter.file.service.impl.FileExportService;
import com.liferay.demo.resources.exporter.model.Resource;
import com.liferay.demo.resources.exporter.service.ResourceExporterService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.exportimport.content.processor.ExportImportContentProcessor;
import com.liferay.exportimport.content.processor.ExportImportContentProcessorRegistryUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PredicateFilter;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

@Component(immediate = true, property = {
				// TODO enter required service properties
}, service = ResourceExporterService.class)
public class JournalarticleExporterService extends CMSExporterBaseService {

	@Override
	public String getOSGiServiceIdentifier() {

		return ResourceExporterService.class.getCanonicalName();
	}

	@Override
	public List<Resource> getExportData(long groupId)
		throws PortalException {

		List<DDMStructure> siteStructures = ddmStructureLocalService.getStructures(groupId);

		List<Resource> sitemapExport = new ArrayList<Resource>();

		for (DDMStructure ddmStructure : siteStructures) {

			// String ddmStructureName =
			// this.fileExportService.escapeFileName(ddmStructure.getName(Locale.ENGLISH));

			for (JournalArticle article : journalArticleLocalService.getArticlesByStructureId(groupId, ddmStructure.getStructureKey(), 0, 10, null)) {
				log.error("Processing Content " + article.getTitle(Locale.ENGLISH));
				ExportImportContentProcessor exportImportContentProcessor =
					ExportImportContentProcessorRegistryUtil.getExportImportContentProcessor(JournalArticle.class.getName());

				try {
					exportImportContentProcessor.validateContentReferences(groupId, article.getContent());
					// "<![CDATA[[$FILE=Sample4.pdf$]]]>";
					String parsedContent = article.getContent();

					String articleName = this.fileExportService.escapeFileName(article.getTitle(Locale.ENGLISH), "xml");

					parsedContent = updateDLReferences(groupId, parsedContent);

					File templateFile = this.fileExportService.writeStringIntoFile(articleName, parsedContent);

					DDMTemplate template = DDMTemplateLocalServiceUtil.getDDMTemplate(Long.parseLong(article.getDDMTemplateKey()) + 1);
					// DDMTemplate template = this.ddmTemplateLocalService.getd
					// article.getDDMTemplateKey()
					// TODO Fix tempalteId + 1
					sitemapExport.add(new Resource(this.getPathName() + this.fileExportService.escapeFileName(template.getName(Locale.ENGLISH)) + "/", articleName, templateFile));

					log.error("file for " + articleName + " exists? " + templateFile.exists());
				}
				catch (PortalException e) {
					log.error("Content " + article.getTitle(Locale.ENGLISH) + "won't be added as content references have not been satified");
					continue;
				}
			}
		}
		return sitemapExport;
	}

	protected String updateDLReferences(long groupId, String content)
		throws PortalException {

		String portalURL = PortalUtil.getPathContext();

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		String[] patterns = {
			portalURL.concat("/c/document_library/get_file?"), portalURL.concat("/documents/"), portalURL.concat("/image/image_gallery?")
		};

		int beginPos = -1;
		int endPos = content.length();
		String parsedcontent = content;
		while (true) {
			beginPos = StringUtil.lastIndexOfAny(content, patterns, endPos);

			if (beginPos == -1) {
				break;
			}

			parsedcontent = getDLReferenceParameters(groupId, parsedcontent, beginPos + portalURL.length(), endPos);

			endPos = beginPos - 1;

		}

		return parsedcontent;
	}

	protected FileEntry getFileEntry(Map<String, String[]> map) {

		if (MapUtil.isEmpty(map)) {
			return null;
		}

		FileEntry fileEntry = null;

		try {
			String uuid = MapUtil.getString(map, "uuid");
			long groupId = MapUtil.getLong(map, "groupId");

			if (Validator.isNotNull(uuid)) {
				fileEntry = DLAppLocalServiceUtil.getFileEntryByUuidAndGroupId(uuid, groupId);
			}
			else {
				if (map.containsKey("folderId")) {
					long folderId = MapUtil.getLong(map, "folderId");
					String name = MapUtil.getString(map, "name");
					String title = MapUtil.getString(map, "title");

					if (Validator.isNotNull(title)) {
						fileEntry = DLAppLocalServiceUtil.getFileEntry(groupId, folderId, title);
					}
					else {
						DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchFileEntryByName(groupId, folderId, name);

						if (dlFileEntry != null) {
							fileEntry = DLAppLocalServiceUtil.getFileEntry(dlFileEntry.getFileEntryId());
						}
					}
				}
				else if (map.containsKey("image_id")) {
					DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchFileEntryByAnyImageId(MapUtil.getLong(map, "image_id"));

					if (dlFileEntry != null) {
						fileEntry = DLAppLocalServiceUtil.getFileEntry(dlFileEntry.getFileEntryId());
					}
				}
			}
		}
		catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug(e, e);
			}
			else if (log.isWarnEnabled()) {
				log.warn(e.getMessage());
			}
		}

		return fileEntry;
	}

	protected String getDLReferenceParameters(long groupId, String content, int beginPos, int endPos) {

		boolean legacyURL = true;
		char[] stopChars = DL_REFERENCE_LEGACY_STOP_CHARS;

		if (content.startsWith("/documents/", beginPos)) {
			legacyURL = false;
			stopChars = DL_REFERENCE_STOP_CHARS;
		}

		endPos = StringUtil.indexOfAny(content, stopChars, beginPos, endPos);

		if (endPos == -1) {
			return null;
		}
		String documentURL = content.substring(beginPos, endPos);
		System.out.println("STRING " + content.substring(beginPos, endPos));

		Map<String, String[]> map = new HashMap<>();

		String dlReference = content.substring(beginPos, endPos);

		while (dlReference.contains(StringPool.AMPERSAND_ENCODED)) {
			dlReference = dlReference.replace(StringPool.AMPERSAND_ENCODED, StringPool.AMPERSAND);
		}

		if (!legacyURL) {
			String[] pathArray = dlReference.split(StringPool.SLASH);

			// if (pathArray.length < 3) {
			// return map;
			// }

			map.put("groupId", new String[] {
				pathArray[2]
			});

			if (pathArray.length == 4) {
				map.put("uuid", new String[] {
					pathArray[3]
				});
			}
			else if (pathArray.length == 5) {
				map.put("folderId", new String[] {
					pathArray[3]
				});
				map.put("title", new String[] {
					HttpUtil.decodeURL(pathArray[4])
				});
			}
			else if (pathArray.length > 5) {
				map.put("uuid", new String[] {
					pathArray[5]
				});
			}
		}
		else {
			dlReference = dlReference.substring(dlReference.indexOf(CharPool.QUESTION) + 1);

			map = HttpUtil.parameterMapFromString(dlReference);

			String[] imageIds = null;

			if (map.containsKey("img_id")) {
				imageIds = map.get("img_id");
			}
			else if (map.containsKey("i_id")) {
				imageIds = map.get("i_id");
			}

			imageIds = ArrayUtil.filter(imageIds, new PredicateFilter<String>() {

				@Override
				public boolean filter(String imageId) {

					if (Validator.isNotNull(imageId)) {
						return true;
					}

					return false;
				}

			});

			if (ArrayUtil.isNotEmpty(imageIds)) {
				map.put("image_id", imageIds);
			}
		}

		map.put("endPos", new String[] {
			String.valueOf(endPos)
		});

		String groupIdString = MapUtil.getString(map, "groupId");

		if (groupIdString.equals("@group_id@")) {
			groupIdString = String.valueOf(groupId);

			map.put("groupId", new String[] {
				groupIdString
			});
		}
		FileEntry fileEntry = getFileEntry(map);
		if (fileEntry != null)
			content = StringUtil.replace(content, documentURL, "[$FILE=" + fileEntry.getFileName() + "$]");
		else
			content = StringUtil.replace(content, documentURL, "");
		return content;
	}

	@Override
	public String getPathName() {

		return super.getPathName() + "articles/";
	}

	@Override
	public String getExportItem() {

		return "journalarticles";
	}

	@Reference
	protected void setDDMTemplateLocalService(DDMTemplateLocalService ddmTemplateLocalService) {

		this.ddmTemplateLocalService = ddmTemplateLocalService;
	}

	protected DDMTemplateLocalService ddmTemplateLocalService;

	protected DDMStructureLocalService ddmStructureLocalService;

	@Reference
	protected void setDDMStructureLocalService(DDMStructureLocalService ddmStructureLocalService) {

		this.ddmStructureLocalService = ddmStructureLocalService;
	}

	protected JournalArticleLocalService journalArticleLocalService;

	@Reference
	protected void setJournalArticleLocalService(JournalArticleLocalService journalArticleLocalService) {

		this.journalArticleLocalService = journalArticleLocalService;
	}

	protected FileExportService fileExportService;

	@Reference
	protected void setFileExportService(FileExportService fileExportService) {

		this.fileExportService = fileExportService;
	}

	protected static final char[] DL_REFERENCE_LEGACY_STOP_CHARS = {
		CharPool.APOSTROPHE, CharPool.CLOSE_BRACKET, CharPool.CLOSE_CURLY_BRACE, CharPool.CLOSE_PARENTHESIS, CharPool.GREATER_THAN, CharPool.LESS_THAN, CharPool.PIPE,
		CharPool.QUOTE, CharPool.SPACE
	};

	protected static final char[] DL_REFERENCE_STOP_CHARS = {
		CharPool.APOSTROPHE, CharPool.CLOSE_BRACKET, CharPool.CLOSE_CURLY_BRACE, CharPool.CLOSE_PARENTHESIS, CharPool.GREATER_THAN, CharPool.LESS_THAN, CharPool.PIPE,
		CharPool.QUESTION, CharPool.QUOTE, CharPool.SPACE
	};

	Log log = LogFactoryUtil.getLog(JournalArticleLocalService.class);
}
