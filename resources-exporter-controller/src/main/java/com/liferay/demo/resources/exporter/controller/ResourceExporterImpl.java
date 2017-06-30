
package com.liferay.demo.resources.exporter.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import com.liferay.demo.resources.exporter.file.service.impl.FileExportService;
import com.liferay.demo.resources.exporter.model.Resource;
import com.liferay.demo.resources.exporter.service.ResourceExporterService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.zip.ZipWriter;
import com.liferay.portal.kernel.zip.ZipWriterFactoryUtil;

@Component(immediate = true, service = ResourceExporter.class)
public class ResourceExporterImpl implements IdentifiableOSGiService, ResourceExporter {

	public String generateResourceExportPackage(long groupId) {

		File resourcesZipFile = this.fileExportService.getZipContainer("resources-export");
		ZipWriter zipFile = ZipWriterFactoryUtil.getZipWriter(resourcesZipFile);
		List<Resource> exportFiles = new ArrayList<Resource>();

		for (ResourceExporterService resourceExporterLocalService : services.values()) {

			try {
				exportFiles = resourceExporterLocalService.getExportData(groupId);
			}
			catch (PortalException e) {
				log.error("Error getting export data for " + resourceExporterLocalService.getClass().getName() + " " + e.getCause());
			}

			if (exportFiles != null) {

				if (log.isDebugEnabled()) {
					log.debug("Run of ResourceExporterService " + resourceExporterLocalService.getClass().getName() + " has generated " + exportFiles.size() + "file(s)");
				}

				for (Resource resoruce : exportFiles) {

					try {
						zipFile.addEntry(resoruce.getPath() + resoruce.getName(), new FileInputStream(resoruce.getResourceFile()));
					}
					catch (IOException e) {
						log.error("Error processing resource file " + resoruce.getName() + " " + e.getMessage());
					}
				}
			}
		}

		return resourcesZipFile.getAbsolutePath();
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY, unbind = "removeResourceExporterService")
	protected void addResourceExporterService(ResourceExporterService ddlExporter) {

		if (services == null) {
			services = new HashMap<String, ResourceExporterService>();
		}

		services.put(ddlExporter.getExportItem(), ddlExporter);

		if (log.isDebugEnabled()) {
			log.debug("New ResourceExporterService has been registered {name: " + ddlExporter.getExportItem() + ", impl: " + ddlExporter.getClass().getName() + "}");
		}
	}

	protected void removeResourceExporterService(ResourceExporterService ddlExporter) {

		if (services != null) {
			services.remove(ddlExporter.getExportItem());
		}

		if (log.isDebugEnabled()) {
			log.debug("Existing ResourceExporterService has been removed {name: " + ddlExporter.getExportItem() + ", impl: " + ddlExporter.getClass().getName() + "}");
		}
	}

	protected Map<String, ResourceExporterService> services;

	@Override
	public String getOSGiServiceIdentifier() {

		return ResourceExporterImpl.class.getName();
	}

	protected FileExportService fileExportService;

	@Reference
	protected void setFileExportService(FileExportService fileExportService) {

		this.fileExportService = fileExportService;
	}

	Log log = LogFactoryUtil.getLog(ResourceExporterImpl.class);

}
