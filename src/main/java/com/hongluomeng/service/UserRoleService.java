package com.hongluomeng.service;

import java.util.List;

import com.hongluomeng.dao.UserRoleDao;
import com.hongluomeng.model.UserRole;

public class UserRoleService {

	private UserRoleDao userRoleDao = new UserRoleDao();
	private CacheService cacheService = new CacheService();

	public List<UserRole> listByUser_idAndUser_type(String user_id, String user_type) {
		return userRoleDao.listByUser_idAndUser_type(user_id, user_type);
	}

	public void save(List<UserRole> userRoleList, String user_id, String user_type) {
		userRoleDao.delete(userRoleList, user_id, user_type);

		userRoleDao.save(userRoleList, user_id);

		cacheService.removeOperationListByUser_id(user_id);
	}

}
