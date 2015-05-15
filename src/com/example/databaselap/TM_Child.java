package com.example.databaselap;





import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TM_Child {
	//mendeklarasikan ROW
	private static final String ROW_CHILD_ID = "child_id";
	private static final String ROW_CHILD_NAME = "child_name";
	private static final String ROW_CHILD_BOD = "child_bod";
	private static final String ROW_CHILD_GENDER = "child_gender";
	private static final String ROW_CHILD_ADDRESS = "child_address";
	private static final String ROW_DRUG_TYPE_ID = "drug_type_id";
	private static final String ROW_PARENT_ID = "parent_id";
	private static final String ROW_CAREGIVER_NAME = "caregiver_name";
	private static final String ROW_CAREGIVER_ID = "caregiver_id";
	private static final String ROW_CAREGIVER_PHONE = "caregiver_phone";
	private static final String ROW_FATHER_NAME = "father_name";
	private static final String ROW_MOTHER_NAME = "mother_name";
	private static final String ROW_BLOOD_TYPE = "blood_type";
	private static final String ROW_SCHOOL_NAME = "school_name";
	private static final String ROW_SUBDISTRICT_ID = "subdistrict_id";
	private static final String ROW_CLASS_ID = "class_id";
	private static final String ROW_GROUP_ID = "group_id";
	private static final String ROW_SCHOOL_SUBDISTRICT_ID = "school_subdistrict_id";
	private static final String ROW_DAD_STATUS_ID = "dad_status_id";
	private static final String ROW_MOM_STATUS_ID = "mom_status_id";
	private static final String ROW_DRUG_TAKEN = "drug_taken";
	private static final String ROW_FACILITY_ID = "facility_id";
	private static final String ROW_DRUG_STATUS_ID = "drug_status_id";
	private static final String ROW_DRUG_DOSE_ID= "drug_dose_id";
	private static final String ROW_IMAGE_NAME = "image_name";
	private static final String ROW_IMAGE_PATH = "image_path";
	private static final String ROW_IMAGE_SERVER_PATH = "image_server_path";
	private static final String ROW_PARAM_ID = "param_Id";
	private static final String ROW_PARAM_GROUP = "param_group";
	private static final String ROW_CREATED_BY = "created_by";
	private static final String ROW_CREATED_TIME = "created_time";
	private static final String ROW_UPDATE_BY = "update_by";
	private static final String ROW_UPDATE_TIME = "update_time";
	
	
	//mendeklarasikan NAMA_DB DAN TABLE DAN DATABASE VERSION
	private static final String NAMA_DB ="INIDB";
	private static final String NAMA_TABEL="TM_Child";
	private static final int DB_VERSION=1;
	//mendeklarasikan CREATE_TABLE = MEMBUAT TABLE" 
	private static final String CREATE_TABLE = 
	"create table "+NAMA_TABEL+" ( "+ROW_CHILD_ID+" integer PRIMARY KEY autoincrement, "+ROW_CHILD_NAME+" text, "+ROW_CHILD_BOD+" date, " +	
			" "+ROW_CHILD_GENDER+" varchar, "+ROW_CHILD_ADDRESS+" text, "+ROW_DRUG_TYPE_ID+" varchar , "+ROW_PARENT_ID+" varchar, " +
					" "+ROW_CAREGIVER_NAME+" text, "+ROW_CAREGIVER_ID+" varchar, "+ROW_CAREGIVER_PHONE+" numeric, "+ROW_FATHER_NAME+" text, " +
							" "+ROW_MOTHER_NAME+" text, "+ROW_BLOOD_TYPE+" varchar, "+ROW_SCHOOL_NAME+" text, "+ROW_SUBDISTRICT_ID+" varchar, " +
									" "+ROW_CLASS_ID+" varchar, "+ROW_GROUP_ID+" varchar, "+ROW_SCHOOL_SUBDISTRICT_ID+" varchar, " +
											" "+ROW_DAD_STATUS_ID+" varchar, "+ROW_MOM_STATUS_ID+" varchar, "+ROW_DRUG_TAKEN+" varchar, "+ROW_FACILITY_ID+" varchar, " +
													" "+ROW_DRUG_STATUS_ID+" varchar, "+ROW_DRUG_DOSE_ID+" varchar, "+ROW_IMAGE_NAME+" text, "+ROW_IMAGE_PATH+" varchar, " +
															" "+ROW_IMAGE_SERVER_PATH+" varchar, "+ROW_PARAM_ID+" varchar, "+ROW_PARAM_GROUP+" varchar, " +
					" "+ROW_CREATED_BY+" VARCHAR, "+ROW_CREATED_TIME+" VARCHAR, " + " "+ROW_UPDATE_BY+" VARCHAR, "+ROW_UPDATE_TIME+" VARCHAR ) ";
	//"create table "+NAMA_TABEL+" ("+ROW_ID+" integer PRIMARY KEY autoincrement, "+ROW_NAMA+" text,"+ROW_SEKOLAH+" text, "+ROW_AGE+" varchar, "+ROW_TTL+" string)";
	//membuat mendeklarasikan Context itu adalah context
	private final Context context;
	//membuat mendeklarasikan DatabaseOpenHelper itu adalah dbhelper
	private DatabaseOpenHelper dbhelper;
	//membuat mendeklarasikan SQLiteDatabase itu adalah db
	private SQLiteDatabase db;
	
	
	public TM_Child(Context ctx) {
		//mendeklarasikan ctx adalah context ( context context di ganti ctx )
		this.context = ctx;
		// membuat DatabaseOpenHelper 
		dbhelper = new DatabaseOpenHelper(context);
		//menuliskan DatabaseOpenHelper = SQLiteDatabase
		db = dbhelper.getWritableDatabase();
	} 
	private static class DatabaseOpenHelper extends SQLiteOpenHelper {
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
		//memperbarui database bila sudah ada 
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+NAMA_DB);
			onCreate(db);
 
		}
	}
	//menutup DatabaseOpenHelper
	public void close() {
		dbhelper.close();
	}
	//menambahkan pada row
	public void addRow(String child_name /*, String child_bod,
			String child_gender , String blood_type , String father_name ,
			String mother_name ,
			String caregiver_name , String child_address , String caregiver_phone ,  String school_name */
			// String drug_taken
			// ,String image_name , String image_path , String image_server_path,
			//String created_by , String created_time ,String update_by , String update_time 
			) {
		 
		ContentValues values = new ContentValues();

		values.put(ROW_CHILD_NAME, child_name);
		/*values.put(ROW_CHILD_BOD, child_bod);
		values.put(ROW_CHILD_GENDER, child_gender);
		values.put(ROW_BLOOD_TYPE, blood_type);
		values.put(ROW_FATHER_NAME, father_name);
		values.put(ROW_MOTHER_NAME, mother_name);
		values.put(ROW_CAREGIVER_NAME, caregiver_name);
		values.put(ROW_CHILD_ADDRESS, child_address);


		

		values.put(ROW_CAREGIVER_PHONE, caregiver_phone);
		
		values.put(ROW_SCHOOL_NAME, school_name);*/

		//values.put(ROW_DRUG_TAKEN, drug_taken);


		//values.put(ROW_IMAGE_NAME, image_name);
		//values.put(ROW_IMAGE_PATH, image_path);
		//values.put(ROW_IMAGE_SERVER_PATH, image_server_path);

		//values.put(ROW_CREATED_BY, created_by);
		//values.put(ROW_CREATED_TIME, created_time);
		//values.put(ROW_UPDATE_BY, update_by);
		//values.put(ROW_UPDATE_TIME, update_time);
		
 
		try {
			//menambahkan nama tabel bila tidak akan error
			//db.delete(NAMA_TABEL, null, null);
			db.insert(NAMA_TABEL, null, values);
		} catch (Exception e) {
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();
		}
	}
	//membuat array pada table layout
	public ArrayList<ArrayList<Object>> ambilSemuaBaris() {
		ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
		Cursor cur;
		try {
			cur = db.query(NAMA_TABEL,
					new String[] { ROW_CHILD_NAME , 
			
					ROW_CHILD_BOD,
					ROW_CHILD_GENDER,
					ROW_CHILD_ADDRESS,
					
					ROW_CAREGIVER_NAME,
					
					ROW_CAREGIVER_PHONE,
					ROW_FATHER_NAME,
					ROW_MOTHER_NAME,
					ROW_BLOOD_TYPE,
					ROW_SCHOOL_NAME,
					
					//ROW_DRUG_TAKEN,
					
					//ROW_IMAGE_NAME,
					//ROW_IMAGE_PATH,
					//ROW_IMAGE_SERVER_PATH,
					
					//ROW_CREATED_BY,
					//ROW_CREATED_TIME,
					//ROW_UPDATE_BY,
					//ROW_UPDATE_TIME,
					
			}, null, null,
					null, null, null);
			cur.moveToFirst();
			if (!cur.isAfterLast()) {
				do {
					ArrayList<Object> dataList = new ArrayList<Object>();
					dataList.add(cur.getLong(0));
					dataList.add(cur.getString(1));
					dataList.add(cur.getString(2));
					dataList.add(cur.getString(3));
					dataList.add(cur.getString(4));
					dataList.add(cur.getString(5));
					dataList.add(cur.getString(6));
 
					dataArray.add(dataList);
 
				} while (cur.moveToNext());
 
			}
 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("DEBE ERROR", e.toString());
		}
		return dataArray;
 
	}
}
