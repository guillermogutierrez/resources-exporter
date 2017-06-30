
package com.liferay.demo.resources.exporter.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

public class ResourceFormatter {

	public static String quoteText(String text) {

		return "\"" + text + "\"";
	}

	public static void addKeyValue(StringBuilder text, String key, String value) {

		text.append(ResourceFormatter.quoteText(key)).append(": ").append(ResourceFormatter.quoteText(value));

	}

	public static JSONObject addJsonNode(JSONObject object, String key, Object value) {

		return object.put(key, value);
	}

	public static JSONArray addJsonNodeToArray(JSONArray jsonArray, JSONObject value) {

		return jsonArray.put(value);
	}
}
