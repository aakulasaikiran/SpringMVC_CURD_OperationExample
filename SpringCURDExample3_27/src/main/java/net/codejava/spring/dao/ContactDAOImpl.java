package net.codejava.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import net.codejava.spring.model.Contact;

import org.bson.BasicBSONDecoder;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.mongodb.BasicDBObject;

/**
 * An implementation of the ContactDAO interface.
 * 
 * @author www.codejava.net
 *
 */
public class ContactDAOImpl implements ContactDAO {

	private MongoTemplate mongoTemplate;

	public ContactDAOImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public void saveOrUpdate(Contact contact) {
		//if (contact.getId() < 0) {

			mongoTemplate.save(contact);
		//} 
			/*else {
			int contactId = contact.getId();
			Query query = new Query(Criteria.where("id").is(contactId));

			
			mongoTemplate.save(contact);
		}*/
		/*
		 * if (contact.getId() > 0) {
		 * 
		 * BasicDBObject query = new BasicDBObject("id", contact.getId());
		 * 
		 * Query query1 = new Query(Criteria.where("id").is(contact.getId()));
		 * 
		 * mongoTemplate.updateFirst(query1, Update.update("name",
		 * contact.getName()), Contact.class);
		 * 
		 * } else { Query query1 = new
		 * Query(Criteria.where("id").is(contact.getId()));
		 * mongoTemplate.updateFirst(query1, Update.update("name",
		 * contact.getName()), Contact.class);
		 * 
		 * 
		 * 
		 * mongoTemplate.save(contact); }
		 */

	}

	public void delete(String email) {

		Query query = new Query(Criteria.where("email").is(email));
		mongoTemplate.remove(query, Contact.class);
	}

	public List<Contact> list() {

		Query query2 = new Query();

		return mongoTemplate.findAll(Contact.class, "Contact");
	}

	public Contact get(String contactId, Contact contact) {
		// String sql = "SELECT * FROM contact WHERE contact_id=" + contactId;

		Query query = new Query(Criteria.where("id").is(contactId));

		Update update = new Update();
		// update.set("id",contact.getId());
		update.set("name", contact.getName());
		update.set("email", contact.getEmail());
		update.set("address", contact.getAddress());
		update.set("telephone", contact.getTelephone());
		return mongoTemplate.findAndModify(query, update, Contact.class);

		/*
		 * public Contact extractData(ResultSet rs) throws SQLException,
		 * DataAccessException { if (rs.next()) { Contact contact = new
		 * Contact(); contact.setId(rs.getInt("contact_id"));
		 * contact.setName(rs.getString("name"));
		 * contact.setEmail(rs.getString("email"));
		 * contact.setAddress(rs.getString("address"));
		 * contact.setTelephone(rs.getString("telephone")); return contact; }
		 * 
		 * return null; }
		 * 
		 * });
		 */
	}

}
