package orm.dao;

import orm.domain.Store;

import javax.persistence.EntityManager;
import java.util.List;

public class StoreDao extends Dao {

	public StoreDao(EntityManager em) {
		super(em);
	}

	public Store findById(int id) {
		throw new UnsupportedOperationException();
	}

	public List<Store> findAll() {
		throw new UnsupportedOperationException();
	}

}
