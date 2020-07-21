package com.automation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

public class PropertyFileHandler {
//	private static Logger logger = Logger.getLogger(PropertyFileHandler.class);
//
//	private static Properties applicationProperties;
//	private static Properties coreProperties;
//
//	private static final String FILE_NOT_FOUND = "File dosent Exists :";
//	private static final String USER_DIR = "user.dir";
//
//	private PropertyFileHandler() {
//	}
//
//	/**
//	 * Return the property Value from the file
//	 * \\src\\main\\resources\\properties\\coreconfig.properties
//	 * <p>
//	 * {@code USAGE : PropertyFileHandler.getCoreProperty("browser");}
//	 * <p>
//	 * 
//	 * @param propertyName
//	 * @return
//	 * @throws FileNotFoundException
//	 */
//	public static String getCoreProperty(String propertyName) {		
//		String returnValue = null;
//		
//		if (null == coreProperties)
//			loadCorePropertyFile();
//		try {
//			returnValue = coreProperties.getProperty(propertyName);
//		}catch(Exception e) {
//			logger.error("Incorrect password to decrypt the paramater(Update system environment variable -xautomationPass )"+ e);
//		}
//		return returnValue;
//		
//	}
//
//	/**
//	 * Return the property Value from the file
//	 * \\src\\main\\resources\\properties\\coreconfig.properties"
//	 * <p>
//	 * {@code USAGE : PropertyFileHandler.getApplicationProperty("browser");}
//	 * 
//	 * @param propertyName
//	 * @return
//	 * @throws FileNotFoundException
//	 */
//
//	public static String getApplicationProperty(String propertyName) {
//		String returnValue = null;
//		
//		if (null == applicationProperties)
//			loadApplicationPropertyFile();
//		try {
//			returnValue = applicationProperties.getProperty(propertyName);
//		}catch(Exception e) {
//			logger.error("Incorrect password to decrypt the paramater(Update system environment variable -xautomationPass )"+ e);
//		}
//		return returnValue;
//	}
//
//	private static void loadApplicationPropertyFile() {
//
//		File f;
//		f = new File(System.getProperty(USER_DIR) + "\\src\\test\\resources\\properties\\application.properties");
//		if (f.exists()) {
//			logger.info("call to load application properties");					
//			
//			try {
//				FileInputStream fis = null;					
//				String password = System.getenv("xautomationPass");
//				StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
//				encryptor.setPassword(password);	
//				fis = new FileInputStream(f);
//				applicationProperties = new EncryptableProperties(encryptor);
//				applicationProperties.load(fis);
//			} catch (Exception e) {
//				logger.error(e.getMessage());
//			}
//
//		} else {
//			logger.error(FILE_NOT_FOUND + f.getAbsolutePath());
//		}
//
//	}
//
//	private static void loadCorePropertyFile()  {
//
//		File f;
//		f = new File(System.getProperty(USER_DIR) + "\\src\\main\\resources\\properties\\coreconfig.properties");
//		if (f.exists()) {
//			logger.info("call to load core properties");
//			try {
//				String password = System.getenv("xautomationPass");
//				StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
//				encryptor.setPassword(password);
//				
//				FileInputStream fis = new FileInputStream(f);
//				coreProperties = new EncryptableProperties(encryptor);
//				coreProperties.load(fis);
//			} catch (Exception e) {
//				logger.error(e.getMessage());
//			}
//		} else {
//			logger.error(FILE_NOT_FOUND + f.getAbsolutePath());
//		}
//
//	}
//	
//
//	// ###########################################
//
//	public static Properties loadPropertyFiles(String propertyFile) throws FileNotFoundException {
//		File f;
//		Properties property = new Properties();
//		f = new File(
//				System.getProperty(USER_DIR) + "\\src\\main\\resources\\properties\\" + propertyFile + ".properties");
//		if (f.exists()) {
//			logger.info("call to global properties");
//
//			FileInputStream fis = new FileInputStream(f);
//			try {
//				property.load(fis);
//			} catch (IOException e) {
//				logger.error(e.getMessage());
//			}
//		} else {
//			logger.error(FILE_NOT_FOUND + propertyFile);
//		}
//
//		return property;
//
//	}
//
//	public static String getPropertyValue(String propertyFile, String property) {
//		String propertyValue = null;
//		try {
//			Properties assignProperty = PropertyFileHandler.loadPropertyFiles(propertyFile);
//			propertyValue = assignProperty.getProperty(property);
//			logger.info("propert value is been assiged");
//		} catch (FileNotFoundException e) {
//
//			logger.error(FILE_NOT_FOUND);
//		}
//		return propertyValue;
//	}
//
//	public static void setProperty(String propertyFilePath, String propertyName, Object propertyValue)
//			throws IOException {
//		try {
//			File f = new File(propertyFilePath);
//			FileOutputStream fos = new FileOutputStream(f);
//			Properties proptery = new Properties();
//			if (f.exists()) {
//				proptery.setProperty(propertyName, String.valueOf(propertyValue));
//				logger.info("Property value stored to - " + propertyFilePath);
//			} else {
//				proptery.setProperty(propertyName, String.valueOf(propertyValue));
//				proptery.store(fos, null);
//				logger.info("Property value stored to created property file - " + propertyFilePath);
//			}
//			fos.flush();
//			fos.close();
//
//		} catch (FileNotFoundException e) {
//
//			logger.error(e.getMessage());
//		}
//
//	}

}