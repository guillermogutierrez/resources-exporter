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

package com.liferay.demo.resources.exporter.test.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SiteExporterLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SiteExporterLocalService
 * @generated
 */
@ProviderType
public class SiteExporterLocalServiceWrapper implements SiteExporterLocalService,
	ServiceWrapper<SiteExporterLocalService> {
	public SiteExporterLocalServiceWrapper(
		SiteExporterLocalService siteExporterLocalService) {
		_siteExporterLocalService = siteExporterLocalService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getSiteExportData(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _siteExporterLocalService.getSiteExportData(groupId);
	}

	@Override
	public java.lang.String getExportItem() {
		return _siteExporterLocalService.getExportItem();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _siteExporterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.lang.String getPathName() {
		return _siteExporterLocalService.getPathName();
	}

	@Override
	public java.util.List<com.liferay.demo.resources.exporter.model.Resource> getExportData(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _siteExporterLocalService.getExportData(groupId);
	}

	@Override
	public SiteExporterLocalService getWrappedService() {
		return _siteExporterLocalService;
	}

	@Override
	public void setWrappedService(
		SiteExporterLocalService siteExporterLocalService) {
		_siteExporterLocalService = siteExporterLocalService;
	}

	private SiteExporterLocalService _siteExporterLocalService;
}