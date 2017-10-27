package com.atp.solicitudes.dao;

import java.util.List;

import com.atp.solicitudes.model.Solicitud;
import com.atp.solicitudes.model.User;
import com.objectwave.dao.utils.DaoOrder;
import com.objectwave.dao.utils.DaoQuery;
import com.objectwave.dao.utils.DaoResult;

public interface SolicitudDao
{
	Solicitud getWithFolio(Integer folio) throws Exception;
	void save(Solicitud solicitud) throws Exception;
	DaoResult<Solicitud> query(DaoQuery query, DaoOrder order) throws Exception;
	DaoResult<Solicitud> queryN4(DaoQuery query, DaoOrder order,List<String> user) throws Exception;
	User getUserFrom(Solicitud solicitud) throws Exception;
	List<String> getColumnValues(String column, String match, int maxResults) throws Exception;
	List<String> getSolicitudByN4(String userN4,String idUser) throws Exception;
	List<String> getSolicitudByN4(String idUser) throws Exception;
	//prueba
	List<String> getSolicitudBy_(String User) throws Exception;
	List<String> getSolicitudByIdUser(String idUser)throws Exception;
	List<String>  getWithcontenedor(String user, String contenedor) throws Exception;
	List<String>  getWithcontenedorAA(String user, String contenedor) throws Exception;
	List<String>  getWithcontenedorcon(String user, String contenedor) throws Exception;
	List<String>  getWithcontenedortran(String user, String contenedor) throws Exception;
	List<String>  getdocumentos(String bls, String impe,String linea,String ferr,String u) throws Exception;
	List<String>  getdocumentostodos(String bls, String impe,String linea,String ferr,String u) throws Exception;
	List<String>  getdocumentoscon(String bls, String impe,String linea,String ferr,String u) throws Exception;
	List<String>  getdocumentostra(String bls, String impe,String linea,String ferr,String u) throws Exception;
	List<String>  getpedimentos(String bls) throws Exception;

	
}
