package com.cbsinc.entity;

// Generated 29.03.2014 21:04:06 by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Creteria10.
 * @see com.cbsinc.entity.Creteria10
 * @author Hibernate Tools
 */
public class Creteria10Home {

	private static final Log log = LogFactory.getLog(Creteria10Home.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Creteria10 transientInstance) {
		log.debug("persisting Creteria10 instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Creteria10 instance) {
		log.debug("attaching dirty Creteria10 instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Creteria10 instance) {
		log.debug("attaching clean Creteria10 instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Creteria10 persistentInstance) {
		log.debug("deleting Creteria10 instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Creteria10 merge(Creteria10 detachedInstance) {
		log.debug("merging Creteria10 instance");
		try {
			Creteria10 result = (Creteria10) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Creteria10 findById(long id) {
		log.debug("getting Creteria10 instance with id: " + id);
		try {
			Creteria10 instance = (Creteria10) sessionFactory
					.getCurrentSession()
					.get("com.cbsinc.entity.Creteria10", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Creteria10 instance) {
		log.debug("finding Creteria10 instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.cbsinc.entity.Creteria10")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
