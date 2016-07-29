package app.connection;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import app.entityClasses.EFeatures;
import app.entityClasses.Manufacturer;
import app.entityClasses.Model;
import app.entityClasses.Segment;
import app.entityClasses.User;
import app.service.PersistenceService;

@Component
public class HibernatePersistence implements PersistenceService {

	private SessionFactory factory;
	
	@PostConstruct
	public void init(){
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
		try{
			String hql = "select * from segment";
			SQLQuery query = session.createSQLQuery(hql);
			query.addEntity(Segment.class);
			list = query.list();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Manufacturer> getAllManufacturers(int id) {
		Session session = factory.openSession();
		List<Manufacturer> list = null;
		
		try{
			String hql = "select * from manuf where seg = "+id;
			SQLQuery query = session.createSQLQuery(hql);
			query.addEntity(Manufacturer.class);
			list = query.list();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Model> getAllModels(int manufacturerId) {
		Session session = factory.openSession();
		List<Model> list = null;
		
		try{
			String hql = "select * from model where manu = "+manufacturerId;
			SQLQuery query = session.createSQLQuery(hql);
			query.addEntity(Model.class);
			list = query.list();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public List<String> getAllAvailableFeatureNames(int modelId, EFeatures title) {
		Session session = factory.openSession();
		List<String> list = null;
		try{
			String hql = "select distinct(s.name) from " + /*VCEnum.STANDARD_FEATURE_MAPPER +*/ "m join " /*+ VCEnum.STANDARD_FEATURES*/ + 
						" s on m.feature = s.id and m.model = " + modelId;
			SQLQuery query = session.createSQLQuery(hql);
			list = query.list();
			System.out.println(list);
		}catch(HibernateException he){
			he.printStackTrace();
		}
		return list;
	}
}
