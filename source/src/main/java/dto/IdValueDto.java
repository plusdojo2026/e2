package dto;

import java.io.Serializable;

public class IdValueDto implements Serializable {

	private int id;
	private String value;

	public IdValueDto(int id, String value) {
		this.id = id;
		this.value = value;
	}

	public IdValueDto() {
		this(0, "");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
