package com.pzy.controller;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pzy.entity.Contract;
import com.pzy.service.ContractService;
/***
 * @author panchaoyang
 *
 */
@Controller
@RequestMapping("/admin/contract")
public class ContractController {
	@Autowired
	private ContractService contractService;
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				try {
					setValue(DateUtils.parseDate(text, "yyyy-MM-dd"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});
	}
	@RequestMapping("index")
	public String index() {
		return "admin/contract/index";
	}
	@RequestMapping("create")
	public String create() {
		return "admin/contract/create";
	}
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(
			@RequestParam(value = "sEcho", defaultValue = "1") int sEcho,
			@RequestParam(value = "iDisplayStart", defaultValue = "0") int iDisplayStart,
			@RequestParam(value = "iDisplayLength", defaultValue = "10") int iDisplayLength, String contractname
			) throws ParseException {
		int pageNumber = (int) (iDisplayStart / iDisplayLength) + 1;
		int pageSize = iDisplayLength;
		Page<Contract> contracts = contractService.findAll(pageNumber, pageSize, contractname);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("aaData", contracts.getContent());
		map.put("iTotalRecords", contracts.getTotalElements());
		map.put("iTotalDisplayRecords", contracts.getTotalElements());
		map.put("sEcho", sEcho);
		return map;
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(Contract contract) {
		contract.setCreateDate(new Date());
		contractService.save(contract);
		ModelAndView mv=new ModelAndView("admin/contract/create");
		mv.addObject("state", "success");
		mv.addObject("msg", "保存成功");
		return mv;
	}
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, Object> update(Contract contract) {
		Contract bean=contractService.find(contract.getId());
		bean.setTitle(contract.getTitle());
		bean.setEnd(contract.getEnd());
		bean.setStart(contract.getStart());
		contractService.save(bean);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "success");
		map.put("msg", "保存成功");
		return map;
	}
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			contractService.delete(id);
			map.put("state", "success");
			map.put("msg", "删除成功");
		} catch (Exception e) {
			map.put("state", "error");
			map.put("msg", "删除失败，外键约束");
		}
        return map;
	}

	@RequestMapping(value = "/get/{id}")
	@ResponseBody
	public Map<String, Object> get(@PathVariable Long id ) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("object", contractService.find(id));
		map.put("state", "success");
		map.put("msg", "成功");
		return map;
	}
}
