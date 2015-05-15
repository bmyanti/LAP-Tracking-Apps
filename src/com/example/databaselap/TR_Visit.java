package com.example.databaselap;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TR_Visit {
	//deklarasi row
	
	private static final String ROW_CHILD_ID = "child_id";
	private static final String ROW_VISIT_DATE = "visit_date";
	private static final String ROW_COMPLAINT_ID = "complaint_id";
	private static final String ROW_VISIT_TYPE_ID = "visit_type_id";
	private static final String ROW_UNIT_ID = "unit_Id";
	private static final String ROW_UNIT_RESULT = "unit_result";
	private static final String ROW_ACTION = "action";
	private static final String ROW_NOTE = "note";
	private static final String ROW_FACILITY_ID = "facility_id";
	private static final String ROW_DRUG_TAKEN = "drug_taken";
	private static final String ROW_PARAM_ID = "param_id";
	private static final String ROW_PARAM_GROUP = "param_group";
	private static final String ROW_FACILITY_QUANTITY = "facility_quantity";
	private static final String ROW_ENVIRONMENT_ID = "environment_id";
	private static final String ROW_CREATED_BY = "created_by";
	private static final String ROW_CREATED_TIME = "created_time";
	private static final String ROW_UPDATE_BY = "update_by";
	private static final String ROW_UPDATE_TIME = "update_time";
	
	
	//mendeklarasikan NAMA_DB DAN TABLE DAN DATABASE VERSION
		private static final String NAMA_DB ="DB_LAP";
		private static final String NAMA_TABEL="TM_VISIT";
		private static final int DB_VERSION=1;
	
	//mendeklarasikan CREATE_TABLE = MEMBUAT TABLE" 
		private static final String CREATE_TABLE ="create table "+NAMA_TABEL+"("+ROW_VISIT_DATE+" integer PRIMARY KEY autoincrement," +
				""+ROW_CHILD_ID+"varchar PRIMARY KEY,"+ROW_COMPLAINT_ID+"varchar PRIMARY KEY,"+ROW_VISIT_TYPE_ID+"varchar PRIMARY KEY,"+ROW_UNIT_ID+"varchar PRIMARY KEY,"+ROW_UNIT_RESULT+"varchar,"+ROW_ACTION+"VARCHAR,"+ROW_NOTE+"text," +
						""+ROW_FACILITY_ID+"varchar PRIMARY KEY,"+ROW_DRUG_TAKEN+"varchar,"+ROW_PARAM_ID+"varchar PRIMARY KEY,"+ROW_PARAM_GROUP+"varchar PRIMARY KEY,"+ROW_FACILITY_QUANTITY+"varchar,"+ROW_ENVIRONMENT_ID+"varchar PRIMARY KEY," +
								""+ROW_CREATED_BY+"VARCHAR,"+ROW_CREATED_TIME+"VARCHAR," +""+ROW_UPDATE_BY+"VARCHAR,"+ROW_UPDATE_TIME+"VARCHAR,)";
		
		//membuat mendeklarasikan Context itu adalah context
		private final Context context;
		//membuat mendeklarasikan DatabaseOpenHelper itu adalah dbhelper
		private DatabaseOpenHelper dbhelper;
		//membuat mendeklarasikan SQLiteDatabase itu adalah db
		private SQLiteDatabase db;
		
	public TR_Visit(Context ctx)
	{
		//mendeklarasikan ctx adalah context ( context context di ganti ctx )
		this.context = ctx;
		// membuat DatabaseOpenHelper 
		dbhelper = new DatabaseOpenHelper(context);
		//menuliskan DatabaseOpenHelper = SQLiteDatabase
		db = dbhelper.getWritableDatabase();
	}
	private static class DatabaseOpenHelper extends SQLiteOpenHelper
	{

		//membuat database
				public DatabaseOpenHelper(Context context) {
					super(context, NAMA_DB, null, DB_VERSION);
					// TODO Auto-generated constructor stub
		}

				@Override
				public void onCreate(SQLiteDatabase db) {
					// TODO Auto-generated method stub
					db.execSQL(CREATE_TABLE);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
						db.execSQL("DROP TABLE IF EXISTS "+NAMA_DB);
						onCreate(db);
			
		}
		
	}
	//menutup DatabaseOpenHelper
			public void close() {
				dbhelper.close();
			}
	
			public void addRow(String visit_date , String child_id , String visit_type_id ,
					String unit_id , String unit_result , String note , String action ,
					String facility_id , String drug_taken , String param_id , String param_group ,
					String facility_quantity , String environment_id ,
					String created_by , String created_time ,String update_by , String update_time , String complaint_id
					)
			{
				ContentValues values = new ContentValues();
				values.put(ROW_VISIT_DATE, visit_date);
				values.put(ROW_CHILD_ID, child_id);
				values.put(ROW_COMPLAINT_ID, complaint_id);
				values.put(ROW_VISIT_TYPE_ID, visit_type_id);
				values.put(ROW_UNIT_ID , unit_id);
				values.put(ROW_UNIT_RESULT, unit_result);
				values.put(ROW_ACTION, action);
				values.put(ROW_NOTE, note);
				values.put(ROW_FACILITY_ID, facility_id);
				values.put(ROW_DRUG_TAKEN, drug_taken);
				values.put(ROW_PARAM_ID, param_id);
				values.put(ROW_PARAM_GROUP, param_group);
				values.put(ROW_FACILITY_QUANTITY, facility_quantity);
				values.put(ROW_ENVIRONMENT_ID, environment_id);
				values.put(ROW_CREATED_BY, created_by);
				values.put(ROW_CREATED_TIME, created_time);
				values.put(ROW_UPDATE_BY, update_by);
				values.put(ROW_UPDATE_TIME, update_time);
				
				
				try {
					//menambahkan nama tabel bila tidak akan error
					//	db.delete(NAMA_TABEL, null, null);
						db.insert(NAMA_TABEL, null, values);
					
				} catch (Exception e) {
					// TODO: handle exception
					Log.e("DB ERROR", e.toString());
					e.printStackTrace();
					
				}
			}
			//membuat array pada table layout
			public ArrayList<ArrayList<Object>> ambilSemuaBaris()
			{
				ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
				Cursor cur;
				
				try {
					cur = db.query(NAMA_TABEL, 
							new String[]{ROW_VISIT_DATE, ROW_CHILD_ID,ROW_VISIT_TYPE_ID,ROW_COMPLAINT_ID,
							ROW_VISIT_TYPE_ID,ROW_UNIT_ID,ROW_UNIT_RESULT,
							ROW_ACTION, ROW_NOTE, ROW_FACILITY_ID, ROW_DRUG_TAKEN, 
							ROW_PARAM_ID , ROW_PARAM_GROUP,ROW_FACILITY_ID , ROW_FACILITY_QUANTITY , ROW_ENVIRONMENT_ID,
							ROW_CREATED_BY,ROW_CREATED_TIME, ROW_UPDATE_BY, ROW_UPDATE_TIME,
							}, null, null,
							null, null, null);
					cur.moveToFirst();
					if(!cur.isAfterLast())
					{
						do
						{
							ArrayList<Object> dataList = new ArrayList<Object>();
							dataList.add(cur.getLong(0));
							dataList.add(cur.getString(1));
							dataList.add(cur.getString(2));
							dataList.add(cur.getString(3));
							dataList.add(cur.getString(4));
							dataList.add(cur.getString(5));
							dataList.add(cur.getString(6));
							dataList.add(cur.getString(7));
							dataList.add(cur.getString(8));
							dataList.add(cur.getString(9));
							dataList.add(cur.getString(10));
							dataList.add(cur.getString(11));
							dataList.add(cur.getString(12));
							dataList.add(cur.getString(13));
							
							
							dataArray.add(dataList);
						}while (cur.moveToNext());
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					Log.e("DEBE ERROR", e.toString());
				}
				return dataArray;
			}		
	
}
