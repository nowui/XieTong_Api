package com.hongluomeng.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.hongluomeng.common.Utility;
import com.hongluomeng.model.Grade;
import com.hongluomeng.model.Student;

public class StudentDao {

	private Integer count(Student student) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM " + Student.KEY_STUDENT + " ");

		Boolean isExit = false;

		if (! Utility.isNullOrEmpty(student.getGrade_id())) {
			if(isExit) {
				sql.append("AND ");
			} else {
				sql.append("WHERE ");
			}
			sql.append(Student.KEY_GRADE_ID + " = ? ");
			parameterList.add(student.getGrade_id());

			isExit = true;
		}

		if (! Utility.isNullOrEmpty(student.getStudent_name())) {
			if(isExit) {
				sql.append("AND ");
			} else {
				sql.append("WHERE ");
			}
			sql.append(Student.KEY_STUDENT_NAME + " = ? ");
			parameterList.add(student.getStudent_name());

			isExit = true;
		}

		if (! Utility.isNullOrEmpty(student.getStudent_name())) {
			if(isExit) {
				sql.append("AND ");
			} else {
				sql.append("WHERE ");
			}
			sql.append(Student.KEY_STUDENT_NAME + " LIKE ? ");
			parameterList.add("%" + student.getStudent_name() + "%");

			isExit = true;
		}

		if(isExit) {
			sql.append("AND ");
		} else {
			sql.append("WHERE ");
		}
		sql.append(Student.KEY_STUDENT_STATUS + " = 1 ");

		Number count = Db.queryFirst(sql.toString(), parameterList.toArray());
		return count.intValue();
	}

	public Integer count(String grade_id, String student_name) {
		Student student = new Student();
		student.setGrade_id(grade_id);
		student.setStudent_name(student_name);

		return count(student);
	}

	private List<Student> list(Student student, Integer m, Integer n) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT " + Student.KEY_STUDENT + ".*, " + Grade.KEY_GRADE + "." + Grade.KEY_GRADE_NAME + " AS " + Grade.KEY_GRADE_NAME + " FROM " + Student.KEY_STUDENT + " ");

		sql.append("LEFT JOIN " + Grade.KEY_GRADE + " ON " + Student.KEY_STUDENT + "." + Student.KEY_GRADE_ID + " = " + Grade.KEY_GRADE + "." + Grade.KEY_GRADE_ID + " ");

		Boolean isExit = false;

		if (! Utility.isNullOrEmpty(student.getGrade_id())) {
			if(isExit) {
				sql.append("AND ");
			} else {
				sql.append("WHERE ");
			}
			sql.append(Student.KEY_STUDENT + "." + Student.KEY_GRADE_ID + " = ? ");
			parameterList.add(student.getGrade_id());

			isExit = true;
		}

		if (! Utility.isNullOrEmpty(student.getStudent_name())) {
			if(isExit) {
				sql.append("AND ");
			} else {
				sql.append("WHERE ");
			}
			sql.append(Student.KEY_STUDENT + "." + Student.KEY_STUDENT_NAME + " LIKE ? ");
			parameterList.add("%" + student.getStudent_name() + "%");

			isExit = true;
		}

		if(isExit) {
			sql.append("AND ");
		} else {
			sql.append("WHERE ");
		}
		sql.append(Student.KEY_STUDENT + "." + Student.KEY_STUDENT_STATUS + " = 1 ");

		sql.append("ORDER BY " + Student.KEY_STUDENT + "." + Student.KEY_STUDENT_NUMBER + " DESC ");

		if (n > 0) {
			sql.append("LIMIT ?, ? ");
			parameterList.add(m);
			parameterList.add(n);
		}

		List<Student> studentList = student.find(sql.toString(), parameterList.toArray());
		return studentList;
	}

	public List<Student> list(String grade_id, String student_name, Integer m, Integer n) {
		Student student = new Student();
		student.setGrade_id(grade_id);
		student.setStudent_name(student_name);

		return list(student, m, n);
	}

	private Student find(Student student) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT * FROM " + Student.KEY_STUDENT + " ");

		Boolean isExit = false;

		if (! Utility.isNullOrEmpty(student.getStudent_id())) {
			if(isExit) {
				sql.append("AND ");
			} else {
				sql.append("WHERE ");
			}
			sql.append(Student.KEY_STUDENT_ID + " = ? ");
			parameterList.add(student.getStudent_id());

			isExit = true;
		}

		if (! Utility.isNullOrEmpty(student.getUser_id())) {
			if(isExit) {
				sql.append("AND ");
			} else {
				sql.append("WHERE ");
			}
			sql.append(Student.KEY_USER_ID + " = ? ");
			parameterList.add(student.getUser_id());

			isExit = true;
		}

		if(isExit) {
			sql.append("AND ");
		} else {
			sql.append("WHERE ");
		}
		sql.append(Student.KEY_STUDENT_STATUS + " = 1 ");

		if(! isExit) {
			return null;
		}

		List<Student> studentList = student.find(sql.toString(), parameterList.toArray());
		if(studentList.size() == 0) {
			return null;
		} else {
			return studentList.get(0);
		}
	}

	public Student findByStudent_id(String student_id) {
		Student student = new Student();
		student.setStudent_id(student_id);

		return find(student);
	}

	public Student findByUser_id(String user_id) {
		Student student = new Student();
		student.setUser_id(user_id);

		return find(student);
	}

	public void save(Student student, String request_user_id) {
		student.setStudent_id(Utility.getUUID());
		student.setStudent_create_user_id(request_user_id);
		student.setStudent_create_time(new Date());
		student.setStudent_update_user_id(request_user_id);
		student.setStudent_update_time(new Date());
		student.setStudent_status(true);

		student.save();
	}

	public void update(Student student, String request_user_id) {
		student.remove(Student.KEY_USER_ID);
		student.remove(Student.KEY_STUDENT_CREATE_USER_ID);
		student.remove(Student.KEY_STUDENT_CREATE_TIME);
		student.setStudent_update_user_id(request_user_id);
		student.setStudent_update_time(new Date());

		student.update();
	}

	/*public void updateUser_idByStudent_id(String user_id, String student_id) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("UPDATE " + Student.KEY_STUDENT + " SET " + Student.KEY_USER_ID + " = ? WHERE " + Student.KEY_STUDENT_ID + " = ? ");

		parameterList.add(user_id);
		parameterList.add(student_id);

		Db.update(sql.toString(), parameterList.toArray());
	}*/

	public void delete(String student_id, String request_user_id) {
		Student student = new Student();
		student.setStudent_id(student_id);
		student.setStudent_update_user_id(request_user_id);
		student.setStudent_update_time(new Date());
		student.setStudent_status(false);

		student.update();
	}

}
