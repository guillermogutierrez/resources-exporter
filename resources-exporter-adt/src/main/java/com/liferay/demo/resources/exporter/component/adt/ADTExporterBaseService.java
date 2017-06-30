
package com.liferay.demo.resources.exporter.component.adt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.liferay.demo.resources.exporter.file.service.impl.FileExportService;
import com.liferay.demo.resources.exporter.model.Resource;
import com.liferay.demo.resources.exporter.service.ResourceExporterService;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.PortalUtil;

public abstract class ADTExporterBaseService implements ResourceExporterService {

	@Override
	public String getPathName() {

		return "templates/application_display/";
	}

	protected List<Resource> processDDMTemplates(long groupId, String className)
		throws PortalException {

		List<Resource> sitemapExport = new ArrayList<Resource>();

		for (DDMTemplate assetEntryTemplate : ddmTemplateLocalService.getTemplates(groupId, PortalUtil.getClassNameId(className))) {

			String ddmTemplateName = this.fileExportService.escapeFileName(assetEntryTemplate.getName(Locale.ENGLISH), "xml");

			File templateFile = this.fileExportService.writeStringIntoFile(ddmTemplateName, assetEntryTemplate.getScript());

			sitemapExport.add(new Resource(this.getPathName(), ddmTemplateName, templateFile));
		}

		return sitemapExport;
	}

	protected abstract void setDDMTemplateLocalService(DDMTemplateLocalService ddmTemplateLocalService);

	protected abstract void setFileExportService(FileExportService fileExportService);

	protected DDMTemplateLocalService ddmTemplateLocalService;

	protected FileExportService fileExportService;

	protected String adtClassName;

}
