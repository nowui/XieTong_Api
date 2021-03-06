package com.hongluomeng.validator;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.hongluomeng.common.Const;
import com.hongluomeng.common.Utility;
import com.hongluomeng.model.Member;
import com.hongluomeng.model.Sms;
import com.hongluomeng.model.User;
import com.hongluomeng.type.CodeEnum;

public class MemberValidator extends Validator {

	protected void validate(Controller controller) {
		String actionKey = getActionKey();

		JSONObject jsonObject = controller.getAttr(Const.KEY_REQUEST);

		Boolean isExit = false;

		String message = "";

		if(actionKey.equals(Const.URL_MEMBER_LIST)) {
			isExit = true;

			message += Utility.checkPageAndLimit(jsonObject);
		} else if(actionKey.equals(Const.URL_MEMBER_FIND)) {
			isExit = true;

			Member member = jsonObject.toJavaObject(Member.class);

			if(Utility.isNullOrEmpty(member.getMember_id())) {
				message += "编号为空";
				message += Const.LINE_FEED;
			}
		} else if(actionKey.equals(Const.URL_MEMBER_DELETE)) {
			isExit = true;

			Member member = jsonObject.toJavaObject(Member.class);

			if(Utility.isNullOrEmpty(member.getMember_id())) {
				message += "编号为空";
				message += Const.LINE_FEED;
			}
		} else if(actionKey.equals(Const.URL_MEMBER_LOGIN)) {
			isExit = true;

			User user = jsonObject.toJavaObject(User.class);

			if(Utility.isNullOrEmpty(user.getUser_phone())) {
				message += "帐号为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(user.getUser_password())) {
				message += "密码为空";
				message += Const.LINE_FEED;
			}
		} else if(actionKey.equals(Const.URL_MEMBER_REGISTER) || actionKey.equals(Const.URL_MEMBER_PASSWORD_UPDATE)) {
			isExit = true;

			User user = jsonObject.toJavaObject(User.class);

			if(Utility.isNullOrEmpty(user.getUser_phone())) {
				message += "帐号为空";
				message += Const.LINE_FEED;
			} else {
				if (! Utility.isPhone(user.getUser_phone())) {
					message += "帐号不是手机号";
					message += Const.LINE_FEED;
				}
			}

			if(Utility.isNullOrEmpty(user.getUser_password())) {
				message += "密码为空";
				message += Const.LINE_FEED;
			}

			Sms sms = jsonObject.toJavaObject(Sms.class);

			if(Utility.isNullOrEmpty(sms.getSms_code())) {
				message += "验证码为空";
				message += Const.LINE_FEED;
			}
		} else if(actionKey.equals(Const.URL_MEMBER_WEIBO_OAUTH) || actionKey.equals(Const.URL_MEMBER_WEIBO_BIND)) {
			isExit = true;

			Member member = jsonObject.toJavaObject(Member.class);

			User user = jsonObject.toJavaObject(User.class);

			if(Utility.isNullOrEmpty(member.getMember_name())) {
				message += "名称为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(member.getMember_avatar())) {
				message += "头像为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(jsonObject.getString(Member.KEY_MEMBER_AVATAR + "_" + Const.UPLOAD_SMALL))) {
				message += "小头像为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(jsonObject.getString(Member.KEY_MEMBER_AVATAR + "_" + Const.UPLOAD_LARGE))) {
				message += "大头像为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(user.getWeibo_uid())) {
				message += "微博UID为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(user.getWeibo_access_token())) {
				message += "微博Access_Token为空";
				message += Const.LINE_FEED;
			}
		} else if(actionKey.equals(Const.URL_MEMBER_WECHAT_OAUTH) || actionKey.equals(Const.URL_MEMBER_WECHAT_BIND)) {
			isExit = true;

			Member member = jsonObject.toJavaObject(Member.class);

			User user = jsonObject.toJavaObject(User.class);

			if(Utility.isNullOrEmpty(member.getMember_name())) {
				message += "名称为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(member.getMember_avatar())) {
				message += "头像为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(jsonObject.getString(Member.KEY_MEMBER_AVATAR_SMALL))) {
				message += "小头像为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(jsonObject.getString(Member.KEY_MEMBER_AVATAR_LARGE))) {
				message += "大头像为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(user.getWechat_uid())) {
				message += "微信UID为空";
				message += Const.LINE_FEED;
			}

			if(Utility.isNullOrEmpty(user.getWechat_access_token())) {
				message += "微信Access_Token为空";
				message += Const.LINE_FEED;
			}
		} else if(actionKey.equals(Const.URL_MEMBER_AVATAR_UPLOAD)) {
			isExit = true;

		} else if(actionKey.equals(Const.URL_MEMBER_NAME_UPDATE)) {
			isExit = true;

			Member member = jsonObject.toJavaObject(Member.class);

			if(Utility.isNullOrEmpty(member.getMember_name())) {
				message += "姓名为空";
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
