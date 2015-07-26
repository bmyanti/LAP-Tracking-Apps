package com.example.modellap;

import java.io.Serializable;

public class ChildFacility_Model implements Serializable{
	
	private String Child_facility_id;
	private String Child_id;
	private String Facility_id;
	private String Facility_cost_id;
	private String Facility_Quantity;
	
	public String getChild_facility_id()
	{
		return this.Child_facility_id;
	}
	public String getChild_id()
	{
		return this.Child_id;
	}
	public String getFacility_id()
	{
		return this.Facility_id;
	}
	public String getFacility_cost_id()
	{
		return this.Facility_cost_id;
	}
	
	public void setChild_facility_id(String Child_facility_id)
	{
		this.Child_facility_id=Child_facility_id;
	}
	public void setChild_id(String Child_id)
	{
		this.Child_id=Child_id;
	}
	public void setFacility_id(String Facility_id)
	{
		this.Facility_id=Facility_id;
	}
	public void setFacility_cost_id(String Facility_cost_id)
	{
		this.Facility_cost_id=Facility_cost_id;
	}
	public String GetFacilityQty()
	{
		return this.Facility_Quantity;
	}
	public void SetFacilityQty(String qty)
	{
		this.Facility_Quantity = qty;
	}
}
