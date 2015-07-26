package com.example.modellap;

import java.io.Serializable;

public class Environment_Model implements Serializable{
	private String Environment_ID;
	private String Environment_Score;
	
	public void SetEnvironment_ID(String id_env)
	{
		this.Environment_ID = id_env;
	}
	public void SetEnvironment_Score(String score_env)
	{
		this.Environment_Score = score_env;
	}
	public String GetEnvironmentID()
	{
		return this.Environment_ID;
	}
	
	public String GetEnvironmentScore()
	{
		return this.Environment_Score;
	}
}
