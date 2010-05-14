/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2010-3-19 - 下午02:14:31
 */
package com.rensea.message.server.service.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.rensea.message.server.service.TagService;
import com.rensea.message.spi.XMemcachedOperation;
import com.sirius.core.utils.StringUtils;

/**
 * @since 2010-3-19
 * @author pippo
 */
@Service("tagService")
public class TagServiceImpl implements TagService {

	@Resource(name = "cachedOperation")
	private XMemcachedOperation memcachedOperation;

	@Override
	public List<Long> getTagFollowing(String tagName) {
		if (StringUtils.isBlank(tagName)) {
			return Collections.emptyList();
		}
		List<Long> following = this.memcachedOperation.get(String.format("tag_following_users_%s", tagName));
		return CollectionUtils.isEmpty(following) ? Collections.<Long> emptyList() : following;
	}

}
