package com.hongluomeng.model;

import java.util.Date;

import com.hongluomeng.common.Utility;
import com.jfinal.plugin.activerecord.Model;

public class CourseApplyHistory extends Model<CourseApplyHistory> {

	private static final long serialVersionUID = 1L;

	public static final String KEY_COURSE_APPLY_HISTORY = "course_apply_history";
	public static final String KEY_COURSE_ID = "course_id";
	public static final String KEY_USER_ID = "user_id";
	public static final String KEY_COURSE_APPLY_HISTORY_IS_APPLY = "course_apply_history_is_apply";
	public static final String KEY_COURSE_APPLY_HISTORY_CREATE_TIME = "course_apply_history_create_time";
	public static final String KEY_COURSE_APPLY_HISTORY_STATUS = "course_apply_history_status";

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

	public Boolean getCourse_apply_history_is_apply() {
		return getBoolean(KEY_COURSE_APPLY_HISTORY_IS_APPLY);
	}

	public void setCourse_apply_history_is_apply(Boolean course_apply_history_is_apply) {
		set(KEY_COURSE_APPLY_HISTORY_IS_APPLY, course_apply_history_is_apply);
	}

	public String getCourse_apply_history_create_time() {
		return Utility.getDateTimeString(getDate(KEY_COURSE_APPLY_HISTORY_CREATE_TIME));
	}

	public void setCourse_apply_history_create_time(Date course_apply_history_create_time) {
		set(KEY_COURSE_APPLY_HISTORY_CREATE_TIME, course_apply_history_create_time);
	}

	public Boolean getCourse_apply_history_status() {
		return getBoolean(KEY_COURSE_APPLY_HISTORY_STATUS);
	}

	public void setCourse_apply_history_status(Boolean course_apply_history_status) {
		set(KEY_COURSE_APPLY_HISTORY_STATUS, course_apply_history_status);
	}

}
