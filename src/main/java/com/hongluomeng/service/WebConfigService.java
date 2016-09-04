package com.hongluomeng.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.hongluomeng.common.Const;
import com.hongluomeng.common.Utility;
import com.hongluomeng.dao.WebConfigDao;
import com.hongluomeng.model.WebConfig;

public class WebConfigService {

	private WebConfigDao webConfigDao = new WebConfigDao();
	private CacheService cacheService = new CacheService();

	public Map<String, Object> list(JSONObject jsonObject) {
		//Web_config member_levelMap = jsonObject.toJavaObject(Web_config.class);

		Integer count = webConfigDao.count();

		List<WebConfig> webConfigList = webConfigDao.list(Utility.getStarNumber(jsonObject), Utility.getEndNumber(jsonObject));

		Map<String, Object> resultMap = Utility.setResultMap(count, webConfigList);

		return resultMap;
	}

	public List<WebConfig> listByUser_id(String user_id) {
		return webConfigDao.list(0, 0);
	}

	public WebConfig find(JSONObject jsonObject) {
		WebConfig member_levelMap = jsonObject.toJavaObject(WebConfig.class);

		WebConfig member_level = webConfigDao.findByWeb_config_id(member_levelMap.getWeb_config_id());

		return member_level;
	}

	public WebConfig find() {
		WebConfig webConfig = cacheService.getWebConfig();

		if(webConfig == null) {
			List<WebConfig> webConfigList = webConfigDao.list(0, 0);

			if(webConfigList.size() > 0) {
				webConfig = webConfigList.get(0);

				cacheService.setWebConfig(webConfig);
			}
		}

		return webConfig;
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

		cacheService.removeWebConfig();
	}

	public void delete(JSONObject jsonObject) {
		WebConfig member_levelMap = jsonObject.toJavaObject(WebConfig.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		webConfigDao.delete(member_levelMap.getWeb_config_id(), request_user_id);
	}

}
