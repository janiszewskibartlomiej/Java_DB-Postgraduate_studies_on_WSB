package orm.dao;

import orm.domain.Customer;

import javax.persistence.EntityManager;
import java.util.List;

public class CustomerDao extends Dao {

	public CustomerDao(EntityManager em) {
		super(em);
	}

	public List<Customer> findByLastName(String lastName) {
		throw new UnsupportedOperationException();
	}

	public List<Customer> findByLastNameStartinWith(String lastName) {
		throw new UnsupportedOperationException();
	}

	public List<Customer> findByCity(String city) {
		throw new UnsupportedOperationException();
	}

	public Long countByCity(String city) {
		throw new UnsupportedOperationException();
	}

}
