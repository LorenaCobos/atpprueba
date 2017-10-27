package com.atp.solicitudes.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.runtime.log.Log;
import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Array;

import com.atp.solicitudes.dao.SolicitudDao;
import com.atp.solicitudes.model.Solicitud;
import com.atp.solicitudes.model.SolicitudAppointment;
import com.atp.solicitudes.model.SolicitudContenedor;
import com.atp.solicitudes.model.User;
import com.objectwave.dao.utils.DaoOrder;
import com.objectwave.dao.utils.DaoQuery;
import com.objectwave.dao.utils.DaoResult;
import com.objectwave.dao.utils.HibernateDaoTemplate;
import com.sun.org.apache.bcel.internal.generic.ISUB;

import javax.persistence.*;


public class SolicitudDaoHibernate extends HibernateDaoTemplate implements SolicitudDao
{
	private Criteria getCriteria()
	{
		return getSession().createCriteria(Solicitud.class);
	}

	public Solicitud getWithFolio(Integer folio) throws Exception
	{
		Criteria criteria = getCriteria();

		criteria.add(Restrictions.eq("folio", folio));

		// get the collection
		@SuppressWarnings("unchecked")
		List<Solicitud> col = criteria.list();

		// if no elements found, return null
		// otherwise, return the first object found
		if (col.size() == 0)
			return null;
		else
			return col.get(0);
	}
	
	public void save(Solicitud object) throws Exception
	{
		getHibernateTemplate().saveOrUpdate(object);
	}

	public DaoResult<Solicitud>query(DaoQuery query, DaoOrder order)
	{
		Criteria criteria = getCriteria();
//		String test= query.getFieldAliasFromPath("nbrField", getCriteria());
		query.applyFetchingToCriteria(criteria);
		String[] eqs = {"folio", "status", "operationType","user"};
		registerEqPaths(eqs, query, criteria);
		String[] likes = {"reference","user.username"};
		registerFullLikePaths(likes, query, criteria);
		DaoResult<Solicitud> result = new DaoResult<Solicitud>();
		result.processWith(query, order, criteria);
		// return the result
		return result;
	}
	
	//preuba
	public List<String>  getWithcontenedor(String user, String contenedor) throws Exception
	{
		//String n ="\"contenedor\":{\"id\":354932082,\"label\":\"MSCU6722836\"}"; appointmentNbr
		
		String n =contenedor;
		Query query = getSession().createQuery("select model.folio from  Solicitud as model  where model.id in"+
		"(select distinct solappoim.solicitud.folio from SolicitudAppointment as solappoim where solappoim.definition like "+ n+")");
		System.out.println("resultado" + query);
		System.out.println("resultado" + contenedor);
		//query.setParameter("searchKeyword", "%"+n);
		@SuppressWarnings("unchecked")
		List<String> queryResult = (List<String>)query.list();
			
			System.out.println("resultado" + query);
		
		return queryResult;	
	}
	
	public List<String> getdocumentos(String bsl,String impedi,String linea,String ferrocarril,String User)
	{

		Query query = getSession().createQuery("select model.folio from  Solicitud as model  where model.id in"+
				"(select distinct solappoim.id from SolicitudAppointment as solappoim where solappoim.id in"+
				"(select distinct documento.solicitud from DocumentoSolicitud as documento where documento.pedimentos like "+impedi+" or documento.bls like "+bsl+" or documento.linea_naviera="+linea+" or documento.ferrocarril="+ferrocarril+")) and model.user.id ="+User+"");
		@SuppressWarnings("unchecked")
		List<String> queryResult = (List<String>)query.list();
			
			System.out.println("resultado" + query);
		
		return queryResult;	
	}
	
	public List<String> getpedimentos(String bsl)
	{

		Query query = getSession().createQuery("select model.folio from  Solicitud as model  where model.id in"+
				"(select distinct solappoim.solicitud.folio from SolicitudAppointment as solappoim where solappoim.pedimento_n4 like "+bsl+")");
		//Query query = getSession().createQuery("select distinct solappoim.solicitud.folio from SolicitudAppointment as solappoim where solappoim.pedimento_n4 like "+bsl);
		@SuppressWarnings("unchecked")
		List<String> queryResult = (List<String>)query.list();
			System.out.println("resultado" + query);
		return queryResult;	
	}
	
	public List<String> getdocumentostodos(String bsl,String impedi,String linea,String ferrocarril,String User)
	{

		Query query = getSession().createQuery("select model.folio from  Solicitud as model  where model.id in"+
				"(select distinct solappoim.id from SolicitudAppointment as solappoim where solappoim.id in"+
				"(select distinct documento.solicitud from DocumentoSolicitud as documento where documento.pedimentos like "+impedi+" or documento.bls like "+bsl+" or documento.linea_naviera="+linea+" or documento.ferrocarril="+ferrocarril+"))");
		@SuppressWarnings("unchecked")
		List<String> queryResult = (List<String>)query.list();
			
			System.out.println("resultado" + query);
		
		return queryResult;	
	}
	
