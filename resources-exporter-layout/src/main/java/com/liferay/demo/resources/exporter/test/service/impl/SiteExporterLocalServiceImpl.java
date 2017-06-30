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

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.demo.resources.exporter.file.service.impl.FileExportService;
import com.liferay.demo.resources.exporter.model.Resource;
import com.liferay.demo.resources.exporter.service.ResourceExporterService;
import com.liferay.demo.resources.exporter.test.service.LayoutExporterLocalService;
import com.liferay.demo.resources.exporter.test.service.LayoutExporterLocalServiceUtil;
import com.liferay.demo.resources.exporter.test.service.base.SiteExporterLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;

/**
 * The implementation of the site exporter local service. <p> All custom service
 * methods should be put in this class. Whenever methods are added, rerun
 * ServiceBuilder to copy their definitions into the
 * {@link com.liferay.demo.resources.exporter.test.service.SiteExporterLocalService}
 * interface. <p> This is a local service. Methods of this service will not have
 * security checks based on the propagated JAAS credentials because this service
 * can only be accessed from within the same VM. </p>
 *
 * @author Brian Wing Shun Chan
 * @see SiteExporterLocalServiceBaseImpl
 * @see com.liferay.demo.resources.exporter.test.service.SiteExporterLocalServiceUtil
 */
@Component(immediate = true, property = {
				// TODO enter required service properties
}, service = ResourceExporterService.class)
public class SiteExporterLocalServiceImpl extends SiteExporterLocalServiceBaseImpl implements ResourceExporterService, IdentifiableOSGiService {

	public JSONObject getSiteExportData(long groupId)
		throws PortalException {

		JSONObject siteContent = JSONFactoryUtil.createJSONObject();

		JSONArray privatePages = JSONFactoryUtil.createJSONArray();
		for (Layout privateLayout : LayoutLocalServiceUtil.getLayouts(groupId, Boolean.TRUE)) {
			privatePages.put(LayoutExporterLocalServiceUtil.getLayoutData(privateLayout.getPlid()));
		}
		siteContent.put("privatePages", privatePages);

		JSONArray publicPages = JSONFactoryUtil.createJSONArray();
		for (Layout privateLayout : LayoutLocalServiceUtil.getLayouts(groupId, Boolean.FALSE, 0)) {
			publicPages.put(LayoutExporterLocalServiceUtil.getLayoutData(privateLayout.getPlid()));
		}

		siteContent.put("publicPages", publicPages);

		return siteContent;
	}

	@Override
	public List<Resource> getExportData(long groupId)
		throws PortalException {

		List<Resource> sitemapExport = new ArrayList<Resource>(1);

		sitemapExport.add(new Resource("", "sitemap.json", this.fileExportService.writeJsonIntoFile("sitemap", "json", getSiteExportData(groupId))));

		return sitemapExport;
	}

	@Override
	public String getPathName() {

		return "sitemap.json";
	}

	@Override
	public String getExportItem() {

		return "layouts";
	}

	protected LayoutExporterLocalService layoutExporterLocalService;

	@Reference
	public void setLayoutExporterLocalService(LayoutExporterLocalService layoutExporterLocalService) {

		this.layoutExporterLocalService = layoutExporterLocalService;
	}

	protected FileExportService fileExportService;

	@Reference
	public void setFileExportService(FileExportService fileExportService) {

		this.fileExportService = fileExportService;
	}
}
