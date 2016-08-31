package com.hongluomeng.model;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.hongluomeng.common.Utility;
import com.jfinal.plugin.activerecord.Model;

public class Course extends Model<Course> {

	private static final long serialVersionUID = 1L;

	public static final String KEY_COURSE = "course";
	public static final String KEY_COURSE_ID = "course_id";
	public static final String KEY_COURSE_GRADE = "course_grade";
	public static final String KEY_COURSE_TEACHER = "course_teacher";
	public static final String KEY_COURSE_NAME = "course_name";
	public static final String KEY_COURSE_CLASS = "course_class";
	public static final String KEY_COURSE_APPLY_LIMIT = "course_apply_limit";
	public static final String KEY_COURSE_APPLY_COUNT = "apply_apply_count";
	public static final String KEY_COURSE_ADDRESS = "course_address";
	public static final String KEY_COURSE_REMARK = "course_remark";
	public static final String KEY_COURSE_CONTENT = "course_content";
	public static final String KEY_COURSE_CREATE_USER_ID = "course_create_user_id";
	public static final String KEY_COURSE_CREATE_TIME = "course_create_time";
	public static final String KEY_COURSE_UPDATE_USER_ID = "course_update_user_id";
	public static final String KEY_COURSE_UPDATE_TIME = "course_update_time";
	public static final String KEY_COURSE_STATUS = "course_status";

	private List<Grade> gradeList;
	private List<Teacher> teacherList;
	private Boolean isApply;

	public String getCourse_id() {
		return getStr(KEY_COURSE_ID);
	}

	public void setCourse_id(String course_id) {
		set(KEY_COURSE_ID, course_id);
	}

	public JSONArray getCourse_grade() {
		return JSONArray.parseArray(getStr(KEY_COURSE_GRADE));
	}

	public void setCourse_grade(String course_grade) {
		set(KEY_COURSE_GRADE, course_grade);
	}

	public JSONArray getCourse_teacher() {
		return JSONArray.parseArray(getStr(KEY_COURSE_TEACHER));
	}

	public void setCourse_teacher(String course_teacher) {
		set(KEY_COURSE_TEACHER, course_teacher);
	}

	public String getCourse_name() {
		return getStr(KEY_COURSE_NAME);
	}

	public void setCourse_name(String course_name) {
		set(KEY_COURSE_NAME, course_name);
	}

	public String getCourse_class() {
		return getStr(KEY_COURSE_CLASS);
	}

	public void setCourse_class(String course_class) {
		set(KEY_COURSE_CLASS, course_class);
	}

	public Integer getCourse_apply_limit() {
		return Utility.getIntegerValue(get(KEY_COURSE_APPLY_LIMIT));
	}

	public void setCourse_apply_limit(Integer course_apply_limit) {
		set(KEY_COURSE_APPLY_LIMIT, course_apply_limit);
	}

	public Integer getCourse_apply_count() {
		return Utility.getIntegerValue(get(KEY_COURSE_APPLY_COUNT));
	}

	public String getCourse_address() {
		return getStr(KEY_COURSE_ADDRESS);
	}

	public void setCourse_address(String course_address) {
		set(KEY_COURSE_ADDRESS, course_address);
	}

	public String getCourse_remark() {
		return getStr(KEY_COURSE_REMARK);
	}

	public void setCourse_remark(String course_remark) {
		set(KEY_COURSE_REMARK, course_remark);
	}

	public String getCourse_content() {
		return getStr(KEY_COURSE_CONTENT);
	}

	public void setCourse_content(String course_content) {
		set(KEY_COURSE_CONTENT, course_content);
	}

	public void setCourse_create_user_id(String course_create_user_id) {
		set(KEY_COURSE_CREATE_USER_ID, course_create_user_id);
	}

	public void setCourse_create_time(Date course_create_time) {
		set(KEY_COURSE_CREATE_TIME, course_create_time);
	}

	public void setCourse_update_user_id(String course_update_user_id) {
		set(KEY_COURSE_UPDATE_USER_ID, course_update_user_id);
	}

	public void setCourse_update_time(Date course_update_time) {
		set(KEY_COURSE_UPDATE_TIME, course_update_time);
	}

	public Boolean getCourse_status() {
		return getBoolean(KEY_COURSE_STATUS);
	}

	public void setCourse_status(Boolean course_status) {
		set(KEY_COURSE_STATUS, course_status);
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}

	public Boolean getIsApply() {
		return isApply;
	}

	public void setIsApply(Boolean isApply) {
		this.isApply = isApply;
	}

}
