package com.example.databaselap;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TR_Visit {
	// deklarasi row
	private static final String ROW_VISIT_ID = "visit_id";
	private static final String ROW_CHILD_ID = "child_id";
	private static final String ROW_VISIT_DATE = "visit_date";
	private static final String ROW_COMPLAINT_ID = "complaint_id";
	private static final String ROW_COMPLAINT_STATUS_ID = "complaint_status_id";
	private static final String ROW_VISIT_TYPE_ID = "visit_type_id";
	private static final String ROW_ACTION = "action";
	private static final String ROW_NOTE = "note";
	private static final String ROW_HEIGHT = "height";
	private static final String ROW_WEIGHT = "weight";
	private static final String ROW_LILA = "lila";
	private static final String ROW_REMINDER_ID = "reminder_id";
	// private static final String ROW_CHILD_FACILITY_ID = "child_facility_id";
	private static final String ROW_DRUG_TAKEN = "drug_taken";
	// private static final String ROW_FACILITY_QUANTITY = "facility_quantity";
	// private static final String ROW_ENVIRONMENT_ID = "environment_id";
	private static final String ROW_CREATED_BY = "created_by";
	private static final String ROW_CREATED_TIME = "created_time";
	private static final String ROW_UPDATE_BY = "update_by";
	private static final String ROW_UPDATE_TIME = "update_time";
	private static final String ROW_DELETE_STATUS = "delete_status";

	// mendeklarasikan NAMA_DB DAN TABLE DAN DATABASE VERSION
	private static final String NAMA_DB = "DB_LAP_VISIT";
	private static final String NAMA_TABEL = "TR_VISIT";
	private static final int DB_VERSION = 1;

	// mendeklarasikan CREATE_TABLE = MEMBUAT TABLE"
	private static final String CREATE_TABLE = "create table " + NAMA_TABEL
			+ "(" + ROW_VISIT_ID + " integer PRIMARY KEY autoincrement," + ""
			+ "" + ROW_CHILD_ID + "varchar ," + ROW_COMPLAINT_ID + "varchar ,"
			+ ROW_VISIT_TYPE_ID + "varchar ," + ROW_COMPLAINT_STATUS_ID + "varchar,"
			+ ROW_VISIT_DATE + "varchar," + ROW_ACTION + "VARCHAR," + ROW_NOTE
			+ "text," + "" + ROW_HEIGHT + "varchar ," + ROW_DRUG_TAKEN
			+ "varchar," + ROW_WEIGHT + "varchar," + ROW_LILA
			+ "varchar ," + ROW_REMINDER_ID + "varchar,"
			+ "" + ROW_CREATED_BY
			+ "VARCHAR," + ROW_CREATED_TIME + "VARCHAR," + "" + ROW_UPDATE_BY
			+ "VARCHAR," + ROW_UPDATE_TIME + "VARCHAR," + "" + ROW_DELETE_STATUS
			+ "VARCHAR,)";

	// membuat mendeklarasikan Context itu adalah context
	private final Context context;
	// membuat mendeklarasikan DatabaseOpenHelper itu adalah dbhelper
	private DatabaseOpenHelper dbhelper;
	// membuat mendeklarasikan SQLiteDatabase itu adalah db
	private SQLiteDatabase db;

	public TR_Visit(Context ctx) {
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

	//insert the tr visit
	public void InsertToTableVisit(String id_child, String complaint_id, String complaint_status_id, String visit_date, String visit_type_id, String drug_taken, String height,
			String lila, String weight, String action, String note, String reminder_id, String cb, String ct, String ub, String ut, String ds)
	{
		ContentValues val = new ContentValues();
		val.put(ROW_CHILD_ID, id_child);
		val.put(ROW_COMPLAINT_ID, complaint_id);
		val.put(ROW_COMPLAINT_STATUS_ID, complaint_status_id);
		val.put(ROW_VISIT_DATE, visit_date);
		val.put(ROW_VISIT_TYPE_ID, visit_type_id);
		val.put(ROW_DRUG_TAKEN, drug_taken);
		val.put(ROW_HEIGHT, height);
		val.put(ROW_LILA, lila);
		val.put(ROW_WEIGHT, weight);
		val.put(ROW_ACTION, action);
		val.put(ROW_NOTE, note);
		val.put(ROW_REMINDER_ID, reminder_id);
		val.put(ROW_CREATED_BY,  cb);
		val.put(ROW_CREATED_TIME, ct);
		val.put(ROW_UPDATE_BY, ub);
		val.put(ROW_UPDATE_TIME, ut);
		val.put(ROW_DELETE_STATUS, ds);
		
		
		try {
			//menambahkan nama tabel bila tidak akan error
			//	db.delete(NAMA_TABEL, null, null);
				db.insert(NAMA_TABEL, null, val);
			
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("Failed to insert data visit anak", e.toString());
			e.printStackTrace();
			
		}
	}
	//update the tr visit
	public void UpdateToTableVisit(String id_child, String complaint_id, String complaint_status_id, String visit_date, String visit_type_id, String drug_taken, String height,
			String lila, String weight, String action, String note, String reminder_id, String cb, String ct, String ub, String ut, String ds)
	{
		ContentValues val = new ContentValues();
		val.put(ROW_CHILD_ID, id_child);
		val.put(ROW_COMPLAINT_ID, complaint_id);
		val.put(ROW_COMPLAINT_STATUS_ID, complaint_status_id);
		val.put(ROW_VISIT_DATE, visit_date);
		val.put(ROW_VISIT_TYPE_ID, visit_type_id);
		val.put(ROW_DRUG_TAKEN, drug_taken);
		val.put(ROW_HEIGHT, height);
		val.put(ROW_LILA, lila);
		val.put(ROW_WEIGHT, weight);
		val.put(ROW_ACTION, action);
		val.put(ROW_NOTE, note);
		val.put(ROW_REMINDER_ID, reminder_id);
		val.put(ROW_CREATED_BY,  cb);
		val.put(ROW_CREATED_TIME, ct);
		val.put(ROW_UPDATE_BY, ub);
		val.put(ROW_UPDATE_TIME, ut);
		val.put(ROW_DELETE_STATUS, ds);
		try {
			//menambahkan nama tabel bila tidak akan error
			//	db.delete(NAMA_TABEL, null, null);
				//db.insert(NAMA_TABEL, null, val);
				db.update(NAMA_TABEL, val, ("child_id = ? AND visit_date = "
						+ visit_date),
						new String[] { id_child });
			
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("Failed to insert data visit anak", e.toString());
			e.printStackTrace();
			
		}
	}
	
	//select visit date of child
	
}
