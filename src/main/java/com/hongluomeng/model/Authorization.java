package com.hongluomeng.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.jfinal.plugin.activerecord.Model;

public class Authorization extends Model<Authorization> {

	private static final long serialVersionUID = 1L;

	public static final String KEY_AUTHORIZATION = "authorization";
	public static final String KEY_AUTHORIZATION_ID = "authorization_id";
	public static final String KEY_AUTHORIZATION_TOKEN = "authorization_token";
	public static final String KEY_USER_ID = "user_id";
	public static final String KEY_AUTHORIZATION_CREATE_TIME = "authorization_create_time";
	public static final String KEY_AUTHORIZATION_EXPIRE_TIME = "authorization_expire_time";

	public String getAuthorization_id() {
		return getStr(KEY_AUTHORIZATION_ID);
	}

	public void setAuthorization_id(String authorization_id) {
		set(KEY_AUTHORIZATION_ID, authorization_id);
	}

	public String getAuthorization_token() {
		return getStr(KEY_AUTHORIZATION_TOKEN);
	}

	public void setAuthorization_token(String authorization_token) {
		set(KEY_AUTHORIZATION_TOKEN, authorization_token);
	}

	public String getUser_id() {
		return getStr(KEY_USER_ID);
	}

	public void setUser_id(String user_id) {
		set(KEY_USER_ID, user_id);
	}

	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	public Date getAuthorization_create_time() {
		return getDate(KEY_AUTHORIZATION_CREATE_TIME);
	}

	public void setAuthorization_create_time(Date authorization_create_time) {
		set(KEY_AUTHORIZATION_CREATE_TIME, authorization_create_time);
	}

	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	public Date getAuthorization_expire_time() {
		return getDate(KEY_AUTHORIZATION_EXPIRE_TIME);
	}

	public void setAuthorization_expire_time(Date authorization_expire_time) {
		set(KEY_AUTHORIZATION_EXPIRE_TIME, authorization_expire_time);
	}

}
