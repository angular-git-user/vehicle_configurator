package app.connection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.stat.Statistics;
import org.springframework.stereotype.Component;

import app.entityClasses.FeatureTypes;
import app.entityClasses.Manufacturer;
import app.entityClasses.Model;
import app.entityClasses.ModelManufacturerMapper;
import app.entityClasses.Segment;
import app.entityClasses.User;
import app.service.PersistenceService;

@Component
public class HibernatePersistence implements PersistenceService {

	private SessionFactory factory;

	@PostConstruct
	public void init() {
		factory = HibernateConnectionUtil.getConnectionFactory();
	}

	@Override
	public Integer createUser(User user) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer created = null;
		try {
			if (user != null) {
				User isExsistingUser = getUserWithLoginId(user.getUserId());
				if (isExsistingUser == null) {
					tx = session.beginTransaction();
					created = (Integer) session.save(user);
					tx.commit();
				}
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return created;
	}

	@Override
	public boolean login(String loginId, String password) {
		boolean success = false;
		if (loginId != null && password != null) {

			User user = getUserWithLoginId(loginId);
			if (user != null && (user.getUserId().equals(loginId)) && (user.getPassword().equals(password))) {
				success = true;
			} else {
				success = false;
			}

		}
		return success;
	}

	public User getUserWithLoginId(String loginId) {
		Session session = factory.openSession();
		User user = null;
		try {
			String hql = "select * from Users u where u.user_id='" + loginId + "'";
			SQLQuery query = session.createSQLQuery(hql);
			query.addEntity(User.class);
			user = query.list().size() > 0 ? (User) query.list().get(0) : null;

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Segment> getAllSegment() {
		Session session = factory.openSession();
		List<Segment> list = null;
		Statistics stats = setStats();
		try {
			list = session.createCriteria(Segment.class).setCacheable(true).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		printStats(stats);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Manufacturer> getAllManufacturers(int segmentId) {
		Session session = factory.openSession();
		List<Manufacturer> list = null;
		Statistics stats = setStats();
		try {
			list = session.createCriteria(Manufacturer.class).setCacheable(true).setCacheRegion("segmentManuf").
					add(Restrictions.eq("segment.id", new Integer(segmentId))).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		printStats(stats);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Model> getAllModels(int manufacturerId) {
		Session session = factory.openSession();
		List<Model> list = null;

		try {
			list = session.createCriteria(Model.class).
					add(Restrictions.eq("manufacturer.id", manufacturerId))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes"})
	@Override
	public List<ModelManufacturerMapper> getAllFeatures(int modelId) {
		Session session = factory.openSession();
		List<ModelManufacturerMapper> list = new ArrayList<ModelManufacturerMapper>();
		try {
			Criteria criteria = session.createCriteria(FeatureTypes.class)
					.createCriteria("subFeatures", "sub")
					.createCriteria("manufactureresOfFeature", "manu")
					.createCriteria("modelManufMapper","modelManu")
					.add(Restrictions.eq("modelManu.modelMapper.id", modelId))
					.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List featureTypes = criteria.list();
			Iterator iterator =featureTypes.iterator();
			while(iterator.hasNext()){
				Map map= (Map)iterator.next();
				ModelManufacturerMapper mapper = (ModelManufacturerMapper) map.get("modelManu");
				list.add(mapper);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Model> getSearchSuggestionsForModels(String searchString) {
		Session session = factory.openSession();
		List<Model> list = null;

		try {
			list = session.createCriteria(Model.class).
					add(Restrictions.eq("modelName", searchString+"%"))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	private Statistics setStats(){
		Statistics stats = factory.getStatistics();
		stats.setStatisticsEnabled(true);
		return stats;
	}
	
	private static void printStats(Statistics stats) {	
		System.out.println("Fetch Count="
				+ stats.getEntityFetchCount());
		System.out.println("Second Level Hit Count="
				+ stats.getSecondLevelCacheHitCount());
		System.out.println("Second Level Miss Count="
						+ stats.getSecondLevelCacheMissCount());
		System.out.println("Second Level Put Count="
				+ stats.getSecondLevelCachePutCount());
	}
}
