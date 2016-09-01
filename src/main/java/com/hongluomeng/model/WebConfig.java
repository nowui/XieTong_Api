package com.hongluomeng.model;

import java.util.Date;

import com.hongluomeng.common.Utility;
import com.jfinal.plugin.activerecord.Model;

public class WebConfig extends Model<WebConfig> {

	private static final long serialVersionUID = 1L;

	public static final String KEY_WEB_CONFIG = "web_config";
	public static final String KEY_WEB_CONFIG_ID = "web_config_id";
	public static final String KEY_WEB_CONFIG_APPLY_START_TIME= "web_config_apply_start_time";
	public static final String KEY_WEB_CONFIG_APPLY_END_TIME = "web_config_apply_end_time";
	public static final String KEY_WEB_CONFIG_CREATE_USER_ID = "web_config_create_user_id";
	public static final String KEY_WEB_CONFIG_CREATE_TIME = "web_config_create_time";
	public static final String KEY_WEB_CONFIG_UPDATE_USER_ID = "web_config_update_user_id";
	public static final String KEY_WEB_CONFIG_UPDATE_TIME = "web_config_update_time";
	public static final String KEY_WEB_CONFIG_STATUS = "web_config_status";

	public String getWeb_config_id() {
		return getStr(KEY_WEB_CONFIG_ID);
	}

	public void setWeb_config_id(String web_config_id) {
		set(KEY_WEB_CONFIG_ID, web_config_id);
	}

	public String getWeb_config_apply_start_time() {
		return getStr(KEY_WEB_CONFIG_APPLY_START_TIME);
	}

	public void setWeb_config_apply_start_time(String web_config_apply_start_time) {
		set(KEY_WEB_CONFIG_APPLY_START_TIME, web_config_apply_start_time);
	}

	public String getWeb_config_apply_end_time() {
		return getStr(KEY_WEB_CONFIG_APPLY_END_TIME);
	}

	public void setWeb_config_apply_end_time(String web_config_apply_end_time) {
		set(KEY_WEB_CONFIG_APPLY_END_TIME, web_config_apply_end_time);
	}

	public void setWeb_config_create_user_id(String web_config_create_user_id) {
		set(KEY_WEB_CONFIG_CREATE_USER_ID, web_config_create_user_id);
	}

	public void setWeb_config_create_time(Date web_config_create_time) {
		set(KEY_WEB_CONFIG_CREATE_TIME, web_config_create_time);
	}

	public void setWeb_config_update_user_id(String web_config_update_user_id) {
		set(KEY_WEB_CONFIG_UPDATE_USER_ID, web_config_update_user_id);
	}

	public void setWeb_config_update_time(Date web_config_update_time) {
		set(KEY_WEB_CONFIG_UPDATE_TIME, web_config_update_time);
	}

	public Boolean getWeb_config_status() {
		return getBoolean(KEY_WEB_CONFIG_STATUS);
	}

	public void setWeb_config_status(Boolean web_config_status) {
		set(KEY_WEB_CONFIG_STATUS, web_config_status);
	}

}
