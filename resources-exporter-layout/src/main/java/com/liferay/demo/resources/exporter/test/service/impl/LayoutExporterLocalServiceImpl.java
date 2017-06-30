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

import java.util.Locale;

import com.liferay.demo.resources.exporter.service.ResourceFormatter;
import com.liferay.demo.resources.exporter.test.service.base.LayoutExporterLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

/**
 * The implementation of the layout exporter local service. <p> All custom
 * service methods should be put in this class. Whenever methods are added,
 * rerun ServiceBuilder to copy their definitions into the
 * {@link com.liferay.demo.resources.exporter.test.service.LayoutExporterLocalService}
 * interface. <p> This is a local service. Methods of this service will not have
 * security checks based on the propagated JAAS credentials because this service
 * can only be accessed from within the same VM. </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutExporterLocalServiceBaseImpl
 * @see com.liferay.demo.resources.exporter.test.service.LayoutExporterLocalServiceUtil
 */
public class LayoutExporterLocalServiceImpl extends LayoutExporterLocalServiceBaseImpl {

	public JSONObject getLayoutData(Layout layout)
		throws PortalException {

		UnicodeProperties layoutSettings = layout.getTypeSettingsProperties();

		JSONObject layoutExport = JSONFactoryUtil.createJSONObject();

		JSONArray columnSettings = JSONFactoryUtil.createJSONArray();

		for (String key : layoutSettings.keySet()) {
			if (key.startsWith("column") && !key.contains("customizable")) {
				columnSettings.put(columnExporterLocalService.getColumnData(layout.getPlid(), layoutSettings.get(key)));
			}
		}

		ResourceFormatter.addJsonNode(layoutExport, "columns", columnSettings);

		ResourceFormatter.addJsonNode(layoutExport, "friendlyURL", layout.getFriendlyURL());

		ResourceFormatter.addJsonNode(layoutExport, "nameMap", getPageNames(layout));

		ResourceFormatter.addJsonNode(layoutExport, "title", layout.getTitle());

		// ResourceFormatter.addJsonNode(layoutExport, "typeSettings",
		// layout.getTypeSettings());

		// ResourceFormatter.addJsonNode(layoutExport, "hidden",
		// layout.isHidden());

		// ResourceFormatter.addJsonNode(layoutExport, "themeId",
		// layout.getThemeId());
		//
		// ResourceFormatter.addJsonNode(layoutExport, "layoutCSS",
		// layout.getCss());
		//
		// ResourceFormatter.addJsonNode(layoutExport, "colorSchemeId",
		// layout.getColorSchemeId());
		//
		ResourceFormatter.addJsonNode(layoutExport, "layoutTemplateId", layoutSettings.get("layout-template-id"));
		//
		// ResourceFormatter.addJsonNode(layoutExport, "type",
		// layout.getType());

		// TODO Add layoutPrototype attributes

		// JSONArray childLayouts = JSONFactoryUtil.createJSONArray();
		//
		// for (Layout childLayout :
		// LayoutLocalServiceUtil.getLayouts(layout.getGroupId(),
		// layout.getPrivateLayout(), layout.getLayoutId())) {
		// childLayouts.put(getLayoutData(childLayout));
		// }
		//
		// if (childLayouts.length() != 0)
		// ResourceFormatter.addJsonNode(layoutExport, "layouts", childLayouts);

		return layoutExport;
	}

	public JSONObject getLayoutData(long layoutId)
		throws PortalException {

		return getLayoutData(LayoutLocalServiceUtil.getLayout(layoutId));
	}

	protected JSONObject getPageNames(Layout layout) {

		JSONObject nameMapValues = JSONFactoryUtil.createJSONObject();
		for (Locale siteNameLocale : layout.getNameMap().keySet()) {
			ResourceFormatter.addJsonNode(nameMapValues, siteNameLocale.toString(), layout.getNameMap().get(siteNameLocale));
		}
		return nameMapValues;
	}

	private Log log = LogFactoryUtil.getLog(LayoutExporterLocalServiceImpl.class);
}