	public List<String> getdocumentoscon(String bsl,String impedi,String linea,String ferrocarril,String User)
	{

		Query query = getSession().createQuery("select model.folio from  Solicitud as model  where model.id in"+
				"(select distinct solappoim.id from SolicitudAppointment as solappoim where solappoim.id in"+
				"(select distinct documento.solicitud from DocumentoSolicitud as documento where documento.pedimentos like "+impedi+" or documento.bls like "+bsl+" or documento.linea_naviera="+linea+" or documento.ferrocarril="+ferrocarril+")) and model.agenciaAduanalId ="+User+"");
		@SuppressWarnings("unchecked")
		List<String> queryResult = (List<String>)query.list();
			
			System.out.println("resultado" + query);
		
		return queryResult;	
	}
	
	public List<String> getdocumentostra(String bsl,String impedi,String linea,String ferrocarril,String User)
	{

		Query query = getSession().createQuery("select model.folio from  Solicitud as model  where model.id in"+
				"(select distinct solappoim.id from SolicitudAppointment as solappoim where solappoim.id in"+
				"(select distinct documento.solicitud from DocumentoSolicitud as documento where documento.pedimentos like "+impedi+" or documento.bls like "+bsl+" or documento.linea_naviera="+linea+" or documento.ferrocarril="+ferrocarril+")) "
						+ "and solappoim.transportistaId in "+
				"(select user.userN4_Id from User as user where user.id  = "+User+")) and "+ 
				"model.agenciaAduanalId in (select tras.user_n4_id  from UserTransportistas as tras where tras.transportista_id in(select user.userN4_Id from User as user where user.id  = "+User+" and tras.nivel <> 4))");
		@SuppressWarnings("unchecked")
		List<String> queryResult = (List<String>)query.list();
			
			System.out.println("resultado" + query);
		
		return queryResult;	
	}
	
