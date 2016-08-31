package com.hongluomeng.type;

public enum UserEnum {

	ADMIN("admin"),
	MEMBER("member"),
	ENTERPRISE("enterprise"),
	TEACHER("teacher"),
	STUDENT("student");

	private String key;

	private UserEnum(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

}
