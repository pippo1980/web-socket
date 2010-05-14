/**
 * project : message-server-socket
 * user created : pippo
 * date created : 2009-11-24 - 下午04:19:40
 */
package com.rensea.message.server.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @since 2009-11-24
 * @author pippo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.context.xml" })
public class UserServiceTest {

	@Resource
	private UserService userService;

	@Test
	public void testGetFollower() {
		System.out.println(this.userService.getFollower("292"));
	}

	@Test
	public void testGetFollowing() {
		System.out.println(this.userService.getFollowing("292"));
	}

	@Test
	public void testGetUser() {
		System.out.println(this.userService.getUser("292"));
	}

}
