package com.pzy.controller;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pzy.entity.Contract;
import com.pzy.entity.Sign;
import com.pzy.entity.User;
import com.pzy.service.ContractService;
import com.pzy.service.SignService;
import com.pzy.service.UserService;
/***
 * @author panchaoyang
 *
 */
@Controller
@RequestMapping("/admin/mycontract")
public class MyContractController {
	@Autowired
	private ContractService contractService;
	@Autowired
	private UserService userService;
	@Autowired
	private SignService signService;
	@RequestMapping("index")
	public ModelAndView index(HttpSession httpSession) {
		User user=(User)httpSession.getAttribute("adminUser");
		ModelAndView mv=new ModelAndView("admin/mycontract/index");
		mv.addObject("signed", contractService.findSigned(user));
		mv.addObject("notsign", contractService.findNotSign(user));
		return mv;
	}
	@RequestMapping("sign/{id}")
	public ModelAndView sign(@PathVariable Long id,HttpSession httpSession) {
		User user=(User)httpSession.getAttribute("adminuser");
		Sign sign=new Sign();
		sign.setUser(user);
		Contract contract=contractService.find(id);
		sign.setContract(contract);
		sign.setSignDate(new Date());
		signService.save(sign);
		ModelAndView mv=new ModelAndView("admin/mycontract/index");
		mv.addObject("tip","合同签订成功！");
		mv.addObject("signed", contractService.findSigned(user));
		mv.addObject("notsign", contractService.findNotSign(user));
		return mv;
	}
}
