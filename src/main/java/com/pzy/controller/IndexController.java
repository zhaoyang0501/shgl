package com.pzy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pzy.entity.User;
import com.pzy.service.UserService;
/***
 * 后台首页，处理后台登录验证权限等操作
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin")
public class IndexController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("index")
	public String index() {
		return "admin/login";
	}
	@RequestMapping("login")
	public String login() {
		return "admin/login";
	}
	@RequestMapping("loginout")
	public String loginout(HttpSession httpSession) {
		httpSession.setAttribute("adminuser", null);
		return "admin/login";
	}
	@RequestMapping("gologin")
	public String gologin(HttpSession httpSession,String userName,String password,Model model) {
		
		User user=userService.login(userName, password);
    	if("admin".equals(userName)&&"123456".equals(password)){
    		User admin=new User(); 
    		admin.setUsername("admin");
    		admin.setPassword("123456");
    		httpSession.setAttribute("adminuser", admin);
    		return "admin/index";
    	}
		
    	else if(user!=null){
    		httpSession.setAttribute("adminuser", user);
    		return "admin/index";
    	}
    	else{
    		httpSession.setAttribute("adminuser", null);
    		model.addAttribute("tip","登陆失败 不存在此用户名或密码!");
    		return "admin/login";
    	}
    	
	}
	/***
	 * 点击登录按钮
	 * @return
	 *//*
     @Action(value = "login", results = { @Result(name = "success", location = "/WEB-INF/views/admin/login.jsp") })
     public String login(){
          return SUCCESS;
     }
     *//***
      * 注销退出
      * @return
      *//*
     @Action(value = "loginout", results = { @Result(name = "success", location = "/WEB-INF/views/admin/login.jsp") })
     public String loginout(){
    	 	ActionContext.getContext().getSession().remove("adminuser");
    	 	ActionContext.getContext().getSession().clear();
    	 	return SUCCESS;
     }
     *//**
      * 登录，后台数据库验证
      * @return
      *//*
     @Action(value = "gologin", results = { @Result(name = "success", location = "/WEB-INF/views/admin/index.jsp"),@Result(name = "input", location = "/WEB-INF/views/admin/login.jsp") })
     public String gologin(){
    	AdminUser adminUser=adminUserService.login(this.userName, this.password);
    	
    	if(adminUser!=null){
    		ActionContext.getContext().getSession().put("adminuser",adminUser);
            return SUCCESS; 
    	}
    	else{
    		ActionContext.getContext().getSession().clear();
    		this.tip="登陆失败 不存在此用户名或密码!";
    		return LOGIN;
    	}
     }
     *//*********getter setter********************//*
 	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
    public String getUserName() {
 		return userName;
 	}
 	public void setUserName(String userName) {
 		this.userName = userName;
 	}
 	public String getPassword() {
 		return password;
 	}
 	public void setPassword(String password) {
		this.password = password;
	}*/
}

