package orm.dao;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import orm.domain.Store;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StoreDaoTest {

	EntityManager em;
	StoreDao storeDao;

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

		storeDao = new StoreDao(em);
	}

	@AfterEach
	void afterEach() {
		em.close();
	}

	@Test
	void findById() {
		Store store = storeDao.findById(1);

		System.out.println(store);

		assertEquals(1, store.getStoreId());

		assertEquals(1, statistics.getPrepareStatementCount());
	}

	@Test
	void findById_withAddress() {
		Store store = storeDao.findById(1);

		System.out.println(store);

		assertEquals(1, store.getStoreId());
		assertNotNull(store.getAddress());
		assertEquals(1, store.getAddress().getAddressId());
		assertEquals("47 MySakila Drive", store.getAddress().getAddress());

		assertEquals(1, statistics.getPrepareStatementCount());
	}

	@Test
	void findAll_withAddresses() {
		List<Store> stores = storeDao.findAll();

		stores.forEach(System.out::println);

		assertEquals(2, stores.size());
		assertNotNull(stores.get(0).getAddress());
	}

	@Test
	void findAll_withAddresses_singleQuery() {
		List<Store> stores = storeDao.findAll();

		stores.forEach(System.out::println);

		assertEquals(2, stores.size());
		assertNotNull(stores.get(0).getAddress());

		assertEquals(1, statistics.getPrepareStatementCount());
	}

	@Test
	void findAll_withAddressesAndCities_singleQuery() {
		List<Store> stores = storeDao.findAll();

		stores.forEach(System.out::println);

		assertEquals(2, stores.size());
		assertNotNull(stores.get(0).getAddress());
		assertNotNull(stores.get(0).getAddress().getCity());

		assertEquals(1, statistics.getPrepareStatementCount());
	}

}
