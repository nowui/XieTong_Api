package com.hongluomeng.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hongluomeng.common.Const;
import com.hongluomeng.common.Utility;
import com.hongluomeng.dao.StudentDao;
import com.hongluomeng.model.Grade;
import com.hongluomeng.model.Student;
import com.hongluomeng.model.User;
import com.hongluomeng.model.UserRole;
import com.hongluomeng.type.UserEnum;

public class StudentService {

	private StudentDao studentDao = new StudentDao();
	private UserService userService = new UserService();
	private RoleService roleService = new RoleService();
	private GradeService gradeService = new GradeService();
	private AuthorizationService authorizationService = new AuthorizationService();

	public Integer count(JSONObject jsonObject) {
		//Student studentMap = jsonObject.toJavaObject(Student.class);

		return studentDao.count();
	}

	public List<Student> list(JSONObject jsonObject) {
		//Student studentMap = jsonObject.toJavaObject(Student.class);

		return studentDao.list(Utility.getStarNumber(jsonObject), Utility.getEndNumber(jsonObject));
	}

	public Student find(JSONObject jsonObject) {
		Student studentMap = jsonObject.toJavaObject(Student.class);

		Student student;

		if (Utility.isNullOrEmpty(studentMap.getStudent_id())) {
			student = new Student();
		} else {
			student = studentDao.findByStudent_id(studentMap.getStudent_id());

			User user = userService.findByUser_id(student.getUser_id());

			student.setUser_account(user.getUser_account());
		}

		List<Grade> gradeList = gradeService.listAll();
		student.setGradeList(gradeList);

		return student;
	}

	public Student findByUser_id(String user_id) {
		return studentDao.findByUser_id(user_id);
	}

	public void save(JSONObject jsonObject) {
		Student studentMap = jsonObject.toJavaObject(Student.class);

		User userMap = jsonObject.toJavaObject(User.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		String user_id = userService.saveByAccount(userMap.getUser_account(), userMap.getUser_password(), UserEnum.STUDENT.getKey(), request_user_id);

		studentMap.setUser_id(user_id);

		studentDao.save(studentMap, request_user_id);

		userService.updateObject_idByUser_id(studentMap.getStudent_id(), user_id);
	}

	public void update(JSONObject jsonObject) {
		Student studentMap = jsonObject.toJavaObject(Student.class);

		User userMap = jsonObject.toJavaObject(User.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		studentDao.update(studentMap, request_user_id);

		userService.updateUser_account(jsonObject);

		userService.updateUser_passwordByUser_id(userMap.getUser_id(), userMap.getUser_password(), request_user_id);
	}

	public void delete(JSONObject jsonObject) {
		Student studentMap = jsonObject.toJavaObject(Student.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		studentDao.delete(studentMap.getStudent_id(), request_user_id);

		userService.deleteByObject_id(studentMap.getStudent_id(), request_user_id);
	}

	public List<Map<String, Object>> listRole(JSONObject jsonObject) {
		Student studentMap = jsonObject.toJavaObject(Student.class);

		List<Map<String, Object>> list  = roleService.listByUser_idAndUser_type(studentMap.getUser_id(), UserEnum.STUDENT.getKey());

        return list;
	}

	public void updateRole(JSONObject jsonObject) {
		Student studentMap = jsonObject.toJavaObject(Student.class);
		JSONArray jsonArray = jsonObject.getJSONArray(UserRole.KEY_USER_ROLE);

		List<UserRole> userRoleList = new ArrayList<UserRole>();

		for(int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = jsonArray.getJSONObject(i);

			UserRole userRole = object.toJavaObject(UserRole.class);
			userRole.setUser_id(studentMap.getUser_id());
			userRole.setUser_type(UserEnum.STUDENT.getKey());

			userRoleList.add(userRole);
		}

		roleService.updateUserRole(userRoleList, studentMap.getUser_id(), UserEnum.STUDENT.getKey());
	}

	public Map<String, Object> login(JSONObject jsonObject) {
		User userMap = jsonObject.toJavaObject(User.class);

		User user = userService.loginByUser_accountAndUser_password(userMap.getUser_account(), userMap.getUser_password());

		if(user == null) {
			return null;
		} else {
			Map<String, Object> resultMap = new HashMap<String, Object>();

			String token = authorizationService.saveByUser_id(user.getUser_id());

			Student student = studentDao.findByUser_id(user.getUser_id());

			resultMap.put(Const.KEY_TOKEN, token);
			resultMap.put(Student.KEY_STUDENT_NAME, student.getStudent_name());

			return resultMap;
		}
	}

}
