package com.multi.contactsapp;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.multi.contactsapp.service.ContactService;

@Controller
@RequestMapping(value="/contacts")
public class ContactRestController {
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	@Qualifier("xmlView")
	private View xmlView;
	
	@Resource(name="jsonView")
	private View jsonView;

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getContactsList() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", contactService.getContactList());
		mav.setView(jsonView);
		//mav.setView(xmlView);
		return mav;
	}
}
