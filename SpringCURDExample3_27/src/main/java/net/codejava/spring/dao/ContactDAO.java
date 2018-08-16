package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.Contact;

/**
 * Defines DAO operations for the contact model.
 * 
 * @author www.codejava.net
 *
 */
public interface ContactDAO {

	public void saveOrUpdate(Contact contact);

	
	  public void delete(String email);
	  
	  public Contact get(String contactId,Contact contact);
	 
	public List<Contact> list();
}
