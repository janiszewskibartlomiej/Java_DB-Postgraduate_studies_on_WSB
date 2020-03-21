package orm.dao;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import orm.domain.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerDaoTest {

	EntityManager em;
	CustomerDao customerDao;

	Statistics statistics;

	/**
	 * Tworzymy nowego EntityManagera dla każdego testu z osobna,
	 * by być w stanie zliczyć ilość zapytań.
	 * W przeciwnym razie testy wpływałyby na siebie,
	 * gdy Hibernate cachowałby encje wyciągnięte w jednym teście i zwracał je w kolejnym,
	 * bez wykonywania zapytania.
	 */
	@BeforeEach
	void beforeEach() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu1");
		em = entityManagerFactory.createEntityManager();

		// Only needed to get statistics from Hibernate
		SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
		sessionFactory.getStatistics().setStatisticsEnabled(true);
		statistics = sessionFactory.getStatistics();

		customerDao = new CustomerDao(em);
	}

	@AfterEach
	void afterEach() {
		em.close();
	}

	@Test
	void findByLastName() {
		List<Customer> customers = customerDao.findByLastName("anderson");

		customers.forEach(System.out::println);

		assertEquals(1, customers.size());
	}

	@Test
	void findByLastName_singleQuery() {
		List<Customer> customers = customerDao.findByLastName("anderson");

		customers.forEach(System.out::println);

		assertEquals(1, customers.size());

		assertEquals(1, statistics.getPrepareStatementCount());
	}

	@Test
	void findByLastNameStartinWith() {
		List<Customer> customers = customerDao.findByLastNameStartinWith("and");

		customers.forEach(System.out::println);

		assertEquals(3, customers.size());

		assertEquals(1, statistics.getPrepareStatementCount());
	}

	@Test
	void findByCity() {
		List<Customer> customers = customerDao.findByCity("London");

		customers.forEach(System.out::println);

		assertEquals(2, customers.size());

		assertEquals(1, statistics.getPrepareStatementCount());
	}

	@Test
	void countByCity() {
		Long customersCount = customerDao.countByCity("London");

		assertEquals(2, customersCount);

		assertEquals(1, statistics.getPrepareStatementCount());
	}
}
