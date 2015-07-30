package com.example.databaselap;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class TM_Drug_Dose {

	private static final String ROW_DRUG_DOSE_ID = "drug_dose_id";
	private static final String ROW_DRUG_DOSE_DESCRIPTION = "drug_dose_description";

	private static final String ROW_CREATED_BY = "created_by";
	private static final String ROW_CREATED_TIME = "created_time";
	private static final String ROW_UPDATE_BY = "update_by";
	private static final String ROW_UPDATE_TIME = "update_time";

	// mendeklarasikan NAMA_DB DAN TABLE DAN DATABASE VERSION
	private static final String NAMA_DB = "DB_LAP_DD";
	private static final String NAMA_TABEL = "TM_Drug_Dose";
	private static final int DB_VERSION = 1;

	// mendeklarasikan membuat CREATE_TABLE = MEMBUAT TABLE"
	private static final String CREATE_TABLE = "create table " + NAMA_TABEL
			+ " ( " + ROW_DRUG_DOSE_ID + " varchar PRIMARY KEY , "
			+ ROW_DRUG_DOSE_DESCRIPTION + " text, " + ROW_CREATED_BY
			+ " date, " + " " + ROW_CREATED_TIME + " varchar, " + ROW_UPDATE_BY
			+ " text, " + ROW_UPDATE_TIME + " varchar  ) ";

	// membuat mendeklarasikan Context itu adalah context
	private final Context context;
	// membuat mendeklarasikan DatabaseOpenHelper itu adalah dbhelper
	private DatabaseOpenHelper dbhelper;
	// membuat mendeklarasikan SQLiteDatabase itu adalah db
	private static SQLiteDatabase db;

	public TM_Drug_Dose(Context ctx) {
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
				// hapus dari tabel kemudian update yang baru

				// masih data static
				ContentValues values = new ContentValues();
				values.put(ROW_DRUG_DOSE_ID, "DD000");
				values.put(ROW_DRUG_DOSE_DESCRIPTION, "-");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
				
				values.put(ROW_DRUG_DOSE_ID, "DD001");
				values.put(ROW_DRUG_DOSE_ID, "DD001");
				values.put(ROW_DRUG_DOSE_DESCRIPTION, "1/2");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_DRUG_DOSE_ID, "DD002");
				values.put(ROW_DRUG_DOSE_ID, "DD002");
				values.put(ROW_DRUG_DOSE_DESCRIPTION, "1");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_DRUG_DOSE_ID, "DD003");
				values.put(ROW_DRUG_DOSE_ID, "DD003");
				values.put(ROW_DRUG_DOSE_DESCRIPTION, "1 1/2");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_DRUG_DOSE_ID, "DD004");
				values.put(ROW_DRUG_DOSE_ID, "DD004");
				values.put(ROW_DRUG_DOSE_DESCRIPTION, "2");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
				
				values.put(ROW_DRUG_DOSE_ID, "DD005");
				values.put(ROW_DRUG_DOSE_ID, "DD005");
				values.put(ROW_DRUG_DOSE_DESCRIPTION, "2 1/2");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_DRUG_DOSE_ID, "DD006");
				values.put(ROW_DRUG_DOSE_ID, "DD006");
				values.put(ROW_DRUG_DOSE_DESCRIPTION, "3");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				
				values.put(ROW_DRUG_DOSE_ID, "DD007");
				values.put(ROW_DRUG_DOSE_DESCRIPTION, "3 1/2");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
				
				values.put(ROW_DRUG_DOSE_ID, "DD008");
				values.put(ROW_DRUG_DOSE_DESCRIPTION, "1 SDM");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
				
				values.put(ROW_DRUG_DOSE_ID, "DD009");
				values.put(ROW_DRUG_DOSE_DESCRIPTION, "2 SDM");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
				
				values.put(ROW_DRUG_DOSE_ID, "DD010");
				values.put(ROW_DRUG_DOSE_DESCRIPTION, "3 SDM");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);


			} catch (Exception e) {
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

			String sql_insert = "INSERT INTO TM_Drug_Dose(drug_dose_description,created_by,created_time,update_by,update_time) VALUES (?,null,null,null,null)";
			for (String aa : data) {
				// cek the duplicate one
				String sql = "select * from " + NAMA_TABEL
						+ " WHERE drug_dose_description = '" + aa + "'";
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

	// query data kelas
	public ArrayList<String> getAllData() {
		ArrayList<String> allData = new ArrayList<String>();
		Cursor cursor = null;

		cursor = db.query(NAMA_TABEL, new String[] { ROW_DRUG_DOSE_ID,
				ROW_DRUG_DOSE_DESCRIPTION, ROW_CREATED_BY, ROW_CREATED_TIME,
				ROW_UPDATE_BY, ROW_UPDATE_TIME }, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			allData.add(cursor.getString(1));

			cursor.moveToNext();
		}

		cursor.close();
		return allData;
	}

	public  void deleteAll() {
		db.delete(NAMA_TABEL, null, null);
	}

	public  void InsertData() {
		try {
			// hapus dari tabel kemudian update yang baru
			deleteAll();
			// masih data static
			ContentValues values = new ContentValues();
			values.put(ROW_DRUG_DOSE_DESCRIPTION, "1 x sehari");
			values.put(ROW_CREATED_BY, "-");
			values.put(ROW_CREATED_TIME, "-");
			values.put(ROW_UPDATE_BY, "-");
			values.put(ROW_UPDATE_TIME, "-");
			db.insert(NAMA_TABEL, null, values);

			values.put(ROW_DRUG_DOSE_DESCRIPTION, "2 x sehari");
			values.put(ROW_CREATED_BY, "-");
			values.put(ROW_CREATED_TIME, "-");
			values.put(ROW_UPDATE_BY, "-");
			values.put(ROW_UPDATE_TIME, "-");
			db.insert(NAMA_TABEL, null, values);

			values.put(ROW_DRUG_DOSE_DESCRIPTION, "3 x sehari");
			values.put(ROW_CREATED_BY, "-");
			values.put(ROW_CREATED_TIME, "-");
			values.put(ROW_UPDATE_BY, "-");
			values.put(ROW_UPDATE_TIME, "-");
			db.insert(NAMA_TABEL, null, values);

			values.put(ROW_DRUG_DOSE_DESCRIPTION, "4 x sehari");
			values.put(ROW_CREATED_BY, "-");
			values.put(ROW_CREATED_TIME, "-");
			values.put(ROW_UPDATE_BY, "-");
			values.put(ROW_UPDATE_TIME, "-");
			db.insert(NAMA_TABEL, null, values);

		} catch (Exception e) {
			e.printStackTrace();
			Log.e("Gagal Insert Data", e.toString());
		}
	}

	// get id dosis arv
	public String getIdDosisARV(String dosis_arv) {
		String id = "";
		Cursor mCursor = db.rawQuery(
				"SELECT  drug_dose_id  FROM  TM_Drug_Dose WHERE drug_dose_description= '"
						+ dosis_arv + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			id = mCursor.getString(0);
		}
		mCursor.close();
		return id;
	}

	// get dose name
	public String getNameDose(String id) {
		String name = "";
		Cursor mCursor = db.rawQuery(
				"SELECT  drug_dose_description  FROM  TM_Drug_Dose WHERE drug_dose_id= '"
						+ id + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			name = mCursor.getString(mCursor.getColumnIndex(ROW_DRUG_DOSE_DESCRIPTION));
		}
		mCursor.close();
		return name;
	}

}
