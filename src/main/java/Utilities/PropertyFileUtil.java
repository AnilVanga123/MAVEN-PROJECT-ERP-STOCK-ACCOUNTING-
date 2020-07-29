package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil {
	public static String getValueForKey(String Key)throws Throwable
	{
		Properties configerproperties=new Properties();
		configerproperties.load(new FileInputStream("F:\\December_Selenium\\ERP_MAVEN\\PropertyFile\\Environment.properties"));
		return configerproperties.getProperty(Key);
		
	}

}
