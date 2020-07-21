package com.automation.utilities;

import java.io.File;

import org.apache.log4j.xml.DOMConfigurator;

public class LoggerConfig {

	private LoggerConfig() {
	}

	public static void configuringlog4jXmlFile() {
		String log4jPath = System.getProperty("user.dir") +File.separator+ "src"+File.separator+"main"+File.separator+"resources"+File.separator+"log4j.xml";
		DOMConfigurator.configure(log4jPath);
	}
}
