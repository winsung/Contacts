package com.multi.contactsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.contactsapp.dao.ContactDAO;
import com.multi.contactsapp.domain.Contact;

@Service
public class ContactService {
	
	@Autowired
	private ContactDAO contactDAO;
	
	public List<Contact> getContactList() {
		return contactDAO.getContactList();
	}
	
	public List<Contact> getContactList(int pageNo, int pageSize) {
		return contactDAO.getContactList(pageNo, pageSize);
	}
	
	public Contact getContactOne(Contact contact) {
		return contactDAO.getContactOne(contact);
	}
	
	public int insertContact(Contact contact) {
		return contactDAO.insertContact(contact);
	}
	
	public int updateContact(Contact contact) {
		return contactDAO.updateContact(contact);
	}
	
	public int deleteContact(Contact contact) {
		return contactDAO.deleteContact(contact);
	}
}
