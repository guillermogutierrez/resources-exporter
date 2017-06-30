
package com.liferay.demo.resources.exporter.file.service.impl;

import java.io.File;

import com.liferay.portal.kernel.json.JSONObject;

public interface FileExportService {

	File writeJsonIntoFile(String fileName, String extension, JSONObject json);

	File writeStringIntoFile(String fileName, String extension, String text);

	String addJsonIntoZipFile(File zipFile, String fileName, JSONObject json);

	File getZipContainer(String zipName);

	String escapeFileName(String filename, String extension);

	File writeJsonIntoFile(String fileNameWithExtension, JSONObject json);

	File writeStringIntoFile(String fileNameWithExtension, String text);

	String escapeFileName(String filename);
}
