package com.atp.solicitudes.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;

import com.atp.solicitudes.dao.BookingDao;
import com.atp.solicitudes.model.Booking;
import com.atp.solicitudes.model.BookingItem;
import com.objectwave.dao.utils.DaoOrder;
import com.objectwave.dao.utils.DaoQuery;
import com.objectwave.dao.utils.DaoResult;
import com.objectwave.dao.utils.MyBatisDaoTemplate;
import com.objectwave.utils.SimpleEntry;

public class BookingDaoMyBatis extends MyBatisDaoTemplate implements BookingDao
{
	static final String MYBATIS_NAMESPACE = "mybatis.BookingMapper";

	public BookingDaoMyBatis(SqlSessionFactory sqlSessionFactory)
	{
		super(sqlSessionFactory);
	}

	public Booking getWithId(Integer aValue) throws Exception
	{
		return (Booking) selectOne(MYBATIS_NAMESPACE + ".selectById", aValue);
	}

	public Booking getWithNombre(String aValue) throws Exception
	{
		return (Booking) selectOne(MYBATIS_NAMESPACE + ".selectByNombre", aValue);
	}

	public List<SimpleEntry> getDistinct(String match, int maxResults) throws Exception
	{
		Map<String, String> map = new HashMap<String, String>();

		map.put("columnName", "BOOKING");
		map.put("columnValue", match);

		RowBounds rowBounds = new RowBounds(0, maxResults);

		List<SimpleEntry> collection = selectList(MYBATIS_NAMESPACE + ".selectDistinct", map, rowBounds);
		return collection;
	}
	
	public List<Booking> getEqualsByName(String name, int maxResults) throws Exception
	{
		Map<String, String> map = new HashMap<String, String>();

		map.put("columnName", "BOOKING");
		map.put("columnValue", name);

		RowBounds rowBounds = new RowBounds(0, maxResults);
		
		List<Booking> collection = selectList(MYBATIS_NAMESPACE + ".selectEqualsByName", map, rowBounds);

		return collection;
	}
	
	public DaoResult<Booking> getWithIdForType(DaoQuery query, DaoOrder order) throws Exception
	{
		DaoResult<Booking> result = new DaoResult<Booking>();
		result.setCursorPropertiesFrom(query);

		Map<String, Object> map = new HashMap<String, Object>();

		String[] equals = { "id", "id" };
		registerEqPaths(equals, query, map);

	/*	if (query.getRequestTotalRows())
		{
			Long totalRows = selectOne(MYBATIS_NAMESPACE + ".selectCount", map);
			result.setTotalRows(new Long(totalRows));
		}
		*/
		registerMultipleOrder(order, map);
		List<Booking> col = this.<Booking> selectList(MYBATIS_NAMESPACE + ".selectById", map, getRowBoundsFrom(query));
		result.setCollection(col);

		return result;
	}
}
