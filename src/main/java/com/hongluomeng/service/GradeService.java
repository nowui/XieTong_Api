package com.hongluomeng.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.hongluomeng.common.Const;
import com.hongluomeng.common.Utility;
import com.hongluomeng.dao.GradeDao;
import com.hongluomeng.model.Grade;

public class GradeService {

	private GradeDao gradeDao = new GradeDao();

	public Map<String, Object> list(JSONObject jsonObject) {
		//Grade gradeMap = jsonObject.toJavaObject(Grade.class);

		Integer count = gradeDao.count();

		List<Grade> gradeList = gradeDao.list(Utility.getStarNumber(jsonObject), Utility.getEndNumber(jsonObject));

		Map<String, Object> resultMap = Utility.setResultMap(count, gradeList);

		return resultMap;
	}

	public List<Grade> listAll() {
		return gradeDao.list(0, 0);
	}

	public Grade find(JSONObject jsonObject) {
		Grade gradeMap = jsonObject.toJavaObject(Grade.class);

		Grade grade = gradeDao.findByGrade_id(gradeMap.getGrade_id());

		return grade;
	}

	public Grade findByGrade_id(String grade_id) {
		return gradeDao.findByGrade_id(grade_id);
	}

	public void save(JSONObject jsonObject) {
		Grade gradeMap = jsonObject.toJavaObject(Grade.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		gradeDao.save(gradeMap, request_user_id);
	}

	public void update(JSONObject jsonObject) {
		Grade gradeMap = jsonObject.toJavaObject(Grade.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		gradeDao.update(gradeMap, request_user_id);
	}

	public void delete(JSONObject jsonObject) {
		Grade gradeMap = jsonObject.toJavaObject(Grade.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		gradeDao.delete(gradeMap.getGrade_id(), request_user_id);
	}

}
