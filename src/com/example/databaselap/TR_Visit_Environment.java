package com.example.databaselap;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TR_Visit_Environment {
	private static final String ROW_VISIT_ENVIRONMENT_ID = "visit_environment_id";
	private static final String ROW_VISIT_DATE = "visit_date";
	private static final String ROW_CHILD_ID = "child_id";
	private static final String ROW_ENVIRONMENT_ID = "environment_id";
	private static final String ROW_ENVIRONMENT_SCORE = "enviroment_score";

	// mendeklarasikan NAMA_DB DAN TABLE DAN DATABASE VERSION
	private static final String NAMA_DB = "DB_LAP_VE";
	private static final String NAMA_TABEL = "TR_Visit_Environment";
	private static final int DB_VERSION = 1;

	// mendeklarasikan membuat CREATE_TABLE = MEMBUAT TABLE"
	private static final String CREATE_TABLE = "create table " + NAMA_TABEL
			+ " ( " + ROW_VISIT_ENVIRONMENT_ID
			+ " integer PRIMARY KEY autoincrement, " + ROW_VISIT_DATE
			+ " text, " + ROW_CHILD_ID + " text, " + " " + ROW_ENVIRONMENT_ID
			+ " varchar, " + ROW_ENVIRONMENT_SCORE + " text ) ";

	// membuat mendeklarasikan Context itu adalah context
	private final Context context;
	// membuat mendeklarasikan DatabaseOpenHelper itu adalah dbhelper
	private DatabaseOpenHelper dbhelper;
	// membuat mendeklarasikan SQLiteDatabase itu adalah db
	private SQLiteDatabase db;

	public TR_Visit_Environment(Context ctx) {
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

	// public void SaveDataFromServer(String[] data) {
	// try {
	// // cek jika sudah ada di database
	//
	// String sql_insert =
	// "INSERT INTO TM_Drug_Status(drug_status_description,created_by,created_time,update_by,update_time) VALUES (?,null,null,null,null)";
	// for (String aa : data) {
	// // cek the duplicate one
	// String sql = "select * from " + NAMA_TABEL
	// + " WHERE drug_status_description = '" + aa + "'";
	// Cursor cursorinfo = null;
	// try {
	// cursorinfo = db.rawQuery(sql, null);
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	// //if data is not in database
	// if (!cursorinfo.moveToFirst()) {
	// db.execSQL(sql_insert, new String[] { aa });
	// Log.i("save", aa);
	// } else {
	// Log.i("data already in database", aa);
	// }
	//
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// Log.e("Failed to save", e.toString());
	// }
	//
	// }

	// insert to tabel tr visit environment
	public void InsertEnvironmentScore(String visit_date, String child_id,
			String environment_id, String Score) {
		try {
			ContentValues values = new ContentValues();
			values.put(ROW_VISIT_DATE, visit_date);
			values.put(ROW_CHILD_ID, child_id);
			values.put(ROW_ENVIRONMENT_ID, environment_id);
			values.put(ROW_ENVIRONMENT_SCORE, Score);
			db.insert(NAMA_TABEL, null, values);
		} catch (Exception e) {
			Log.e("Gagal insert tabel visist environmet", "" + e.toString());
		}

	}

	public int CountALLEnvironmentScore(String id_child, String visit_date) {
		int score = 0;
		String sc = "";
		Cursor mCursor = db.rawQuery(
				"SELECT  enviroment_score  FROM  TR_Visit_Environment WHERE visit_date= '"
						+ visit_date + "' and child_id= '" + id_child + "'",
				null);

		if (mCursor != null) {
			mCursor.moveToFirst();
			while (!mCursor.isAfterLast()) {
				sc = mCursor.getString(0);
				score += Integer.parseInt(sc);
				mCursor.moveToNext();
			}
		}

		return score;
	}

	public int GetEnvironmentScore(String id_child, String visit_date,
			String environment_id) {
		int score = 0;
		String sc = "";
		Cursor mCursor = db.rawQuery(
				"SELECT  enviroment_score  FROM  TR_Visit_Environment WHERE visit_date= '"
						+ visit_date + "' and child_id= '" + id_child
						+ "' and environment_id= '" + environment_id + "'",
				null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			sc = mCursor.getString(0);
			score = Integer.parseInt(sc);
		}
		mCursor.close();

		return score;
	}

	public void UpdateEnvironmentScore(String id_child, String visit_date,
			String environment_id, String score) {
		try {
			ContentValues values = new ContentValues();
			values.put(ROW_ENVIRONMENT_SCORE, score);
			db.update(NAMA_TABEL, values, ("visit_date = ? AND child_id = "
					+ id_child + "environment_id = " + environment_id),
					new String[] { visit_date });
		} catch (Exception e) {
			Log.e("Update Env Score gagal", ""+e.toString());
		}
	}

}
