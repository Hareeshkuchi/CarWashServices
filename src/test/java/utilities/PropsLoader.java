package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropsLoader {
	public static String URL;
	public static String browser;
	public static void loadProps() {
		try {
	        Properties props = new Properties();
			FileInputStream istream=new FileInputStream("C:\\Users\\2408719\\IdeaProjects\\CarWashServices\\src\\test\\resources\\CarWash.properties");
			props.load(istream);
			URL=props.getProperty("carWash.url");
			browser=props.getProperty("carWash.browser");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
