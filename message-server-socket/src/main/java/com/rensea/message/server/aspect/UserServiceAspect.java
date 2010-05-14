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

import com.rensea.message.server.cache.UserFollowerCache;
import com.rensea.message.server.cache.UserFollowingCache;
import com.rensea.message.server.cache.UserTagsCache;
import com.rensea.message.server.cache.UserFollowerCache.UserFollowerCacheKey;
import com.rensea.message.server.cache.UserFollowingCache.UserFollowingCacheKey;
import com.rensea.message.server.cache.UserTagsCache.UserTagsCacheKey;
import com.sirius.core.cache.CacheManager;
import com.sirius.core.utils.StringUtils;

/**
 * @since 2009-11-30
 * @author pippo
 */
@Component
@Aspect
public class UserServiceAspect implements Ordered {

	private static Logger logger = LoggerFactory.getLogger(UserServiceAspect.class);

	@Resource
	private CacheManager cacheManager;

	@Around("execution(* com.rensea.message.server.service.UserService.getUserTags(..))")
	public Object getUserTags(ProceedingJoinPoint pjp) throws Throwable {
		String userId = (String) pjp.getArgs()[0];
		UserTagsCacheKey cacheKey = new UserTagsCacheKey(userId);
		UserTagsCache cache;
		try {
			cache = this.cacheManager.get(cacheKey);
			if (cache != null) {
				if (logger.isDebugEnabled()) {
					logger.debug("find user tag cache ignore get operation");
				}
				return cache.getCachedObj();
			}
		} catch (Exception e) {
			logger.error("get user tag cache due to error", e);
		}

		String userTags = (String) pjp.proceed();

		try {
			if (StringUtils.isNotBlank(userTags)) {
				cache = new UserTagsCache(userTags);
				this.cacheManager.cache(cacheKey, cache);
			}
		} catch (Exception e) {
			logger.error("set user tag cache due to error", e);
		}

		return userTags;
	}

	@SuppressWarnings("unchecked")
	@Around("execution(* com.rensea.message.server.service.UserService.getFollower(..))")
	public Object getFollower(ProceedingJoinPoint pjp) throws Throwable {
		String userId = (String) pjp.getArgs()[0];
		UserFollowerCacheKey cacheKey = new UserFollowerCacheKey(userId);
		UserFollowerCache cache;
		try {
			cache = this.cacheManager.get(cacheKey);
			if (cache != null) {
				if (logger.isDebugEnabled()) {
					logger.debug("find user follower cache ignore get operation");
				}
				return cache.getCachedObj();
			}
		} catch (Exception e) {
			logger.error("get user follower cache due to error", e);
		}

		List<String> followers = (List<String>) pjp.proceed();

		try {
			if (!followers.isEmpty()) {
				cache = new UserFollowerCache(followers);
				this.cacheManager.cache(cacheKey, cache);
			}
		} catch (Exception e) {
			logger.error("set user follower cache due to error", e);
		}

		return followers;
	}

	@SuppressWarnings("unchecked")
	@Around("execution(* com.rensea.message.server.service.UserService.getFollowing(..))")
	public Object getFollowing(ProceedingJoinPoint pjp) throws Throwable {
		String userId = (String) pjp.getArgs()[0];
		UserFollowingCacheKey cacheKey = new UserFollowingCacheKey(userId);
		UserFollowingCache cache;
		try {
			cache = this.cacheManager.get(cacheKey);
			if (cache != null) {
				if (logger.isDebugEnabled()) {
					logger.debug("find user following cache ignore get operation");
				}
				return cache.getCachedObj();
			}
		} catch (Exception e) {
			logger.error("get user following cache due to error", e);
		}

		List<String> followings = (List<String>) pjp.proceed();

		try {
			if (!followings.isEmpty()) {
				cache = new UserFollowingCache(followings);
				this.cacheManager.cache(cacheKey, cache);
			}
		} catch (Exception e) {
			logger.error("set user following cache due to error", e);
		}

		return followings;
	}

	@Override
	public int getOrder() {
		return 49;
	}

}
