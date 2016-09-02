package com.hongluomeng.service;

import java.util.List;

import com.hongluomeng.dao.CourseApplyDao;
import com.hongluomeng.model.CourseApply;

public class CourseApplyService {

	private CourseApplyDao courseApplyDao = new CourseApplyDao();

	public Integer countByCourse_id(String course_id) {
		return courseApplyDao.countByCourse_id(course_id);
	}

	public Integer countByUser_id(String user_id) {
		return courseApplyDao.countByUser_id(user_id);
	}

	public Integer countByUser_idAndCourse_class(String user_id, String course_class) {
		return courseApplyDao.countByUser_idAndCourse_class(user_id, course_class);
	}

	public Integer countByUser_idAndCourse_id(String user_id, String course_id) {
		return courseApplyDao.countByUser_idAndCourse_id(user_id, course_id);
	}

	public List<CourseApply> listByUser_id(String user_id) {
		return courseApplyDao.listByUser_id(user_id);
	}

	public List<CourseApply> listByCourse_id(String course_id) {
		return courseApplyDao.listByCourse_id(course_id);
	}

	public void save(String course_id, String request_user_id) {
		courseApplyDao.save(course_id, request_user_id);
	}

	public void delete(String course_id, String request_user_id) {
		courseApplyDao.delete(course_id, request_user_id);
	}

}
