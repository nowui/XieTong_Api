package com.hongluomeng.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.hongluomeng.common.Utility;
import com.hongluomeng.model.WebConfig;

public class WebConfigDao {

	private Integer count(WebConfig memberLevel) {
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
		WebConfig memberLevel = new WebConfig();

		return count(memberLevel);
	}

	private List<WebConfig> list(WebConfig memberLevel, Integer m, Integer n) {
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

		List<WebConfig> memberLevelList = memberLevel.find(sql.toString(), parameterList.toArray());
		return memberLevelList;
	}

	public List<WebConfig> list(Integer m, Integer n) {
		WebConfig memberLevel = new WebConfig();

		return list(memberLevel, m, n);
	}

	private WebConfig find(WebConfig memberLevel) {
		List<Object> parameterList = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("SELECT * FROM " + WebConfig.KEY_WEB_CONFIG + " ");

		Boolean isExit = false;

		if (! Utility.isNullOrEmpty(memberLevel.getWeb_config_id())) {
			if(isExit) {
				sql.append(" AND ");
			} else {
				sql.append(" WHERE ");
			}
			sql.append(WebConfig.KEY_WEB_CONFIG_ID + " = ? ");
			parameterList.add(memberLevel.getWeb_config_id());

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

		List<WebConfig> memberLevelList = memberLevel.find(sql.toString(), parameterList.toArray());
		if(memberLevelList.size() == 0) {
			return null;
		} else {
			return memberLevelList.get(0);
		}
	}

	public WebConfig findByWeb_config_id(String memberLevel_id) {
		WebConfig memberLevel = new WebConfig();
		memberLevel.setWeb_config_id(memberLevel_id);

		return find(memberLevel);
	}

	public void save(WebConfig memberLevel, String request_user_id) {
		memberLevel.setWeb_config_id(Utility.getUUID());
		memberLevel.setWeb_config_create_user_id(request_user_id);
		memberLevel.setWeb_config_create_time(new Date());
		memberLevel.setWeb_config_update_user_id(request_user_id);
		memberLevel.setWeb_config_update_time(new Date());
		memberLevel.setWeb_config_status(true);

		memberLevel.save();
	}

	public void update(WebConfig memberLevel, String request_user_id) {
		memberLevel.remove(WebConfig.KEY_WEB_CONFIG_CREATE_USER_ID);
		memberLevel.remove(WebConfig.KEY_WEB_CONFIG_CREATE_TIME);
		memberLevel.setWeb_config_update_user_id(request_user_id);
		memberLevel.setWeb_config_update_time(new Date());

		memberLevel.update();
	}

	public void delete(String memberLevel_id, String request_user_id) {
		WebConfig memberLevel = new WebConfig();
		memberLevel.setWeb_config_id(memberLevel_id);
		memberLevel.setWeb_config_update_user_id(request_user_id);
		memberLevel.setWeb_config_update_time(new Date());
		memberLevel.setWeb_config_status(false);

		memberLevel.update();
	}

}
