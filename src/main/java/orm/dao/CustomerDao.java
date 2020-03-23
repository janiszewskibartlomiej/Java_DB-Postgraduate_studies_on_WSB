package orm.dao;

import orm.domain.Customer;

import javax.persistence.EntityManager;
import java.util.List;

public class CustomerDao extends Dao {

	public CustomerDao(EntityManager em) {
		super(em);
	}

	public List<Customer> findByLastName(String lastName) {
		return em.createQuery("SELECT c FROM Customer c JOIN FETCH c.address a " +
					"JOIN FETCH a.city WHERE c.lastName = :lastName",Customer.class)
				.setParameter("lastName",lastName.toUpperCase())
				.getResultList();
	}

	public List<Customer> findByLastNameStartinWith(String lastName) {
		return em.createQuery("SELECT c FROM Customer c JOIN FETCH c.address a " +
					"JOIN FETCH a.city WHERE c.lastName LIKE CONCAT(:lastName, '%')",Customer.class)
				.setParameter("lastName",lastName.toUpperCase())
				.getResultList();
	}

	public List<Customer> findByCity(String city) {
		return em.createQuery("FROM Customer c " +
					"JOIN FETCH c.address a " +
					"JOIN FETCH a.city ci " +
					"WHERE ci.city = :city",Customer.class)
				.setParameter("city",city)
				.getResultList();
	}

	public Long countByCity(String city) {
		return em.createQuery("SELECT COUNT(c) " +
					"FROM Customer c " +
					"JOIN c.address a " +
					"JOIN a.city ci " +
					"WHERE ci.city = :city",Long.class)
				.setParameter("city",city)
				.getSingleResult();
	}

}
