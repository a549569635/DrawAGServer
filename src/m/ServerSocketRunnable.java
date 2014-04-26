package m;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketRunnable implements Runnable {
	private Socket socket;//服务套接字
	private ObjectOutputStream objOut;//写入数据
	private ObjectInputStream objIn;//读取数据
	private User user=null;//创建用户对象
	private String num=null;
	public ServerSocketRunnable(Socket socket){
		this.socket=socket;
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根

	}

	

}
