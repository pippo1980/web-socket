/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2010-3-3 - 上午11:12:52
 */
package com.rensea.message.server.cache;

import com.sirius.core.cache.Cacheable;
import com.sirius.core.cache.KeySupport;

/**
 * @since 2010-3-3
 * @author pippo
 */
public class UserTagsCache implements Cacheable<String> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4213536165412219769L;

	public UserTagsCache(String userTags) {
		this.userTags = userTags;
	}

	private String userTags;

	@Override
	public String getCachedObj() {
		return this.userTags;
	}

	public static class UserTagsCacheKey implements KeySupport {

		public UserTagsCacheKey(String userId) {
			this.userId = userId;
		}

		private String userId;

		@Override
		public String getKey() {
			return this.userId;
		}

		@Override
		public String getRegion() {
			return "com.rensea.message.server.user.tags";
		}

	}

}
