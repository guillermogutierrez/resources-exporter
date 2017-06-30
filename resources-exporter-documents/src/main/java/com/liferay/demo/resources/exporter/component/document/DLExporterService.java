
package com.liferay.demo.resources.exporter.component.document;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.demo.resources.exporter.file.service.impl.FileExportService;
import com.liferay.demo.resources.exporter.model.Resource;
import com.liferay.demo.resources.exporter.service.ResourceExporterService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;

@Component(immediate = true, service = ResourceExporterService.class)
public class DLExporterService extends DLExporterBaseService {

	@Override
	public String getOSGiServiceIdentifier() {

		return ResourceExporterService.class.getCanonicalName();
	}

	@Override
	public List<Resource> getExportData(long groupId)
		throws PortalException {

		List<Resource> sitemapExport = new ArrayList<Resource>();

		sitemapExport.addAll(processDLFolder(groupId, 0));

		return sitemapExport;
	}

	private List<Resource> processDLFolder(DLFolder dlFolder, String folderPath) {

		return processDLFolder(dlFolder.getGroupId(), dlFolder.getFolderId(), folderPath + "/" + dlFolder.getName());
	}

	private List<Resource> processDLFolder(long groupId, long folderId) {

		return processDLFolder(groupId, folderId, StringPool.BLANK);
	}

	private List<Resource> processDLFolder(long groupId, long folderId, String folderPath) {

		List<Resource> sitemapExport = new ArrayList<Resource>();

		for (DLFolder dlFolder : DLFolderLocalServiceUtil.getFolders(groupId, folderId)) {
			sitemapExport.addAll(processDLFolder(dlFolder, folderPath));
		}

		for (DLFileEntry dlFileEntry : DLFileEntryLocalServiceUtil.getFileEntries(groupId, folderId)) {
			try {
				sitemapExport.add(processDLFileEntry(dlFileEntry, folderPath));
			}
			catch (NoFileException exception) {
				log.warn("File " + dlFileEntry.getName() + " will be ignored");
			}
		}

		return sitemapExport;
	}

	private Resource processDLFileEntry(DLFileEntry dlFileEntry, String foldersPath)
		throws NoFileException {

		File tempFile = FileUtil.createTempFile("dlItem", dlFileEntry.getExtension());

		try {
			FileUtil.write(tempFile, dlFileEntry.getContentStream());
		}
		catch (PortalException | IOException e) {
			log.warn("Error trying to retrieve document " + dlFileEntry.getName() + " in folder " + dlFileEntry.getFolderId());
			throw new NoFileException("Error trying to retrieve document " + dlFileEntry.getName() + " in folder " + dlFileEntry.getFolderId());
		}

		Resource dlFileEntryResource = new Resource(this.getPathName() + foldersPath + "/", dlFileEntry.getFileName(), tempFile);

		return dlFileEntryResource;
	}

	@Override
	public String getPathName() {

		return super.getPathName() + "documents/";
	}

	@Override
	public String getExportItem() {

		return "document_library";
	}

	protected FileExportService fileExportService;

	@Reference
	protected void setFileExportService(FileExportService fileExportService) {

		this.fileExportService = fileExportService;
	}

	private Log log = LogFactoryUtil.getLog(DLExporterService.class);
}
