/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-11-30 - 下午04:09:54
 */
package com.rensea.message.server.aspect;

import java.util.List;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.rensea.message.server.cache.TagFollowingCache;
import com.rensea.message.server.cache.TagFollowingCache.TagFollowingCacheKey;
import com.sirius.core.cache.CacheManager;

/**
 * @since 2009-11-30
 * @author pippo
 */
@Component
@Aspect
public class TagServiceAspect implements Ordered {

	private static Logger logger = LoggerFactory.getLogger(TagServiceAspect.class);

	@Resource
	private CacheManager cacheManager;

	@SuppressWarnings("unchecked")
	@Around("execution(* com.rensea.message.server.service.TagService.getFollowing(..))")
	public Object getFollowing(ProceedingJoinPoint pjp) throws Throwable {
		String tagName = (String) pjp.getArgs()[0];
		TagFollowingCacheKey cacheKey = new TagFollowingCacheKey(tagName);
		TagFollowingCache cache;
		try {
			cache = this.cacheManager.get(cacheKey);
			if (cache != null) {
				if (logger.isDebugEnabled()) {
					logger.debug("find tag following cache ignore get operation");
				}
				return cache.getCachedObj();
			}
		} catch (Exception e) {
			logger.error("get tag following cache due to error", e);
		}

		List<Long> followings = (List<Long>) pjp.proceed();

		try {
			if (!followings.isEmpty()) {
				cache = new TagFollowingCache(followings);
				this.cacheManager.cache(cacheKey, cache);
			}
		} catch (Exception e) {
			logger.error("set tag following cache due to error", e);
		}

		return followings;
	}

	@Override
	public int getOrder() {
		return 49;
	}

}
