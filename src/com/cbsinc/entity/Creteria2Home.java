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
 * Home object for domain model class Creteria2.
 * @see com.cbsinc.entity.Creteria2
 * @author Hibernate Tools
 */
public class Creteria2Home {

	private static final Log log = LogFactory.getLog(Creteria2Home.class);

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

	public void persist(Creteria2 transientInstance) {
		log.debug("persisting Creteria2 instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Creteria2 instance) {
		log.debug("attaching dirty Creteria2 instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Creteria2 instance) {
		log.debug("attaching clean Creteria2 instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Creteria2 persistentInstance) {
		log.debug("deleting Creteria2 instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Creteria2 merge(Creteria2 detachedInstance) {
		log.debug("merging Creteria2 instance");
		try {
			Creteria2 result = (Creteria2) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Creteria2 findById(long id) {
		log.debug("getting Creteria2 instance with id: " + id);
		try {
			Creteria2 instance = (Creteria2) sessionFactory.getCurrentSession()
					.get("com.cbsinc.entity.Creteria2", id);
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

	public List findByExample(Creteria2 instance) {
		log.debug("finding Creteria2 instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.cbsinc.entity.Creteria2")
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
