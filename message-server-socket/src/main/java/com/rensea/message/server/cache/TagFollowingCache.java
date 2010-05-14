/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2010-3-19 - 下午02:23:13
 */
package com.rensea.message.server.cache;

import java.util.List;

import com.sirius.core.cache.Cacheable;
import com.sirius.core.cache.KeySupport;

/**
 * @since 2010-3-19
 * @author pippo
 */
public class TagFollowingCache implements Cacheable<List<Long>> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4004742554510769397L;

	public TagFollowingCache(List<Long> following) {
		this.following = following;
	}

	private List<Long> following;

	@Override
	public List<Long> getCachedObj() {
		return this.following;
	}

	public static class TagFollowingCacheKey implements KeySupport {

		public TagFollowingCacheKey(String tagName) {
			this.tagName = tagName;
		}

		private String tagName;

		@Override
		public String getKey() {
			return this.tagName;
		}

		@Override
		public String getRegion() {
			return "com.rensea.message.server.tag.following";
		}

	}

}
