
package com.liferay.demo.resources.exporter.web.portlet;

import java.io.FileInputStream;
import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.demo.resources.exporter.controller.ResourceExporter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.WebKeys;

@Component(property = {
	"javax.portlet.name=com_liferay_resources_exporter_web", "mvc.command.name=/exportzipfile"
}, service = MVCResourceCommand.class)

public class ResourcesExporterFileDownload extends BaseMVCResourceCommand {

	@Override
	public void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

		if (log.isDebugEnabled()) {
			log.debug("Starting resources export process...");
		}

		String fileName = resourcesExporterLocalService.generateResourceExportPackage(themeDisplay.getScopeGroupId());

		if (log.isDebugEnabled()) {
			log.debug("Finished resources export process, generated file can be found in " + fileName);
		}

		try {
			PortletResponseUtil.sendFile(resourceRequest, resourceResponse, "zipfile.zip", new FileInputStream(fileName), ContentTypes.TEXT_XML_UTF8);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected ResourceExporter resourcesExporterLocalService;

	@Reference
	protected void setResourceExporter(ResourceExporter resourcesExporterLocalService) {

		this.resourcesExporterLocalService = resourcesExporterLocalService;
	}

	private Log log = LogFactoryUtil.getLog(ResourcesExporterFileDownload.class);

}
