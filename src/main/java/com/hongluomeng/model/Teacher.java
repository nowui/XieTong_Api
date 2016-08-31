package com.hongluomeng.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;

public class Teacher extends Model<Teacher> {

	private static final long serialVersionUID = 1L;

	public static final String KEY_TEACHER = "teacher";
	public static final String KEY_TEACHER_ID = "teacher_id";
	public static final String KEY_TEACHER_NAME = "teacher_name";
	public static final String KEY_USER_ID = "user_id";
	public static final String KEY_TEACHER_CREATE_USER_ID = "teacher_create_user_id";
	public static final String KEY_TEACHER_CREATE_TIME = "teacher_create_time";
	public static final String KEY_TEACHER_UPDATE_USER_ID = "teacher_update_user_id";
	public static final String KEY_TEACHER_UPDATE_TIME = "teacher_update_time";
	public static final String KEY_TEACHER_STATUS = "teacher_status";

	private String user_account;

	public String getTeacher_id() {
		return getStr(KEY_TEACHER_ID);
	}

	public void setTeacher_id(String teacher_id) {
		set(KEY_TEACHER_ID, teacher_id);
	}

	public String getTeacher_name() {
		return getStr(KEY_TEACHER_NAME);
	}

	public void setTeacher_name(String teacher_name) {
		set(KEY_TEACHER_NAME, teacher_name);
	}

	public String getUser_id() {
		return getStr(KEY_USER_ID);
	}

	public void setUser_id(String user_id) {
		set(KEY_USER_ID, user_id);
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public void setTeacher_create_user_id(String teacher_create_user_id) {
		set(KEY_TEACHER_CREATE_USER_ID, teacher_create_user_id);
	}

	public void setTeacher_create_time(Date teacher_create_time) {
		set(KEY_TEACHER_CREATE_TIME, teacher_create_time);
	}

	public void setTeacher_update_user_id(String teacher_update_user_id) {
		set(KEY_TEACHER_UPDATE_USER_ID, teacher_update_user_id);
	}

	public void setTeacher_update_time(Date teacher_update_time) {
		set(KEY_TEACHER_UPDATE_TIME, teacher_update_time);
	}

	public Boolean getTeacher_status() {
		return getBoolean(KEY_TEACHER_STATUS);
	}

	public void setTeacher_status(Boolean teacher_status) {
		set(KEY_TEACHER_STATUS, teacher_status);
	}

}
