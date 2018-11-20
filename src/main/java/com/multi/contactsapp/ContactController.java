package com.multi.contactsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.multi.contactsapp.domain.Contact;
import com.multi.contactsapp.domain.ContactList;
import com.multi.contactsapp.service.ContactService;

@Controller
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@RequestMapping(value="contacts", method=RequestMethod.GET)
	public ModelAndView getContactList2() {
		ContactList contactList = contactService.getContactList();
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", contactList);
		mav.setViewName("contact");
		return mav;
	}
	
	
	//@GetMapping("list.do")
	@RequestMapping(value="list.do", method=RequestMethod.GET)
	public ModelAndView getContactList() {
		ContactList contactList = contactService.getContactList();
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", contactList);
		mav.setViewName("contact");
		return mav;
		
	}
	
	@RequestMapping(value="add.do", method=RequestMethod.POST)
	public String insertContact(Contact contact) {
		contactService.insertContact(contact);
		return "redirect:list.do";
	}

	@RequestMapping(value="update.do", method=RequestMethod.POST)
	public String updateContact(Contact contact) {
		contactService.updateContact(contact);
		return "redirect:list.do";
	}
}
