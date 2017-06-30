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
 * Provides a wrapper for {@link PortletPreferencesExporterLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PortletPreferencesExporterLocalService
 * @generated
 */
@ProviderType
public class PortletPreferencesExporterLocalServiceWrapper
	implements PortletPreferencesExporterLocalService,
		ServiceWrapper<PortletPreferencesExporterLocalService> {
	public PortletPreferencesExporterLocalServiceWrapper(
		PortletPreferencesExporterLocalService portletPreferencesExporterLocalService) {
		_portletPreferencesExporterLocalService = portletPreferencesExporterLocalService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getPortletPreferences(
		long plid, java.lang.String portletId) {
		return _portletPreferencesExporterLocalService.getPortletPreferences(plid,
			portletId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _portletPreferencesExporterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public PortletPreferencesExporterLocalService getWrappedService() {
		return _portletPreferencesExporterLocalService;
	}

	@Override
	public void setWrappedService(
		PortletPreferencesExporterLocalService portletPreferencesExporterLocalService) {
		_portletPreferencesExporterLocalService = portletPreferencesExporterLocalService;
	}

	private PortletPreferencesExporterLocalService _portletPreferencesExporterLocalService;
}