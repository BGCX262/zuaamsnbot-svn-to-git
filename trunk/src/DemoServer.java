
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.incesoft.botplatform.sdk.RobotAIService;
import com.incesoft.botplatform.sdk.RobotException;
import com.incesoft.botplatform.sdk.RobotServer;
import com.incesoft.botplatform.sdk.RobotServerFactory;
import com.incesoft.botplatform.sdk.support.DefaultRobotAIService;

/**
 * @author LiBo
 */
public class DemoServer {

	private RobotServer server;

	/**
	 * 启动机器人
	 */
	public void start() {
		Properties prop = new Properties();
		RobotAIService ais = null;

		try {
			// 加载配置文件
			prop.load(this.getClass().getClassLoader().getResourceAsStream(
					"config.properties"));

			System.out.println(prop.getProperty("host"));

			// 生成对象,参数1表示服务器地址,参数2表示端口.
			server = RobotServerFactory.getInstance().createRobotServer(
					prop.getProperty("host"),
					Integer.parseInt(prop.getProperty("port")));

			// 连接到机器人平台
			server.setReconnectedSupport(true);

			ais = new DefaultRobotAIService(prop.getProperty("ai.url"), prop
					.getProperty("spid"), prop.getProperty("sppwd"));

			// 设置一个消息侦听器
			server.setRobotHandler(new MyHandler(server, ais));

			// 登录,参数1表示spid, 参数2表示密码.
			server.login(prop.getProperty("spid"), prop.getProperty("sppwd"));

		} catch (RobotException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭机器人
	 */
	public void stop() {
		server.logout(); // 关闭连接
	}

	public static void main(String[] args) throws Exception {
		DemoServer s = new DemoServer(); // 生成一个机器人实例

		s.start(); // 启动
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		while (true) {
			reader.readLine();
			Thread.sleep(500);
		}

		// s.stop(); //关闭

	}

}
