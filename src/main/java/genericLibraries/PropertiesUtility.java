package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * this class contains reusable methods to read data from properties file
 * @author KarunaSekhar
 */

public class PropertiesUtility {
	
	private Properties property;
	
	
	public void propertiesInitialization(String filepath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filepath);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		}
		property = new Properties();
		try {
			property.load(fis);
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
	}
	public String readFromProperties(String key)
	{
	return property.getProperty(key);
	
	}
	

}
