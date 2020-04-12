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
 * Home object for domain model class Deliverystatus.
 * @see com.cbsinc.entity.Deliverystatus
 * @author Hibernate Tools
 */
public class DeliverystatusHome {

	private static final Log log = LogFactory.getLog(DeliverystatusHome.class);

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

	public void persist(Deliverystatus transientInstance) {
		log.debug("persisting Deliverystatus instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Deliverystatus instance) {
		log.debug("attaching dirty Deliverystatus instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Deliverystatus instance) {
		log.debug("attaching clean Deliverystatus instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Deliverystatus persistentInstance) {
		log.debug("deleting Deliverystatus instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Deliverystatus merge(Deliverystatus detachedInstance) {
		log.debug("merging Deliverystatus instance");
		try {
			Deliverystatus result = (Deliverystatus) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Deliverystatus findById(long id) {
		log.debug("getting Deliverystatus instance with id: " + id);
		try {
			Deliverystatus instance = (Deliverystatus) sessionFactory
					.getCurrentSession().get(
							"com.cbsinc.entity.Deliverystatus", id);
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

	public List findByExample(Deliverystatus instance) {
		log.debug("finding Deliverystatus instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.cbsinc.entity.Deliverystatus")
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
