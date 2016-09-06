package com.hongluomeng.controller;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.upload.UploadFile;
import com.hongluomeng.common.Const;
import com.hongluomeng.common.Utility;
import com.hongluomeng.model.Student;
import com.hongluomeng.service.StudentService;
import com.hongluomeng.type.CodeEnum;
import com.hongluomeng.validator.StudentValidator;

public class StudentController extends BaseController {

	private StudentService studentService = new StudentService();

	@Before(StudentValidator.class)
	@ActionKey(Const.URL_STUDENT_LIST)
	public void list() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		Map<String, Object> resultMap = studentService.list(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", resultMap));
    }

	@Before(StudentValidator.class)
	@ActionKey(Const.URL_STUDENT_FIND)
	public void find() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		Student student = studentService.find(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", student));
    }

	@Before(StudentValidator.class)
	@ActionKey(Const.URL_STUDENT_SAVE)
	public void save() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		studentService.save(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(StudentValidator.class)
	@ActionKey(Const.URL_STUDENT_UPDATE)
	public void update() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		studentService.update(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(StudentValidator.class)
	@ActionKey(Const.URL_STUDENT_DELETE)
	public void delete() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		studentService.delete(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(StudentValidator.class)
	@ActionKey(Const.URL_STUDENT_LOGIN)
	public void login() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		Map<String, Object> map = studentService.login(jsonObject);

		if (map == null) {
	        renderJson(Utility.setResponse(CodeEnum.CODE_400, "用户名或者密码不正确", null));
		} else {
	        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", map));
		}
	}

	@Before(StudentValidator.class)
	@ActionKey(Const.URL_STUDENT_EXPORT)
	public void export() {
        render(studentService.export());
	}

	@Before(StudentValidator.class)
	@ActionKey(Const.URL_STUDENT_UPLOAD)
	public void upload() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		UploadFile uploadFile = getFile("file", request_user_id, 1024 * 1024);

		studentService.upload(uploadFile, request_user_id);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));

	}

	@Before(StudentValidator.class)
	@ActionKey(Const.URL_STUDENT_DELETE_2)
	public void delete2() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		studentService.delete2(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(StudentValidator.class)
	@ActionKey(Const.URL_STUDENT_PASSWORD_UPDATE)
	public void updatePassword() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		studentService.updatePassword(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

}
