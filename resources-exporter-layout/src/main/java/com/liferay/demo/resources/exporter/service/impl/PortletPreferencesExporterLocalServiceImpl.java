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

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the portlet preferences exporter local service. <p> All
 * custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.liferay.demo.resources.exporter.service.PortletPreferencesExporterLocalService}
 * interface. <p> This is a local service. Methods of this service will not have
 * security checks based on the propagated JAAS credentials because this service
 * can only be accessed from within the same VM. </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class PortletPreferencesExporterLocalServiceImpl {
	// public class PortletPreferencesExporterLocalServiceImpl extends
	// PortletPreferencesExporterLocalServiceBaseImpl {
	//
	// /*
	// * NOTE FOR DEVELOPERS: Never reference this class directly. Always use
	// * {@link com.liferay.demo.resources.exporter.service.
	// * PortletPreferencesExporterLocalServiceUtil} to access the portlet
	// * preferences exporter local service.
	// */
	//
	// public String getPortletPreferences(long plid, String portletId) {
	//
	// StringBuilder portletPreferencesString = new StringBuilder();
	//
	// PortletPreferencesLocalServiceUtil.getPortletPreferences(plid,
	// portletId).forEach(prefs -> {
	// portletPreferencesString.append(parsePreferences(prefs));
	// });
	//
	// return portletPreferencesString.toString();
	// }
	//
	// private String parsePreferences(PortletPreferences portletPreferences) {
	//
	// javax.portlet.PortletPreferences portletPreferencesStandard =
	// PortletPreferencesFactoryUtil.fromDefaultXML(portletPreferences.getPreferences());
	//
	// Enumeration<String> preferencesKeys =
	// portletPreferencesStandard.getNames();
	//
	// if (preferencesKeys.hasMoreElements()) {
	// StringBuilder prefs = new StringBuilder();
	//
	// prefs.append("\"portletPreferences\": {");
	//
	// while (preferencesKeys.hasMoreElements()) {
	// String key = preferencesKeys.nextElement();
	// prefs.append("\"").append(key).append("\":").append("\"").append(portletPreferencesStandard.getValue(key,
	// "")).append("\"");
	//
	// if (preferencesKeys.hasMoreElements()) {
	// prefs.append(",");
	// }
	// }
	//
	// prefs.append("}");
	//
	// return prefs.toString();
	// }
	//
	// return StringPool.BLANK;
	// }
}
