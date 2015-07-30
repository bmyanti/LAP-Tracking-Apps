package com.example.modellap;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class Environment_Model implements Parcelable {
	private String Environment_ID;
	private String Environment_Score;

	public Environment_Model() {

	}

	public void SetEnvironment_ID(String id_env) {
		this.Environment_ID = id_env;
	}

	public void SetEnvironment_Score(String score_env) {
		this.Environment_Score = score_env;
	}

	public String GetEnvironmentID() {
		return this.Environment_ID;
	}

	public String GetEnvironmentScore() {
		return this.Environment_Score;
	}

	protected Environment_Model(Parcel in) {
		Environment_ID = in.readString();
		Environment_Score = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(Environment_ID);
		dest.writeString(Environment_Score);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Environment_Model> CREATOR = new Parcelable.Creator<Environment_Model>() {
		@Override
		public Environment_Model createFromParcel(Parcel in) {
			return new Environment_Model(in);
		}

		@Override
		public Environment_Model[] newArray(int size) {
			return new Environment_Model[size];
		}
	};

}
