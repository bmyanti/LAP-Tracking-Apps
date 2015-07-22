package com.example.databaselap;

import java.util.ArrayList;

import com.example.modellap.TM_Child_Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TM_Child {
	// mendeklarasikan ROW
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
	private static final String ROW_DRUG_DOSE_ID = "drug_dose_id";
	private static final String ROW_IMAGE_NAME = "image_name";
	private static final String ROW_IMAGE_PATH = "image_path";
	private static final String ROW_IMAGE_SERVER_PATH = "image_server_path";
	private static final String ROW_PARAM_ID = "param_Id";
	private static final String ROW_PARAM_GROUP = "param_group";
	private static final String ROW_CREATED_BY = "created_by";
	private static final String ROW_CREATED_TIME = "created_time";
	private static final String ROW_UPDATE_BY = "update_by";
	private static final String ROW_UPDATE_TIME = "update_time";

	// mendeklarasikan NAMA_DB DAN TABLE DAN DATABASE VERSION
	private static final String NAMA_DB = "INIDB_";
	private static final String NAMA_TABEL = "TM_Child";
	private static final int DB_VERSION = 1;
	// mendeklarasikan CREATE_TABLE = MEMBUAT TABLE"
	private static final String CREATE_TABLE = "create table " + NAMA_TABEL
			+ " ( " + ROW_CHILD_ID + " integer PRIMARY KEY autoincrement, "
			+ ROW_CHILD_NAME + " text, " + ROW_CHILD_BOD + " date, " + " "
			+ ROW_CHILD_GENDER + " varchar, " + ROW_CHILD_ADDRESS + " text, "
			+ ROW_DRUG_TYPE_ID + " varchar , " + ROW_PARENT_ID + " varchar, "
			+ " " + ROW_CAREGIVER_NAME + " text, " + ROW_CAREGIVER_ID
			+ " varchar, " + ROW_CAREGIVER_PHONE + " numeric, "
			+ ROW_FATHER_NAME + " text, " + " " + ROW_MOTHER_NAME + " text, "
			+ ROW_BLOOD_TYPE + " varchar, " + ROW_SCHOOL_NAME + " text, "
			+ ROW_SUBDISTRICT_ID + " varchar, " + " " + ROW_CLASS_ID
			+ " varchar, " + ROW_GROUP_ID + " varchar, "
			+ ROW_SCHOOL_SUBDISTRICT_ID + " varchar, " + " "
			+ ROW_DAD_STATUS_ID + " varchar, " + ROW_MOM_STATUS_ID
			+ " varchar, " + ROW_DRUG_TAKEN + " varchar, " + ROW_FACILITY_ID
			+ " varchar, " + " " + ROW_DRUG_STATUS_ID + " varchar, "
			+ ROW_DRUG_DOSE_ID + " varchar, " + ROW_IMAGE_NAME + " text, "
			+ ROW_IMAGE_PATH + " varchar, " + " " + ROW_IMAGE_SERVER_PATH
			+ " varchar, " + ROW_PARAM_ID + " varchar, " + ROW_PARAM_GROUP
			+ " varchar, " + " " + ROW_CREATED_BY + " VARCHAR, "
			+ ROW_CREATED_TIME + " VARCHAR, " + " " + ROW_UPDATE_BY
			+ " VARCHAR, " + ROW_UPDATE_TIME + " VARCHAR ) ";
	// "create table "+NAMA_TABEL+" ("+ROW_ID+" integer PRIMARY KEY autoincrement, "+ROW_NAMA+" text,"+ROW_SEKOLAH+" text, "+ROW_AGE+" varchar, "+ROW_TTL+" string)";
	// membuat mendeklarasikan Context itu adalah context
	private final Context context;
	// membuat mendeklarasikan DatabaseOpenHelper itu adalah dbhelper
	private DatabaseOpenHelper dbhelper;
	// membuat mendeklarasikan SQLiteDatabase itu adalah db
	private SQLiteDatabase db;
	
	private Cursor cursor;

	public TM_Child(Context ctx) {
		// mendeklarasikan ctx adalah context ( context context di ganti ctx )
		this.context = ctx;
		// membuat DatabaseOpenHelper
		dbhelper = new DatabaseOpenHelper(this.context);
		// menuliskan DatabaseOpenHelper = SQLiteDatabase
		this.db = dbhelper.getWritableDatabase();
	}

	private static class DatabaseOpenHelper extends SQLiteOpenHelper {
		// membuat database
		public DatabaseOpenHelper(Context context) {
			super(context, NAMA_DB, null, DB_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(CREATE_TABLE);
			Log.i("create table", db.toString());
		}

		// memperbarui database bila sudah ada
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + NAMA_DB);
			onCreate(db);

		}
		
	}

	// menutup DatabaseOpenHelper
	public void close() {
//	    if (!cursor.isClosed()) {  
//	        cursor.close();  
//	    }  
	   
		dbhelper.close();
	}

	// insertt data anak
	public void addRow(
			String child_name,
			String child_bod,
			String child_gender,
			String blood_type,
			String father_name,
			String mother_name,
			String caregiver_name,
			String child_address,
			String caregiver_phone,
			String school_name
			// String drug_taken
			// ,String image_name
			,
			String image_path,
			// , String image_server_path,
			// String created_by , String created_time ,String update_by ,
			// String
			// update_time
			String id_statusARV, String id_typeARV, String id_dosisARV,
			String id_class, String id_caregiver, String id_facility,
			String id_status_ayah, String id_status_ibu, String id_subdistrict,
			String id_school_subdistrict

	) {

		ContentValues values = new ContentValues();

		values.put(ROW_CHILD_NAME, child_name);
		values.put(ROW_CHILD_BOD, child_bod);
		values.put(ROW_CHILD_GENDER, child_gender);
		values.put(ROW_BLOOD_TYPE, blood_type);
		values.put(ROW_FATHER_NAME, father_name);
		values.put(ROW_MOTHER_NAME, mother_name);
		values.put(ROW_CAREGIVER_NAME, caregiver_name);
		values.put(ROW_CHILD_ADDRESS, child_address);
		values.put(ROW_CAREGIVER_PHONE, caregiver_phone);
		values.put(ROW_SCHOOL_NAME, school_name);

		// values.put(ROW_DRUG_TAKEN, drug_taken);

		// values.put(ROW_IMAGE_NAME, image_name);
		values.put(ROW_IMAGE_PATH, image_path);
		// values.put(ROW_IMAGE_SERVER_PATH, image_server_path);

		// values.put(ROW_CREATED_BY, created_by);
		// values.put(ROW_CREATED_TIME, created_time);
		// values.put(ROW_UPDATE_BY, update_by);
		// values.put(ROW_UPDATE_TIME, update_time);
		values.put(ROW_DRUG_TYPE_ID, id_typeARV);
		values.put(ROW_DRUG_STATUS_ID, id_statusARV);
		values.put(ROW_DRUG_DOSE_ID, id_dosisARV);
		values.put(ROW_CLASS_ID, id_class);
		values.put(ROW_CAREGIVER_ID, id_caregiver);
		values.put(ROW_FACILITY_ID, id_facility);
		values.put(ROW_DAD_STATUS_ID, id_status_ayah);
		values.put(ROW_MOM_STATUS_ID, id_status_ibu);
		values.put(ROW_SUBDISTRICT_ID, id_subdistrict);
		values.put(ROW_SCHOOL_SUBDISTRICT_ID, id_school_subdistrict);

		try {
			// menambahkan nama tabel bila tidak akan error
			// db.delete(NAMA_TABEL, null, null);
			db.insert(NAMA_TABEL, null, values);
			
			Log.i("berhasil ", "haloo " + child_name + " " + child_gender + " "
					+ child_bod + " " + blood_type + " " + father_name + " "
					+ mother_name + " " + caregiver_name + " " + child_address
					+ " " + caregiver_phone + " " + school_name);
		} catch (Exception e) {
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();
		}
	}
	
	public TM_Child_Model SearchChild(String nama) {
		TM_Child_Model model_child = new TM_Child_Model();
		TM_Child_Model tm = new TM_Child_Model();

		 cursor = db
				.rawQuery(
						" Select child_id,child_name,child_bod,child_gender,blood_type,father_name,mother_name,caregiver_name,child_address,caregiver_phone,school_name,image_path,drug_dose_id,drug_status_id,drug_type_id,class_id,caregiver_id,facility_id,dad_status_id,mom_status_id,subdistrict_id,school_subdistrict_id FROM  TM_Child WHERE child_name = '"
								+ nama + "'", null);
		if (cursor != null) {
			cursor.moveToFirst();
			tm = parseData(cursor);
		}
		cursor.close();
		return tm;

	}

	// ambil semua data dari table
	public ArrayList<TM_Child_Model> getAllData() {

		ArrayList<TM_Child_Model> allData = new ArrayList<TM_Child_Model>();
		 cursor = null;

		cursor = db.query(NAMA_TABEL, new String[] {
				ROW_CHILD_ID,
				ROW_CHILD_NAME,
				ROW_CHILD_BOD,
				ROW_CHILD_GENDER,
				ROW_BLOOD_TYPE,
				ROW_FATHER_NAME,
				ROW_MOTHER_NAME,
				ROW_CAREGIVER_NAME,
				ROW_CHILD_ADDRESS,
				ROW_CAREGIVER_PHONE,
				ROW_SCHOOL_NAME,

				// ROW_DRUG_TAKEN,

				// ROW_IMAGE_NAME,
				ROW_IMAGE_PATH,
				// ROW_IMAGE_SERVER_PATH,

				// ROW_CREATED_BY,
				// ROW_CREATED_TIME,
				// ROW_UPDATE_BY,
				// ROW_UPDATE_TIME,

				ROW_DRUG_DOSE_ID, ROW_DRUG_STATUS_ID, ROW_DRUG_TYPE_ID,
				ROW_CLASS_ID, ROW_CAREGIVER_ID, ROW_FACILITY_ID,
				ROW_DAD_STATUS_ID, ROW_MOM_STATUS_ID, ROW_SUBDISTRICT_ID,
				ROW_SCHOOL_SUBDISTRICT_ID }, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			allData.add(parseData(cursor));

			cursor.moveToNext();
		}

		cursor.close();
		return allData;
	}

	private TM_Child_Model parseData(Cursor cursor) {
		TM_Child_Model curData = new TM_Child_Model();
		curData.setChild_id(cursor.getString(0));
		curData.setChild_name(cursor.getString(1));
		curData.setChild_bod(cursor.getString(2));
		curData.setChild_gender(cursor.getString(3));
		curData.setChild_address(cursor.getString(8));

		curData.setCaregiver_name(cursor.getString(7));
		curData.setCaregiver_phone(cursor.getString(9));
		curData.setFather_name(cursor.getString(5));
		curData.setMother_name(cursor.getString(6));

		curData.setBlood_type(cursor.getString(4));
		curData.setSchool_name(cursor.getString(10));

		curData.setImage_path(cursor.getString(11));

		curData.setDrug_dose_id(cursor.getString(12));
		curData.setDrug_status_id(cursor.getString(13));
		curData.setDrug_type_id(cursor.getString(14));
		curData.setClass_id(cursor.getString(15));
		curData.setCaregiver_id(cursor.getString(16));
		curData.setFacility_id(cursor.getString(17));
		curData.setDad_status_id(cursor.getString(18));
		curData.setMom_status_id(cursor.getString(19));
		curData.setSubdistrict_id(cursor.getString(20));
		curData.setSchool_subdistrict_id(cursor.getString(21));
		return curData;
	}

	public TM_Child_Model getChildIdentityById(String id) {
		TM_Child_Model model_child = new TM_Child_Model();
		cursor = db
				.rawQuery(
						"SELECT  child_id,child_name,child_bod,child_gender,blood_type,father_name,mother_name,caregiver_name,child_address,caregiver_phone,school_name,image_path,drug_dose_id,drug_status_id,drug_type_id,class_id,caregiver_id,facility_id,dad_status_id,mom_status_id,subdistrict_id,school_subdistrict_id FROM  TM_Child WHERE child_id= '"
								+ id + "'", null);
		if (cursor != null) {
			cursor.moveToFirst();
			model_child = parseData(cursor);
		}
		cursor.close();
		return model_child;
	}

	public void updateChildIdentityById(String child_id, String child_name,
			String child_bod, String child_gender, String blood_type,
			String father_name, String mother_name, String caregiver_name,
			String child_address, String caregiver_phone, String school_name,
			
			String id_status_ayah, String id_status_ibu, String id_caregiver,
			String id_subdistrict, String id_school_subdistrict, String id_class,
			String path_picture
			// String drug_taken
			// ,String image_name , String image_path , String image_server_path,
			// String created_by , String created_time ,String update_by , String
			// update_time
	) {
		ContentValues values = new ContentValues();
		values.put(ROW_CHILD_NAME, child_name);
		values.put(ROW_CHILD_BOD, child_bod);
		values.put(ROW_CHILD_GENDER, child_gender);
		values.put(ROW_BLOOD_TYPE, blood_type);
		values.put(ROW_FATHER_NAME, father_name);
		values.put(ROW_MOTHER_NAME, mother_name);
		values.put(ROW_CAREGIVER_NAME, caregiver_name);
		values.put(ROW_CHILD_ADDRESS, child_address);
		values.put(ROW_CAREGIVER_PHONE, caregiver_phone);
		values.put(ROW_SCHOOL_NAME, school_name);

		
		values.put(ROW_DAD_STATUS_ID, id_status_ayah);
		values.put(ROW_MOM_STATUS_ID, id_status_ibu);
		values.put(ROW_CAREGIVER_ID, id_caregiver);
		values.put(ROW_SUBDISTRICT_ID, id_subdistrict);
		values.put(ROW_SCHOOL_SUBDISTRICT_ID, id_school_subdistrict);
		values.put(ROW_CLASS_ID, id_class);
		values.put(ROW_IMAGE_PATH, path_picture);
		
		db.update(NAMA_TABEL, values, "child_id=" + child_id, null);
		
	}

	//get child name
	 public ArrayList<String> getAllChildName() {

	  ArrayList<String> allData = new ArrayList<String>();
	   cursor = null;

	  cursor = db.query(NAMA_TABEL, new String[] { 
	    ROW_CHILD_ID,
	    ROW_CHILD_NAME, 
//	    ROW_CHILD_BOD, ROW_CHILD_GENDER,
//	    ROW_BLOOD_TYPE, ROW_FATHER_NAME, ROW_MOTHER_NAME,
//	    ROW_CAREGIVER_NAME, ROW_CHILD_ADDRESS,ROW_CAREGIVER_PHONE,ROW_SCHOOL_NAME,
	//  
	    // ROW_DRUG_TAKEN,

	    // ROW_IMAGE_NAME,
	     //ROW_IMAGE_PATH,
	    // ROW_IMAGE_SERVER_PATH,

	    // ROW_CREATED_BY,
	    // ROW_CREATED_TIME,
	    // ROW_UPDATE_BY,
	    // ROW_UPDATE_TIME,

	    }, null, null, null, null, null);

	  cursor.moveToFirst();
	  while (!cursor.isAfterLast()) {
	   allData.add(cursor.getString(cursor.getColumnIndex(ROW_CHILD_ID)));
	   allData.add(cursor.getString(cursor.getColumnIndex(ROW_CHILD_NAME)));

	   cursor.moveToNext();
	  }

	  cursor.close();
	  return allData;
	 }
	 
	 public String getLastInsertedChild()
	 {
		 String id = "";
		 cursor = db.rawQuery("SELECT  child_id FROM  TM_Child", null); 
		 if(cursor != null && cursor.moveToLast())
		 {
			 id = cursor.getString(cursor.getColumnIndex(ROW_CHILD_ID));
		 }
		 cursor.close();
		 return id;
	 }

}
