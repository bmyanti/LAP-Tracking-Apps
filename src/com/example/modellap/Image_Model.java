package com.example.modellap;

import android.os.Parcel;
import android.os.Parcelable;

public class Image_Model implements Parcelable{
	//db.InsertPhoto(image_name, 
	//image_date, image_id, image_path, image_server_path, 
	//image_type_id, image_longitude, image_latitude, cb, ct);
	private String image_name;
	private String image_date;
	private String  image_id;
	private String  image_path;
	private String image_server_path;
	private String image_type_id;
	private String image_longitude;
	private String image_latitude;
	
	
	public Image_Model()
	{
		
	}
	public Image_Model(String image_name, String image_date, String image_id, String image_path, String image_server_path, String image_type_id, String longitude, String latitude)
	{
		this.image_name = image_name;
		this.image_date = image_date;
		this.image_id = image_id;
		this.image_path = image_path;
		this.image_server_path  = image_server_path;
		this.image_type_id = image_type_id;
		this.image_longitude = longitude;
		this.image_latitude = latitude;
	}
	
	public String GetImage_name()
	{
		return this.image_name;
	}
	
	public String GetImage_date()
	{
		return this.image_date;
	}
	public String GetImage_id()
	{
		return this.image_id;
	}
	public String GetImage_path()
	{
		return this.image_path;
	}
	public String GetImage_server_path()
	{
		return this.image_server_path;
	}
	public String GetImage_type_id()
	{
		return this.image_type_id;
	}
	public String GetImage_longitude()
	{
		return this.image_longitude;
	}
	public String GetImage_latitude()
	{
		return this.image_latitude;
	}
	
	public void SetImageName(String image_name)
	{
		this.image_name = image_name;
	}
	
	public void SetImageDate(String image_date)
	{
		this.image_date = image_date;
	}
	
	public void SetImagePath(String image_path)
	{
		this.image_path = image_path;
	}
	
	public void SetImageId(String image_id)
	{
		this.image_id = image_id;
	}
	
	public void SetImageServerPath(String image_server_path)
	{
		this.image_server_path = image_server_path;
	}
	
	public void SetImageTypeId(String image_type_id)
	{
		this.image_type_id = image_type_id;
	}
	
	public void SetImageLongitude(String image_longitude)
	{
		this.image_longitude = image_longitude;
	}
	
	public void SetImageLatitude(String image_latitude)
	{
		this.image_latitude = image_latitude;
	}

	protected Image_Model(Parcel in)
	{
		image_name = in.readString();
		image_date= in.readString();
		image_id= in.readString();
		image_path= in.readString();
		image_server_path= in.readString();
		image_type_id= in.readString();
		image_longitude= in.readString();
		image_latitude= in.readString();
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(image_name);
		dest.writeString(image_date);
		dest.writeString(image_id);
		dest.writeString(image_path);
		dest.writeString(image_server_path);
		dest.writeString(image_type_id);
		dest.writeString(image_longitude);
		dest.writeString(image_latitude);
	}
	
	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Image_Model> CREATOR = new Parcelable.Creator<Image_Model>() {
		@Override
		public Image_Model createFromParcel(Parcel in) {
			return new Image_Model(in);
		}

		@Override
		public Image_Model[] newArray(int size) {
			return new Image_Model[size];
		}
	};
	
}
