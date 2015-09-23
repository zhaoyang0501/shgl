package com.pzy.controller;
import java.sql.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pzy.entity.Item;
import com.pzy.entity.Trade;
import com.pzy.entity.User;
import com.pzy.service.ItemService;
import com.pzy.service.TradeService;
/***
 * @author panchaoyang
 *
 */
@Controller
@RequestMapping("/admin")
public class TradeController {
	@Autowired
	private TradeService tradeService;
	@Autowired
	private ItemService itemService; 
	
	@RequestMapping("/trade/index")
	public String trade(Model model,HttpSession httpSession) {
		User user=(User)httpSession.getAttribute("adminuser");
		model.addAttribute("in",tradeService.findByUserAndType(user, "入库"));
		model.addAttribute("out",tradeService.findByUserAndType(user, "出库"));
		return "admin/trade/index";
	}
	@RequestMapping("/itemin/index")
	public String index(Model model) {
		model.addAttribute("items",itemService.findAll());
		return "admin/itemin/index";
	}
	
	@RequestMapping("/itemout/index")
	public String itemout(Model model) {
		model.addAttribute("items",itemService.findAll());
		return "admin/itemout/index";
	}
	@RequestMapping("/itemin/save")
	public String iteminsave(Model model,Trade trade,HttpSession httpSession) {
		Item item=itemService.find(trade.getItem().getId());
		if(trade.getCount()>item.getCount()){
			model.addAttribute("msg", "库存不足！");
			model.addAttribute("items",itemService.findAll());
			return "admin/itemin/index";
		}
		User user=(User)httpSession.getAttribute("adminuser");
		trade.setUser(user);
		trade.setPrice(item.getPrice());
		trade.setCreateDate(new Date(System.currentTimeMillis()));
		trade.setType("入库");
		tradeService.save(trade);
		item.setCount(item.getCount()-trade.getCount());
		itemService.save(item);
		model.addAttribute("items",itemService.findAll());
		model.addAttribute("msg", "入库成功");
		return "admin/itemin/index";
	}
	
	@RequestMapping("/itemout/save")
	public String itemoutsave(Model model,Trade trade,HttpSession httpSession) {
		User user=(User)httpSession.getAttribute("adminuser");
		trade.setUser(user);
		trade.setCreateDate(new Date(System.currentTimeMillis()));
		trade.setType("出库");
		tradeService.save(trade);
		model.addAttribute("items",itemService.findAll());
		model.addAttribute("msg", "出库成功");
		return "admin/itemout/index";
	}
}
