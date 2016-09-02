package com.hongluomeng.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.hongluomeng.common.Const;
import com.hongluomeng.common.Utility;
import com.hongluomeng.model.Grade;
import com.hongluomeng.service.GradeService;
import com.hongluomeng.type.CodeEnum;
import com.hongluomeng.validator.GradeValidator;

public class GradeController extends BaseController {

	private GradeService gradeService = new GradeService();

	@Before(GradeValidator.class)
	@ActionKey(Const.URL_GRADE_LIST)
	public void list() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		List<Grade> gradeList = gradeService.list(jsonObject);

		Integer count = gradeService.count(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", count, gradeList));
    }

	@Before(GradeValidator.class)
	@ActionKey(Const.URL_GRADE_FIND)
	public void find() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		Grade grade = gradeService.find(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", grade));
    }

	@Before(GradeValidator.class)
	@ActionKey(Const.URL_GRADE_SAVE)
	public void save() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		gradeService.save(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(GradeValidator.class)
	@ActionKey(Const.URL_GRADE_UPDATE)
	public void update() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		gradeService.update(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(GradeValidator.class)
	@ActionKey(Const.URL_GRADE_DELETE)
	public void delete() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		gradeService.delete(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(GradeValidator.class)
	@ActionKey(Const.URL_GRADE_LIST_2)
	public void list2() {
		List<Grade> gradeList = gradeService.listAll();

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", gradeList));
    }

}
