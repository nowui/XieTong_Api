package com.hongluomeng.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;

public class CourseApply extends Model<CourseApply> {

	private static final long serialVersionUID = 1L;

	public static final String KEY_COURSE_APPLY = "course_apply";
	public static final String KEY_COURSE_ID = "course_id";
	public static final String KEY_USER_ID = "user_id";
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

	public void setCourseApply_create_user_id(String courseApply_create_user_id) {
		set(KEY_COURSE_APPLY_CREATE_USER_ID, courseApply_create_user_id);
	}

	public void setCourseApply_create_time(Date courseApply_create_time) {
		set(KEY_COURSE_APPLY_CREATE_TIME, courseApply_create_time);
	}

	public void setCourseApply_update_user_id(String courseApply_update_user_id) {
		set(KEY_COURSE_APPLY_UPDATE_USER_ID, courseApply_update_user_id);
	}

	public void setCourseApply_update_time(Date courseApply_update_time) {
		set(KEY_COURSE_APPLY_UPDATE_TIME, courseApply_update_time);
	}

	public Boolean getCourseApply_status() {
		return getBoolean(KEY_COURSE_APPLY_STATUS);
	}

	public void setCourseApply_status(Boolean courseApply_status) {
		set(KEY_COURSE_APPLY_STATUS, courseApply_status);
	}

}
