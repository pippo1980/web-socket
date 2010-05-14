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
public class UserFollowingCache implements Cacheable<List<String>> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4213536165412219769L;

	public UserFollowingCache(List<String> following) {
		this.following = following;
	}

	private List<String> following;

	@Override
	public List<String> getCachedObj() {
		return this.following;
	}

	public static class UserFollowingCacheKey implements KeySupport {

		public UserFollowingCacheKey(String userId) {
			this.userId = userId;
		}

		private String userId;

		@Override
		public String getKey() {
			return this.userId;
		}

		@Override
		public String getRegion() {
			return "com.rensea.message.server.user.following";
		}

	}

}
