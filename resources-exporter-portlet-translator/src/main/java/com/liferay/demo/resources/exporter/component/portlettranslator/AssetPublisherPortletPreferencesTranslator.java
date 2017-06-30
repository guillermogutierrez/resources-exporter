/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.demo.resources.exporter.component.portlettranslator;

import java.lang.reflect.Field;

import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.exportimport.resources.importer.portlet.preferences.PortletPreferencesTranslator;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PortalUtil;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, property = {
	"portlet.preferences.translator.portlet.id=com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet"
}, service = PortletPreferencesTranslator.class)
public class AssetPublisherPortletPreferencesTranslator implements PortletPreferencesTranslator {

	@Override
	public void translate(JSONObject portletPreferencesJSONObject, String key, javax.portlet.PortletPreferences portletPreferences)
		throws PortletException {

		String value = portletPreferencesJSONObject.getString(key);

		if (key.equals("displayStyle")) {
			String templateId = FileUtil.stripExtension(value);
			// portletPreferences;
			// DDMTemplateLocalServiceUtil.getTemplateByUuidAndGroupId(templateId,
			// 266001)
			value = "ddmTemplate_" + DDMTemplateLocalServiceUtil.getDDMTemplatesByUuidAndCompanyId(templateId, PortalUtil.getDefaultCompanyId()).get(0).getTemplateId();
		}

		if (key.equals("groupId") || key.equals("displayStyleGroupId")) {
			Field field;
			try {
				field = portletPreferences.getClass().getDeclaredField("_plid");
				field.setAccessible(true);
				Object plid = field.get(portletPreferences);
				value = plid.toString();
			}
			catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		portletPreferences.setValue(key, value);
	}
}
