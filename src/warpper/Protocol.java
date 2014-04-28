package warpper;

import java.io.Serializable;

public class Protocol implements Serializable {
	private int pro;
	private Object obj;
	
	public Protocol() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
	public Protocol(int pro,Object obj) {
		super();
		this.pro = pro;
		this.obj = obj;
		// TODO 自动生成的构造函数存根
	}
	
	public int getPro() {
		return pro;
	}
	public void setPro(int pro) {
		this.pro = pro;
	}
	
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
}
