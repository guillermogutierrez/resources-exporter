
package com.liferay.demo.resources.exporter.service;

import java.util.List;

import com.liferay.demo.resources.exporter.model.Resource;
import com.liferay.portal.kernel.exception.PortalException;

public interface ResourceExporterService {

	public String getOSGiServiceIdentifier();

	public List<Resource> getExportData(long groupId)
		throws PortalException;

	public String getPathName();

	public String getExportItem();

}
