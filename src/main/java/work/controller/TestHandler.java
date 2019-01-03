package work.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import good.bean.UserBean;

@Controller
public class TestHandler {
      
	@Autowired
	private SqlSessionFactory fa;
	
	@RequestMapping("/test")
	public String getInfo(HttpSession se,Integer userid) {
		UserBean user=new UserBean();
		user.setId(userid);
		se.setAttribute("user", user);
		return "/index2.jsp";
	}
	
	@RequestMapping("/test1")
	@ResponseBody
	public String getInfo1(HttpSession se) {
		return "hhhh";
	}
	
	@RequestMapping("/test2")
	@ResponseBody
	public String getInfo1(HttpSession se,String name) {
		UserBean user=new UserBean();
		user.setName(name);
		se.setAttribute("user", user);
		return "";
	}
	
	@RequestMapping("/test3")
	@ResponseBody
	public String getInfo2(HttpSession se,String name) {
		UserBean user=new UserBean();
		user.setName(name);
		se.setAttribute("user", user);
		//数据库持久操作
		return "success";
	}
	
}
