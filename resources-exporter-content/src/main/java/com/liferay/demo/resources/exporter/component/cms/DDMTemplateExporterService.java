
package com.liferay.demo.resources.exporter.component.cms;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.demo.resources.exporter.file.service.impl.FileExportService;
import com.liferay.demo.resources.exporter.model.Resource;
import com.liferay.demo.resources.exporter.service.ResourceExporterService;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.portal.kernel.exception.PortalException;

@Component(immediate = true, property = {
				// TODO enter required service properties
}, service = ResourceExporterService.class)
public class DDMTemplateExporterService extends CMSExporterBaseService {

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

			String ddmStructureName = this.fileExportService.escapeFileName(ddmStructure.getName(Locale.ENGLISH));

			for (DDMTemplate ddmTemplate : ddmStructure.getTemplates()) {

				String ddmTemplateName = this.fileExportService.escapeFileName(ddmTemplate.getName(Locale.ENGLISH), "xml");

				File templateFile = this.fileExportService.writeStringIntoFile(ddmTemplateName, ddmTemplate.getScript());

				sitemapExport.add(new Resource(this.getPathName() + ddmStructureName + "/", ddmTemplateName, templateFile));
			}

		}

		return sitemapExport;
	}

	@Override
	public String getPathName() {

		return super.getPathName() + "templates/";
	}

	@Override
	public String getExportItem() {

		// TODO Auto-generated method stub
		return "ddmtemplates";
	}

	@Reference
	protected void setDDMStructureLocalService(DDMStructureLocalService ddmStructureLocalService) {

		this.ddmStructureLocalService = ddmStructureLocalService;
	}

	protected DDMStructureLocalService ddmStructureLocalService;

	@Reference
	protected void setDDMTemplateLocalService(DDMTemplateLocalService ddmTemplateLocalService) {

		this.ddmTemplateLocalService = ddmTemplateLocalService;
	}

	protected DDMTemplateLocalService ddmTemplateLocalService;

	protected FileExportService fileExportService;

	@Reference
	protected void setFileExportService(FileExportService fileExportService) {

		this.fileExportService = fileExportService;
	}
}
