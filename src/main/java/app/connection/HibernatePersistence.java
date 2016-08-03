package app.connection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import app.entityClasses.FeatureManufacturers;
import app.entityClasses.FeatureTypes;
import app.entityClasses.Manufacturer;
import app.entityClasses.Model;
import app.entityClasses.ModelManufacturerMapper;
import app.entityClasses.Segment;
import app.entityClasses.SubFeatures;
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
		try {
			list = session.createCriteria(Segment.class).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Manufacturer> getAllManufacturers(int segmentId) {
		Session session = factory.openSession();
		List<Manufacturer> list = null;

		try {
			list = session.createCriteria(Manufacturer.class).
					add(Restrictions.eq("segment.id", new Integer(segmentId))).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
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

	
	//TODO
	@SuppressWarnings("unchecked")
	@Override
	public List<ModelManufacturerMapper> getAllFeatures(int modelId) {
		Session session = factory.openSession();
		List<ModelManufacturerMapper> list = null;
		Set<FeatureTypes> set = new HashSet<FeatureTypes>();
		List<FeatureTypes> types = null;
		try {
			list = session.createCriteria(ModelManufacturerMapper.class).
					add(Restrictions.eq("modelMapper.id", modelId)).list();
			/*String sql = "select types.* from MAPPER_MODEL_MANUFACTURER mapper join FEATURE_MANUFACTURERS feat on feat.MANUFACTURER_ID = mapper.MANUFACTURER_ID join SUB_FEATURES sub on feat.SUB_FEATURE_ID=sub.SUB_FEATURE_ID join FEATURE_TYPES types on types.FEATURE_ID = sub.FEATURE_ID where mapper.MODEL_ID=1 order by types.FEATURE_NAME, sub.SUB_FEATURE_NAME DESC";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(FeatureTypes.class);	
			List<FeatureTypes> li = query.list();*/
			for(ModelManufacturerMapper mapper : list){
				set.add(mapper.getManufacturerMapper().getSubfeature().getFeature());
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

}
