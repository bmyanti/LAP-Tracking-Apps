package com.example.modellap;

import android.os.Parcel;
import android.os.Parcelable;

public class Visit_Facility implements Parcelable{
	private String Facility_id;
	private String Child_id;
	private String Facility_cost_id;
	private String Facility_Quantity;
	private String Visit_Date;
	
	public Visit_Facility()
	{
		
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
	public String getVisitDate()
	{
		return this.Visit_Date;
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
	public void SetVisitDate(String Visit_Date)
	{
		this.Visit_Date = Visit_Date;
	}

	protected Visit_Facility(Parcel in) {
		Facility_id = in.readString();
		Child_id = in.readString();
		Facility_cost_id = in.readString();
		Facility_Quantity = in.readString();
		Visit_Date = in.readString();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(Facility_id);
		dest.writeString(Child_id);
		dest.writeString(Facility_cost_id);
		dest.writeString(Facility_Quantity);
		dest.writeString(Visit_Date);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Visit_Facility> CREATOR = new Parcelable.Creator<Visit_Facility>() {
		@Override
		public Visit_Facility createFromParcel(Parcel in) {
			return new Visit_Facility(in);
		}

		@Override
		public Visit_Facility[] newArray(int size) {
			return new Visit_Facility[size];
		}
	};
}
