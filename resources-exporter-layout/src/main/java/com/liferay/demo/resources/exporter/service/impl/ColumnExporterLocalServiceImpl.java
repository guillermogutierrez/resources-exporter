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

package com.liferay.demo.resources.exporter.service.impl;

/**
 * The implementation of the column exporter local service. <p> All custom
 * service methods should be put in this class. Whenever methods are added,
 * rerun ServiceBuilder to copy their definitions into the
 * {@link com.liferay.demo.resources.exporter.service.ColumnExporterLocalService}
 * interface. <p> This is a local service. Methods of this service will not have
 * security checks based on the propagated JAAS credentials because this service
 * can only be accessed from within the same VM. </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.demo.resources.exporter.service.ColumnExporterLocalServiceUtil
 */
public class ColumnExporterLocalServiceImpl {
	// public class ColumnExporterLocalServiceImpl extends
	// ColumnExporterLocalServiceBaseImpl {
	//
	// public String getColumnData(long plid, String columnData) {
	//
	// StringBuilder columnString = new StringBuilder();
	//
	// columnString.append("[");
	// String[] portlets = columnData.split(",");
	//
	// int index = 0;
	// for (String portletName : portlets) {
	//
	// columnString.append("{\"portletId\":\"").append(portletName);
	//
	// String portletPreferences =
	// portletPreferencesExporterLocalService.getPortletPreferences(plid,
	// portletName);
	//
	// if (!StringUtil.equalsIgnoreCase(StringPool.BLANK, portletPreferences))
	// columnString.append("\", ").append(portletPreferences).append("}");
	//
	// else
	// columnString.append("\"").append("}");
	//
	// if (index < portlets.length - 1) {
	// columnString.append(",");
	// }
	//
	// index++;
	// }
	//
	// columnString.append("]");
	//
	// return columnString.toString();
	// }

}
