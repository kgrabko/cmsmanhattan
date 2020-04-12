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
 * Home object for domain model class DomainProc.
 * @see com.cbsinc.entity.DomainProc
 * @author Hibernate Tools
 */
public class DomainProcHome {

	private static final Log log = LogFactory.getLog(DomainProcHome.class);

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

	public void persist(DomainProc transientInstance) {
		log.debug("persisting DomainProc instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(DomainProc instance) {
		log.debug("attaching dirty DomainProc instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DomainProc instance) {
		log.debug("attaching clean DomainProc instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(DomainProc persistentInstance) {
		log.debug("deleting DomainProc instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DomainProc merge(DomainProc detachedInstance) {
		log.debug("merging DomainProc instance");
		try {
			DomainProc result = (DomainProc) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DomainProc findById(com.cbsinc.entity.DomainProcId id) {
		log.debug("getting DomainProc instance with id: " + id);
		try {
			DomainProc instance = (DomainProc) sessionFactory
					.getCurrentSession()
					.get("com.cbsinc.entity.DomainProc", id);
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

	public List findByExample(DomainProc instance) {
		log.debug("finding DomainProc instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.cbsinc.entity.DomainProc")
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
