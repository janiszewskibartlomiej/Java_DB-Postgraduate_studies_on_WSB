package orm.dao;

import orm.domain.Film;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class FilmDao extends Dao {

	public FilmDao(EntityManager em) {
		super(em);
	}

	public Film findWithId1() {
		Film film = em
				.createQuery("SELECT f FROM Film f WHERE f.id = 1", Film.class)
				.getSingleResult();
		return film;
	}

	public Film findByIdWithParameter(int id) {
		try {
			Film film = em
					.createQuery("SELECT f FROM Film f WHERE f.id = :id", Film.class)
					.setParameter("id", id)
					.getSingleResult();
			return film;
		} catch (NoResultException e) {
			return null;
		}

	}

	public Film findByIdWithEM(int id) {
		return em.find(Film.class, id);
	}

}
