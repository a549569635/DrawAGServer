package m;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ServerRunnable implements Runnable {
	
	private static ServerSocket server;

	public ServerRunnable(int port) {
		// TODO 自动生成的构造函数存根
		try {
			server=new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeServer() {
		// TODO 自动生成的方法存根
		try {
			if(server!=null)
				server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(server != null){
			try {
				Socket soc = server.accept();
				ServerSocketRunnable client=new ServerSocketRunnable(soc);
				new Thread(client).start();
				
				Core.serverframe.info.append("一个客户端已连接，其ip为："+soc.getRemoteSocketAddress()+"\n");
				
				Core.CLIENT_SOCKET.put(soc.getRemoteSocketAddress().toString(), client);

				User user=new User();
				Core.NEW_USER.put(soc.getRemoteSocketAddress().toString(), user);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "服务端关闭");
				   return;
			}
			
		}

	}

}
