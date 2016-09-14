package com.hongluomeng.model;

import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.jfinal.plugin.activerecord.Model;

public class CourseApply extends Model<CourseApply> {

	private static final long serialVersionUID = 1L;

	public static final String KEY_COURSE_APPLY = "course_apply";
	public static final String KEY_COURSE_ID = "course_id";
	public static final String KEY_USER_ID = "user_id";
	public static final String KEY_STUDENT_NAME = "student_name";
	public static final String KEY_GRADE_NAME = "grade_name";
	public static final String KEY_STUDENT_NUMBER = "student_number";
	public static final String KEY_STUDENT_SEX = "student_sex";
	public static final String KEY_COURSE_NAME = "course_name";
	public static final String KEY_COURSE_CLASS = "course_class";
	public static final String KEY_COURSE_TEACHER = "course_teacher";
	public static final String KEY_COURSE_ADDRESS = "course_address";
	public static final String KEY_COURSE_APPLY_CREATE_USER_ID = "course_apply_create_user_id";
	public static final String KEY_COURSE_APPLY_CREATE_TIME = "course_apply_create_time";
	public static final String KEY_COURSE_APPLY_UPDATE_USER_ID = "course_apply_update_user_id";
	public static final String KEY_COURSE_APPLY_UPDATE_TIME = "course_apply_update_time";
	public static final String KEY_COURSE_APPLY_STATUS = "course_apply_status";

	public String getCourse_id() {
		return getStr(KEY_COURSE_ID);
	}

	public void setCourse_id(String course_id) {
		set(KEY_COURSE_ID, course_id);
	}

	public String getUser_id() {
		return getStr(KEY_USER_ID);
	}

	public void setUser_id(String user_id) {
		set(KEY_USER_ID, user_id);
	}

	public String getStudent_name() {
		return getStr(KEY_STUDENT_NAME);
	}

	public String getGrade_name() {
		return getStr(KEY_GRADE_NAME);
	}

	public String getStudent_number() {
		return getStr(KEY_STUDENT_NUMBER);
	}

	public String getStudent_sex() {
		return getStr(KEY_STUDENT_SEX);
	}

	public String getCourse_name() {
		return getStr(KEY_COURSE_NAME);
	}

	public String getCourse_class() {
		return getStr(KEY_COURSE_CLASS);
	}

	public JSONArray getCourse_teacher() {
		return JSONArray.parseArray(getStr(KEY_COURSE_TEACHER));
	}

	public String getCourse_address() {
		return getStr(KEY_COURSE_ADDRESS);
	}

	public void setCourse_apply_create_user_id(String course_apply_create_user_id) {
		set(KEY_COURSE_APPLY_CREATE_USER_ID, course_apply_create_user_id);
	}

	public void setCourse_apply_create_time(Date course_apply_create_time) {
		set(KEY_COURSE_APPLY_CREATE_TIME, course_apply_create_time);
	}

	public void setCourse_apply_update_user_id(String course_apply_update_user_id) {
		set(KEY_COURSE_APPLY_UPDATE_USER_ID, course_apply_update_user_id);
	}

	public void setCourse_apply_update_time(Date course_apply_update_time) {
		set(KEY_COURSE_APPLY_UPDATE_TIME, course_apply_update_time);
	}

	public Boolean getCourse_apply_status() {
		return getBoolean(KEY_COURSE_APPLY_STATUS);
	}

	public void setCourse_apply_status(Boolean course_apply_status) {
		set(KEY_COURSE_APPLY_STATUS, course_apply_status);
	}

}
