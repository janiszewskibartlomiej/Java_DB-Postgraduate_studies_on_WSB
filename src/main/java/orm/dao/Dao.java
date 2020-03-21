package orm.dao;

import javax.persistence.EntityManager;

public abstract class Dao {

	protected final EntityManager em;

	public Dao(EntityManager em) {
		this.em = em;
	}

}
