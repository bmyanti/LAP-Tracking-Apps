package com.example.modellap;

public class ChildARV_Model {
	private String Child_id;
	private String ARV_Type_ID;
	//dosis pagi
	private String ARV_Dose_ID;
	//dosis malam
	private String ARV_Dose_ID1;
	
	public String GetChild_ID()
	{
		return this.Child_id;
	}
	
	public String GetDrugTypeID()
	{
		return this.ARV_Type_ID;
	}
	
	public String GetDrugDoseID()
	{
		return this.ARV_Dose_ID;
	}
	public String GetDrugDoseID1()
	{
		return this.ARV_Dose_ID1;
	}
	
	public void SetChildID(String Child_id)
	{
		this.Child_id = Child_id;
	}
	
	public void SetARVTypeID(String ARV_Type_ID)
	{
		this.ARV_Type_ID = ARV_Type_ID;
	}
	
	public void SetARVDoseID(String ARV_Dose_ID)
	{
		this.ARV_Dose_ID = ARV_Dose_ID;
	}
	public void SetARVDoseID1(String ARV_Dose_ID)
	{
		this.ARV_Dose_ID1 = ARV_Dose_ID;
	}
}
