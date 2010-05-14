/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2010-3-3 - 上午11:12:52
 */
package com.rensea.message.server.cache;

import java.util.List;

import com.sirius.core.cache.Cacheable;
import com.sirius.core.cache.KeySupport;

/**
 * @since 2010-3-3
 * @author pippo
 */
public class UserFollowerCache implements Cacheable<List<String>> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4213536165412219769L;

	public UserFollowerCache(List<String> follower) {
		this.follower = follower;
	}

	private List<String> follower;

	@Override
	public List<String> getCachedObj() {
		return this.follower;
	}

	public static class UserFollowerCacheKey implements KeySupport {

		public UserFollowerCacheKey(String userId) {
			this.userId = userId;
		}

		private String userId;

		@Override
		public String getKey() {
			return this.userId;
		}

		@Override
		public String getRegion() {
			return "com.rensea.message.server.user.follower";
		}

	}

}
