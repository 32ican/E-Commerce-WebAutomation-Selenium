package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

	
	final static String CONFIG_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties";
	private static Properties config = new Properties();
	

	static {
		loadConfigs();
	}


	public static String getProperty(String key) {
		return config.getProperty(key);
	}

	public static void setProperty(String key, String value) {
		config.setProperty(key, value);
		saveConfigs();
	}
	
	
	public static void reloadConfigs() {
		loadConfigs();
	}
	
	private static void loadConfigs() {

		try {
			FileInputStream fis = new FileInputStream(CONFIG_PATH);
			config.load(fis);

		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	private static void saveConfigs() {
		
		try {
			FileOutputStream output = new FileOutputStream(CONFIG_PATH);
			config.store(output, null);
			
		}catch(IOException e) {
			throw new RuntimeException("Unable to save the configuration file....!");
		}
		
	}
}
