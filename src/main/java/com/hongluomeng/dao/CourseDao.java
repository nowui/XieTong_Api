package com.hongluomeng.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.hongluomeng.common.Utility;
import com.hongluomeng.model.Course;
import com.hongluomeng.model.CourseApply;

public class CourseDao {

	private Integer count(Course course) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM " + Course.KEY_COURSE + " ");

		Boolean isExit = false;

		if (! Utility.isNullOrEmpty(course.getCourse_class())) {
			if(isExit) {
				sql.append("AND ");
			} else {
				sql.append("WHERE ");
			}
			sql.append(Course.KEY_COURSE_CLASS + " = ? ");
			parameterList.add(course.getCourse_class());

			isExit = true;
		}

		if (! Utility.isNullOrEmpty(course.getCourse_name())) {
			if(isExit) {
				sql.append("AND ");
			} else {
				sql.append("WHERE ");
			}
			sql.append(Course.KEY_COURSE_NAME + " LIKE ? ");
			parameterList.add("%" + course.getCourse_name() + "%");

			isExit = true;
		}

		if(isExit) {
			sql.append("AND ");
		} else {
			sql.append("WHERE ");
		}
		sql.append(Course.KEY_COURSE_STATUS + " = 1 ");

		Number count = Db.queryFirst(sql.toString(), parameterList.toArray());
		return count.intValue();
	}

	public Integer count(String course_name, String course_class) {
		Course course = new Course();
		course.setCourse_name(course_name);
		course.setCourse_class(course_class);

		return count(course);
	}

	private List<Course> list(Course course, Integer m, Integer n) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT * FROM " + Course.KEY_COURSE + " ");

		Boolean isExit = false;

		if (! Utility.isNullOrEmpty(course.getCourse_class())) {
			if(isExit) {
				sql.append("AND ");
			} else {
				sql.append("WHERE ");
			}
			sql.append(Course.KEY_COURSE_CLASS + " = ? ");
			parameterList.add(course.getCourse_class());

			isExit = true;
		}

		if (! Utility.isNullOrEmpty(course.getCourse_name())) {
			if(isExit) {
				sql.append("AND ");
			} else {
				sql.append("WHERE ");
			}
			sql.append(Course.KEY_COURSE_NAME + " LIKE ? ");
			parameterList.add("%" + course.getCourse_name() + "%");

			isExit = true;
		}

		if(isExit) {
			sql.append("AND ");
		} else {
			sql.append("WHERE ");
		}
		sql.append(Course.KEY_COURSE_STATUS + " = 1 ");

		sql.append("ORDER BY " + Course.KEY_COURSE_CLASS + " ASC ");

		if (n > 0) {
			sql.append("LIMIT ?, ? ");
			parameterList.add(m);
			parameterList.add(n);
		}

		List<Course> courseList = course.find(sql.toString(), parameterList.toArray());
		return courseList;
	}

	public List<Course> list(String course_name, String course_class, Integer m, Integer n) {
		Course course = new Course();
		course.setCourse_name(course_name);
		course.setCourse_class(course_class);

		return list(course, m, n);
	}

	public List<Course> listByGrade_id(String grade_id) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT " + Course.KEY_COURSE + ".*, COUNT(" + CourseApply.KEY_COURSE_APPLY + "." + CourseApply.KEY_USER_ID + ") AS " + Course.KEY_COURSE_APPLY_COUNT + " FROM " + Course.KEY_COURSE + " ");
		sql.append("LEFT JOIN (SELECT * FROM " + CourseApply.KEY_COURSE_APPLY + " WHERE " + CourseApply.KEY_COURSE_APPLY + "." + CourseApply.KEY_COURSE_APPLY_STATUS + " = 1) AS " + CourseApply.KEY_COURSE_APPLY + " ON " + Course.KEY_COURSE + "." + Course.KEY_COURSE_ID + " = " + CourseApply.KEY_COURSE_APPLY + "." + CourseApply.KEY_COURSE_ID + " ");
		sql.append("WHERE " + Course.KEY_COURSE + "." + Course.KEY_COURSE_STATUS + " = 1 ");
		sql.append("AND " + Course.KEY_COURSE + "." + Course.KEY_COURSE_GRADE + " LIKE ? ");
		sql.append("group by " + Course.KEY_COURSE + "." + Course.KEY_COURSE_ID + " ");
		sql.append("order by " + Course.KEY_COURSE + "." + Course.KEY_COURSE_CLASS + " ");

		parameterList.add("%" + grade_id + "%");

		List<Course> courseList = new Course().find(sql.toString(), parameterList.toArray());
		return courseList;
	}

	private Course find(Course course) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT * FROM " + Course.KEY_COURSE + " ");

		Boolean isExit = false;

		if (! Utility.isNullOrEmpty(course.getCourse_id())) {
			if(isExit) {
				sql.append(" AND ");
			} else {
				sql.append(" WHERE ");
			}
			sql.append(Course.KEY_COURSE_ID + " = ? ");
			parameterList.add(course.getCourse_id());

			isExit = true;
		}

		if(isExit) {
			sql.append("AND ");
		} else {
			sql.append("WHERE ");
		}
		sql.append(Course.KEY_COURSE_STATUS + " = 1 ");

		if(! isExit) {
			return null;
		}

		List<Course> courseList = course.find(sql.toString(), parameterList.toArray());
		if(courseList.size() == 0) {
			return null;
		} else {
			return courseList.get(0);
		}
	}

	public Course findByCourse_id(String course_id) {
		Course course = new Course();
		course.setCourse_id(course_id);

		return find(course);
	}

	public Course findByCourse_idForApply(String course_id) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT " + Course.KEY_COURSE + ".*, COUNT(" + CourseApply.KEY_COURSE_APPLY + "." + CourseApply.KEY_USER_ID + ") AS " + Course.KEY_COURSE_APPLY_COUNT + " FROM " + Course.KEY_COURSE + " ");
		sql.append("LEFT JOIN (SELECT * FROM " + CourseApply.KEY_COURSE_APPLY + " WHERE " + CourseApply.KEY_COURSE_APPLY + "." + CourseApply.KEY_COURSE_APPLY_STATUS + " = 1) AS " + CourseApply.KEY_COURSE_APPLY + " ON " + Course.KEY_COURSE + "." + Course.KEY_COURSE_ID + " = " + CourseApply.KEY_COURSE_APPLY + "." + CourseApply.KEY_COURSE_ID + " ");
		sql.append("WHERE " + Course.KEY_COURSE + "." + Course.KEY_COURSE_STATUS + " = 1 ");
		sql.append("AND " + Course.KEY_COURSE + "." + Course.KEY_COURSE_ID + " = ? ");
		sql.append("group by " + Course.KEY_COURSE + "." + Course.KEY_COURSE_ID + " ");

		parameterList.add(course_id);

		List<Course> courseList = new Course().find(sql.toString(), parameterList.toArray());
		if(courseList.size() == 0) {
			return null;
		} else {
			return courseList.get(0);
		}
	}

	public void save(Course course, String request_user_id) {
		course.setCourse_id(Utility.getUUID());
		course.setCourse_create_user_id(request_user_id);
		course.setCourse_create_time(new Date());
		course.setCourse_update_user_id(request_user_id);
		course.setCourse_update_time(new Date());
		course.setCourse_status(true);

		course.save();
	}

	public void update(Course course, String request_user_id) {
		course.remove(Course.KEY_COURSE_CREATE_USER_ID);
		course.remove(Course.KEY_COURSE_CREATE_TIME);
		course.setCourse_update_user_id(request_user_id);
		course.setCourse_update_time(new Date());

		course.update();
	}

	public void delete(String course_id, String request_user_id) {
		Course course = new Course();
		course.setCourse_id(course_id);
		course.setCourse_update_user_id(request_user_id);
		course.setCourse_update_time(new Date());
		course.setCourse_status(false);

		course.update();
	}

}
