package warpper;

import java.io.Serializable;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6481875452314213538L;
	public String from;
	public String to;
	public String body;
	
	public Message(String from, String to, String body){
		this.from = from;
		this.to = to;
		this.body = body;
	}

}
