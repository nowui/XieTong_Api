package com.hongluomeng.controller;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.hongluomeng.common.Const;
import com.hongluomeng.common.Utility;
import com.hongluomeng.model.Teacher;
import com.hongluomeng.service.TeacherService;
import com.hongluomeng.type.CodeEnum;
import com.hongluomeng.validator.TeacherValidator;

public class TeacherController extends BaseController {

	private TeacherService teacherService = new TeacherService();

	@Before(TeacherValidator.class)
	@ActionKey(Const.URL_TEACHER_LIST)
	public void list() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		List<Teacher> teacherList = teacherService.list(jsonObject);

		Integer count = teacherService.count(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", count, teacherList));
    }

	@Before(TeacherValidator.class)
	@ActionKey(Const.URL_TEACHER_FIND)
	public void find() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		Teacher teacher = teacherService.find(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", teacher));
    }

	@Before(TeacherValidator.class)
	@ActionKey(Const.URL_TEACHER_SAVE)
	public void save() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		teacherService.save(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(TeacherValidator.class)
	@ActionKey(Const.URL_TEACHER_UPDATE)
	public void update() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		teacherService.update(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(TeacherValidator.class)
	@ActionKey(Const.URL_TEACHER_DELETE)
	public void delete() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		teacherService.delete(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(TeacherValidator.class)
	@ActionKey(Const.URL_TEACHER_LOGIN)
	public void login() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		Map<String, Object> map = teacherService.login(jsonObject);

		if (map == null) {
	        renderJson(Utility.setResponse(CodeEnum.CODE_400, "用户名或者密码不正确", null));
		} else {
	        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", map));
		}
	}

}
