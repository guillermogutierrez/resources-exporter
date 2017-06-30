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

import com.liferay.demo.resources.exporter.service.ResourceFormatter;
import com.liferay.demo.resources.exporter.test.service.base.ColumnExporterLocalServiceBaseImpl;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * The implementation of the column exporter local service. <p> All custom
 * service methods should be put in this class. Whenever methods are added,
 * rerun ServiceBuilder to copy their definitions into the
 * {@link com.liferay.demo.resources.exporter.test.service.ColumnExporterLocalService}
 * interface. <p> This is a local service. Methods of this service will not have
 * security checks based on the propagated JAAS credentials because this service
 * can only be accessed from within the same VM. </p>
 *
 * @author Brian Wing Shun Chan
 * @see ColumnExporterLocalServiceBaseImpl
 * @see com.liferay.demo.resources.exporter.test.service.ColumnExporterLocalServiceUtil
 */
public class ColumnExporterLocalServiceImpl extends ColumnExporterLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS: Never reference this class directly. Always use
	 * {@link com.liferay.demo.resources.exporter.test.service.
	 * ColumnExporterLocalServiceUtil} to access the column exporter local
	 * service.
	 */

	public JSONArray getColumnData(long plid, String columnData) {

		JSONArray columnArray = JSONFactoryUtil.createJSONArray();

		String[] portlets = columnData.split(",");

		for (String portletName : portlets) {

			JSONObject portletID = JSONFactoryUtil.createJSONObject();

			ResourceFormatter.addJsonNode(portletID, "portletId", portletName);

			JSONObject portletPreferences = portletPreferencesExporterLocalService.getPortletPreferences(plid, portletName);

			if (portletPreferences != null)
				ResourceFormatter.addJsonNode(portletID, "portletPreferences", portletPreferences);

			columnArray.put(portletID);
		}

		return columnArray;
	}
}
