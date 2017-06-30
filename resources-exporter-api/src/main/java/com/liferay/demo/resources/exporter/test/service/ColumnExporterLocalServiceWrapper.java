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
 * Provides a wrapper for {@link ColumnExporterLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ColumnExporterLocalService
 * @generated
 */
@ProviderType
public class ColumnExporterLocalServiceWrapper
	implements ColumnExporterLocalService,
		ServiceWrapper<ColumnExporterLocalService> {
	public ColumnExporterLocalServiceWrapper(
		ColumnExporterLocalService columnExporterLocalService) {
		_columnExporterLocalService = columnExporterLocalService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getColumnData(long plid,
		java.lang.String columnData) {
		return _columnExporterLocalService.getColumnData(plid, columnData);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _columnExporterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public ColumnExporterLocalService getWrappedService() {
		return _columnExporterLocalService;
	}

	@Override
	public void setWrappedService(
		ColumnExporterLocalService columnExporterLocalService) {
		_columnExporterLocalService = columnExporterLocalService;
	}

	private ColumnExporterLocalService _columnExporterLocalService;
}