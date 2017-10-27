package com.atp.solicitudes.model;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.objectwave.enumerator.BasicEnum;
import com.objectwave.enumerator.EnumSerializer;
import com.objectwave.utils.ObjectWithIdResolver;

@JsonSerialize(using = EnumSerializer.class)
public enum UserPantallaEnum implements BasicEnum
{
	ACTIVE("Active"),
	DEACTIVATED("Inactive"),
	DELETED("Deleted");

	public static final UserPantallaEnum[] validDisplayValues = { ACTIVE, DEACTIVATED};
	public static final UserPantallaEnum[] allValues = { ACTIVE, DEACTIVATED, DELETED};
	
	public String name;

	UserPantallaEnum (String s)
	{
		name = s;
	}

	public static UserPantallaEnum withId(Integer id)
	{
		return values()[id];
	}

	public static List<UserPantallaEnum> getAll()
	{
		List <UserPantallaEnum> states = new ArrayList<UserPantallaEnum>();
		
		for (UserPantallaEnum state : allValues)
			states.add(state);

		return states;
	}
	
	public static List<UserPantallaEnum> getValidDisplayValues()
	{
		List <UserPantallaEnum> states = new ArrayList<UserPantallaEnum>();
		
		for (UserPantallaEnum state : validDisplayValues)
			states.add(state);

		return states;
	}

	public Integer getId()
	{
		return ordinal();
	}

	public String getName()
	{
		return name;
	}
	
	public static String getNameForId(Integer id)
	{
		return withId(id).getName();
	}
	
	public static class IdResolver implements ObjectWithIdResolver
	{
		public Object getWithId(Integer id)
		{
			return UserPantallaEnum.withId(id);
		}
	}
}