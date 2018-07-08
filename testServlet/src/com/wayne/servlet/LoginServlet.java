package com.wayne.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");

		String password = req.getParameter("password");
		resp.setContentType("text/html;charset=utf-8");
		// 浏览器默认的编码是iso-8859-1转换成utf-8，如果不转换，会产生乱码。
		String name = new String(username.getBytes("iso-8859-1"), "utf-8");
		String pass = new String(password.getBytes("iso-8859-1"), "utf-8");
		System.out.println("username" + name);
		System.out.println("password" + pass);
		// 如果登陆名是张三，密码是123，登陆成功，否则登陆失败。
		if ("张三".equals(name) && pass.equals("123")) {
			resp.getWriter().print("登录成功");
		} else
			resp.getWriter().write("用户名或密码错误");

	}
}
