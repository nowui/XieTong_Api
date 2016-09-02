package com.hongluomeng.validator;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.hongluomeng.common.Const;
import com.hongluomeng.common.Utility;
import com.hongluomeng.model.Course;
import com.hongluomeng.type.CodeEnum;

public class CourseValidator extends Validator {

	protected void validate(Controller controller) {
		String actionKey = getActionKey();

		JSONObject jsonObject = controller.getAttr(Const.KEY_REQUEST);

		Boolean isExit = false;

		String message = "";

		Course course = jsonObject.toJavaObject(Course.class);

		if(actionKey.equals(Const.URL_COURSE_LIST)) {
			isExit = true;

			message += Utility.checkPageAndLimit(jsonObject);
		} else if(actionKey.equals(Const.URL_COURSE_LIST_2)) {
			isExit = true;
		} else if(actionKey.equals(Const.URL_COURSE_FIND)) {
			isExit = true;
		} else if(actionKey.equals(Const.URL_COURSE_FIND_2)) {
			isExit = true;

			if(Utility.isNullOrEmpty(course.getCourse_id())) {
				message += "编号为空";
				message += Const.LINE_FEED;
			}
		} else if(actionKey.equals(Const.URL_COURSE_SAVE) || actionKey.equals(Const.URL_COURSE_UPDATE)) {
			isExit = true;

			if(actionKey.equals(Const.URL_COURSE_UPDATE) && Utility.isNullOrEmpty(course.getCourse_id())) {
				message += "编号为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(course.getCourse_grade())) {
				message += "上课班级为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(course.getCourse_teacher())) {
				message += "上课老师为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(course.getCourse_name())) {
				message += "名称为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(course.getCourse_class())) {
				message += "上课时间为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(course.getCourse_apply_limit())) {
				message += "限制人数为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNull(course.getCourse_address())) {
				message += "上课地点为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNull(course.getCourse_remark())) {
				message += "自备材料为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNull(course.getCourse_content())) {
				message += "课程介绍为空";
				message += Const.LINE_FEED;
			}
		} else if(actionKey.equals(Const.URL_COURSE_DELETE)) {
			isExit = true;

			if(Utility.isNullOrEmpty(course.getCourse_id())) {
				message += "编号为空";
				message += Const.LINE_FEED;
			}
		} else if(actionKey.equals(Const.URL_COURSE_APPLY)) {
			isExit = true;

			if(Utility.isNullOrEmpty(course.getCourse_id())) {
				message += "编号为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(course.getIsApply())) {
				message += "申请动作为空";
				message += Const.LINE_FEED;
			}
		} else if(actionKey.equals(Const.URL_COURSE_HISTORY)) {
			isExit = true;

			if(Utility.isNullOrEmpty(course.getCourse_id())) {
				message += "编号为空";
				message += Const.LINE_FEED;
			}
		} else if(actionKey.equals(Const.URL_COURSE_EXPORT)) {
			isExit = true;

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
