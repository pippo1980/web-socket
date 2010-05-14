package com.rensea.message.server.service;

import java.util.List;

public interface TagService {

	/**
	 * @param tagName
	 * @return 收藏tagName的人
	 */
	List<Long> getTagFollowing(String tagName);

}