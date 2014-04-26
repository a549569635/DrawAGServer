package m;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ServerRunnable implements Runnable {
	
	private static ServerSocket server;

	public ServerRunnable(int port) {
		// TODO �Զ����ɵĹ��캯�����
		try {
			server=new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeServer() {
		// TODO �Զ����ɵķ������
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
		// TODO �Զ����ɵķ������
		while(server != null){
			try {
				Socket soc = server.accept();
				ServerSocketRunnable client=new ServerSocketRunnable(soc);
				new Thread(client).start();
				
				Core.serverframe.info.append("һ���ͻ��������ӣ���ipΪ��"+soc.getRemoteSocketAddress()+"\n");
				
				Core.CLIENT_SOCKET.put(soc.getRemoteSocketAddress().toString(), client);

				User user=new User();
				Core.NEW_USER.put(soc.getRemoteSocketAddress().toString(), user);
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "����˹ر�");
				   return;
			}
			
		}

	}

}