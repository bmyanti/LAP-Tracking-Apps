package com.example.databaselap;

import java.util.ArrayList;

import com.example.modellap.Child_Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TM_Class {
	private static final String ROW_CLASS_ID = "class_id";
	private static final String ROW_CLASS_DESCRIPTION = "class_description";
	private static final String ROW_CREATED_BY = "created_by";
	private static final String ROW_CREATED_TIME = "created_time";
	private static final String ROW_UPDATE_BY = "update_by";
	private static final String ROW_UPDATE_TIME = "update_time";

	// mendeklarasikan NAMA_DB DAN TABLE DAN DATABASE VERSION
	private static final String NAMA_DB = "DB_LAP_C";
	private static final String NAMA_TABEL = "TM_CLASS";
	private static final int DB_VERSION = 1;

	// mendeklarasikan membuat CREATE_TABLE = MEMBUAT TABLE
	private static final String CREATE_TABLE = "create table " + NAMA_TABEL
			+ " ( " + ROW_CLASS_ID + " varchar PRIMARY KEY, "
			+ ROW_CLASS_DESCRIPTION + " text, " + ROW_CREATED_BY + " date, "
			+ " " + ROW_CREATED_TIME + " varchar, " + ROW_UPDATE_BY + " text, "
			+ ROW_UPDATE_TIME + " varchar  ) ";

	// membuat mendeklarasikan Context itu adalah context
	private final Context context;
	// membuat mendeklarasikan DatabaseOpenHelper itu adalah dbhelper
	private DatabaseOpenHelper dbhelper;
	// membuat mendeklarasikan SQLiteDatabase itu adalah db
	private SQLiteDatabase db;

	public TM_Class(Context ctx) {
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
				values.put(ROW_CLASS_ID, "CL000");
				values.put(ROW_CLASS_DESCRIPTION, "-");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
				
				values.put(ROW_CLASS_ID, "CL001");
				values.put(ROW_CLASS_DESCRIPTION, "PAUD");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_CLASS_ID, "CL002");
				values.put(ROW_CLASS_DESCRIPTION, "TK1");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
				
				values.put(ROW_CLASS_ID, "CL003");
				values.put(ROW_CLASS_DESCRIPTION, "TK2");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
				
				values.put(ROW_CLASS_ID, "CL004");
				values.put(ROW_CLASS_DESCRIPTION, "TK3");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_CLASS_ID, "CL005");
				values.put(ROW_CLASS_DESCRIPTION, "1 SD");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_CLASS_ID, "CL006");
				values.put(ROW_CLASS_DESCRIPTION, "2 SD");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_CLASS_ID, "CL007");
				values.put(ROW_CLASS_DESCRIPTION, "3 SD");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_CLASS_ID, "CL008");
				values.put(ROW_CLASS_DESCRIPTION, "4 SD");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_CLASS_ID, "CL009");
				values.put(ROW_CLASS_DESCRIPTION, "5 SD");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_CLASS_ID, "CL010");
				values.put(ROW_CLASS_DESCRIPTION, "6 SD");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

//				values.put(ROW_CLASS_DESCRIPTION, "1 SMP");
//				values.put(ROW_CREATED_BY, "-");
//				values.put(ROW_CREATED_TIME, "-");
//				values.put(ROW_UPDATE_BY, "-");
//				values.put(ROW_UPDATE_TIME, "-");
//				db.insert(NAMA_TABEL, null, values);
//				
//				values.put(ROW_CLASS_DESCRIPTION, "2 SMP");
//				values.put(ROW_CREATED_BY, "-");
//				values.put(ROW_CREATED_TIME, "-");
//				values.put(ROW_UPDATE_BY, "-");
//				values.put(ROW_UPDATE_TIME, "-");
//				db.insert(NAMA_TABEL, null, values);
//
//				values.put(ROW_CLASS_DESCRIPTION, "3 SMP");
//				values.put(ROW_CREATED_BY, "-");
//				values.put(ROW_CREATED_TIME, "-");
//				values.put(ROW_UPDATE_BY, "-");
//				values.put(ROW_UPDATE_TIME, "-");
//				db.insert(NAMA_TABEL, null, values);

			} catch (Exception e) {
				// TODO: handle exception
				Log.e("DB ERROR", e.toString());
				e.printStackTrace();

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

			String sql_insert = "INSERT INTO TM_CLASS(class_description,created_by,created_time,update_by,update_time) VALUES (?,null,null,null,null)";
			for (String aa : data) {
				// cek the duplicate one
				String sql = "select * from " + NAMA_TABEL
						+ " WHERE class_description = '" + aa + "'";
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
	public ArrayList<String> getDataKelas() {
		ArrayList<String> allData = new ArrayList<String>();
		Cursor cursor = null;

		cursor = db.query(NAMA_TABEL, new String[] { ROW_CLASS_ID,
				ROW_CLASS_DESCRIPTION, ROW_CREATED_BY, ROW_CREATED_TIME,
				ROW_UPDATE_BY, ROW_UPDATE_TIME }, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			allData.add(cursor.getString(1));

			cursor.moveToNext();
		}

		cursor.close();
		return allData;
	}

	

	// get id kelas
	public String getIdKelas(String kelas_deskripsi) {
		String id = "";
		Cursor mCursor = db.rawQuery(
				"SELECT  class_id  FROM  TM_CLASS WHERE class_description= '"
						+ kelas_deskripsi + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			id = mCursor.getString(0);
		}
		mCursor.close();
		return id;
	}

	// get name kelas
	public String getNameKelas(String id) {
		String name = "";
		Cursor mCursor = db
				.rawQuery(
						"SELECT  class_description  FROM  TM_CLASS WHERE class_id= '"
								+ id + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			name = mCursor.getString(mCursor.getColumnIndex(ROW_CLASS_DESCRIPTION));
		}
		mCursor.close();
		return name;
	}
}
