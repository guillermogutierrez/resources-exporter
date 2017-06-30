/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.demo.resources.exporter.test.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import com.liferay.demo.resources.exporter.service.ResourceExporterService;
import com.liferay.demo.resources.exporter.test.service.base.ResourceExporterLocalServiceBaseImpl;

/**
 * The implementation of the resource exporter local service. <p> All custom
 * service methods should be put in this class. Whenever methods are added,
 * rerun ServiceBuilder to copy their definitions into the
 * {@link com.liferay.demo.resources.exporter.test.service.ResourceExporterLocalService}
 * interface. <p> This is a local service. Methods of this service will not have
 * security checks based on the propagated JAAS credentials because this service
 * can only be accessed from within the same VM. </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourceExporterLocalServiceBaseImpl
 * @see com.liferay.demo.resources.exporter.test.service.ResourceExporterLocalServiceUtil
 */
public class ResourceExporterLocalServiceImpl extends ResourceExporterLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS: Never reference this class directly. Always use
	 * {@link com.liferay.demo.resources.exporter.test.service.
	 * ResourceExporterLocalServiceUtil} to access the resource exporter local
	 * service.
	 */

	public String generateResourceExportPackage(long groupId) {

		// File resourcesZipFile =
		// FileExport.getZipContainer("resources-export");
		//
		// List<File> exportFiles = new ArrayList<File>();
		//
		// try {
		// for (ResourceExporterService resourceExporterLocalService :
		// services.values()) {
		// exportFiles = resourceExporterLocalService.getExportData(groupId);
		// for (File file : exportFiles) {
		// FileExport.addJsonIntoZipFile(
		// resourcesZipFile, resourceExporterLocalService.getPathName() +
		// file.getName(), siteExporterLocalService.getSiteExportData(groupId));
		// }
		// }
		// }
		// catch (PortalException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// return resourcesZipFile.getAbsolutePath();
		return null;
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY, unbind = "removeResourceExporterService")
	protected void addResourceExporterService(ResourceExporterService ddlExporter) {

		if (services == null) {
			services = new HashMap<String, ResourceExporterService>();
		}
		services.put(ddlExporter.getExportItem(), ddlExporter);
	}

	protected void removeResourceExporterService(ResourceExporterService ddlExporter) {

		System.out.println("remoevd ResourceExporterLocalService");
	}

	protected Map<String, ResourceExporterService> services;

	@Override
	public void test() {

		// TODO Auto-generated method stub

	}
}
