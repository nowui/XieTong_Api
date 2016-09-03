package com.hongluomeng.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.hongluomeng.common.Utility;
import com.hongluomeng.model.CourseApply;
import com.hongluomeng.model.Course;
import com.hongluomeng.model.Grade;
import com.hongluomeng.model.Student;

public class CourseApplyDao {

	private Integer count(CourseApply courseApply) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM " + CourseApply.KEY_COURSE_APPLY + " ");

		Boolean isExit = false;

		if (! Utility.isNullOrEmpty(courseApply.getCourse_id())) {
			if(isExit) {
				sql.append(" AND ");
			} else {
				sql.append(" WHERE ");
			}
			sql.append(CourseApply.KEY_COURSE_ID + " = ? ");
			parameterList.add(courseApply.getCourse_id());

			isExit = true;
		}

		if (! Utility.isNullOrEmpty(courseApply.getUser_id())) {
			if(isExit) {
				sql.append(" AND ");
			} else {
				sql.append(" WHERE ");
			}
			sql.append(CourseApply.KEY_USER_ID + " = ? ");
			parameterList.add(courseApply.getUser_id());

			isExit = true;
		}

		if(isExit) {
			sql.append("AND ");
		} else {
			sql.append("WHERE ");
		}
		sql.append(CourseApply.KEY_COURSE_APPLY_STATUS + " = 1 ");

