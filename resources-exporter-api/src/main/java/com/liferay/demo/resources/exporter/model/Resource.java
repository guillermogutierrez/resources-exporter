
package com.liferay.demo.resources.exporter.model;

import java.io.File;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class Resource {

	public Resource(String path, String name, File file) {
		this.resourceFile = file;
		this.path = path;
		this.name = name;
		log.error("New resource " + name + " " + path + " physical path " + file.getAbsolutePath());
	}

	public File getResourceFile() {

		return resourceFile;
	}

	public String getName() {

		return name;
	}

	public String getPath() {

		return path;
	}

	private File resourceFile;

	private String name;

	private String path;

	Log log = LogFactoryUtil.getLog(Resource.class);

}
