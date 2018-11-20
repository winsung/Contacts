package com.multi.contactsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.contactsapp.dao.ContactDAO;
import com.multi.contactsapp.domain.Contact;
import com.multi.contactsapp.domain.ContactList;
import com.multi.contactsapp.domain.Result;

@Service
public class ContactService {
	
	@Autowired
	private ContactDAO contactDAO;
	
	/*
	public List<Contact> getContactList() {
		return contactDAO.getContactList();
	}*/
	
	public ContactList getContactList() {
		List<Contact> contacts = contactDAO.getContactList();
		return new ContactList(0, 0, contacts.size(), contacts);
	}
	
	/*
	public List<Contact> getContactList(int pageNo, int pageSize) {
		
		return contactDAO.getContactList(pageNo, pageSize);
	}*/
	
	public ContactList getContactList(int pageNo, int pageSize) {
		return new ContactList(pageNo, pageSize, contactDAO.getContactCount(), contactDAO.getContactList(pageNo, pageSize));
	}
	
	public Contact getContactOne(Contact contact) {
		return contactDAO.getContactOne(contact);
	}
	
	public Result insertContact(Contact contact) {
		Result result = new Result("OK", "", "");
		try {
			int no = contactDAO.insertContact(contact);
			result.setMessage("Insert Success : " + no);
			result.setKey(""+no);
		} catch (Exception e) {
			result.setStatus("Fail");
			result.setMessage(e.getMessage());
			result.setKey("");
		}
		return result;
	}
	
	public Result updateContact(Contact contact) {
		Result result = new Result("OK", "", "");
		try {
			int count = contactDAO.updateContact(contact);
			result.setMessage(count + " Update Success");
			result.setKey(contact.getNo() + "");
		} catch (Exception e) {
			result.setStatus("Fail");
			result.setMessage(e.getMessage());
			result.setKey("");
		}
		
		return result;
	}
	
	public Result deleteContact(Contact contact) {
		Result result = new Result("OK", "", "");
		try {
			int count = contactDAO.deleteContact(contact);
			result.setMessage(count + " Delete Success");
			result.setKey(contact.getNo() + "");
		} catch (Exception e) {
			result.setStatus("Fail");
			result.setMessage(e.getMessage());
			result.setKey("");
		}
		
		return result;
	}
}
