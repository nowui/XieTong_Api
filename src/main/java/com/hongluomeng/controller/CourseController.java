package com.hongluomeng.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.hongluomeng.common.Const;
import com.hongluomeng.common.Utility;
import com.hongluomeng.model.Course;
import com.hongluomeng.model.CourseApply;
import com.hongluomeng.model.CourseApplyHistory;
import com.hongluomeng.service.CourseService;
import com.hongluomeng.type.CodeEnum;
import com.hongluomeng.validator.CourseValidator;

public class CourseController extends BaseController {

	private CourseService courseService = new CourseService();

	@Before(CourseValidator.class)
	@ActionKey(Const.URL_COURSE_LIST)
	public void list() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		List<Course> courseList = courseService.list(jsonObject);

		Integer count = courseService.count(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", count, courseList));
    }

	@Before(CourseValidator.class)
	@ActionKey(Const.URL_COURSE_LIST_2)
	public void list2() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		List<Course> courseList = courseService.listForApply(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", courseList));
    }

	@Before(CourseValidator.class)
	@ActionKey(Const.URL_COURSE_FIND)
	public void find() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		Course course = courseService.find(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", course));
    }

	@Before(CourseValidator.class)
	@ActionKey(Const.URL_COURSE_FIND_2)
	public void find2() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		Course course = courseService.findForApply(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", course));
    }

	@Before(CourseValidator.class)
	@ActionKey(Const.URL_COURSE_SAVE)
	public void save() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		courseService.save(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(CourseValidator.class)
	@ActionKey(Const.URL_COURSE_UPDATE)
	public void update() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		courseService.update(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(CourseValidator.class)
	@ActionKey(Const.URL_COURSE_DELETE)
	public void delete() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		courseService.delete(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(CourseValidator.class)
	@ActionKey(Const.URL_COURSE_APPLY)
	public void apply() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		courseService.apply(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(CourseValidator.class)
	@ActionKey(Const.URL_COURSE_HISTORY)
	public void history() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		List<CourseApplyHistory> courseApplyHistoryList = courseService.history(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", courseApplyHistoryList));
	}

	@Before(CourseValidator.class)
	@ActionKey(Const.URL_COURSE_EXPORT)
	public void export() {
        render(courseService.export());
	}

	@Before(CourseValidator.class)
	@ActionKey(Const.URL_COURSE_MY)
	public void my() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		List<CourseApply> courseApplyList = courseService.my(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", courseApplyList));
	}

}
