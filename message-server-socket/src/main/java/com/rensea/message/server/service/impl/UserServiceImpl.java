/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-11-16 - 下午04:36:55
 */
package com.rensea.message.server.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rensea.core.model.AccountProfile;
import com.rensea.core.model.ExtendedUser;
import com.rensea.message.server.service.UserService;
import com.rensea.message.spi.XMemcachedOperation;
import com.rensea.message.spi.queue.QueueConfiguration;
import com.rensea.message.spi.queue.XMemcachedQueue;
import com.sirius.core.utils.ArrayUtils;
import com.sirius.core.utils.StringUtils;

/**
 * @since 2009-11-16
 * @author pippo
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "cachedOperation")
	private XMemcachedOperation memcachedOperation;

	@Override
	public List<String> getFollower(String userId) {
		if (StringUtils.isBlank(userId)) {
			return Collections.emptyList();
		}
		@SuppressWarnings("unchecked")
		List<Long> follower = (List<Long>) this.memcachedOperation.get(String.format("followers_%s", userId));

		if (follower == null || follower.isEmpty()) {
			return Collections.emptyList();
		}

		List<String> _follower = new ArrayList<String>();
		for (Long id : follower) {
			_follower.add(id + "");
		}
		return _follower;
	}

	@Override
	public List<String> getFollowing(String userId) {
		if (StringUtils.isBlank(userId)) {
			return Collections.emptyList();
		}

		@SuppressWarnings("unchecked")
		List<Long> following = (List<Long>) this.memcachedOperation.get(String.format("followings_%s", userId));

		if (following == null || following.isEmpty()) {
			return Collections.emptyList();
		}

		List<String> _following = new ArrayList<String>();
		for (Long id : following) {
			_following.add(id + "");
		}
		return _following;
	}

	@Override
	public ExtendedUser getUser(String userId) {
		if (StringUtils.isBlank(userId)) {
			return null;
		}
		return this.memcachedOperation.get(String.format("account_%s", userId));
	}

	@Override
	public AccountProfile getAccountProfile(String userId) {
		if (StringUtils.isBlank(userId)) {
			return null;
		}
		return this.memcachedOperation.get(String.format("account_profile_entity_%s", userId));
	}

	@Override
	public String getUserTags(String userId) {
		ExtendedUser user = this.getUser(userId);
		if (user == null) {
			return null;
		}

		String userTags = user.getLabels();
		if (StringUtils.isBlank(userTags)) {
			return null;
		}

		AccountProfile profile = this.getAccountProfile(userId);
		if (profile != null) {
			if (StringUtils.isNotBlank(profile.getLcob())) {
				userTags += "," + profile.getLcob();
			}
			if (StringUtils.isNotBlank(profile.getLcol())) {
				userTags += "," + profile.getLcol();
			}
		}
		return userTags;
	}

	public static void main(String[] args) {
		QueueConfiguration configuration = new QueueConfiguration();
		configuration.setQueueServers("192.168.1.202:11911");
		XMemcachedQueue queue = new XMemcachedQueue();
		queue.setOpTimeout(1000);
		queue.setConnectionPoolSize(1);
		queue.setConfiguration(configuration);

		@SuppressWarnings("unchecked")
		List<Long> following = (List<Long>) queue.poll(String.format("followings_%s", 688));
		System.out.println(ArrayUtils.toString(following));

		@SuppressWarnings("unchecked")
		List<Long> follower = (List<Long>) queue.poll(String.format("followers_%s", 688));
		System.out.println(ArrayUtils.toString(follower));
	}

}
