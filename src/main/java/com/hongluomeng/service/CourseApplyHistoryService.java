package com.hongluomeng.service;

import java.util.List;

import com.hongluomeng.dao.CourseApplyHistoryDao;
import com.hongluomeng.model.CourseApplyHistory;

public class CourseApplyHistoryService {

	private CourseApplyHistoryDao courseApplyHistoryDao = new CourseApplyHistoryDao();

	public Integer countByCourse_id(String course_id) {
		return courseApplyHistoryDao.countByCourse_id(course_id);
	}

	public Integer countByUser_id(String user_id) {
		return courseApplyHistoryDao.countByUser_id(user_id);
	}

	public List<CourseApplyHistory> listByCourse_idAndUser_id(String course_id, String user_id) {
		return courseApplyHistoryDao.listByCourse_idAndUser_id(course_id, user_id);
	}

	public void save(String course_id, String request_user_id, Boolean course_apply_history_is_apply) {
		courseApplyHistoryDao.save(course_id, request_user_id, course_apply_history_is_apply);
	}

	public void deleteAll(String request_user_id) {
		courseApplyHistoryDao.deleteAll(request_user_id);
	}

}