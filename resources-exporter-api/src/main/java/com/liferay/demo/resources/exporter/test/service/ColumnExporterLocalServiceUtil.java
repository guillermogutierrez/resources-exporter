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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ColumnExporter. This utility wraps
 * {@link com.liferay.demo.resources.exporter.test.service.impl.ColumnExporterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ColumnExporterLocalService
 * @see com.liferay.demo.resources.exporter.test.service.base.ColumnExporterLocalServiceBaseImpl
 * @see com.liferay.demo.resources.exporter.test.service.impl.ColumnExporterLocalServiceImpl
 * @generated
 */
@ProviderType
public class ColumnExporterLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.demo.resources.exporter.test.service.impl.ColumnExporterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONArray getColumnData(
		long plid, java.lang.String columnData) {
		return getService().getColumnData(plid, columnData);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static ColumnExporterLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ColumnExporterLocalService, ColumnExporterLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ColumnExporterLocalService.class);
}