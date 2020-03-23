package orm.dao;

import orm.domain.Store;

import javax.persistence.EntityManager;
import java.util.List;

public class StoreDao extends Dao {

	public StoreDao(EntityManager em) {
		super(em);
	}

	public Store findById(int id) {
		return em.find(Store.class, id);
	}

	public List<Store> findAll() {
		return em
				.createQuery("SELECT s FROM Store s " +
						"LEFT JOIN FETCH s.address a " +
						"LEFT JOIN FETCH a.city",
						Store.class) //featch mowi zeby wycignal kolumne address
				.getResultList();
	}

}
