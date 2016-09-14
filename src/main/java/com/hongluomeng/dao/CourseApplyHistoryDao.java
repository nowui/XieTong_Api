package com.hongluomeng.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.hongluomeng.common.Utility;
import com.hongluomeng.model.CourseApplyHistory;

public class CourseApplyHistoryDao {

	private Integer count(CourseApplyHistory courseApplyHistory) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM " + CourseApplyHistory.KEY_COURSE_APPLY_HISTORY + " ");

		Boolean isExit = false;

		if (! Utility.isNullOrEmpty(courseApplyHistory.getCourse_id())) {
			if(isExit) {
				sql.append(" AND ");
			} else {
				sql.append(" WHERE ");
			}
			sql.append(CourseApplyHistory.KEY_COURSE_ID + " = ? ");
			parameterList.add(courseApplyHistory.getCourse_id());

			isExit = true;
		}

		if (! Utility.isNullOrEmpty(courseApplyHistory.getUser_id())) {
			if(isExit) {
				sql.append(" AND ");
			} else {
				sql.append(" WHERE ");
			}
			sql.append(CourseApplyHistory.KEY_USER_ID + " = ? ");
			parameterList.add(courseApplyHistory.getUser_id());

			isExit = true;
		}

		if(isExit) {
			sql.append("AND ");
		} else {
			sql.append("WHERE ");
		}
		sql.append(CourseApplyHistory.KEY_COURSE_APPLY_HISTORY_STATUS + " = 1 ");

		Number count = Db.queryFirst(sql.toString(), parameterList.toArray());
		return count.intValue();
	}

	public Integer countByCourse_id(String course_id) {
		CourseApplyHistory courseApplyHistory = new CourseApplyHistory();
		courseApplyHistory.setCourse_id(course_id);

		return count(courseApplyHistory);
	}

	public Integer countByUser_id(String user_id) {
		CourseApplyHistory courseApplyHistory = new CourseApplyHistory();
		courseApplyHistory.setUser_id(user_id);

		return count(courseApplyHistory);
	}

	private List<CourseApplyHistory> list(CourseApplyHistory courseApplyHistory, Integer m, Integer n) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT * FROM " + CourseApplyHistory.KEY_COURSE_APPLY_HISTORY + " ");

		Boolean isExit = false;

		if (! Utility.isNullOrEmpty(courseApplyHistory.getCourse_id())) {
			if(isExit) {
				sql.append("AND ");
			} else {
				sql.append("WHERE ");
			}
			sql.append(CourseApplyHistory.KEY_COURSE_ID + " = ? ");
			parameterList.add(courseApplyHistory.getCourse_id());

			isExit = true;
		}

		if (! Utility.isNullOrEmpty(courseApplyHistory.getUser_id())) {
			if(isExit) {
				sql.append("AND ");
			} else {
				sql.append("WHERE ");
			}
			sql.append(CourseApplyHistory.KEY_USER_ID + " = ? ");
			parameterList.add(courseApplyHistory.getUser_id());

			isExit = true;
		}

		if(isExit) {
			sql.append("AND ");
		} else {
			sql.append("WHERE ");
		}
		sql.append(CourseApplyHistory.KEY_COURSE_APPLY_HISTORY_STATUS + " = 1 ");

		sql.append("ORDER BY " + CourseApplyHistory.KEY_COURSE_APPLY_HISTORY_CREATE_TIME + " DESC ");

		if (n > 0) {
			sql.append("LIMIT ?, ? ");
			parameterList.add(m);
			parameterList.add(n);
		}

		List<CourseApplyHistory> courseApplyHistoryList = courseApplyHistory.find(sql.toString(), parameterList.toArray());
		return courseApplyHistoryList;
	}

	public List<CourseApplyHistory> listByCourse_idAndUser_id(String course_id, String user_id) {
		CourseApplyHistory courseApplyHistory = new CourseApplyHistory();
		courseApplyHistory.setCourse_id(course_id);
		courseApplyHistory.setUser_id(user_id);

		return list(courseApplyHistory, 0, 0);
	}

	public void save(String course_id, String request_user_id, Boolean course_apply_history_is_apply) {
		CourseApplyHistory courseApplyHistory = new CourseApplyHistory();
		courseApplyHistory.setCourse_id(course_id);
		courseApplyHistory.setUser_id(request_user_id);
		courseApplyHistory.setCourse_apply_history_is_apply(course_apply_history_is_apply);
		courseApplyHistory.setCourse_apply_history_create_time(new Date());
		courseApplyHistory.setCourse_apply_history_status(true);

		courseApplyHistory.save();
	}

	public void deleteAll(String request_user_id) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("UPDATE " + CourseApplyHistory.KEY_COURSE_APPLY_HISTORY + " SET " + CourseApplyHistory.KEY_COURSE_APPLY_HISTORY_STATUS + " = 0 WHERE " + CourseApplyHistory.KEY_COURSE_APPLY_HISTORY_STATUS + " = 1 ");

		Db.update(sql.toString(), parameterList.toArray());
	}

}
