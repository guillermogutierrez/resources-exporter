
package com.liferay.demo.resources.exporter.component.document;

import com.liferay.demo.resources.exporter.service.ResourceExporterService;

public abstract class DLExporterBaseService implements ResourceExporterService {

	@Override
	public String getPathName() {

		return "document_library/";
	}

}
