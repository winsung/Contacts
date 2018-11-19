package com.multi.contactsapp.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.contactsapp.domain.Contact;

@Repository
public class ContactDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public List<Contact> getContactList() {
		return sqlSession.selectList("contact.selectall");
	}
	
	public List<Contact> getContactList(int pageNo, int pageSize) {
		int offset = (pageNo-1) * pageSize;
		int limit = pageSize;
		return sqlSession.selectList("contact.selectall", null, new RowBounds(offset, limit));
	}
	
	public int getContactCount() {
		return sqlSession.selectOne("contact.selectcnt");
	}
	
	public Contact getContactOne(Contact contact) {
		return sqlSession.selectOne("contact.selectone");
	}
	
	public int insertContact(Contact contact) {
		sqlSession.insert("contact.insert", contact);
		return contact.getNo();
	}
	
	public int updateContact(Contact contact) {
		return sqlSession.update("contact.update", contact);
	}
	
	public int deleteContact(Contact contact) {
		return sqlSession.delete("contact.delete", contact);
	}
}
