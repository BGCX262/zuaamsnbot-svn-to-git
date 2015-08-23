import java.awt.Color;
import java.util.List;
import java.util.Random;

import com.incesoft.botplatform.sdk.RobotAIService;
import com.incesoft.botplatform.sdk.RobotException;
import com.incesoft.botplatform.sdk.RobotHandler;
import com.incesoft.botplatform.sdk.RobotMessage;
import com.incesoft.botplatform.sdk.RobotServer;
import com.incesoft.botplatform.sdk.RobotSession;
import com.incesoft.botplatform.sdk.RobotUser;
import com.incesoft.botplatform.sdk.support.AIAnswer;

/**
 * @author LiBo
 */
public class MyHandler implements RobotHandler {

	public final String commandList = "现在是机器人值班时间\r"
			+ " ?    显示帮助信息 .               \r";

	private RobotServer server = null;
	private RobotAIService aiService = null;

	public MyHandler(RobotServer s, RobotAIService ais) {
		server = s;
		aiService = ais;
	}

	/**
	 * '打开对话窗'事件.
	 * 
	 * @param session
	 *            对话
	 */
	public void sessionOpened(RobotSession session) {
		// System.out.println("EVENT: sessionOpened (" +
		// session.getUser().getClientID() + "," + session.getUser().getStatus()
		// + ")");
		try {
			if (RobotSession.OPEN_MODE_CONV_OPEN == session.getOpenMode()) {
				session.send(commandList); // 发送欢迎语.
			} else if (RobotSession.OPEN_MODE_ROBOT == session.getOpenMode()) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				session.send("i'm a bot!");
			}
		} catch (RobotException e) {
			e.printStackTrace();
		}
	}

	/**
	 * '对话窗关闭'事件.
	 * 
	 * @param session
	 *            对话
	 */
	public void sessionClosed(RobotSession session) {
		// System.out.println("EVENT: sessionClosed");

	}

	/**
	 * '收到消息'事件
	 * 
	 * @param session
	 *            对话
	 * @param message
	 *            消息
	 */
	public void messageReceived(RobotSession session, RobotMessage message) {

		System.out.println("EVENT: messageReceived");
		try {
			// 取得命令.
			String command = message.getString();

			// 生成一个将要发送给用户的消息对象.
			RobotMessage msg = session.createMessage();

			// 如果是帮助信息, 则发送菜单.
			if ("help".equalsIgnoreCase(command)
					|| "?".equalsIgnoreCase(command)) {
				session.send(commandList);
			}

			else if ("typing".equalsIgnoreCase(command)) {
				session.sendNudge(); // 振动
				session.sendTyping(); // 发送'正在输入'消息.
			} else if ("weeego".equalsIgnoreCase(command)) {
				// server.setDisplayPicture("__default.dat"); // 设置msn头像
				session.sendTyping(); // 发送'正在输入'消息.
				session.sendNudge(); // 振动
				server.setDisplayPicture("logo.png"); // 设置msn头像
				session.send("耶,weeego!");

			} else {
				Message message2 = new Message();
				session.send(message2.getMessage(command));
			}
		} catch (RobotException e) {
			e.printStackTrace();
		}
	}

	/**
	 * '闪屏振动'事件.
	 * 
	 * @param session
	 *            对话
	 */
	public void nudgeReceived(RobotSession session) {
		System.out.println("EVENT: nudgeReceived");
		try {
			session.send("nudge received!");
		} catch (RobotException e) {
			e.printStackTrace();
		}
	}

	/**
	 * '活动邀请被接受'事件.
	 * 
	 * @param session
	 *            对话
	 */
	public void activityAccepted(RobotSession session) {
		System.out.println("EVENT: activityAccepted");
	}

	/**
	 * '活动邀请被拒绝'事件.
	 * 
	 * @param session
	 *            对话
	 */
	public void activityRejected(RobotSession session) {
		System.out.println("EVENT: activityRejected");
	}

	/**
	 * '异常'事件.
	 * 
	 * @param session
	 *            对话
	 * @param cause
	 *            异常
	 */
	public void exceptionCaught(RobotSession session, Throwable cause) {
		System.out.println("SERVER ERROR: " + cause.getMessage());
	}

	/**
	 * '用户添加机器人'事件.
	 * 
	 * @param robot
	 *            机器人帐号
	 * @param user
	 *            用户帐号
	 */
	public void userAdd(String robot, String user) {
		System.out.println("EVENT: userAdd (" + user + ")");
	}

	/**
	 * '用户删除机器人'事件.
	 * 
	 * @param robot
	 *            机器人帐号
	 * @param user
	 *            用户帐号
	 */
	public void userRemove(String robot, String user) {
		System.out.println("EVENT: userRemove (" + user + ")");
	}

	/**
	 * '活动关闭'事件.
	 * 
	 * @param session
	 *            对话
	 */
	public void activityClosed(RobotSession session) {
		// TODO Auto-generated method stub
	}

	/**
	 * '活动被加载'事件
	 * 
	 * @param session
	 *            对话
	 */
	public void activityLoaded(RobotSession session) {
		// TODO Auto-generated method stub
	}

	/**
	 * '活动发送消息到机器人'事件
	 * 
	 * @param session
	 *            对话
	 * @param data
	 *            数据
	 */
	public void activityReceived(RobotSession session, String data) {
		// TODO Auto-generated method stub
	}

	/**
	 * '背景共享被接受'事件.
	 * 
	 * @param session
	 *            对话
	 */
	public void backgroundAccepted(RobotSession session) {
		// TODO Auto-generated method stub
	}

	/**
	 * '背景共享被拒绝'事件.
	 * 
	 * @param session
	 *            对话
	 */
	public void backgroundRejected(RobotSession session) {
		// TODO Auto-generated method stub
	}

	/**
	 * '背景传送完毕'事件.
	 * 
	 * @param session
	 *            对话
	 */
	public void backgroundTransferEnded(RobotSession session) {
		// TODO Auto-generated method stub
	}

	/**
	 * '文件被接受'事件.
	 * 
	 * @param session
	 *            对话
	 */
	public void fileAccepted(RobotSession session) {
		// TODO Auto-generated method stub
	}

	/**
	 * '传送文件被拒绝'事件.
	 * 
	 * @param session
	 *            对话
	 */
	public void fileRejected(RobotSession session) {
		// TODO Auto-generated method stub
	}

	/**
	 * '传送文件结束'事件.
	 * 
	 * @param session
	 *            对话
	 */
	public void fileTransferEnded(RobotSession session) {
		// TODO Auto-generated method stub
	}

	/**
	 * '用户加入对话'事件.
	 * 
	 * @param session
	 *            对话
	 * @param user
	 *            用户
	 */
	public void userJoined(RobotSession session, RobotUser user) {
		// TODO Auto-generated method stub
	}

	/**
	 * '用户离开'事件.
	 * 
	 * @param session
	 *            对话
	 * @param user
	 *            用户
	 */
	public void userLeft(RobotSession session, RobotUser user) {
		// TODO Auto-generated method stub
	}

	/**
	 * '用户接受视频'事件
	 * 
	 * @param session
	 *            对话
	 */
	public void webcamAccepted(RobotSession session) {
		// TODO Auto-generated method stub
	}

	/**
	 * '用户拒绝视频'事件
	 * 
	 * @param session
	 *            对话
	 */
	public void webcamRejected(RobotSession session) {
		// TODO Auto-generated method stub
	}

	public void userUpdated(RobotUser user) {
		System.out.println("EVENT: userUpdated (" + user + ")");
	}

	public void personalMessageUpdated(String robot, String user,
			String personalMessage) {
		System.out.println("EVENT: personalMessageUpdated (" + robot + ", "
				+ user + ", " + personalMessage + ")");

	}

	public void contactListReceived(String robot, List<RobotUser> friendList) {

		System.out.println("EVENT: friendListReceived(" + robot
				+ ", list.size()=" + friendList.size() + ")");

		for (RobotUser u : friendList) {
			System.out.println(u);
		}

	}

	public void userUpdated(String robot, RobotUser user) {
		// TODO Auto-generated method stub

	}

}
