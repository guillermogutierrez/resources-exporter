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
 * The implementation of the layout exporter local service. <p> All custom
 * service methods should be put in this class. Whenever methods are added,
 * rerun ServiceBuilder to copy their definitions into the
 * {@link com.liferay.demo.resources.exporter.service.LayoutExporterLocalService}
 * interface. <p> This is a local service. Methods of this service will not have
 * security checks based on the propagated JAAS credentials because this service
 * can only be accessed from within the same VM. </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutExporterLocalServiceBaseImpl
 * @see com.liferay.demo.resources.exporter.service.LayoutExporterLocalServiceUtil
 */
public class LayoutExporterLocalServiceImpl {

	/*
	 * NOTE FOR DEVELOPERS: Never reference this class directly. Always use
	 * {@link com.liferay.demo.resources.exporter.service.
	 * LayoutExporterLocalServiceUtil} to access the layout exporter local
	 * service.
	 */
	// public String getLayoutData(long layoutId)
	// throws PortalException {
	//
	// Layout layout = LayoutLocalServiceUtil.getLayout(layoutId);
	//
	// UnicodeProperties layoutSettings = layout.getTypeSettingsProperties();
	//
	// StringBuilder layoutExport = new StringBuilder();
	//
	// layoutExport.append("{");
	//
	// StringBuilder columnSettings = new StringBuilder();
	//
	// for (String key : layoutSettings.keySet()) {
	//
	// if (key.startsWith("column")) {
	//
	// if (columnSettings.length() != 0)
	// columnSettings.append(",");
	//
	// columnSettings.append("[").append(columnExporterLocalService.getColumnData(layout.getPlid(),
	// layoutSettings.get(key))).append("]");
	// }
	//
	// }
	//
	// if (columnSettings.length() != 0) {
	// layoutExport.append("\"columns\":
	// [").append(columnSettings).append("],");
	// }
	//
	// ResourceFormatter.addKeyValue(layoutExport, "friendlyURL",
	// layout.getFriendlyURL());
	// // "nameMap": {
	// // "en_US": "Welcome",
	// // "fr_FR": "Bienvenue"
	// // },
	// ResourceFormatter.addKeyValue(layoutExport, "title", layout.getTitle());
	//
	// layoutExport.append("}");
	//
	// return layoutExport.toString();
	// }
}
