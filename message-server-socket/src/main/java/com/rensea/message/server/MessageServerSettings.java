/**
 * project : app-vote
 * user created : pippo
 * date created : 2009-11-2 - 上午10:20:15
 */
package com.rensea.message.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @since 2009-11-2
 * @author pippo
 */
public class MessageServerSettings {

	public MessageServerSettings(File settingFile) {
		this.settingFile = settingFile;
	}

	private File settingFile;

	public void init() throws FileNotFoundException, IOException {
		this.properties.load(new FileInputStream(this.settingFile));

	}

	private Properties properties = new Properties();

	public String getSetting(String key) {
		return this.properties.getProperty(key);
	}

	public Long getLong(String key) {
		String value = this.getSetting(key);
		return Long.valueOf(value);
	}

}
