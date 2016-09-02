package com.hongluomeng.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.hongluomeng.common.Utility;
import com.hongluomeng.model.WebConfig;

public class WebConfigDao {

	private Integer count(WebConfig webConfig) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM " + WebConfig.KEY_WEB_CONFIG + " ");

		Boolean isExit = false;

		if(isExit) {
			sql.append("AND ");
		} else {
			sql.append("WHERE ");
		}
		sql.append(WebConfig.KEY_WEB_CONFIG_STATUS + " = 1 ");

		Number count = Db.queryFirst(sql.toString(), parameterList.toArray());
		return count.intValue();
	}

	public Integer count() {
		WebConfig webConfig = new WebConfig();

		return count(webConfig);
	}

	private List<WebConfig> list(WebConfig webConfig, Integer m, Integer n) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT * FROM " + WebConfig.KEY_WEB_CONFIG + " ");

		Boolean isExit = false;

		if(isExit) {
			sql.append("AND ");
		} else {
			sql.append("WHERE ");
		}
		sql.append(WebConfig.KEY_WEB_CONFIG_STATUS + " = 1 ");

		sql.append("ORDER BY " + WebConfig.KEY_WEB_CONFIG_CREATE_TIME + " DESC ");

		if (n > 0) {
			sql.append("LIMIT ?, ? ");
			parameterList.add(m);
			parameterList.add(n);
		}

		List<WebConfig> webConfigList = webConfig.find(sql.toString(), parameterList.toArray());
		return webConfigList;
	}

	public List<WebConfig> list(Integer m, Integer n) {
		WebConfig webConfig = new WebConfig();

		return list(webConfig, m, n);
	}

	private WebConfig find(WebConfig webConfig) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT * FROM " + WebConfig.KEY_WEB_CONFIG + " ");

		Boolean isExit = false;

		if (! Utility.isNullOrEmpty(webConfig.getWeb_config_id())) {
			if(isExit) {
				sql.append(" AND ");
			} else {
				sql.append(" WHERE ");
			}
			sql.append(WebConfig.KEY_WEB_CONFIG_ID + " = ? ");
			parameterList.add(webConfig.getWeb_config_id());

			isExit = true;
		}

		if(isExit) {
			sql.append("AND ");
		} else {
			sql.append("WHERE ");
		}
		sql.append(WebConfig.KEY_WEB_CONFIG_STATUS + " = 1 ");

		if(! isExit) {
			return null;
		}

		List<WebConfig> webConfigList = webConfig.find(sql.toString(), parameterList.toArray());
		if(webConfigList.size() == 0) {
			return null;
		} else {
			return webConfigList.get(0);
		}
	}

	public WebConfig findByWeb_config_id(String webConfig_id) {
		WebConfig webConfig = new WebConfig();
		webConfig.setWeb_config_id(webConfig_id);

		return find(webConfig);
	}

	public void save(WebConfig webConfig, String request_user_id) {
		webConfig.setWeb_config_id(Utility.getUUID());
		webConfig.setWeb_config_create_user_id(request_user_id);
		webConfig.setWeb_config_create_time(new Date());
		webConfig.setWeb_config_update_user_id(request_user_id);
		webConfig.setWeb_config_update_time(new Date());
		webConfig.setWeb_config_status(true);

		webConfig.save();
	}

	public void update(WebConfig webConfig, String request_user_id) {
		webConfig.remove(WebConfig.KEY_WEB_CONFIG_CREATE_USER_ID);
		webConfig.remove(WebConfig.KEY_WEB_CONFIG_CREATE_TIME);
		webConfig.setWeb_config_update_user_id(request_user_id);
		webConfig.setWeb_config_update_time(new Date());

		webConfig.update();
	}

	public void delete(String webConfig_id, String request_user_id) {
		WebConfig webConfig = new WebConfig();
		webConfig.setWeb_config_id(webConfig_id);
		webConfig.setWeb_config_update_user_id(request_user_id);
		webConfig.setWeb_config_update_time(new Date());
		webConfig.setWeb_config_status(false);

		webConfig.update();
	}

}
