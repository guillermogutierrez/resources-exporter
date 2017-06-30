
package com.liferay.demo.resources.exporter.component.cms;

import com.liferay.demo.resources.exporter.service.ResourceExporterService;

public abstract class CMSExporterBaseService implements ResourceExporterService {

	@Override
	public String getPathName() {

		return "journal/";
	}

}
