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

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.liferay.demo.resources.exporter.service.ResourceFormatter;
import com.liferay.demo.resources.exporter.test.service.base.PortletPreferencesExporterLocalServiceBaseImpl;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;

/**
 * The implementation of the portlet preferences exporter local service. <p> All
 * custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.liferay.demo.resources.exporter.test.service.PortletPreferencesExporterLocalService}
 * interface. <p> This is a local service. Methods of this service will not have
 * security checks based on the propagated JAAS credentials because this service
 * can only be accessed from within the same VM. </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortletPreferencesExporterLocalServiceBaseImpl
 * @see com.liferay.demo.resources.exporter.test.service.PortletPreferencesExporterLocalServiceUtil
 */
public class PortletPreferencesExporterLocalServiceImpl extends PortletPreferencesExporterLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS: Never reference this class directly. Always use
	 * {@link com.liferay.demo.resources.exporter.test.service.
	 * PortletPreferencesExporterLocalServiceUtil} to access the portlet
	 * preferences exporter local service.
	 */

	@PostConstruct
	protected void init() {

		nonExportPrefs = new HashMap<String, Boolean>();
		nonExportPrefs.put("portletSetupCss", Boolean.FALSE);
	}

	public JSONObject getPortletPreferences(long plid, String portletId) {

		JSONObject portletPreferencesJson = JSONFactoryUtil.createJSONObject();

		for (PortletPreferences portletPreferences : PortletPreferencesLocalServiceUtil.getPortletPreferences(plid, portletId)) {
			portletPreferencesJson = parsePreferences(portletPreferences);
		} ;

		return portletPreferencesJson;
	}

	private JSONObject parsePreferences(PortletPreferences portletPreferences) {

		javax.portlet.PortletPreferences portletPreferencesStandard = PortletPreferencesFactoryUtil.fromDefaultXML(portletPreferences.getPreferences());

		Enumeration<String> preferencesKeys = portletPreferencesStandard.getNames();

		if (preferencesKeys.hasMoreElements()) {
			JSONObject prefs = JSONFactoryUtil.createJSONObject();

			while (preferencesKeys.hasMoreElements()) {
				String key = preferencesKeys.nextElement();
				if (isExportable(key)) {
					String value = portletPreferencesStandard.getValue(key, "");
					// if (StringUtil.equalsIgnoreCase(key, "groupId"))
					// value = "${groupId}";
					// if (StringUtil.equalsIgnoreCase(key,
					// "displayStyleGroupId"))
					// value = "${groupId}";
					// if (key.equals("displayStyle")) {
					// try {
					// value =
					// DDMTemplateLocalServiceUtil.getDDMTemplate(57116).getTemplateKey();
					// }
					// catch (PortalException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }
					// }

					ResourceFormatter.addJsonNode(prefs, key, value);
				}
			}

			return prefs;
		}

		return null;
	}

	private boolean isExportable(String key) {

		init();
		return nonExportPrefs.containsKey(key) ? nonExportPrefs.get(key) : Boolean.TRUE;
	}

	private Map<String, Boolean> nonExportPrefs;
}
