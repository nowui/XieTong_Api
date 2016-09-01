package com.hongluomeng.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.hongluomeng.common.Const;
import com.hongluomeng.common.Utility;
import com.hongluomeng.model.WebConfig;
import com.hongluomeng.service.WebConfigService;
import com.hongluomeng.type.CodeEnum;
import com.hongluomeng.validator.WebConfigValidator;

public class WebConfigController extends BaseController {

	private WebConfigService webConfigService = new WebConfigService();

	@Before(WebConfigValidator.class)
	@ActionKey(Const.URL_WEB_CONFIG_LIST)
	public void list() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		List<WebConfig> webConfigList = webConfigService.list(jsonObject);

		Integer count = webConfigService.count(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", count, webConfigList));
    }

	@Before(WebConfigValidator.class)
	@ActionKey(Const.URL_WEB_CONFIG_FIND)
	public void find() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		WebConfig webConfig = webConfigService.find(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", webConfig));
    }

	@Before(WebConfigValidator.class)
	@ActionKey(Const.URL_WEB_CONFIG_SAVE)
	public void save() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		webConfigService.save(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(WebConfigValidator.class)
	@ActionKey(Const.URL_WEB_CONFIG_UPDATE)
	public void update() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		webConfigService.update(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(WebConfigValidator.class)
	@ActionKey(Const.URL_WEB_CONFIG_DELETE)
	public void delete() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		webConfigService.delete(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

}
