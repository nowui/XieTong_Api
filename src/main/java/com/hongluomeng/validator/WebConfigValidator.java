package com.hongluomeng.validator;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.hongluomeng.common.Const;
import com.hongluomeng.common.Utility;
import com.hongluomeng.model.WebConfig;
import com.hongluomeng.type.CodeEnum;

public class WebConfigValidator extends Validator {

	protected void validate(Controller controller) {
		String actionKey = getActionKey();

		JSONObject jsonObject = controller.getAttr(Const.KEY_REQUEST);

		Boolean isExit = false;

		String message = "";

		WebConfig member_level = jsonObject.toJavaObject(WebConfig.class);

		if(actionKey.equals(Const.URL_WEB_CONFIG_LIST)) {
			isExit = true;

			message += Utility.checkPageAndLimit(jsonObject);
		} else if(actionKey.equals(Const.URL_WEB_CONFIG_FIND)) {
			isExit = true;

			if(Utility.isNullOrEmpty(member_level.getWeb_config_id())) {
				message += "编号为空";
				message += Const.LINE_FEED;
			}
		} else if(actionKey.equals(Const.URL_WEB_CONFIG_SAVE) || actionKey.equals(Const.URL_WEB_CONFIG_UPDATE)) {
			isExit = true;

			if(actionKey.equals(Const.URL_WEB_CONFIG_UPDATE) && Utility.isNullOrEmpty(member_level.getWeb_config_id())) {
				message += "编号为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(member_level.getWeb_config_apply_start_time())) {
				message += "申请开始时间为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(member_level.getWeb_config_apply_end_time())) {
				message += "申请结束时间为空";
				message += Const.LINE_FEED;
			}
		} else if(actionKey.equals(Const.URL_WEB_CONFIG_DELETE)) {
			isExit = true;

			if(Utility.isNullOrEmpty(member_level.getWeb_config_id())) {
				message += "编号为空";
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
