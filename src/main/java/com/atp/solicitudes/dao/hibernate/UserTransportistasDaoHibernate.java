package com.atp.solicitudes.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.atp.solicitudes.dao.UserTransportistasDao;
import com.atp.solicitudes.model.SolicitudContenedor;
import com.atp.solicitudes.model.UserTransportistas;
import com.atp.solicitudes.model.User;
import com.objectwave.dao.utils.DaoOrder;
import com.objectwave.dao.utils.DaoQuery;
import com.objectwave.dao.utils.DaoResult;
import com.objectwave.dao.utils.HibernateDaoTemplate;
import com.objectwave.utils.SimpleEntry;

public class UserTransportistasDaoHibernate extends HibernateDaoTemplate implements UserTransportistasDao{
	public Criteria getCriteria(){
		return getSession().createCriteria(UserTransportistas.class);
	}
	
	public UserTransportistas getWithId(Integer id){
		Criteria criteria = getCriteria();

		// restriction to query
		criteria.add(Restrictions.eq("id", id));

		// get the collection
		@SuppressWarnings("unchecked")
		List<UserTransportistas> col = criteria.list();

		// if no elements found, return null
		// otherwise, return the first object found
		if (col.size() == 0)
			return null;
		else
			return col.get(0);
	}
	
	public UserTransportistas getUserTransportistasWithIdN4Transport(Integer id,Integer userN4,Integer idTrnas){
		Criteria criteria = getCriteria();
		System.out.println("-----id  nose----------   " + id);
		User fake = new User();
		fake.setId(id);
		// restriction to query
		criteria.add(Restrictions.eq("user_n4_id", userN4));
		criteria.add(Restrictions.eq("transportista_id", idTrnas));

		// get the collection
		@SuppressWarnings("unchecked")
		List<UserTransportistas> col = criteria.list();

		// if no elements found, return null
		// otherwise, return the first object found
		if (col.size() == 0)
			return null;
		else
			return col.get(0);
	}
	
	public DaoResult<UserTransportistas> query(DaoQuery query, DaoOrder order){
		Criteria criteria = getCriteria();

		String[] eqs = {"id", "solicitud"};
		registerEqPaths(eqs, query, criteria);

		// apply order
		order.applyToCriteria(criteria, query);

		// create the result object
		DaoResult<UserTransportistas> result = new DaoResult<UserTransportistas>();
	
		// process it
		result.processWith(query, criteria);

		return result;	
	}
	public List<String> getContenedorStatus(String v)
	{
		Query query = getSession().createQuery("select model.username from User as model where model.userN4_Id = "+ v + "and model.profile="+"6");
		@SuppressWarnings("unchecked")
					List<String> queryResult = (List<String>)query.list();
					return queryResult;		
	
	}
	public List<UserTransportistas> getUserTransportistasbyUserN4(Integer UserN4){
		Criteria criteria = getCriteria();

		// restriction to query
		criteria.add(Restrictions.eq("user_n4_id", UserN4));

		// get the collection
		@SuppressWarnings("unchecked")
		List<UserTransportistas> col = criteria.list();

		// if no elements found, return null
		// otherwise, return the first object found
		if (col.size() == 0)
			return null;
		else
			return col;
	}
	
	public List<UserTransportistas> getUserTransportistasbyUserN4_(Integer UserN4){
		Query query = getSession().createQuery("select model.id,model.user,model.user_n4_id,model.transportista_id,model.username,model.nivel from UserTransportistas as model where model.user_n4_id = "+ UserN4 );
		@SuppressWarnings("unchecked")
					List<UserTransportistas> queryResult = (List<UserTransportistas>)query.list();
					return queryResult;	
	}
	
	public List<UserTransportistas> getnombresByAdmin(List<SimpleEntry> listObj)
	{
		Criteria criteria = getCriteria();

		// restriction to query
		criteria.add(Restrictions.eq("user_n4_id",""));

		// get the collection
		@SuppressWarnings("unchecked")
		List<UserTransportistas> col = criteria.list();

		// if no elements found, return null
		// otherwise, return the first object found
		if (col.size() == 0)
			return null;
		else
			return col;
	}
	
	public void save(UserTransportistas userTransportistas)
	{
		getHibernateTemplate().saveOrUpdate(userTransportistas);
	}
	
	public void delete(UserTransportistas userTransportistas)
	{
		getHibernateTemplate().delete(userTransportistas);
	}	
	
	public void update(UserTransportistas userTransportistas)
	{
		getHibernateTemplate().update(userTransportistas);
	}	
}
