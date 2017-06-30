
package com.liferay.demo.resources.exporter.web.portlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

@Component(immediate = true, property = {
	"com.liferay.portlet.display-category=Test", "com.liferay.portlet.instanceable=true", "javax.portlet.init-param.template-path=/",
	"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.resource-bundle=content.Language", "javax.portlet.security-role-ref=power-user,user",
	"javax.portlet.name=com_liferay_resources_exporter_web"
}, service = Portlet.class)
public class ResourcesExporterWebPortlet extends MVCPortlet {

}
