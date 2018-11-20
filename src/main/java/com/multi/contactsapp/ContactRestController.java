package com.multi.contactsapp;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.multi.contactsapp.domain.Contact;
import com.multi.contactsapp.domain.ContactList;
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
	public ModelAndView getContactsList(
			@RequestParam(value="pageno", required=false, defaultValue="0") int pageNo,
			@RequestParam(value="pagesize", required=false, defaultValue="3") int pageSize) {
		ContactList contactList = null;
		if (pageNo==0) {
			contactList = contactService.getContactList();
		} else {
			contactList = contactService.getContactList(pageNo, pageSize);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", contactList);
		mav.setView(jsonView);
		//mav.setView(xmlView);
		return mav;
	}
	
	@RequestMapping(value="{no}", method=RequestMethod.GET)
	public ModelAndView getContactOne(@PathVariable("no") int no) {
		Contact contact = new Contact();
		contact.setNo(no);
		ModelAndView mav = new ModelAndView();
		Contact ret = contactService.getContactOne(contact);
		System.out.println(contact.getNo() + ": " + ret);
		mav.addObject("data", contactService.getContactOne(contact));
		mav.setView(jsonView);

		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView insertContact(@RequestBody Contact contact) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", contactService.insertContact(contact));
		mav.setView(jsonView);
		
		return mav;
	}
	
	@RequestMapping(value="{no}", method=RequestMethod.PUT)
	public ModelAndView updateContact(
			@RequestBody Contact contact,
			@PathVariable("no") int no) {
		contact.setNo(no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", contactService.updateContact(contact));
		mav.setView(jsonView);
		
		return mav;
	}
	
	@RequestMapping(value="{no}", method=RequestMethod.DELETE)
	public ModelAndView deleteContact(@PathVariable("no") int no) {
		Contact contact = new Contact();
		contact.setNo(no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", contactService.deleteContact(contact));
		mav.setView(jsonView);
		
		return mav;
	}
}
