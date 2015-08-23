import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Message {

	public String getMessage(String str) {
		String a = new String(str);
		MD5 md5 = new MD5();
		Properties prop = new Properties();
		InputStream in;

		try {
			in = new BufferedInputStream(new FileInputStream(
					"E:\\msn\\weeego.properties"));
			prop.load(in);
			if (str == null) {
				return prop.getProperty("weeego.help");
			} else {
				a = prop.getProperty("weeego." + md5.getMD5ofStr(str));
				System.out.println(a + (a == null || "".equals(a)));
				if (a == null || "".equals(a)) {
					FileWriter w1 = new FileWriter("E:\\msn\\newsay.txt", true);
					w1.write("#" + str);
					w1.write("\r\n");
					w1.write("weeego." + md5.getMD5ofStr(str) + " =" + str);
					w1.write("\r\n");
					w1.close();
					a = prop.getProperty("weeego.help");
				}
			}

		} catch (IOException e) {
			a = "sorry";
			e.printStackTrace();
		}

		return a;

	}
}
