package com.pzy.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/***
 * @author panchaoyang
 *
 */
@Controller
@RequestMapping("/admin /dailyReport")
public class DailyReportController {
	
	/***   
	 * @return
	 */
	@RequestMapping("b")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("dailyreport/dailyreport/index");
		return model;
	}
	@RequestMapping("indexa")
	public ModelAndView aaaa() {
		ModelAndView model = new ModelAndView("admin/news/index");
		return model;
	}
}
