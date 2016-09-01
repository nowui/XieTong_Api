package com.hongluomeng.model;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class Student extends Model<Student> {

	private static final long serialVersionUID = 1L;

	public static final String KEY_STUDENT = "student";
	public static final String KEY_STUDENT_ID = "student_id";
	public static final String KEY_GRADE_ID = "grade_id";
	public static final String KEY_GRADE_NAME = "grade_name";
	public static final String KEY_STUDENT_NUMBER = "student_number";
	public static final String KEY_STUDENT_NAME = "student_name";
	public static final String KEY_STUDENT_SEX = "student_sex";
	public static final String KEY_USER_ID = "user_id";
	public static final String KEY_STUDENT_CREATE_USER_ID = "student_create_user_id";
	public static final String KEY_STUDENT_CREATE_TIME = "student_create_time";
	public static final String KEY_STUDENT_UPDATE_USER_ID = "student_update_user_id";
	public static final String KEY_STUDENT_UPDATE_TIME = "student_update_time";
	public static final String KEY_STUDENT_STATUS = "student_status";

	private String user_account;
	private List<Grade> gradeList;

	public String getStudent_id() {
		return getStr(KEY_STUDENT_ID);
	}

	public void setStudent_id(String student_id) {
		set(KEY_STUDENT_ID, student_id);
	}

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

	public String getStudent_number() {
		return getStr(KEY_STUDENT_NUMBER);
	}

	public void setStudent_number(String student_number) {
		set(KEY_STUDENT_NUMBER, student_number);
	}

	public String getStudent_name() {
		return getStr(KEY_STUDENT_NAME);
	}

	public void setStudent_name(String student_name) {
		set(KEY_STUDENT_NAME, student_name);
	}

	public String getStudent_sex() {
		return getStr(KEY_STUDENT_SEX);
	}

	public void setStudent_sex(String student_sex) {
		set(KEY_STUDENT_SEX, student_sex);
	}

	public String getUser_id() {
		return getStr(KEY_USER_ID);
	}

	public void setUser_id(String user_id) {
		set(KEY_USER_ID, user_id);
	}

	public void setStudent_create_user_id(String student_create_user_id) {
		set(KEY_STUDENT_CREATE_USER_ID, student_create_user_id);
	}

	public void setStudent_create_time(Date student_create_time) {
		set(KEY_STUDENT_CREATE_TIME, student_create_time);
	}

	public void setStudent_update_user_id(String student_update_user_id) {
		set(KEY_STUDENT_UPDATE_USER_ID, student_update_user_id);
	}

	public void setStudent_update_time(Date student_update_time) {
		set(KEY_STUDENT_UPDATE_TIME, student_update_time);
	}

	public Boolean getStudent_status() {
		return getBoolean(KEY_STUDENT_STATUS);
	}

	public void setStudent_status(Boolean student_status) {
		set(KEY_STUDENT_STATUS, student_status);
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

}
