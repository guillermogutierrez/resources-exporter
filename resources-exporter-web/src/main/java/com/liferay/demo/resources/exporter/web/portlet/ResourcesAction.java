
package com.liferay.demo.resources.exporter.web.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;

@Component(property = {
	"javax.portlet.name=com_liferay_resources_exporter_web", "mvc.command.name=/announcements/edit_entry"
}, service = MVCActionCommand.class)
public class ResourcesAction extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		System.out.println("actoipon");
	}
}