	public List<String>  getWithcontenedorAA(String user, String contenedor) throws Exception
	{
		//String n ="\"contenedor\":{\"id\":354932082,\"label\":\"MSCU6722836\"}"; appointmentNbr
		
		String n =contenedor;
		Query query = getSession().createQuery("select model.folio from  Solicitud as model  where model.id in"+
		"(select distinct solappoim.solicitud.folio from SolicitudAppointment as solappoim where solappoim.definition like "+ n+" and model.user.id ="+user+")");
		System.out.println("resultado" + query);
		System.out.println("resultado" + contenedor);
		//query.setParameter("searchKeyword", "%"+n);
		@SuppressWarnings("unchecked")
		List<String> queryResult = (List<String>)query.list();
			
			System.out.println("resultado" + query);
		
		return queryResult;	
	}
	//consulta y admin clie
	public List<String>  getWithcontenedorcon(String user, String contenedor) throws Exception
	{
		//String n ="\"contenedor\":{\"id\":354932082,\"label\":\"MSCU6722836\"}"; appointmentNbr
		//'%"contenedor"%"FCIU4362498"%'
		//305981
		String n =contenedor;
		Query query = getSession().createQuery("select model.folio from  Solicitud as model  where model.id in"+
		"(select distinct solappoim.solicitud.folio from SolicitudAppointment as solappoim where solappoim.definition like "+ n+" and model.agenciaAduanalId ="+user+")");
		System.out.println("resultado" + query);
		System.out.println("resultado" + contenedor);
		@SuppressWarnings("unchecked")
		List<String> queryResult = (List<String>)query.list();
			
			System.out.println("resultado---" + query);
		
		return queryResult;	
	}
	//trans
	public List<String>  getWithcontenedortran(String user, String contenedor) throws Exception
	{
		//String n ="\"contenedor\":{\"id\":354932082,\"label\":\"MSCU6722836\"}"; appointmentNbr
		
		String n =contenedor;
		Query query = getSession().createQuery("select model.folio from Solicitud as model where model.id in "+
				"(select distinct solappoim.solicitud.folio from SolicitudAppointment as solappoim where solappoim.definition like "+ n+" and solappoim.transportistaId in "+
				"(select user.userN4_Id from User as user where user.id  = "+user+")) and "+ 
				"model.agenciaAduanalId in (select tras.user_n4_id  from UserTransportistas as tras where tras.transportista_id in(select user.userN4_Id from User as user where user.id  = "+user+" and tras.nivel <> 4))");
		System.out.println("resultado" + query);
		System.out.println("resultado" + contenedor);
		//query.setParameter("searchKeyword", "%"+n);
		@SuppressWarnings("unchecked")
		List<String> queryResult = (List<String>)query.list();
			
			System.out.println("resultado" + query);
		
		return queryResult;	
	}
	public DaoResult<Solicitud>queryN4(DaoQuery query, DaoOrder order,List<String> listid)
	{
		//List<String>app_nbr = new ArrayList<String>();
		Criteria criteria = getCriteria();
		//String test= query.getFieldAliasFromPath("nbrField", getCriteria());
		query.applyFetchingToCriteria(criteria);
		String[] eqs = {"folio", "status", "operationType", "model.agenciaAduanalId"};//model.agenciaAduanalId		
		registerEqPaths(eqs, query, criteria);
		criteria.add(Restrictions.in("folio", listid));
		String[] likes = {"reference", "model.agenciaAduanalId"};
		registerFullLikePaths(likes, query, criteria);
		DaoResult<Solicitud> result = new DaoResult<Solicitud>();
		result.processWith(query, order, criteria);
		// return the result
		return result;
	}
    
/*	public DaoResult<Solicitud>query(DaoQuery query, DaoOrder order,String userN4)
	{
		Criteria criteria = getCriteria();
//		String test= query.getFieldAliasFromPath("nbrField", getCriteria());
		query.applyFetchingToCriteria(criteria);
		String[] eqs = {"folio", "status", "operationType","user"};
		registerEqPaths(eqs, query, criteria);
		String[] likes = {"reference", "user.username"};
		registerFullLikePaths(likes, query, criteria);
		DaoResult<Solicitud> result = new DaoResult<Solicitud>();
		result.processWith(query, order, criteria);
		// return the result
		return result;
	}*/
	public User getUserFrom(Solicitud solicitud) throws Exception
	{
		// do a none lock on the object that contains the User
		getSession().buildLockRequest(LockOptions.NONE).lock(solicitud);
		// retrieve the User from the solicitud
		User obj = solicitud.getUser();
		
		// touch the proxy object so it gets the full contents of the User
		obj.getId();
		
		return obj;
	}
	
 
	public List<String> getColumnValues(String column, String match, int maxResults) throws Exception
	{
		//Query query = getSession().createQuery("select solappoim.appointmentNbr from SolicitudAppointment as solappoim,Solicitud as model  where model.agenciaAduanalId = '305975'");
		
		Query query = getSession().createQuery("select distinct " + column + " from Solicitud where " + column + " like ? order by " + column + " asc");
		query.setString(0, match);
		
		if(maxResults != 0)
			query.setMaxResults(maxResults);
		
		@SuppressWarnings("unchecked")
		List<String> queryResult = (List<String>)query.list();
		
		return queryResult;
	}
	
    public List<String> getSolicitudByN4(String userN4, String idUser) throws Exception
	{//odel.agenciaAduanalId = "+userN4+"
		Query query = getSession().createQuery("select model.folio from Solicitud as model where model.agenciaAduanalId = "+userN4+" ))");
		@SuppressWarnings("unchecked")
		List<String> queryResult = (List<String>)query.list();
		
		List<String> queryResult1 = getSolicitudByN4(idUser);

		queryResult.addAll(queryResult1);
	    //queryResult1.addAll(idUser);
		//List<String> queryResu = (idUser);
		return queryResult;		
	}  
	public List<String> getSolicitudByN4(String idUser) throws Exception
	{
		//TRANSPORTISTAS
		Query query = getSession().createQuery("select model.folio from Solicitud as model where model.id in "+
							"(select distinct solappoim.solicitud.folio from SolicitudAppointment as solappoim where solappoim.transportistaId in "+
							"(select user.userN4_Id from User as user where user.id  = "+idUser+")) and "+ 
							"model.agenciaAduanalId in (select tras.user_n4_id  from UserTransportistas as tras where tras.transportista_id in(select user.userN4_Id from User as user where user.id  = "+idUser+" and tras.nivel <> 4))");
		@SuppressWarnings("unchecked")
		List<String> queryResult = (List<String>)query.list();
		return queryResult;
	}
	
    public List<String> getSolicitudBy_(String user) throws Exception
		{
    	System.out.print(user);
    	Query query = getSession().createQuery("select model.folio from Solicitud as model where user.id = "+ user);
@SuppressWarnings("unchecked")
			List<String> queryResult = (List<String>)query.list();
			return queryResult;		
		}
	
	public List<String> getSolicitudByIdUser(String idUser)throws Exception{
		Query query = getSession().createQuery("select model.folio from Solicitud as model where user.id = "+ idUser);

		@SuppressWarnings("unchecked")
		List<String> queryResult = (List<String>)query.list();
		return queryResult;
	}
}
