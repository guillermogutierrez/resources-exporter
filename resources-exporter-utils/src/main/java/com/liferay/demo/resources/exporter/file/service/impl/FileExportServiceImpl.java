
package com.liferay.demo.resources.exporter.file.service.impl;

import java.io.File;
import java.io.IOException;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.zip.ZipWriterFactoryUtil;

@Component(immediate = true, property = {
				// TODO enter required service properties
}, service = FileExportService.class)
public class FileExportServiceImpl implements FileExportService {

	@Override
	public File writeJsonIntoFile(String fileNameWithExtension, JSONObject json) {

		String[] fileNameSplit = StringUtil.split(fileNameWithExtension, StringPool.PERIOD);

		return writeJsonIntoFile(fileNameSplit[0], fileNameSplit[1], json);
	}

	@Override
	public File writeJsonIntoFile(String fileName, String extension, JSONObject json) {

		return writeStringIntoFile(fileName, extension, json.toJSONString());
	}

	@Override
	public File writeStringIntoFile(String fileNameWithExtension, String text) {

		String[] fileNameSplit = StringUtil.split(fileNameWithExtension, StringPool.PERIOD);

		return writeStringIntoFile(fileNameSplit[0], fileNameSplit[1], text);
	}

	@Override
	public File writeStringIntoFile(String fileName, String extension, String text) {

		File tempFile = FileUtil.createTempFile(FileUtil.createTempFileName(), extension);
		try {
			// tempFile.createNewFile();
			System.out.println("Created file for " + fileName + " and exists? " + tempFile.exists());

			FileUtil.write(tempFile, text);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Created file for " + fileName + " and exists? " + tempFile.exists());
		return tempFile;
	}

	@Override
	public String addJsonIntoZipFile(File zipFile, String fileName, JSONObject json) {

		try {
			ZipWriterFactoryUtil.getZipWriter(zipFile).addEntry(fileName, json.toJSONString());
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return zipFile.getAbsolutePath();
	}

	@Override
	public File getZipContainer(String zipName) {

		File fileZip = FileUtil.createTempFile(zipName, "zip");
		// fileZip.deleteOnExit();

		return fileZip;
	}

	@Override
	public String escapeFileName(String filename) {

		return escapeFileName(filename, null);
	}

	@Override
	public String escapeFileName(String filename, String extension) {

		StringBuilder fileNameBuilder = new StringBuilder();

		fileNameBuilder.append(StringUtil.replace(filename, StringPool.SPACE, StringPool.BLANK));

		if (extension != null)
			fileNameBuilder.append(StringPool.PERIOD).append(extension);

		return fileNameBuilder.toString();
	}
}
