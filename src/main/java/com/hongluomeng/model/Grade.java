package com.hongluomeng.model;

import java.util.Date;

import com.hongluomeng.common.Utility;
import com.jfinal.plugin.activerecord.Model;

public class Grade extends Model<Grade> {

	private static final long serialVersionUID = 1L;

	public static final String KEY_GRADE = "grade";
	public static final String KEY_GRADE_ID = "grade_id";
	public static final String KEY_GRADE_NAME = "grade_name";
	public static final String KEY_GRADE_COURSE_APPLY_LIMIT = "grade_course_apply_limit";
	public static final String KEY_GRADE_SORT = "grade_sort";
	public static final String KEY_GRADE_CREATE_USER_ID = "grade_create_user_id";
	public static final String KEY_GRADE_CREATE_TIME = "grade_create_time";
	public static final String KEY_GRADE_UPDATE_USER_ID = "grade_update_user_id";
	public static final String KEY_GRADE_UPDATE_TIME = "grade_update_time";
	public static final String KEY_GRADE_STATUS = "grade_status";

	private String user_account;

	public String getGrade_id() {
		return getStr(KEY_GRADE_ID);
	}

	public void setGrade_id(String grade_id) {
		set(KEY_GRADE_ID, grade_id);
	}

	public String getGrade_name() {
		return getStr(KEY_GRADE_NAME);
	}

	public void setGrade_name(String grade_name) {
		set(KEY_GRADE_NAME, grade_name);
	}

	public Integer getGrade_course_apply_limit() {
		return Utility.getIntegerValue(get(KEY_GRADE_COURSE_APPLY_LIMIT));
	}

	public void setGrade_course_apply_limit(Integer grade_course_apply_limit) {
		set(KEY_GRADE_COURSE_APPLY_LIMIT, grade_course_apply_limit);
	}

	public Integer getGrade_sort() {
		return Utility.getIntegerValue(get(KEY_GRADE_SORT));
	}

	public void setGrade_sort(Integer grade_sort) {
		set(KEY_GRADE_SORT, grade_sort);
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public void setGrade_create_user_id(String grade_create_user_id) {
		set(KEY_GRADE_CREATE_USER_ID, grade_create_user_id);
	}

	public void setGrade_create_time(Date grade_create_time) {
		set(KEY_GRADE_CREATE_TIME, grade_create_time);
	}

	public void setGrade_update_user_id(String grade_update_user_id) {
		set(KEY_GRADE_UPDATE_USER_ID, grade_update_user_id);
	}

	public void setGrade_update_time(Date grade_update_time) {
		set(KEY_GRADE_UPDATE_TIME, grade_update_time);
	}

	public Boolean getGrade_status() {
		return getBoolean(KEY_GRADE_STATUS);
	}

	public void setGrade_status(Boolean grade_status) {
		set(KEY_GRADE_STATUS, grade_status);
	}

}
