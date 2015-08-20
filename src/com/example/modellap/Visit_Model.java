package com.example.modellap;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Visit_Model implements Parcelable {
	private String Visit_date;
	private String Child_id;
	private String Visit_Type_Id;
	private String Note;
	private String Height;
	private String Weight;
	private String LILA;
	private String BMI_Score;
	private String ARV_Taken;
	private String Reminder_ID;
	// complaint
	private ArrayList<Complaint_Model> Complaints;
	// environment
	private ArrayList<Environment_Model> Environments;
	// fasilitas
	private ArrayList<Visit_Facility> ChildFacilities;
//	//foto anak
//	private ArrayList<String> Child_Photos;
//	//foto lingkungan
//	private ArrayList<String> Environment_Photos;
	//foto
	private ArrayList<Image_Model> Photos;
	private ArrayList<String> creat_time;
	private ArrayList<String> vis_type_id;


	public String GetVisitDate() {
		return this.Visit_date;
	}

	public String GetChildID() {
		return this.Child_id;
	}

	public String GetVisitTypeID() {
		return this.Visit_Type_Id;
	}

	public String GetNote() {
		return this.Note;
	}

	public String GetHeight() {
		return this.Height;
	}

	public String GetWeight() {
		return this.Weight;
	}

	public String GetLILA() {
		return this.LILA;
	}

	public String GetBMIScore() {
		return this.BMI_Score;
	}
	public ArrayList<String> GetCreatTime()
	{
		return this.creat_time;
	}
	

	public String GetARVTaken() {
		return this.ARV_Taken;
	}

	public String GetReminderID() {
		return this.Reminder_ID;
	}

	public ArrayList<Visit_Facility> GetFasilitasKunjungan() {
		return this.ChildFacilities;
	}

	public ArrayList<Complaint_Model> GetComplaints() {
		return this.Complaints;
	}

	public ArrayList<Environment_Model> GetEnvironment() {
		return this.Environments;
	}
	
	public ArrayList<Image_Model> GetPhotos()
	{
		return this.Photos;
	}
	public ArrayList<String> GetVisitTypId()
	{
		return this.vis_type_id;
	}

	// setter

	public void setVisitDate(String Visit_date) {
		this.Visit_date = Visit_date;
	}

	public void setChildID(String Child_id) {
		this.Child_id = Child_id;
	}

	public void setVisitTypeID(String Visit_Type_Id) {
		this.Visit_Type_Id = Visit_Type_Id;
	}

	public void setNote(String Note) {
		this.Note = Note;
	}

	public void setHeight(String Height) {
		this.Height = Height;
	}

	public void setWeight(String Weight) {
		this.Weight = Weight;
	}

	public void setLILA(String LILA) {
		this.LILA = LILA;
	}
	public void setCreatTime(ArrayList<String> creat_time)
	{
		this.creat_time = creat_time;
	}

	public void setBMIScore(String BMI_Score) {
		this.BMI_Score = BMI_Score;
	}

	public void setARVTaken(String ARV_Taken) {
		this.ARV_Taken = ARV_Taken;
	}

	public void setReminder_ID(String Reminder_ID) {
		this.Reminder_ID = Reminder_ID;
	}

	public void setComplaints(ArrayList<Complaint_Model> Complaints) {
		this.Complaints = Complaints;
	}

	public void setFasilitasKunjungan(ArrayList<Visit_Facility> ChildFacilities) {
		this.ChildFacilities = ChildFacilities;
	}

	public void setEnvironments(ArrayList<Environment_Model> Environments) {
		this.Environments = Environments;
	}
	
	public void setPhotos(ArrayList<Image_Model> Photos)
	{
		this.Photos = Photos;
	}
	public void setVisTypId(ArrayList<String> vis_typ_id)
	{
		this.vis_type_id = vis_typ_id;
	}

	
	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(Child_id);
		out.writeString(Visit_date);
		out.writeString(Visit_Type_Id);
		out.writeString(Height);
		out.writeString(Weight);
		out.writeString(LILA);
		out.writeString(Note);
		out.writeString(ARV_Taken);
		out.writeTypedList(Complaints);
		out.writeTypedList(Environments);
		out.writeTypedList(ChildFacilities);
		out.writeTypedList(Photos);
	}

	private Visit_Model(Parcel in) {
		Child_id = in.readString();
		Visit_date = in.readString();
		Visit_Type_Id = in.readString();
		Height = in.readString();
		Weight = in.readString();
		LILA = in.readString();
		Note = in.readString();
		ARV_Taken = in.readString();
		Complaints = in.createTypedArrayList(Complaint_Model.CREATOR);
		Environments = in.createTypedArrayList(Environment_Model.CREATOR);
		ChildFacilities = in.createTypedArrayList(Visit_Facility.CREATOR);
		Photos = in.createTypedArrayList(Image_Model.CREATOR);
	}

	public Visit_Model() {
		Complaints = new ArrayList<Complaint_Model>();
		ChildFacilities = new ArrayList<Visit_Facility>();
		Photos = new ArrayList<Image_Model>();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	 public static final Parcelable.Creator<Visit_Model> CREATOR= new Parcelable.Creator<Visit_Model>() {

 // This simply calls our new constructor (typically private) and 
 // passes along the unmarshalled `Parcel`, and then returns the new object!
	 @Override
	 public Visit_Model createFromParcel(Parcel in) {
	     return new Visit_Model(in);
	 }
	 @Override
     public Visit_Model[] newArray(int size) {
         return new Visit_Model[size];
     }
 };
}
