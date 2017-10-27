package com.atp.solicitudes.model;

public class Special 
{ 
	private String special;
	private String descripcion;
	private String id_hold;

	public boolean equals(Object anObject)
	{
		if (anObject == null)
			return false;

		if (this == anObject)
			return true;

		if (!(anObject instanceof Special))
			return false;

		Special realObject = (Special) anObject;

		return this.getSpecial().equals(realObject.getSpecial());
	}
	
	public int hashCode()
	{
		return getSpecial().hashCode();
	}

	public String getDescripcion()
	{
		return descripcion;
	}

	public void setid_hold(String id_hold)
	{
		this.id_hold = id_hold;
	}
	public String getid_hold()
	{
		return id_hold;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	public String getSpecial()
	{
		return special;
	}

	public void setSpecial(String special)
	{
		this.special = special;
	}
}
