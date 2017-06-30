
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
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;

@Component(immediate = true, property = {
				// TODO enter required service properties
}, service = ResourceExporterService.class)
public class DDMStructureExporterService extends CMSExporterBaseService {

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
			String fileName = this.fileExportService.escapeFileName(ddmStructure.getName(Locale.ENGLISH), "json");

			File tempFile = this.fileExportService.writeJsonIntoFile(fileName, JSONFactoryUtil.createJSONObject(ddmStructure.getDefinition()));

			sitemapExport.add(new Resource(this.getPathName(), fileName, tempFile));
		}

		return sitemapExport;
	}

	@Override
	public String getPathName() {

		return super.getPathName() + "structures/";
	}

	@Override
	public String getExportItem() {

		// TODO Auto-generated method stub
		return "ddmstructures";
	}

	@Reference
	protected void setDDMStructureLocalService(DDMStructureLocalService ddmStructureLocalService) {

		this.ddmStructureLocalService = ddmStructureLocalService;
	}

	protected DDMStructureLocalService ddmStructureLocalService;

	protected FileExportService fileExportService;

	@Reference
	protected void setFileExportService(FileExportService fileExportService) {

		this.fileExportService = fileExportService;
	}
}
