package com.test.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ProducerUtility {
	static final Logger logger = Logger.getLogger(ProducerUtility.class);
	public static Properties smartsConf = new Properties();
	static
	{
		ProducerUtility prop = new ProducerUtility();
		prop.loadProperties();

	}
	private void loadProperties() {
		try
		{
			smartsConf.load(new InputStreamReader(new FileInputStream("/home/vboxuser/Documents/JsonProps.conf")));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			logger.error(e);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			logger.error(e);
		}
	}
}
