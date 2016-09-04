package com.hongluomeng.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hongluomeng.common.Const;
import com.hongluomeng.common.Utility;
import com.hongluomeng.dao.TeacherDao;
import com.hongluomeng.model.Teacher;
import com.hongluomeng.model.User;
import com.hongluomeng.model.UserRole;
import com.hongluomeng.type.UserEnum;

public class TeacherService {

	private TeacherDao teacherDao = new TeacherDao();
	private UserService userService = new UserService();
	private RoleService roleService = new RoleService();
	private AuthorizationService authorizationService = new AuthorizationService();

	public Map<String, Object> list(JSONObject jsonObject) {
		//Teacher teacherMap = jsonObject.toJavaObject(Teacher.class);

		Integer count = teacherDao.count();

		List<Teacher> teacherList = teacherDao.list(Utility.getStarNumber(jsonObject), Utility.getEndNumber(jsonObject));

		Map<String, Object> resultMap = Utility.setResultMap(count, teacherList);

		return resultMap;
	}

	public List<Teacher> ListAll() {
		return teacherDao.list(0, 0);
	}

	public Teacher find(JSONObject jsonObject) {
		Teacher teacherMap = jsonObject.toJavaObject(Teacher.class);

		Teacher teacher = teacherDao.findByTeacher_id(teacherMap.getTeacher_id());

		User user = userService.findByUser_id(teacher.getUser_id());

		teacher.setUser_account(user.getUser_account());

		return teacher;
	}

	public Teacher findByTeacher_id(String teacher_id) {
		return teacherDao.findByTeacher_id(teacher_id);
	}

	public void save(JSONObject jsonObject) {
		Teacher teacherMap = jsonObject.toJavaObject(Teacher.class);

		User userMap = jsonObject.toJavaObject(User.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		String user_id = userService.saveByAccount(userMap.getUser_account(), userMap.getUser_password(), UserEnum.TEACHER.getKey(), request_user_id);

		teacherMap.setUser_id(user_id);

		teacherDao.save(teacherMap, request_user_id);

		userService.updateObject_idByUser_id(teacherMap.getTeacher_id(), user_id);
	}

	public void update(JSONObject jsonObject) {
		Teacher teacherMap = jsonObject.toJavaObject(Teacher.class);

		User userMap = jsonObject.toJavaObject(User.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		teacherDao.update(teacherMap, request_user_id);

		userService.updateUser_account(jsonObject);

		userService.updateUser_passwordByUser_id(userMap.getUser_id(), userMap.getUser_password(), request_user_id);
	}

	public void delete(JSONObject jsonObject) {
		Teacher teacherMap = jsonObject.toJavaObject(Teacher.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		teacherDao.delete(teacherMap.getTeacher_id(), request_user_id);

		userService.deleteByObject_id(teacherMap.getTeacher_id(), request_user_id);
	}

	public List<Map<String, Object>> listRole(JSONObject jsonObject) {
		Teacher teacherMap = jsonObject.toJavaObject(Teacher.class);

		List<Map<String, Object>> list  = roleService.listByUser_idAndUser_type(teacherMap.getUser_id(), UserEnum.TEACHER.getKey());

        return list;
	}

	public void updateRole(JSONObject jsonObject) {
		Teacher teacherMap = jsonObject.toJavaObject(Teacher.class);
		JSONArray jsonArray = jsonObject.getJSONArray(UserRole.KEY_USER_ROLE);

		List<UserRole> userRoleList = new ArrayList<UserRole>();

		for(int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = jsonArray.getJSONObject(i);

			UserRole userRole = object.toJavaObject(UserRole.class);
			userRole.setUser_id(teacherMap.getUser_id());
			userRole.setUser_type(UserEnum.TEACHER.getKey());

			userRoleList.add(userRole);
		}

		roleService.updateUserRole(userRoleList, teacherMap.getUser_id(), UserEnum.TEACHER.getKey());
	}

	public Map<String, Object> login(JSONObject jsonObject) {
		User userMap = jsonObject.toJavaObject(User.class);

		User user =  userService.loginByUser_accountAndUser_password(userMap.getUser_account(), userMap.getUser_password());

		if(user == null) {
			return null;
		} else {
			Map<String, Object> resultMap = new HashMap<String, Object>();

			String token = authorizationService.saveByUser_id(user.getUser_id());

			resultMap.put(Const.KEY_TOKEN, token);

			return resultMap;
		}
	}

}
