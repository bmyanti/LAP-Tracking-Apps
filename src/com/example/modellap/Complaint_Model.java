package com.example.modellap;

import android.os.Parcel;
import android.os.Parcelable;

public class Complaint_Model implements Parcelable {
	private String Keluhan;
	private String Status_Keluhan;
	private String Tindakan;

	public Complaint_Model() {

	}

	public Complaint_Model(String keluhan, String status, String tindakan) {
		super();
		this.Keluhan = keluhan;
		this.Status_Keluhan = status;
		this.Tindakan = tindakan;
	}

	public String GetKeluhan() {
		return this.Keluhan;
	}

	public String GetStatusKeluhan() {
		return this.Status_Keluhan;
	}

	public String GetTindakan() {
		return this.Tindakan;
	}

	public void SetKeluhan(String keluhan) {
		this.Keluhan = keluhan;
	}

	public void SetStatusKeluhan(String status_keluhan) {
		this.Status_Keluhan = status_keluhan;
	}

	public void SetTindakan(String tindakan) {
		this.Tindakan = tindakan;
	}

	protected Complaint_Model(Parcel in) {
		Keluhan = in.readString();
		Status_Keluhan = in.readString();
		Tindakan = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(Keluhan);
		dest.writeString(Status_Keluhan);
		dest.writeString(Tindakan);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Complaint_Model> CREATOR = new Parcelable.Creator<Complaint_Model>() {
		@Override
		public Complaint_Model createFromParcel(Parcel in) {
			return new Complaint_Model(in);
		}

		@Override
		public Complaint_Model[] newArray(int size) {
			return new Complaint_Model[size];
		}
	};

}
