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
 * Provides a wrapper for {@link LayoutExporterLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutExporterLocalService
 * @generated
 */
@ProviderType
public class LayoutExporterLocalServiceWrapper
	implements LayoutExporterLocalService,
		ServiceWrapper<LayoutExporterLocalService> {
	public LayoutExporterLocalServiceWrapper(
		LayoutExporterLocalService layoutExporterLocalService) {
		_layoutExporterLocalService = layoutExporterLocalService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getLayoutData(
		com.liferay.portal.kernel.model.Layout layout)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _layoutExporterLocalService.getLayoutData(layout);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getLayoutData(
		long layoutId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _layoutExporterLocalService.getLayoutData(layoutId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _layoutExporterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public LayoutExporterLocalService getWrappedService() {
		return _layoutExporterLocalService;
	}

	@Override
	public void setWrappedService(
		LayoutExporterLocalService layoutExporterLocalService) {
		_layoutExporterLocalService = layoutExporterLocalService;
	}

	private LayoutExporterLocalService _layoutExporterLocalService;
}