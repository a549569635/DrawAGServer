package m;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketRunnable implements Runnable {
	private Socket socket;//�����׽���
	private ObjectOutputStream objOut;//д������
	private ObjectInputStream objIn;//��ȡ����
	private User user=null;//�����û�����
	private String num=null;
	public ServerSocketRunnable(Socket socket){
		this.socket=socket;
	}

	@Override
	public void run() {
		// TODO �Զ����ɵķ������

	}

	

}
