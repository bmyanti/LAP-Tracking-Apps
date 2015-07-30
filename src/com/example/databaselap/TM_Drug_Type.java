package com.example.databaselap;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TM_Drug_Type {

	private static final String ROW_DRUG_TYPE_ID = "drug_type_id";
	private static final String ROW_DRUG_TYPE_DESCRIPTION = "drug_type_description";

	private static final String ROW_CREATED_BY = "created_by";
	private static final String ROW_CREATED_TIME = "created_time";
	private static final String ROW_UPDATE_BY = "update_by";
	private static final String ROW_UPDATE_TIME = "update_time";

	// mendeklarasikan NAMA_DB DAN TABLE DAN DATABASE VERSION
	private static final String NAMA_DB = "DB_LAP_DT";
	private static final String NAMA_TABEL = "TM_Drug_Type";
	private static final int DB_VERSION = 1;

	// mendeklarasikan membuat CREATE_TABLE = MEMBUAT TABLE"
	private static final String CREATE_TABLE = "create table " + NAMA_TABEL
			+ " ( " + ROW_DRUG_TYPE_ID + " varchar PRIMARY KEY, "
			+ ROW_DRUG_TYPE_DESCRIPTION + " text, " + ROW_CREATED_BY
			+ " date, " + " " + ROW_CREATED_TIME + " varchar, " + ROW_UPDATE_BY
			+ " text, " + ROW_UPDATE_TIME + " varchar  ) ";

	// membuat mendeklarasikan Context itu adalah context
	private final Context context;
	// membuat mendeklarasikan DatabaseOpenHelper itu adalah dbhelper
	private DatabaseOpenHelper dbhelper;
	// membuat mendeklarasikan SQLiteDatabase itu adalah db
	private SQLiteDatabase db;

	public TM_Drug_Type(Context ctx) {
		// mendeklarasikan ctx adalah context ( context context di ganti ctx )
		this.context = ctx;
		// membuat DatabaseOpenHelper
		dbhelper = new DatabaseOpenHelper(context);
		// menuliskan DatabaseOpenHelper = SQLiteDatabase
		db = dbhelper.getWritableDatabase();
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
			
			try {
				ContentValues values = new ContentValues();
				
				values.put(ROW_DRUG_TYPE_ID, "DTY000");
				values.put(ROW_DRUG_TYPE_DESCRIPTION, "-");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
				
				values.put(ROW_DRUG_TYPE_ID, "DTY001");
				values.put(ROW_DRUG_TYPE_DESCRIPTION, "Zidovudin");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_DRUG_TYPE_ID, "DTY002");
				values.put(ROW_DRUG_TYPE_DESCRIPTION, "Hiviral");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_DRUG_TYPE_ID, "DTY003");
				values.put(ROW_DRUG_TYPE_DESCRIPTION, "Stafudin");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
				
				values.put(ROW_DRUG_TYPE_ID, "DTY004");
				values.put(ROW_DRUG_TYPE_DESCRIPTION, "Efavirens");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
				
				values.put(ROW_DRUG_TYPE_ID, "DTY005");
				values.put(ROW_DRUG_TYPE_DESCRIPTION, "Mylan");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
				
				values.put(ROW_DRUG_TYPE_ID, "DTY006");
				values.put(ROW_DRUG_TYPE_DESCRIPTION, "Ciplan");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Log.e("Gagal Insert Data", e.toString());
			}
				
			

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + NAMA_DB);
			onCreate(db);

		}

	}

	// menutup DatabaseOpenHelper
	public void close() {
		dbhelper.close();
	}

	public void SaveDataFromServer(String[] data) {
		try {
			// cek jika sudah ada di database

			String sql_insert = "INSERT INTO TM_Drug_Type(drug_type_description,created_by,created_time,update_by,update_time) VALUES (?,null,null,null,null)";
			for (String aa : data) {
				// cek the duplicate one
				String sql = "select * from " + NAMA_TABEL
						+ " WHERE drug_type_description = '" + aa + "'";
				Cursor cursorinfo = null;
				try {
					cursorinfo = db.rawQuery(sql, null);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				//if data is not in database
				if (!cursorinfo.moveToFirst()) {
					db.execSQL(sql_insert, new String[] { aa });
					Log.i("save", aa);
				} else {
					Log.i("data already in database", aa);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.e("Failed to save", e.toString());
		}

	}
	// query data type arv
	public ArrayList<String> getAllData() {
		ArrayList<String> allData = new ArrayList<String>();
		Cursor cursor = null;

		cursor = db.query(NAMA_TABEL, new String[] { ROW_DRUG_TYPE_ID,
				ROW_DRUG_TYPE_DESCRIPTION, ROW_CREATED_BY, ROW_CREATED_TIME,
				ROW_UPDATE_BY, ROW_UPDATE_TIME }, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			allData.add(cursor.getString(1));

			cursor.moveToNext();
		}

		cursor.close();
		return allData;
	}

	public void deleteAll() {
		db.delete(NAMA_TABEL, null, null);
	}

	// this data is from server
	public void InsertData(String[] a) {
		//try {
			ContentValues values = new ContentValues();
			//insert default value and then insert the inserted value
			Cursor cur = db.rawQuery("SELECT drug_type_description FROM " + NAMA_TABEL, null);
			if(cur != null && cur.moveToFirst() && cur.getInt(0) > 0)
			{
				for(String aa : a)
					{
						if(getNameTypeARV(aa) == "")
						{
							values.put(ROW_DRUG_TYPE_DESCRIPTION, aa);
							values.put(ROW_CREATED_BY, "-");
							values.put(ROW_CREATED_TIME, "-");
							values.put(ROW_UPDATE_BY, "-");
							values.put(ROW_UPDATE_TIME, "-");
							db.insert(NAMA_TABEL, null, values);
							Log.d("insert table type", ""+aa);
						}
					}
					
				Log.i(getClass().getName(), "table not empty"+cur.moveToFirst());
			}
			else
			{
				values.put(ROW_DRUG_TYPE_DESCRIPTION, "Pil");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_DRUG_TYPE_DESCRIPTION, "Kapsul");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_DRUG_TYPE_DESCRIPTION, "Sirup");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_DRUG_TYPE_DESCRIPTION, "dll");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
				
//				for(String aa : a)
//				{
//					if(getNameTypeARV(aa) == "")
//					{
//						values.put(ROW_DRUG_TYPE_DESCRIPTION, aa);
//						values.put(ROW_CREATED_BY, "-");
//						values.put(ROW_CREATED_TIME, "-");
//						values.put(ROW_UPDATE_BY, "-");
//						values.put(ROW_UPDATE_TIME, "-");
//						db.insert(NAMA_TABEL, null, values);
//						Log.d("insert table type", ""+aa);
//					}
//				}
				Log.i(getClass().getName(), "table is empty");
			}
			
//			if (cur != null && cur.moveToFirst() && cur.getInt(0) > 0) {
//				values.put(ROW_DRUG_TYPE_DESCRIPTION, "Pil");
//				values.put(ROW_CREATED_BY, "-");
//				values.put(ROW_CREATED_TIME, "-");
//				values.put(ROW_UPDATE_BY, "-");
//				values.put(ROW_UPDATE_TIME, "-");
//				db.insert(NAMA_TABEL, null, values);
//
//				values.put(ROW_DRUG_TYPE_DESCRIPTION, "Kapsul");
//				values.put(ROW_CREATED_BY, "-");
//				values.put(ROW_CREATED_TIME, "-");
//				values.put(ROW_UPDATE_BY, "-");
//				values.put(ROW_UPDATE_TIME, "-");
//				db.insert(NAMA_TABEL, null, values);
//
//				values.put(ROW_DRUG_TYPE_DESCRIPTION, "Sirup");
//				values.put(ROW_CREATED_BY, "-");
//				values.put(ROW_CREATED_TIME, "-");
//				values.put(ROW_UPDATE_BY, "-");
//				values.put(ROW_UPDATE_TIME, "-");
//				db.insert(NAMA_TABEL, null, values);
//
//				values.put(ROW_DRUG_TYPE_DESCRIPTION, "dll");
//				values.put(ROW_CREATED_BY, "-");
//				values.put(ROW_CREATED_TIME, "-");
//				values.put(ROW_UPDATE_BY, "-");
//				values.put(ROW_UPDATE_TIME, "-");
//				db.insert(NAMA_TABEL, null, values);
//				
//				for(String aa : a)
//				{
//					if(getNameTypeARV(aa) == "")
//					{
//						values.put(ROW_DRUG_TYPE_DESCRIPTION, aa);
//						values.put(ROW_CREATED_BY, "-");
//						values.put(ROW_CREATED_TIME, "-");
//						values.put(ROW_UPDATE_BY, "-");
//						values.put(ROW_UPDATE_TIME, "-");
//						db.insert(NAMA_TABEL, null, values);
//						Log.d("insert table type", ""+aa);
//					}
//				}
//			    Log.i(getClass().getName(), "table not empty");
//			} 
//			else {
//				//cek the existance of data in local db
//				for(String aa : a)
//				{
//					if(getNameTypeARV(aa) == "")
//					{
//						values.put(ROW_DRUG_TYPE_DESCRIPTION, aa);
//						values.put(ROW_CREATED_BY, "-");
//						values.put(ROW_CREATED_TIME, "-");
//						values.put(ROW_UPDATE_BY, "-");
//						values.put(ROW_UPDATE_TIME, "-");
//						db.insert(NAMA_TABEL, null, values);
//						Log.d("insert table type", ""+aa);
//					}
//				}
//			    Log.i(getClass().getName(), "table is empty");
//			}
			
			
//		} catch (Exception e) {
//			e.printStackTrace();
//			Log.e("Gagal Insert Data", e.toString());
//		}
			cur.close();
	}

	// get id type arv
	public String getIdTypeARV(String type_arv) {
		String id = "";
		Cursor mCursor = db.rawQuery(
				"SELECT  drug_type_id  FROM  TM_Drug_Type WHERE drug_type_description= '"
						+ type_arv + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			id = mCursor.getString(0);
		}
		mCursor.close();
		return id;
	}

	// get name type arv
	public String getNameTypeARV(String id) {
		String name = "";
		Cursor mCursor = db.rawQuery(
				"SELECT  drug_type_description  FROM  TM_Drug_Type WHERE drug_type_id= '"
						+ id + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			name = mCursor.getString(mCursor.getColumnIndex(ROW_DRUG_TYPE_DESCRIPTION));
		}
		mCursor.close();
		return name;
	}

}
