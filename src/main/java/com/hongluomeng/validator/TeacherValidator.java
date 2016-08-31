package com.hongluomeng.validator;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.hongluomeng.common.Const;
import com.hongluomeng.common.Utility;
import com.hongluomeng.model.Teacher;
import com.hongluomeng.model.User;
import com.hongluomeng.type.CodeEnum;

public class TeacherValidator extends Validator {

	protected void validate(Controller controller) {
		String actionKey = getActionKey();

		JSONObject jsonObject = controller.getAttr(Const.KEY_REQUEST);

		Boolean isExit = false;

		String message = "";

		if(actionKey.equals(Const.URL_TEACHER_LIST)) {
			isExit = true;

			message += Utility.checkPageAndLimit(jsonObject);
		} else if(actionKey.equals(Const.URL_TEACHER_FIND)) {
			isExit = true;

			Teacher teacher = jsonObject.toJavaObject(Teacher.class);

			if(Utility.isNullOrEmpty(teacher.getTeacher_id())) {
				message += "编号为空";
				message += Const.LINE_FEED;
			}
		} else if(actionKey.equals(Const.URL_TEACHER_SAVE) || actionKey.equals(Const.URL_TEACHER_UPDATE)) {
			isExit = true;

			Teacher teacher = jsonObject.toJavaObject(Teacher.class);

			User user = jsonObject.toJavaObject(User.class);

			if(actionKey.equals(Const.URL_TEACHER_UPDATE) && Utility.isNullOrEmpty(teacher.getTeacher_id())) {
				message += "编号为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(teacher.getTeacher_name())) {
				message += "名称为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(user.getUser_account())) {
				message += "帐号为空";
				message += Const.LINE_FEED;
			}

			if(actionKey.equals(Const.URL_TEACHER_SAVE) && Utility.isNullOrEmpty(user.getUser_password())) {
				message += "密码为空";
				message += Const.LINE_FEED;
			}
		} else if(actionKey.equals(Const.URL_TEACHER_DELETE)) {
			isExit = true;

			Teacher teacher = jsonObject.toJavaObject(Teacher.class);

			if(Utility.isNullOrEmpty(teacher.getTeacher_id())) {
				message += "编号为空";
				message += Const.LINE_FEED;
			}
		} else if(actionKey.equals(Const.URL_TEACHER_LOGIN)) {
			isExit = true;

			User user = jsonObject.toJavaObject(User.class);

			if(Utility.isNullOrEmpty(user.getUser_account())) {
				message += "帐号为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(user.getUser_password())) {
				message += "密码为空";
				message += Const.LINE_FEED;
			}
		}

		if (! isExit) {
	        addError(Const.KEY_MESSAGE, Const.URL_DENIED);
		}

		if (! Utility.isNullOrEmpty(message)) {
	        addError(Const.KEY_MESSAGE, message);
		}
	}

	protected void handleError(Controller c) {
		c.renderJson(Utility.setResponse(CodeEnum.CODE_400, c.getAttr(Const.KEY_MESSAGE), null));
	}

}
