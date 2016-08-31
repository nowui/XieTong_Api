package com.hongluomeng.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.hongluomeng.common.Utility;
import com.hongluomeng.model.Teacher;

public class TeacherDao {

	private Integer count(Teacher teacher) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM " + Teacher.KEY_TEACHER + " ");

		Boolean isExit = false;

		if(isExit) {
			sql.append("AND ");
		} else {
			sql.append("WHERE ");
		}
		sql.append(Teacher.KEY_TEACHER_STATUS + " = 1 ");

		Number count = Db.queryFirst(sql.toString(), parameterList.toArray());
		return count.intValue();
	}

	public Integer count() {
		Teacher teacher = new Teacher();

		return count(teacher);
	}

	private List<Teacher> list(Teacher teacher, Integer m, Integer n) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT * FROM " + Teacher.KEY_TEACHER + " ");

		Boolean isExit = false;

		if(isExit) {
			sql.append("AND ");
		} else {
			sql.append("WHERE ");
		}
		sql.append(Teacher.KEY_TEACHER_STATUS + " = 1 ");

		sql.append("ORDER BY " + Teacher.KEY_TEACHER_CREATE_TIME + " DESC ");

		if (n > 0) {
			sql.append("LIMIT ?, ? ");
			parameterList.add(m);
			parameterList.add(n);
		}

		List<Teacher> teacherList = teacher.find(sql.toString(), parameterList.toArray());
		return teacherList;
	}

	public List<Teacher> list(Integer m, Integer n) {
		Teacher teacher = new Teacher();

		return list(teacher, m, n);
	}

	private Teacher find(Teacher teacher) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT * FROM " + Teacher.KEY_TEACHER + " ");

		Boolean isExit = false;

		if (! Utility.isNullOrEmpty(teacher.getTeacher_id())) {
			if(isExit) {
				sql.append("AND ");
			} else {
				sql.append("WHERE ");
			}
			sql.append(Teacher.KEY_TEACHER_ID + " = ? ");
			parameterList.add(teacher.getTeacher_id());

			isExit = true;
		}

		if(isExit) {
			sql.append("AND ");
		} else {
			sql.append("WHERE ");
		}
		sql.append(Teacher.KEY_TEACHER_STATUS + " = 1 ");

		if(! isExit) {
			return null;
		}

		List<Teacher> teacherList = teacher.find(sql.toString(), parameterList.toArray());
		if(teacherList.size() == 0) {
			return null;
		} else {
			return teacherList.get(0);
		}
	}

	public Teacher findByTeacher_id(String teacher_id) {
		Teacher teacher = new Teacher();
		teacher.setTeacher_id(teacher_id);

		return find(teacher);
	}

	public void save(Teacher teacher, String request_user_id) {
		teacher.setTeacher_id(Utility.getUUID());
		teacher.setTeacher_create_user_id(request_user_id);
		teacher.setTeacher_create_time(new Date());
		teacher.setTeacher_update_user_id(request_user_id);
		teacher.setTeacher_update_time(new Date());
		teacher.setTeacher_status(true);

		teacher.save();
	}

	public void update(Teacher teacher, String request_user_id) {
		teacher.remove(Teacher.KEY_USER_ID);
		teacher.remove(Teacher.KEY_TEACHER_CREATE_USER_ID);
		teacher.remove(Teacher.KEY_TEACHER_CREATE_TIME);
		teacher.setTeacher_update_user_id(request_user_id);
		teacher.setTeacher_update_time(new Date());

		teacher.update();
	}

	/*public void updateUser_idByTeacher_id(String user_id, String teacher_id) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("UPDATE " + Teacher.KEY_TEACHER + " SET " + Teacher.KEY_USER_ID + " = ? WHERE " + Teacher.KEY_TEACHER_ID + " = ? ");

		parameterList.add(user_id);
		parameterList.add(teacher_id);

		Db.update(sql.toString(), parameterList.toArray());
	}*/

	public void delete(String teacher_id, String request_user_id) {
		Teacher teacher = new Teacher();
		teacher.setTeacher_id(teacher_id);
		teacher.setTeacher_update_user_id(request_user_id);
		teacher.setTeacher_update_time(new Date());
		teacher.setTeacher_status(false);

		teacher.update();
	}

}
