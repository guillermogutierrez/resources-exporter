
package com.liferay.demo.resources.exporter.component.adt;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.demo.resources.exporter.file.service.impl.FileExportService;
import com.liferay.demo.resources.exporter.model.Resource;
import com.liferay.demo.resources.exporter.service.ResourceExporterService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.portal.kernel.exception.PortalException;

@Component(immediate = true, service = ResourceExporterService.class)
public class DocumentsADTExporterService extends ADTExporterBaseService {

	@Override
	public String getOSGiServiceIdentifier() {

		return ResourceExporterService.class.getCanonicalName();
	}

	@Override
	public List<Resource> getExportData(long groupId)
		throws PortalException {

		return processDDMTemplates(groupId, adtClassName);
	}

	@Override
	public String getPathName() {

		return super.getPathName() + "document_library/";
	}

	@Override
	public String getExportItem() {

		return "adt_document_entry";
	}

	@Reference
	protected void setFileExportService(FileExportService fileExportService) {

		this.fileExportService = fileExportService;
	}

	@Reference
	protected void setDDMTemplateLocalService(DDMTemplateLocalService ddmTemplateLocalService) {

		this.ddmTemplateLocalService = ddmTemplateLocalService;
	}

	protected String adtClassName = "com.liferay.portal.kernel.repository.model.FileEntry";
}
