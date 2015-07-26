package com.example.databaselap;

import java.util.ArrayList;

import com.example.modellap.ChildFacility_Model;
import com.example.modellap.Child_Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TM_Child_Facility {

	private static final String ROW_CHILD_FACILITY_ID = "child_facility_id";
	private static final String ROW_CHILD_ID = "child_id";
	private static final String ROW_FACILITY_ID = "facility_id";
	private static final String ROW_FACILITY_COST_ID = "facility_cost_id";
	private static final String ROW_CREATED_BY = "created_by";
	private static final String ROW_CREATED_TIME = "created_time";
	private static final String ROW_UPDATE_BY = "update_by";
	private static final String ROW_UPDATE_TIME = "update_time";

	// mendeklarasikan NAMA_DB DAN TABLE DAN DATABASE VERSION
	private static final String NAMA_DB = "DB_LAP_CF";
	private static final String NAMA_TABEL = "TM_Child_Facility";
	private static final int DB_VERSION = 1;

	// mendeklarasikan membuat CREATE_TABLE = MEMBUAT TABLE"
	private static final String CREATE_TABLE = "create table " + NAMA_TABEL
			+ " ( " + ROW_CHILD_FACILITY_ID
			+ " integer PRIMARY KEY autoincrement, " + ROW_CHILD_ID + " text, "
			+ " " + ROW_FACILITY_ID + " varchar, " + ROW_FACILITY_COST_ID
			+ " text, " + ROW_CREATED_BY + " date, " + " " + ROW_CREATED_TIME
			+ " varchar, " + ROW_UPDATE_BY + " text, " + ROW_UPDATE_TIME
			+ " varchar  ) ";

	// membuat mendeklarasikan Context itu adalah context
	private final Context context;
	// membuat mendeklarasikan DatabaseOpenHelper itu adalah dbhelper
	private DatabaseOpenHelper dbhelper;
	// membuat mendeklarasikan SQLiteDatabase itu adalah db
	private SQLiteDatabase db;

	public TM_Child_Facility(Context ctx) {
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

	public void InsertFasilitasAnak(String child_id, String fasilitas_id,
			String fasilitas_cost_id) {
		ContentValues values = new ContentValues();

		values.put(ROW_CHILD_ID, child_id);
		values.put(ROW_FACILITY_ID, fasilitas_id);
		values.put(ROW_FACILITY_COST_ID, fasilitas_cost_id);
		values.put(ROW_CREATED_BY, "-");
		values.put(ROW_CREATED_TIME, "-");
		values.put(ROW_UPDATE_BY, "-");
		values.put(ROW_UPDATE_TIME, "-");
		try {
			// menambahkan nama tabel bila tidak akan error
			// db.delete(NAMA_TABEL, null, null);
			db.insert(NAMA_TABEL, null, values);
			Log.e("Insert tabel child facility", "child_id " + child_id
					+ " fasilitas id " + fasilitas_id + " fasilitas_cost_id "
					+ fasilitas_cost_id);
		} catch (Exception e) {
			Log.e("Eror padda tabel child_facility", e.toString());
		}
	}

	public void DeleteFasilitasAnak(String child_id) {
		// delete first
		try {
			db.delete(NAMA_TABEL, ROW_CHILD_ID + " = ?",
					new String[] { child_id });
			// insert the new
		} catch (Exception e) {
			Log.e("Eror delete fasilitas anak", e.toString());
		}

	}

	public ArrayList<ChildFacility_Model> getSemuaFasilitasAnak(
			String child_id) {
		ArrayList<ChildFacility_Model> data = new ArrayList<ChildFacility_Model>();
		Cursor mCursor = db
				.rawQuery(
						"SELECT  child_id,facility_id,facility_cost_id  FROM  TM_Child_Facility WHERE child_id= '"
								+ child_id + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			while (!mCursor.isAfterLast()) {
				// parse data into model
				Log.e("fasilitas anak", " id anak " + mCursor.getString(0)
						+ " id fasilitas " + mCursor.getString(1)
						+ " id cost fasilitas " + mCursor.getString(2));
				data.add(parseData(mCursor));
				mCursor.moveToNext();
			}
		}
		mCursor.close();
		return data;
	}

	// pulic ArrayList<String> getAll_IdFasilitasAnak(String child_id)
	// {
	// ArrayList<String> data = new ArrayList<String>();
	// Cursor mCursor = db.rawQuery(
	// "SELECT  facility_id  FROM  TM_Child_Facility WHERE child_id= '"+
	// child_id + "'", null);
	// if (mCursor != null) {
	// while (!mCursor.isAfterLast()) {
	// //parse data into model
	// data.add(mCursor.getString(0).toString());
	// mCursor.moveToNext();
	// }
	// }
	// mCursor.close();
	// return data;
	// }
	//
	// public ArrayList<String> getAll_IdBrandNameFasilitasAnak(String child_id,
	// String fasilitas_id)
	// {
	//
	// }

	private ChildFacility_Model parseData(Cursor cursor) {
		ChildFacility_Model curData = new ChildFacility_Model();
		// curData.setChild_facility_id(cursor.getString(0));
		curData.setChild_id(cursor.getString(cursor
				.getColumnIndex(ROW_CHILD_ID)));
		curData.setFacility_id(cursor.getString(cursor
				.getColumnIndex(ROW_FACILITY_ID)));
		curData.setFacility_cost_id(cursor.getString(cursor
				.getColumnIndex(ROW_FACILITY_COST_ID)));

		return curData;
	}

	// public void SaveDataFromServer(String[] data) {
	// try {
	// // cek jika sudah ada di database
	//
	// String sql_insert =
	// "INSERT INTO TM_Child_Facility(facility_id,facility_description,created_time,update_by,update_time) VALUES (?,null,null,null,null)";
	// for (String aa : data) {
	// // cek the duplicate one
	// String sql = "select * from " + NAMA_TABEL
	// + " WHERE facility_description = '" + aa + "'";
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

	// menutup DatabaseOpenHelper
	public void close() {

		if (dbhelper != null) {
			dbhelper.close();
		}
	}
}
