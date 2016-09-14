package com.hongluomeng.service;

import java.util.List;

import com.hongluomeng.dao.CourseApplyDao;
import com.hongluomeng.model.CourseApply;

public class CourseApplyService {

	private CourseApplyDao courseApplyDao = new CourseApplyDao();
	private CourseApplyHistoryService courseApplyHistoryService = new CourseApplyHistoryService();

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

	public List<CourseApply> listMyCourseApply(String user_id) {
		return courseApplyDao.listMyCourseApply(user_id);
	}

	public List<CourseApply> listOrderByCourse_id() {
		return courseApplyDao.listOrderByCourse_id();
	}

	public List<CourseApply> listOrderByGrade_idAndStudent_id() {
		return courseApplyDao.listOrderByGrade_idAndStudent_id();
	}

	public void save(String course_id, String request_user_id) {
		courseApplyDao.save(course_id, request_user_id);
	}

	public void delete(String course_id, String request_user_id) {
		courseApplyDao.delete(course_id, request_user_id);
	}

	public void deleteAll(String request_user_id) {
		courseApplyDao.deleteAll(request_user_id);

		courseApplyHistoryService.deleteAll(request_user_id);
	}

}