		Number count = Db.queryFirst(sql.toString(), parameterList.toArray());
		return count.intValue();
	}

	public Integer countByCourse_id(String course_id) {
		CourseApply courseApply = new CourseApply();
		courseApply.setCourse_id(course_id);

		return count(courseApply);
	}

	public Integer countByUser_id(String user_id) {
		CourseApply courseApply = new CourseApply();
		courseApply.setUser_id(user_id);

		return count(courseApply);
	}

	public Integer countByUser_idAndCourse_class(String user_id, String course_class) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM " + CourseApply.KEY_COURSE_APPLY + " ");
		sql.append("LEFT JOIN " + Course.KEY_COURSE + " ON " + CourseApply.KEY_COURSE_APPLY + "." + CourseApply.KEY_COURSE_ID + " = " + Course.KEY_COURSE + "." + Course.KEY_COURSE_ID + " ");

		sql.append("WHERE " + CourseApply.KEY_COURSE_APPLY + "." + CourseApply.KEY_USER_ID + " = ? ");
		parameterList.add(user_id);

		sql.append("AND " + CourseApply.KEY_COURSE_APPLY + "." + CourseApply.KEY_COURSE_APPLY_STATUS + " = 1 ");

		sql.append("AND " + Course.KEY_COURSE + "." + Course.KEY_COURSE_CLASS + " = ? ");
		parameterList.add(course_class);

		sql.append("AND " + Course.KEY_COURSE + "." + Course.KEY_COURSE_STATUS + " = 1 ");

		Number count = Db.queryFirst(sql.toString(), parameterList.toArray());
		return count.intValue();

	}

	public Integer countByUser_idAndCourse_id(String user_id, String course_id) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM " + CourseApply.KEY_COURSE_APPLY + " ");
		sql.append("LEFT JOIN " + Course.KEY_COURSE + " ON " + CourseApply.KEY_COURSE_APPLY + "." + CourseApply.KEY_COURSE_ID + " = " + Course.KEY_COURSE + "." + Course.KEY_COURSE_ID + " ");

		sql.append("WHERE " + CourseApply.KEY_COURSE_APPLY + "." + CourseApply.KEY_USER_ID + " = ? ");
		parameterList.add(user_id);

		sql.append("AND " + CourseApply.KEY_COURSE_APPLY + "." + CourseApply.KEY_COURSE_APPLY_STATUS + " = 1 ");

		sql.append("AND " + Course.KEY_COURSE + "." + Course.KEY_COURSE_ID + " = ? ");
		parameterList.add(course_id);

		sql.append("AND " + Course.KEY_COURSE + "." + Course.KEY_COURSE_STATUS + " = 1 ");

		Number count = Db.queryFirst(sql.toString(), parameterList.toArray());
		return count.intValue();

	}

	private List<CourseApply> list(CourseApply courseApply, Integer m, Integer n) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT * FROM " + CourseApply.KEY_COURSE_APPLY + " ");

		Boolean isExit = false;

		if (! Utility.isNullOrEmpty(courseApply.getCourse_id())) {
			if(isExit) {
				sql.append("AND ");
			} else {
				sql.append("WHERE ");
			}
			sql.append(CourseApply.KEY_COURSE_ID + " = ? ");
			parameterList.add(courseApply.getCourse_id());

			isExit = true;
		}

		if (! Utility.isNullOrEmpty(courseApply.getUser_id())) {
			if(isExit) {
				sql.append("AND ");
			} else {
				sql.append("WHERE ");
			}
			sql.append(CourseApply.KEY_USER_ID + " = ? ");
			parameterList.add(courseApply.getUser_id());

			isExit = true;
		}

		if(isExit) {
			sql.append("AND ");
		} else {
			sql.append("WHERE ");
		}
		sql.append(CourseApply.KEY_COURSE_APPLY_STATUS + " = 1 ");

		sql.append("ORDER BY " + CourseApply.KEY_COURSE_APPLY_CREATE_TIME + " DESC ");

		if (n > 0) {
			sql.append("LIMIT ?, ? ");
			parameterList.add(m);
			parameterList.add(n);
		}

		List<CourseApply> courseApplyList = courseApply.find(sql.toString(), parameterList.toArray());
		return courseApplyList;
	}

	public List<CourseApply> listByUser_id(String user_id) {
		CourseApply courseApply = new CourseApply();
		courseApply.setUser_id(user_id);

		return list(courseApply, 0, 0);
	}

	public List<CourseApply> listMyCourseApply(String user_id) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT " + CourseApply.KEY_COURSE_APPLY + ".*, " + Student.KEY_STUDENT + "." + Student.KEY_STUDENT_NAME + ", " + Student.KEY_STUDENT + "." + Student.KEY_STUDENT_NUMBER + ", " + Student.KEY_STUDENT + "." + Student.KEY_STUDENT_SEX + ", " + Grade.KEY_GRADE + "." + Grade.KEY_GRADE_NAME + ", " + Course.KEY_COURSE + "." + Course.KEY_COURSE_NAME + ", " + Course.KEY_COURSE + "." + Course.KEY_COURSE_CLASS + ", " + Course.KEY_COURSE + "." + Course.KEY_COURSE_TEACHER + ", " + Course.KEY_COURSE + "." + Course.KEY_COURSE_ADDRESS + " FROM " + CourseApply.KEY_COURSE_APPLY + " ");
		sql.append("LEFT JOIN " + Student.KEY_STUDENT + " ON " + CourseApply.KEY_COURSE_APPLY + "." + CourseApply.KEY_USER_ID + " = " + Student.KEY_STUDENT + "." + Student.KEY_USER_ID + " ");
		sql.append("LEFT JOIN " + Grade.KEY_GRADE + " ON " + Student.KEY_STUDENT + "." + Student.KEY_GRADE_ID + " = " + Grade.KEY_GRADE + "." + Grade.KEY_GRADE_ID + " ");
		sql.append("LEFT JOIN " + Course.KEY_COURSE + " ON " + CourseApply.KEY_COURSE_APPLY + "." + CourseApply.KEY_COURSE_ID + " = " + Course.KEY_COURSE + "." + Course.KEY_COURSE_ID + " ");
		sql.append("WHERE " + CourseApply.KEY_COURSE_APPLY + "." + CourseApply.KEY_COURSE_APPLY_STATUS + " = 1 ");
		sql.append("AND " + CourseApply.KEY_COURSE_APPLY + "." + CourseApply.KEY_USER_ID + " = ? ");
		sql.append("ORDER BY " + Course.KEY_COURSE_CLASS + " ASC ");

		parameterList.add(user_id);

		List<CourseApply> courseApplyList = new CourseApply().find(sql.toString(), parameterList.toArray());
		return courseApplyList;
	}

	private List<CourseApply> listOrderByObject_id(String object_id) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT " + CourseApply.KEY_COURSE_APPLY + ".*, " + Student.KEY_STUDENT + "." + Student.KEY_STUDENT_NAME + ", " + Student.KEY_STUDENT + "." + Student.KEY_STUDENT_NUMBER + ", " + Student.KEY_STUDENT + "." + Student.KEY_STUDENT_SEX + ", " + Grade.KEY_GRADE + "." + Grade.KEY_GRADE_NAME + ", " + Course.KEY_COURSE + "." + Course.KEY_COURSE_NAME + ", " + Course.KEY_COURSE + "." + Course.KEY_COURSE_CLASS + ", " + Course.KEY_COURSE + "." + Course.KEY_COURSE_TEACHER + ", " + Course.KEY_COURSE + "." + Course.KEY_COURSE_ADDRESS + " FROM " + CourseApply.KEY_COURSE_APPLY + " ");
		sql.append("LEFT JOIN " + Student.KEY_STUDENT + " ON " + CourseApply.KEY_COURSE_APPLY + "." + CourseApply.KEY_USER_ID + " = " + Student.KEY_STUDENT + "." + Student.KEY_USER_ID + " ");
		sql.append("LEFT JOIN " + Grade.KEY_GRADE + " ON " + Student.KEY_STUDENT + "." + Student.KEY_GRADE_ID + " = " + Grade.KEY_GRADE + "." + Grade.KEY_GRADE_ID + " ");
		sql.append("LEFT JOIN " + Course.KEY_COURSE + " ON " + CourseApply.KEY_COURSE_APPLY + "." + CourseApply.KEY_COURSE_ID + " = " + Course.KEY_COURSE + "." + Course.KEY_COURSE_ID + " ");
		sql.append("WHERE " + CourseApply.KEY_COURSE_APPLY + "." + CourseApply.KEY_COURSE_APPLY_STATUS + " = 1 ");
		sql.append("ORDER BY " + object_id + " ");

		System.out.println(sql.toString());

		List<CourseApply> courseApplyList = new CourseApply().find(sql.toString(), parameterList.toArray());
		return courseApplyList;
	}

	public List<CourseApply> listOrderByCourse_id() {
		return listOrderByObject_id(Course.KEY_COURSE + "." + Course.KEY_COURSE_ID + ", " + Course.KEY_COURSE_CLASS + ", " + Student.KEY_STUDENT_NUMBER + " ASC");
	}

	public List<CourseApply> listOrderByGrade_idAndStudent_id() {
		return listOrderByObject_id(Grade.KEY_GRADE + "." + Grade.KEY_GRADE_NAME + " ASC, " + Student.KEY_STUDENT + "." + Student.KEY_STUDENT_ID + " DESC, " + Course.KEY_COURSE_CLASS + " ASC");
	}

	public void save(String course_id, String request_user_id) {
		CourseApply courseApply = new CourseApply();
		courseApply.setCourse_id(course_id);
		courseApply.setUser_id(request_user_id);
		courseApply.setCourseApply_create_user_id(request_user_id);
		courseApply.setCourseApply_create_time(new Date());
		courseApply.setCourseApply_update_user_id(request_user_id);
		courseApply.setCourseApply_update_time(new Date());
		courseApply.setCourseApply_status(true);

		courseApply.save();
	}

	public void delete(String course_id, String request_user_id) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("UPDATE " + CourseApply.KEY_COURSE_APPLY + " SET " + CourseApply.KEY_COURSE_APPLY_STATUS + " = 0, " + CourseApply.KEY_COURSE_APPLY_UPDATE_USER_ID + " = ?, " + CourseApply.KEY_COURSE_APPLY_UPDATE_TIME + " = ? WHERE " + CourseApply.KEY_COURSE_ID + " = ? AND " + CourseApply.KEY_USER_ID + " = ? ");

		parameterList.add(request_user_id);
		parameterList.add(new Date());
		parameterList.add(course_id);
		parameterList.add(request_user_id);

		Db.update(sql.toString(), parameterList.toArray());
	}

}
