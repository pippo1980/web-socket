/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-11-16 - 下午04:36:10
 */
package com.rensea.message.server.service;

import java.util.List;

import com.rensea.core.model.AccountProfile;
import com.rensea.core.model.ExtendedUser;

/**
 * @since 2009-11-16
 * @author pippo
 */
public interface UserService {

	/**
	 * @param userId
	 * @return 跟随我的
	 */
	List<String> getFollower(String userId);

	/**
	 * @param userId
	 * @return 我跟随的
	 */
	List<String> getFollowing(String userId);

	/**
	 * @param userId
	 * @return 用户信息
	 */
	ExtendedUser getUser(String userId);

	AccountProfile getAccountProfile(String userId);

	String getUserTags(String userId);
}
