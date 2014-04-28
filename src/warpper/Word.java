package warpper;

import java.io.Serializable;

public class Word implements Serializable, Cloneable {
	private int ID;
	private String Value;
	private int Length;
	private String category;
	private String Part;
	
	public Word(String value, int length, String category, String part) {
		super();
		setValue(value);
		setLength(length);
		this.setCategory(category);
		setPart(part);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	public int getLength() {
		return Length;
	}

	public void setLength(int length) {
		Length = length;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPart() {
		return Part;
	}

	public void setPart(String part) {
		Part = part;
	}

}
