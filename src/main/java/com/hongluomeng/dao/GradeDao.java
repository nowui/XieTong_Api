package com.hongluomeng.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.hongluomeng.common.Utility;
import com.hongluomeng.model.Grade;

public class GradeDao {

	private Integer count(Grade grade) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM " + Grade.KEY_GRADE + " ");

		Boolean isExit = false;

		if(isExit) {
			sql.append("AND ");
		} else {
			sql.append("WHERE ");
		}
		sql.append(Grade.KEY_GRADE_STATUS + " = 1 ");

		Number count = Db.queryFirst(sql.toString(), parameterList.toArray());
		return count.intValue();
	}

	public Integer count() {
		Grade grade = new Grade();

		return count(grade);
	}

	private List<Grade> list(Grade grade, Integer m, Integer n) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT * FROM " + Grade.KEY_GRADE + " ");

		Boolean isExit = false;

		if(isExit) {
			sql.append("AND ");
		} else {
			sql.append("WHERE ");
		}
		sql.append(Grade.KEY_GRADE_STATUS + " = 1 ");

		sql.append("ORDER BY " + Grade.KEY_GRADE_NAME + " ");

		if (n > 0) {
			sql.append("LIMIT ?, ? ");
			parameterList.add(m);
			parameterList.add(n);
		}

		List<Grade> gradeList = grade.find(sql.toString(), parameterList.toArray());
		return gradeList;
	}

	public List<Grade> list(Integer m, Integer n) {
		Grade grade = new Grade();

		return list(grade, m, n);
	}

	private Grade find(Grade grade) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT * FROM " + Grade.KEY_GRADE + " ");

		Boolean isExit = false;

		if (! Utility.isNullOrEmpty(grade.getGrade_id())) {
			if(isExit) {
				sql.append(" AND ");
			} else {
				sql.append(" WHERE ");
			}
			sql.append(Grade.KEY_GRADE_ID + " = ? ");
			parameterList.add(grade.getGrade_id());

			isExit = true;
		}

		if(isExit) {
			sql.append("AND ");
		} else {
			sql.append("WHERE ");
		}
		sql.append(Grade.KEY_GRADE_STATUS + " = 1 ");

		if(! isExit) {
			return null;
		}

		List<Grade> gradeList = grade.find(sql.toString(), parameterList.toArray());
		if(gradeList.size() == 0) {
			return null;
		} else {
			return gradeList.get(0);
		}
	}

	public Grade findByGrade_id(String grade_id) {
		Grade grade = new Grade();
		grade.setGrade_id(grade_id);

		return find(grade);
	}

	public void save(Grade grade, String request_user_id) {
		grade.setGrade_id(Utility.getUUID());
		grade.setGrade_create_user_id(request_user_id);
		grade.setGrade_create_time(new Date());
		grade.setGrade_update_user_id(request_user_id);
		grade.setGrade_update_time(new Date());
		grade.setGrade_status(true);

		grade.save();
	}

	public void update(Grade grade, String request_user_id) {
		grade.remove(Grade.KEY_GRADE_CREATE_USER_ID);
		grade.remove(Grade.KEY_GRADE_CREATE_TIME);
		grade.setGrade_update_user_id(request_user_id);
		grade.setGrade_update_time(new Date());

		grade.update();
	}

	public void delete(String grade_id, String request_user_id) {
		Grade grade = new Grade();
		grade.setGrade_id(grade_id);
		grade.setGrade_update_user_id(request_user_id);
		grade.setGrade_update_time(new Date());
		grade.setGrade_status(false);

		grade.update();
	}

}
