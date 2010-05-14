/**
 * project : flashpush-server
 * user created : pippo
 * date created : 2009-8-13 - 下午02:42:05
 */
package com.rensea.message.server.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rensea.message.server.AuthenticationManager;
import com.sirius.component.spring.BeanLocator;
import com.sirius.core.utils.StringUtils;

/**
 * @since 2009-8-13
 * @author pippo
 */
public class AuthenicationServlet extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4812451334473621535L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		String userId = req.getParameter("userId");
		if (StringUtils.isBlank(userId))
			return;
		String ticket = BeanLocator.getBean(AuthenticationManager.class).applyTicket(userId);
//		req.setAttribute("ticket", ticket);
//		req.getRequestDispatcher("index.jsp").forward(req, resp);
		resp.getOutputStream().write(ticket.getBytes());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		this.doPost(req, resp);
	}

}
