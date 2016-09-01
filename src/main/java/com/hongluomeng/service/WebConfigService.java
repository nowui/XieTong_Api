package com.hongluomeng.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.hongluomeng.common.Const;
import com.hongluomeng.common.Utility;
import com.hongluomeng.dao.WebConfigDao;
import com.hongluomeng.model.WebConfig;

public class WebConfigService {

	private WebConfigDao webConfigDao = new WebConfigDao();

	public Integer count(JSONObject jsonObject) {
		//Web_config member_levelMap = jsonObject.toJavaObject(Web_config.class);

		return webConfigDao.count();
	}

	public List<WebConfig> list(JSONObject jsonObject) {
		//Web_config member_levelMap = jsonObject.toJavaObject(Web_config.class);

		return webConfigDao.list(Utility.getStarNumber(jsonObject), Utility.getEndNumber(jsonObject));
	}

	public List<WebConfig> listByUser_id(String user_id) {
		return webConfigDao.list(0, 0);
	}

	public WebConfig find(JSONObject jsonObject) {
		WebConfig member_levelMap = jsonObject.toJavaObject(WebConfig.class);

		WebConfig member_level = webConfigDao.findByWeb_config_id(member_levelMap.getWeb_config_id());

		return member_level;
	}

	public void save(JSONObject jsonObject) {
		WebConfig member_levelMap = jsonObject.toJavaObject(WebConfig.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		webConfigDao.save(member_levelMap, request_user_id);
	}

	public void update(JSONObject jsonObject) {
		WebConfig member_levelMap = jsonObject.toJavaObject(WebConfig.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		webConfigDao.update(member_levelMap, request_user_id);
	}

	public void delete(JSONObject jsonObject) {
		WebConfig member_levelMap = jsonObject.toJavaObject(WebConfig.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		webConfigDao.delete(member_levelMap.getWeb_config_id(), request_user_id);
	}

}
