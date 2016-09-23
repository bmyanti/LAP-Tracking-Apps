package com.example.databaselap;

import java.util.ArrayList;
import java.util.Collections;

import com.example.modellap.ChildARV_Model;
import com.example.modellap.ChildFacility_Model;
import com.example.modellap.Child_Model;
import com.example.modellap.Complaint_Model;
import com.example.modellap.Environment_Model;
import com.example.modellap.Image_Model;
import com.example.modellap.Visit_Facility;
import com.example.modellap.Visit_Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
	private static final String nama_database = "LENTERAANAKPELANGI17.db";
	private static final int versi_database = 1;
	
	/********************SEQUENCE*****************************/
	private static final String SEQ = "SEQ";
	
	private static final String seq_number = "seq_num";
	

	/******************* CAREGIVER ***************************/
	private static final String TM_CAREGIVER = "TM_CAREGIVER";

	private static final String CAREGIVER_ID = "CAREGIVER_id";
	private static final String CAREGIVER_DESCRIPTION = "CAREGIVER_description";
	private static final String CAREGIVER_CREATED_BY = "CAREGIVER_created_by";
	private static final String CAREGIVER_CREATED_TIME = "CAREGIVER_created_time";
	private static final String CAREGIVER_UPDATE_BY = "CAREGIVER_update_by";
	private static final String CAREGIVER_UPDATE_TIME = "CAREGIVER_update_time";
	private static final String CAREGIVER_DELETE_STATUS = "CAREGIVER_delete_status";

	/******************* TM_CHILD_FACILITY ***************************/
	private static final String TM_CHILD_FACILITY = "TM_CHILD_FACILITY";

	private static final String CHILD_FACILITY_CHILD_ID = "CF_child_id";
	private static final String CHILD_FACILITY_FACILITY_ID = "CF_facility_id";
	private static final String CHILD_FACILITY_FACILITY_COST_ID = "CF_facility_cost_id";
	
	/******************TM_COST_FACILITY**********************************/
	private static final String TM_COST_FACILITY = "TM_COST_FACILITY";

	private static final String COST_FACILITY_FACILITY_COST_ID = "COST_FACILITY_FACILITY_COST_ID";
	private static final String COST_FACILITY_FACILITY_ID = "COST_FACILITY_FACILITY_ID";
	private static final String COST_FACILITY_FACILITY_BRAND_NAME = "COST_FACILITY_FACILITY_BRAND_NAME";
	private static final String COST_FACILITY_FACILITY_PRICE = "COST_FACILITY_FACILITY_PRICE";
	private static final String COST_FACILITY_CREATE_TIME = "COST_FACILITY_CREATE_TIME";
	private static final String COST_FACILITY_CREATE_BY = "COST_FACILITY_CREATE_BY";
	private static final String COST_FACILITY_UPDATE_TIME = "COST_FACILITY_UPDATE_TIME";
	private static final String COST_FACILITY_UPDATE_BY = "COST_FACILITY_UPDATE_BY";
	private static final String COST_FACILITY_DELETE_STATUS = "COST_FACILITY_DELETE_STATUS";

	/******************* TM_CHILD ***************************/
	private static final String TM_CHILD = "TM_CHILD";

	private static final String CHILD_ID = "CHILD_id";
	private static final String CHILD_NAME = "CHILD_name";
	private static final String CHILD_BOD = "CHILD_bod";
	private static final String CHILD_GENDER = "CHILD_gender";
	private static final String CHILD_ADDRESS = "CHILD_address";
	private static final String CHILD_CAREGIVER_NAME = "CHILD_caregiver_name";
	private static final String CHILD_CAREGIVER_ID = "CHILD_caregiver_id";
	private static final String CHILD_CAREGIVER_PHONE = "CHILD_caregiver_phone";
	private static final String CHILD_FATHER_NAME = "CHILD_father_name";
	private static final String CHILD_MOTHER_NAME = "CHILD_mother_name";
	private static final String CHILD_BLOOD_TYPE = "CHILD_blood_type";
	private static final String CHILD_SCHOOL_NAME = "CHILD_school_name";
	private static final String CHILD_SUBDISTRICT_ID = "CHILD_subdistrict_id";
	private static final String CHILD_CLASS_ID = "CHILD_class_id";
	private static final String CHILD_GROUP_ID = "CHILD_group_id";
	private static final String CHILD_SCHOOL_SUBDISTRICT_ID = "CHILD_school_subdistrict_id";
	private static final String CHILD_DAD_STATUS_ID = "CHILD_dad_status_id";
	private static final String CHILD_MOM_STATUS_ID = "CHILD_mom_status_id";
	private static final String CHILD_STATUS = "CHILD_status_id";
	private static final String CHILD_DRUG_TAKEN = "CHILD_drug_taken";
	private static final String CHILD_FACILITY_ID = "CHILD_facility_id";
	private static final String CHILD_DRUG_TYPE_ID = "CHILD_drug_type_id";
	private static final String CHILD_DRUG_STATUS_ID = "CHILD_drug_status_id";
	private static final String CHILD_DRUG_DOSE_ID = "CHILD_drug_dose_id";
	private static final String CHILD_IMAGE_NAME = "CHILD_image_name";
	private static final String CHILD_IMAGE_PATH = "CHILD_image_path";
	private static final String CHILD_IMAGE_SERVER_PATH = "CHILD_image_server_path";
	private static final String CHILD_PARAM_ID = "CHILD_param_Id";
	private static final String CHILD_CREATED_BY = "CHILD_created_by";
	private static final String CHILD_CREATED_TIME = "CHILD_created_time";
	private static final String CHILD_UPDATE_BY = "CHILD_update_by";
	private static final String CHILD_UPDATE_TIME = "CHILD_update_time";
	private static final String CHILD_DELETE_STATUS = "CHILD_delete_status";

	/******************* TM_CLASS ***************************/
	private static final String TM_CLASS = "TM_CLASS";

	private static final String CLASS_ID = "Class_id";
	private static final String CLASS_DESCRIPTION = "CLASS_description";
	private static final String CLASS_CREATED_BY = "CLASS_created_by";
	private static final String CLASS_CREATED_TIME = "CLASS_created_time";
	private static final String CLASS_UPDATE_BY = "CLASS_update_by";
	private static final String CLASS_UPDATE_TIME = "CLASS_update_time";
	private static final String CLASS_DELETE_STATUS = "CLASS_delete_status";

	/******************* TM_DISTRICT ***************************/
	private static final String TM_DISTRICT = "TM_DISTRICT";

	private static final String DISTRICT_ID = "DISTRICT_id";
	private static final String DISTRICT_DESCRIPTION = "DISTRICT_description";
	private static final String DISTRICT_CREATED_BY = "DISTRICT_created_by";
	private static final String DISTRICT_CREATED_TIME = "DISTRICT_created_time";
	private static final String DISTRICT_UPDATE_BY = "DISTRICT_update_by";
	private static final String DISTRICT_UPDATE_TIME = "DISTRICT_update_time";
	private static final String DISTRICT_DELETE_STATUS = "DISTRICT_delete_status";

	/******************* TM_SUBDISTRICT ***************************/
	private static final String TM_SUBDISTRICT = "TM_SUBDISTRICT";

	private static final String SUBDISTRICT_ID = "SUBDISTRICT_id";
	private static final String SUBDISTRICT_DISTRICT_ID = "SUBDISTRICT_district_id";
	private static final String SUBDISTRICT_DESCRIPTION = "SUBDISTRICT_description";
	private static final String SUBDISTRICT_CREATED_BY = "SUBDISTRICT_created_by";
	private static final String SUBDISTRICT_CREATED_TIME = "SUBDISTRICT_created_time";
	private static final String SUBDISTRICT_UPDATE_BY = "SUBDISTRICT_update_by";
	private static final String SUBDISTRICT_UPDATE_TIME = "SUBDISTRICT_update_time";
	private static final String SUBDISTRICT_DELETE_STATUS = "SUBDISTRICT_delete_status";

	/******************* TM_FACILITY ***************************/
	private static final String TM_FACILITY = "TM_FACILITY";

	private static final String FACILITY_ID = "FACILITY_id";
	private static final String FACILITY_DESCRIPTION = "FACILITY_description";
	private static final String FACILITY_CREATED_BY = "FACILITY_created_by";
	private static final String FACILITY_CREATED_TIME = "FACILITY_created_time";
	private static final String FACILITY_UPDATE_BY = "FACILITY_update_by";
	private static final String FACILITY_UPDATE_TIME = "FACILITY_update_time";
	private static final String FACILITY_DELETE_STATUS = "FACILITY_delete_status";

	/******************* TM_PARENT_STATUS ***************************/
	private static final String TM_PARENT_STATUS = "TM_PARENT_STATUS";

	private static final String PARENT_STATUS_ID = "PARENT_STATUS_id";
	private static final String PARENT_STATUS_DESCRIPTION = "PARENT_STATUS_description";
	private static final String PARENT_STATUS_CREATED_BY = "PARENT_STATUS_created_by";
	private static final String PARENT_STATUS_CREATED_TIME = "PARENT_STATUS_created_time";
	private static final String PARENT_STATUS_UPDATE_BY = "PARENT_STATUS_update_by";
	private static final String PARENT_STATUS_UPDATE_TIME = "PARENT_STATUS_update_time";
	private static final String PARENT_STATUS_DELETE_STATUS = "PARENT_STATUS_delete_status";

	/******************* TM_DRUG_TYPE ***************************/
	private static final String TM_DRUG_TYPE = "TM_DRUG_TYPE";

	private static final String DRUG_TYPE_ID = "DRUG_TYPE_id";
	private static final String DRUG_TYPE_DESCRIPTION = "DRUG_TYPE_description";
	private static final String DRUG_TYPE_DRUG_STATUS_ID = "DRUG_TYPE_drug_status_id";
	private static final String DRUG_TYPE_CREATED_BY = "DRUG_TYPE_created_by";
	private static final String DRUG_TYPE_CREATED_TIME = "DRUG_TYPE_created_time";
	private static final String DRUG_TYPE_UPDATE_BY = "DRUG_TYPE_update_by";
	private static final String DRUG_TYPE_UPDATE_TIME = "DRUG_TYPE_update_time";
	private static final String DRUG_TYPE_DELETE_STATUS = "DRUG_TYPE_delete_status";

	/******************* TM_DRUG_STATUS ***************************/
	private static final String TM_DRUG_STATUS = "TM_DRUG_STATUS";

	private static final String DRUG_STATUS_ID = "DRUG_STATUS_id";
	private static final String DRUG_STATUS_DESCRIPTION = "DRUG_STATUS_description";
	private static final String DRUG_STATUS_CREATED_BY = "DRUG_STATUS_created_by";
	private static final String DRUG_STATUS_CREATED_TIME = "DRUG_STATUS_created_time";
	private static final String DRUG_STATUS_UPDATE_BY = "DRUG_STATUS_update_by";
	private static final String DRUG_STATUS_UPDATE_TIME = "DRUG_STATUS_update_time";
	private static final String DRUG_STATUS_DELETE_STATUS = "DRUG_STATUS_delete_status";

	/******************* TM_DRUG_DOSE ***************************/
	private static final String TM_DRUG_DOSE = "TM_DRUG_DOSE";

	private static final String DRUG_DOSE_ID = "DRUG_DOSE_id";
	private static final String DRUG_DOSE_DESCRIPTION = "DRUG_DOSE_description";
	private static final String DRUG_DOSE_CREATED_BY = "DRUG_DOSE_created_by";
	private static final String DRUG_DOSE_CREATED_TIME = "DRUG_DOSE_created_time";
	private static final String DRUG_DOSE_UPDATE_BY = "DRUG_DOSE_update_by";
	private static final String DRUG_DOSE_UPDATE_TIME = "DRUG_DOSE_update_time";
	private static final String DRUG_DOSE_DELETE_STATUS = "DRUG_DOSE_delete_status";
	private static final String DRUG_DOSE_TIME = "DRUG_DOSE_drug_dose_time";

	/******************* TR_CHILD_DRUG_TYPE ***************************/
	private static final String TR_CHILD_DRUG_TYPE = "TR_CHILD_DRUG_TYPE";

	private static final String CK_DT_CHILD_ID = "TR_CDT_child_id";
	private static final String CK_DT_DRUG_TYPE_ID = "TR_CDT_drug_type_id";
	private static final String CK_DT_DRUG_DOSE_ID = "TR_CDT_drug_dose_id";
	private static final String CK_DT_CREATED_BY = "TR_CDT_created_by";
	private static final String CK_DT_CREATED_TIME = "TR_CDT_created_time";
	private static final String CK_DT_UPDATE_BY = "TR_CDT_update_by";
	private static final String CK_DT_UPDATE_TIME = "TR_CDT_update_time";
	private static final String CK_DT_DELETE_STATUS = "TR_CDT_delete_status";
	
	/***/
	/**************************TM_COMPLAINT_STATUS*********************************************/
	private static final String TM_COMPLAINT_STATUS = "TM_COMPLAINT_STATUS";

	private static final String COMPLAINT_STATUS_ID = "COMPLAINT_STATUS_id";
	private static final String COMPLAINT_STATUS_DESCRIPTION = "COMPLAINT_STATUS_description";
	private static final String COMPLAINT_STATUS_CREATED_BY = "COMPLAINT_STATUS_created_by";
	private static final String COMPLAINT_STATUS_CREATED_TIME = "COMPLAINT_STATUS_created_time";
	private static final String COMPLAINT_STATUS_UPDATE_BY = "COMPLAINT_STATUS_update_by";
	private static final String COMPLAINT_STATUS_UPDATE_TIME = "COMPLAINT_STATUS_update_time";
	private static final String COMPLAINT_STATUS_DELETE_STATUS = "COMPLAINT_STATUS_delete_status";
	
	/***************************TM_COMPLAINT********************************************/
	private static final String TM_COMPLAINT = "TM_COMPLAINT";

	private static final String COMPLAINT_ID = "COMPLAINT_id";
	private static final String COMPLAINT_DESCRIPTION = "COMPLAINT_description";
	private static final String COMPLAINT_CREATED_BY = "COMPLAINT_created_by";
	private static final String COMPLAINT_CREATED_TIME = "COMPLAINT_created_time";
	private static final String COMPLAINT_UPDATE_BY = "COMPLAINT_update_by";
	private static final String COMPLAINT_UPDATE_TIME = "COMPLAINT_update_time";
	private static final String COMPLAINT_DELETE_STATUS = "COMPLAINT_delete_status";
	
	/**************************TM_ENVIRONMENT*********************************************/
	private static final String TM_ENVIRONMENT = "TM_ENVIRONMENT";

	private static final String ENVIRONMENT_ID = "ENVIRONMENT_id";
	private static final String ENVIRONMENT_DESCRIPTION = "ENVIRONMENT_description";
	private static final String ENVIRONMENT_CREATED_BY = "ENVIRONMENT_created_by";
	private static final String ENVIRONMENT_CREATED_TIME = "ENVIRONMENT_created_time";
	private static final String ENVIRONMENT_UPDATE_BY = "ENVIRONMENT_update_by";
	private static final String ENVIRONMENT_UPDATE_TIME = "ENVIRONMENT_update_time";
	private static final String ENVIRONMENT_DELETE_STATUS = "ENVIRONMENT_delete_status";
	
	/**************************TM_IMAGE_TYPE*********************************************/
	private static final String TM_IMAGE_TYPE = "TM_IMAGE_TYPE";

	private static final String IMAGE_TYPE_ID = "IMAGE_TYPE_id";
	private static final String IMAGE_TYPE_DESCRIPTION = "IMAGE_TYPE_description";
	private static final String IMAGE_TYPE_CREATED_BY = "IMAGE_TYPE_created_by";
	private static final String IMAGE_TYPE_CREATED_TIME = "IMAGE_TYPE_created_time";
	private static final String IMAGE_TYPE_UPDATE_BY = "IMAGE_TYPE_update_by";
	private static final String IMAGE_TYPE_UPDATE_TIME = "IMAGE_TYPE_update_time";
	private static final String IMAGE_TYPE_DELETE_STATUS = "IMAGE_TYPE_delete_status";
	
	
	/**************************TM_UNIT*********************************************/
	
	/*--*/
	private static final String TM_UNIT = "TM_UNIT";

	private static final String UNIT_ID = "UNIT_id";
	private static final String UNIT_DESCRIPTION = "UNIT_description";
	private static final String UNIT_VISIT_TYPE_ID = "UNIT_visit_type_id";
	private static final String UNIT_UNIT_UOM = "UNIT_unit_uom";
	private static final String UNIT_SCORE_REFERENCES = "UNIT_score_references";
	private static final String UNIT_CREATED_BY = "UNIT_created_by";
	private static final String UNIT_CREATED_TIME = "UNIT_created_time";
	private static final String UNIT_UPDATE_BY = "UNIT_update_by";
	private static final String UNIT_UPDATE_TIME = "UNIT_update_time";
	private static final String UNIT_DELETE_STATUS = "UNIT_delete_status";
	private static final String UNIT_REFERENCES_NUMBER = "UNIT_references_number";
	
	
	/***************************TM_VISIT_TYPE********************************************/
	private static final String TM_VISIT_TYPE = "TM_VISIT_TYPE";

	private static final String VISIT_TYPE_ID = "VISIT_TYPE_id";
	private static final String VISIT_TYPE_DESCRIPTION = "VISIT_TYPE_description";
	private static final String VISIT_TYPE_CREATED_BY = "VISIT_TYPE_created_by";
	private static final String VISIT_TYPE_CREATED_TIME = "VISIT_TYPE_created_time";
	private static final String VISIT_TYPE_UPDATE_BY = "VISIT_TYPE_update_by";
	private static final String VISIT_TYPE_UPDATE_TIME = "VISIT_TYPE_update_time";
	private static final String VISIT_TYPE_DELETE_STATUS = "VISIT_TYPE_delete_status";
	
	/**************************TR_IMAGE*********************************************/
	private static final String TR_IMAGE = "TR_IMAGE";

	private static final String IMAGE_NAME = "IMAGE_name";
	private static final String IMAGE_DATE = "IMAGE_date";
	private static final String IMAGE_ID = "IMAGE_id";
	private static final String IMAGE_PATH = "IMAGE_path";
	private static final String IMAGE_SERVER_PATH = "IMAGE_server_path";
	private static final String IMAGE_IMAGE_TYPE_ID = "IMAGE_image_type_id";
	private static final String IMAGE_LONGITUDE = "IMAGE_longitude";
	private static final String IMAGE_LATITUDE = "IMAGE_latitude";
	private static final String IMAGE_CREATED_BY = "IMAGE_created_by";
	private static final String IMAGE_CREATED_TIME = "IMAGE_created_time";
	private static final String IMAGE_UPDATE_BY = "IMAGE_update_by";
	private static final String IMAGE_UPDATE_TIME = "IMAGE_update_time";
	private static final String IMAGE_DELETE_STATUS = "IMAGE_delete_status";
	
	/**************************TR_VISIT_ENVIRONMENT*********************************************/
	private static final String TR_VISIT_ENVIRONMENT = "TR_VISIT_ENVIRONMENT";

	private static final String VE_VISIT_DATE = "VE_visit_date";
	private static final String VE_CHILD_ID = "VE_child_id";
	private static final String VE_ENVIRONMENT_ID = "VE_environment_id";
	private static final String VE_ENVIRONMENT_SCORE = "VE_environment_score";
	
	/**************************TR_VISIT_FACILITY*********************************************/
	private static final String TR_VISIT_FACILITY = "TR_VISIT_FACILITY";

	private static final String VF_VISIT_DATE = "VF_visit_date";
	private static final String VF_CHILD_ID = "VF_child_id";
	private static final String VF_FACILITY_ID = "VF_facility_id";
	private static final String VF_FACILITY_COST_ID = "VF_facility_cost_id";
	private static final String VF_FACILITY_QUANTITY = "VF_facility_quantity";
	
	/**************************TR_VISIT*********************************************/
	private static final String TR_VISIT = "TR_VISIT";

	private static final String VISIT_DATE = "VISIT_date";
	private static final String VISIT_CHILD_ID = "VISIT_child_id";
	private static final String VISIT_COMPLAINT_ID = "VISIT_complaint_id";
	private static final String VISIT_COMPLAINT_STATUS_ID= "VISIT_complaint_status_id";
	private static final String VISIT_VISIT_TYPE_ID = "VISIT_visit_type_id";
	private static final String VISIT_ACTION = "VISIT_action";
	private static final String VISIT_NOTE = "VISIT_note";
	private static final String VISIT_HEIGHT = "VISIT_height";
	private static final String VISIT_WEIGHT = "VISIT_weight";
	private static final String VISIT_LILA = "VISIT_lila";
	private static final String VISIT_BMI_SCORE = "VISIT_bmi_score";
	private static final String VISIT_DRUG_TAKEN = "VISIT_drug_taken";
	private static final String VISIT_REMINDER_ID = "VISIT_reminder_id";
	private static final String VISIT_CREATE_TIME = "VISIT_create_time";
	private static final String VISIT_CREATE_BY = "VISIT_create_by";
	private static final String VISIT_UPDATE_TIME = "VISIT_update_time";
	private static final String VISIT_UPDATE_BY = "VISIT_update_by";
	private static final String VISIT_DELETE_STATUS = "VISIT_delete_status";
	
	//**/
	/**************************TM_ABSENT*********************************************/
	private static final String TM_ABSENT = "TM_Absent";

	private static final String ABSENT_EVENT_ID  = "ABSENT_event_id";
	private static final String ABSENT_EMPLOYEE_ID = "ABSENT_employee_id";
	private static final String ABSENT_CHILD_ID = "ABSENT_child_id";
	private static final String ABSENT_START_DATE = "ABSENT_start_date";
	private static final String ABSENT_EVENT_DESCRIPTION = "ABSENT_event_description";
	private static final String ABSENT_CREATED_BY = "ABSENT_created_by";
	private static final String ABSENT_CREATED_TIME = "ABSENT_created_time";
	private static final String ABSENT_UPDATE_BY = "ABSENT_update_by";
	private static final String ABSENT_UPDATE_TIME = "ABSENT_update_time";
	
	/**************************TM_AUTHORITY*********************************************/
	private static final String TM_AUTHORITY = "TM_Authority";

	private static final String AUTHORITY_AUTHORITY_ID  = "AUTHORITY_authority_id";
	private static final String AUTHORITY_AUTHORITY_STATUS = "AUTHORITY_authority_status";
	private static final String AUTHORITY_MENU_ID = "AUTHORITY_menu_id";
	private static final String AUTHORITY_CREATED_BY = "AUTHORITY_created_by";
	private static final String AUTHORITY_CREATED_TIME = "AUTHORITY_created_time";
	private static final String AUTHORITY_UPDATE_BY = "AUTHORITY_update_by";
	private static final String AUTHORITY_UPDATE_TIME = "AUTHORITY_update_time";
	
	/**************************TM_BMI_CALC*********************************************/
	private static final String TM_BMI_CALC = "TM_BMI_Calc";

	private static final String BMI_CALC_START_AGE  = "BMI_CALC_start_age";
	private static final String BMI_CALC_END_AGE = "BMI_CALC_end_age";
	private static final String BMI_CALC_GENDER = "BMI_CALC_gender";
	private static final String BMI_CALC_START_BMI = "BMI_CALC_start_bmi";
	private static final String BMI_CALC_END_BMI = "BMI_CALC_end_bmi";
	private static final String BMI_CALC_SCORE = "BMI_CALC_score";
	private static final String BMI_CALC_CREATED_BY = "BMI_CALC_created_by";
	private static final String BMI_CALC_CREATED_TIME = "BMI_CALC_created_time";
	private static final String BMI_CALC_UPDATE_BY = "BMI_CALC_update_by";
	private static final String BMI_CALC_UPDATE_TIME = "BMI_CALC_update_time";
	
	/**************************TM_EMPLOYEE*********************************************/
	private static final String TM_EMPLOYEE = "TM_Employee";

	private static final String EMPLOYEE_EMPLOYEE_ID   = "EMPLOYEE_employee_id";
	private static final String EMPLOYEE_USERNAME = "EMPLOYEE_username";
	private static final String EMPLOYEE_PASSWORD = "EMPLOYEE_password";
	private static final String EMPLOYEE_AUTHORITY_ID = "EMPLOYEE_authority_id";
	private static final String EMPLOYEE_GROUP_ID = "EMPLOYEE_group_id";
	private static final String EMPLOYEE_CREATED_BY = "EMPLOYEE_created_by";
	private static final String EMPLOYEE_CREATED_TIME = "EMPLOYEE_created_time";
	private static final String EMPLOYEE_UPDATE_BY = "EMPLOYEE_update_by";
	private static final String EMPLOYEE_UPDATE_TIME = "EMPLOYEE_update_time";
	
	/**************************TM_EVENT*********************************************/
	private static final String TM_EVENT = "TM_Event";

	private static final String EVENT_EVENT_ID = "EVENT_event_id";
	private static final String EVENT_EVENT_DESCRIPTION = "EVENT_event_description";
	private static final String EVENT_START_DATE = "EVENT_start_date";
	private static final String EVENT_END_DATE = "EVENT_end_date";
	private static final String EVENT_CREATED_BY = "EVENT_created_by";
	private static final String EVENT_CREATED_TIME = "EVENT_created_time";
	private static final String EVENT_UPDATE_BY = "EVENT_update_by";
	private static final String EVENT_UPDATE_TIME = "EVENT_update_time";
	
	/**************************TM_GROUP*********************************************/
	private static final String TM_GROUP = "TM_Group";

	private static final String GROUP_GROUP_ID = "GROUP_group_id";
	private static final String GROUP_GROUP_DESCRIPTION = "GROUP_group_description";
	private static final String GROUP_CREATED_BY = "GROUP_created_by";
	private static final String GROUP_CREATED_TIME = "GROUP_created_time";
	private static final String GROUP_UPDATE_BY = "GROUP_update_by";
	private static final String GROUP_UPDATE_TIME = "GROUP_update_time";
	
	/**************************TM_MENU*********************************************/
	private static final String TM_MENU = "TM_Menu";

	private static final String MENU_MENU_ID = "MENU_menu_id";
	private static final String MENU_MENU_DESCRIPTION = "MENU_menu_description";
	private static final String MENU_CREATED_BY = "MENU_created_by";
	private static final String MENU_CREATED_TIME = "MENU_created_time";
	private static final String MENU_UPDATE_BY  = "MENU_update_by";
	private static final String MENU_UPDATE_TIME = "MENU_update_time";
	
	/**************************TM_MOVE_CHILD_GROUP*********************************************/
	private static final String TM_MOVE_CHILD_GROUP = "TM_Move_Child_Group";

	private static final String MOVE_CHILD_GROUP_DISTRICT_ID = "MOVE_CHILD_GROUP_district_id";
	private static final String MOVE_CHILD_GROUP_GROUP_ID = "MOVE_CHILD_GROUP_group_id";
	private static final String MOVE_CHILD_GROUP_CHILD_ID = "MOVE_CHILD_GROUP_child_id";
	private static final String MOVE_CHILD_GROUP_CREATED_BY = "MOVE_CHILD_GROUP_created_by";
	private static final String MOVE_CHILD_GROUP_CREATED_TIME  = "MOVE_CHILD_GROUP_created_time";
	private static final String MOVE_CHILD_GROUP_UPDATE_BY = "MOVE_CHILD_GROUP_update_by";
	private static final String MOVE_CHILD_GROUP_UPDATE_TIME = "MOVE_CHILD_GROUP_update_time";
	
	/**************************TM_PARAMETER*********************************************/
	private static final String TM_PARAMETER = "TM_Parameter";

	private static final String PARAMETER_PARAM_GROUP = "PARAMETER_param_group";
	private static final String PARAMETER_PARAM_ID = "PARAMETER_param_id";
	private static final String PARAMETER_REMINDER_DATE  = "PARAMETER_reminder_date";
	private static final String PARAMETER_START_DATE = "PARAMETER_start_date";
	private static final String PARAMETER_END_DATE  = "PARAMETER_end_date";
	private static final String PARAMETER_CREATED_BY = "PARAMETER_created_by";
	private static final String PARAMETER_CREATED_TIME = "PARAMETER_created_time";
	private static final String PARAMETER_UPDATE_BY = "PARAMETER_update_by";
	private static final String PARAMETER_UPDATE_TIME = "PARAMETER_update_time";
	
	/**************************TM_REASON*********************************************/
	private static final String TM_REASON = "TM_Reason";

	private static final String REASON_REASON_ID = "REASON_reason_id";
	private static final String REASON_REASON_DESCRIPTION = "REASON_reason_description";
	private static final String REASON_CREATED_BY  = "REASON_created_by";
	private static final String REASON_CREATED_TIME = "REASON_created_time";
	private static final String REASON_UPDATE_BY  = "REASON_update_by";
	private static final String REASON_UPDATE_TIME = "REASON_update_time";
	
	
	/**************************TR_LABRESULT*********************************************/
	private static final String TR_LABRESULT = "TR_LabResult";

	private static final String LABRESULT_CHILD_ID = "LABRESULT_child_id";
	private static final String LABRESULT_DATE_TIME = "LABRESULT_date_time";
	private static final String LABRESULT_UNIT_TYPE  = "LABRESULT_unit_type";
	private static final String LABRESULT_UNIT_ID = "LABRESULT_unit_id";
	private static final String LABRESULT_CREATED_BY  = "LABRESULT_created_by";
	private static final String LABRESULT_CREATED_TIME = "LABRESULT_created_time";
	private static final String LABRESULT_UPDATE_BY  = "LABRESULT_update_by";
	private static final String LABRESULT_UPDATE_TIME = "LABRESULT_update_time";
	
	
	/**************************TR_NONACTIVE*********************************************/
	private static final String TR_NONACTIVE = "TR_NonActive";

	private static final String NONACTIVE_CHILD_ID = "NONACTIVE_child_id";
	private static final String NONACTIVE_REASON_ID = "NONACTIVE_reason_id";
	private static final String NONACTIVE_CREATED_BY  = "NONACTIVE_created_by";
	private static final String NONACTIVE_CREATED_TIME = "NONACTIVE_created_time";
	private static final String NONACTIVE_UPDATE_BY  = "NONACTIVE_update_by";
	private static final String NONACTIVE_UPDATE_TIME = "NONACTIVE_update_time";
	
	/**************************TR_NOTIFICATION*********************************************/
	private static final String TR_NOTIFICATION = "TR_Notification";

	private static final String NOTIFICATION_CHILD_ID = "NOTIFICATION_child_id";
	private static final String NOTIFICATION_REASON_ID = "NOTIFICATION_reason_id";
	private static final String NOTIFICATION_CREATED_BY  = "NOTIFICATION_created_by";
	private static final String NOTIFICATION_CREATED_TIME = "NOTIFICATION_created_time";
	private static final String NOTIFICATION_UPDATE_BY  = "NOTIFICATION_update_by";
	private static final String NOTIFICATION_UPDATE_TIME = "NOTIFICATION_update_time";
	
	/**************************TM_REMINDER*********************************************/
	 private static final String TM_REMINDER = "TM_REMINDER";

	 private static final String REMINDER_REMINDER_ID = "REMINDER_reminder_id";
	 private static final String REMINDER_REMINDER_GROUP_ID = "REMINDER_reminder_group_id";
	 private static final String REMINDER_REMINDER_DATE  = "REMINDER_reminder_date";
	 private static final String REMINDER_START_DATE = "REMINDER_start_date";
	 private static final String REMINDER_END_DATE  = "REMINDER_end_date";
	 private static final String REMINDER_CREATE_TIME = "REMINDER_create_time";
	 private static final String REMINDER_CREATE_BY = "REMINDER_create_by";
	 private static final String REMINDER_UPDATE_TIME  = "REMINDER_update_time";
	 private static final String REMINDER_UPDATE_BY = "REMINDER_update_by";
	 private static final String REMINDER_DELETE_STATUS  = "REMINDER_delete_status";
	
	/**************************CREATE TABLE********************************************/
	
	String TABLE_SEQ = "CREATE TABLE " + SEQ  + "("
            + seq_number  + " TEXT PRIMARY KEY  )";
	
	String CREATE_TABLE_CAREGIVER = "CREATE TABLE " + TM_CAREGIVER + "("
		    + CAREGIVER_ID + " TEXT PRIMARY KEY," 
			+ CAREGIVER_DESCRIPTION + " TEXT,"
		    + CAREGIVER_CREATED_BY + " TEXT, " 
			+ CAREGIVER_CREATED_TIME + " TEXT," 
		    + CAREGIVER_UPDATE_BY + " TEXT, " 
			+ CAREGIVER_UPDATE_TIME + " TEXT," 
		    + CAREGIVER_DELETE_STATUS + " TEXT" + ")";
	
	
	/**/
	String CREATE_TABLE_CHILD_FACILITY = "CREATE TABLE " + TM_CHILD_FACILITY + "("
		    + CHILD_FACILITY_CHILD_ID + " TEXT," 
			+ CHILD_FACILITY_FACILITY_ID + " TEXT,"
		    + CHILD_FACILITY_FACILITY_COST_ID + " TEXT, PRIMARY KEY("+CHILD_FACILITY_CHILD_ID+","+CHILD_FACILITY_FACILITY_ID+","+CHILD_FACILITY_FACILITY_COST_ID+ "))";
	
	
	String CREATE_TM_COST_FACILITY = "CREATE TABLE " + TM_COST_FACILITY + "("
		    + COST_FACILITY_FACILITY_COST_ID + " TEXT PRIMARY KEY," + COST_FACILITY_FACILITY_ID + " TEXT,"
		    + COST_FACILITY_FACILITY_BRAND_NAME + " TEXT, " + COST_FACILITY_FACILITY_PRICE + " TEXT," + COST_FACILITY_CREATE_TIME
		    + " TEXT, " + COST_FACILITY_CREATE_BY + " TEXT," 
		    + COST_FACILITY_UPDATE_TIME + " TEXT," + COST_FACILITY_UPDATE_BY + " TEXT,"
		    + COST_FACILITY_DELETE_STATUS + " TEXT" + ")";
	
	
	String CREATE_TM_CHILD = "CREATE TABLE " + TM_CHILD + "("
		    + CHILD_ID + " TEXT PRIMARY KEY," 
			+ CHILD_NAME + " TEXT,"
		    + CHILD_BOD + " TEXT, " 
		    + CHILD_GENDER + " TEXT," 
		    + CHILD_ADDRESS + " TEXT, " 
		    + CHILD_CAREGIVER_NAME + " TEXT," 
		    + CHILD_CAREGIVER_ID + " TEXT," 
		    + CHILD_CAREGIVER_PHONE + " TEXT,"
		    + CHILD_FATHER_NAME + " TEXT," 
		    + CHILD_MOTHER_NAME + " TEXT,"
		    + CHILD_BLOOD_TYPE + " TEXT," 
		    + CHILD_SCHOOL_NAME + " TEXT,"
		    + CHILD_SUBDISTRICT_ID + " TEXT," 
		    + CHILD_CLASS_ID + " TEXT,"
		    + CHILD_GROUP_ID + " TEXT,"
		    + CHILD_SCHOOL_SUBDISTRICT_ID + " TEXT, " 
		    + CHILD_DAD_STATUS_ID + " TEXT," 
		    + CHILD_MOM_STATUS_ID + " TEXT, " 
		    + CHILD_STATUS + " TEXT," 
		    + CHILD_DRUG_TAKEN + " TEXT," 
		    + CHILD_FACILITY_ID + " TEXT,"
		    + CHILD_DRUG_TYPE_ID + " TEXT," 
		    + CHILD_DRUG_STATUS_ID + " TEXT,"
		    + CHILD_DRUG_DOSE_ID + " TEXT," 
		    + CHILD_IMAGE_NAME + " TEXT,"
		    + CHILD_IMAGE_PATH + " TEXT," 
		    + CHILD_IMAGE_SERVER_PATH + " TEXT,"
		    + CHILD_PARAM_ID + " TEXT,"
		    + CHILD_CREATED_BY + " TEXT," 
		    + CHILD_CREATED_TIME + " TEXT,"
		    + CHILD_UPDATE_BY + " TEXT," 
		    + CHILD_UPDATE_TIME + " TEXT,"
		    + CHILD_DELETE_STATUS + " TEXT" + ")";
	
	String CREATE_TM_CLASS = "CREATE TABLE " + TM_CLASS + "("
		    + CLASS_ID + " TEXT PRIMARY KEY," 
			+ CLASS_DESCRIPTION + " TEXT,"
		    + CLASS_CREATED_BY + " TEXT, " 
		    + CLASS_CREATED_TIME + " TEXT," 
		    + CLASS_UPDATE_BY + " TEXT, " 
		    + CLASS_UPDATE_TIME + " TEXT,"
		    + CLASS_DELETE_STATUS + " TEXT" + ")";
	
	String CREATE_TM_DISTRICT = "CREATE TABLE " + TM_DISTRICT + "("
            + DISTRICT_ID + " TEXT PRIMARY KEY, "
            + DISTRICT_DESCRIPTION + " TEXT,"
            + DISTRICT_CREATED_BY + " TEXT, "
            + DISTRICT_CREATED_TIME + " TEXT, "
            + DISTRICT_UPDATE_BY + " TEXT, "
            + DISTRICT_UPDATE_TIME + " TEXT, "
            + DISTRICT_DELETE_STATUS + " TEXT )";
	
	String CREATE_TM_SUBDISTRICT = "CREATE TABLE " + TM_SUBDISTRICT + "("
            + SUBDISTRICT_ID + " integer PRIMARY KEY autoincrement, "
            + SUBDISTRICT_DISTRICT_ID + " TEXT,"
            + SUBDISTRICT_DESCRIPTION + " TEXT, "
            + SUBDISTRICT_CREATED_BY + " TEXT, "
            + SUBDISTRICT_CREATED_TIME + " TEXT, "
            + SUBDISTRICT_UPDATE_BY + " TEXT, "
            + SUBDISTRICT_UPDATE_TIME + " TEXT, "
            + SUBDISTRICT_DELETE_STATUS + " TEXT )";
	
	String CREATE_TM_FACILITY = "CREATE TABLE " + TM_FACILITY + "("
            + FACILITY_ID + " TEXT PRIMARY KEY, "
            + FACILITY_DESCRIPTION + " TEXT,"
            + FACILITY_CREATED_BY + " TEXT, "
            + FACILITY_CREATED_TIME + " TEXT, "
            + FACILITY_UPDATE_BY + " TEXT, "
            + FACILITY_UPDATE_TIME + " TEXT, "
            + FACILITY_DELETE_STATUS + " TEXT )";
 
	String CREATE_TM_PARENT_STATUS = "CREATE TABLE " + TM_PARENT_STATUS + "("
            + PARENT_STATUS_ID + " TEXT PRIMARY KEY, "
            + PARENT_STATUS_DESCRIPTION + " TEXT,"
            + PARENT_STATUS_CREATED_BY + " TEXT, "
            + PARENT_STATUS_CREATED_TIME + " TEXT, "
            + PARENT_STATUS_UPDATE_BY + " TEXT, "
            + PARENT_STATUS_UPDATE_TIME + " TEXT, "
            + PARENT_STATUS_DELETE_STATUS + " TEXT )";
	
	String CREATE_TM_DRUG_TYPE = "CREATE TABLE " + TM_DRUG_TYPE + "("
            + DRUG_TYPE_ID + " TEXT PRIMARY KEY, "
            + DRUG_TYPE_DESCRIPTION + " TEXT,"
            + DRUG_TYPE_DRUG_STATUS_ID + " TEXT, "
            + DRUG_TYPE_CREATED_BY + " TEXT, "
            + DRUG_TYPE_CREATED_TIME + " TEXT, "
            + DRUG_TYPE_UPDATE_BY + " TEXT, "
            + DRUG_TYPE_UPDATE_TIME + " TEXT, "
            + DRUG_TYPE_DELETE_STATUS + " TEXT )";
 
	String CREATE_TM_DRUG_STATUS = "CREATE TABLE " + TM_DRUG_STATUS + "("
            + DRUG_STATUS_ID + " TEXT PRIMARY KEY, "
            + DRUG_STATUS_DESCRIPTION + " TEXT,"
            + DRUG_STATUS_CREATED_BY + " TEXT, "
            + DRUG_STATUS_CREATED_TIME + " TEXT, "
            + DRUG_STATUS_UPDATE_BY + " TEXT, "
            + DRUG_STATUS_UPDATE_TIME + " TEXT, "
            + DRUG_STATUS_DELETE_STATUS + " TEXT )";
	
	String CREATE_TM_DRUG_DOSE = "CREATE TABLE " + TM_DRUG_DOSE + "("
            + DRUG_DOSE_ID + " TEXT PRIMARY KEY, "
            + DRUG_DOSE_DESCRIPTION + " TEXT,"
            + DRUG_DOSE_CREATED_BY + " TEXT, "
            + DRUG_DOSE_CREATED_TIME + " TEXT, "
            + DRUG_DOSE_UPDATE_BY + " TEXT, "
            + DRUG_DOSE_UPDATE_TIME + " TEXT, "
            + DRUG_DOSE_DELETE_STATUS + " TEXT, "
            + DRUG_DOSE_TIME + " TEXT )";
	
	String CREATE_TR_CHILD_DRUG_TYPE = "CREATE TABLE " + TR_CHILD_DRUG_TYPE + "("
            + CK_DT_CHILD_ID + " TEXT, "
            + CK_DT_DRUG_TYPE_ID + " TEXT,"
            + CK_DT_DRUG_DOSE_ID + " TEXT, "
            + CK_DT_CREATED_BY + " TEXT, "
            + CK_DT_CREATED_TIME + " TEXT, "
            + CK_DT_UPDATE_BY + " TEXT, "
            + CK_DT_UPDATE_TIME + " TEXT, "
            + CK_DT_DELETE_STATUS + " TEXT , PRIMARY KEY("+CK_DT_CHILD_ID+","+CK_DT_DRUG_TYPE_ID+","+CK_DT_DRUG_DOSE_ID+"))";
	
	//
	String CREATE_TM_COMPLAINT_STATUS = "CREATE TABLE " + TM_COMPLAINT_STATUS  + "("
            + COMPLAINT_STATUS_ID + " TEXT PRIMARY KEY, "
            + COMPLAINT_STATUS_DESCRIPTION + " TEXT,"
            + COMPLAINT_STATUS_CREATED_BY + " TEXT, "
            + COMPLAINT_STATUS_CREATED_TIME  + " TEXT, "
            + COMPLAINT_STATUS_UPDATE_BY  + " TEXT, "
            + COMPLAINT_STATUS_UPDATE_TIME  + " TEXT, "
            + COMPLAINT_STATUS_DELETE_STATUS  + " TEXT )";
	
	//complaint
	String CREATE_TM_COMPLAINT = "CREATE TABLE " + TM_COMPLAINT   + "("
            + COMPLAINT_ID + " TEXT PRIMARY KEY, "
            + COMPLAINT_DESCRIPTION  + " TEXT,"
            + COMPLAINT_CREATED_BY  + " TEXT, "
            + COMPLAINT_CREATED_TIME   + " TEXT, "
            + COMPLAINT_UPDATE_BY   + " TEXT, "
            + COMPLAINT_UPDATE_TIME   + " TEXT, "
            + COMPLAINT_DELETE_STATUS   + " TEXT )";
	
	//environment
	String CREATE_TM_ENVIRONMENT = "CREATE TABLE " + TM_ENVIRONMENT + "("
            + ENVIRONMENT_ID  + " TEXT PRIMARY KEY, "
            + ENVIRONMENT_DESCRIPTION   + " TEXT,"
            + ENVIRONMENT_CREATED_BY   + " TEXT, "
            + ENVIRONMENT_CREATED_TIME    + " TEXT, "
            + ENVIRONMENT_UPDATE_BY    + " TEXT, "
            + ENVIRONMENT_UPDATE_TIME    + " TEXT, "
            + ENVIRONMENT_DELETE_STATUS    + " TEXT )";
	
	//image type
	String CREATE_TM_IMAGE_TYPE = "CREATE TABLE " + TM_IMAGE_TYPE + "("
            + IMAGE_TYPE_ID   + " TEXT PRIMARY KEY, "
            + IMAGE_TYPE_DESCRIPTION    + " TEXT,"
            + IMAGE_TYPE_CREATED_BY    + " TEXT, "
            + IMAGE_TYPE_CREATED_TIME     + " TEXT, "
            + IMAGE_TYPE_UPDATE_BY     + " TEXT, "
            + IMAGE_TYPE_UPDATE_TIME     + " TEXT, "
            + IMAGE_TYPE_DELETE_STATUS     + " TEXT )";
	
	//unit
	String CREATE_TM_UNIT = "CREATE TABLE " + TM_UNIT + "("
            + UNIT_ID   + " TEXT PRIMARY KEY, "
            + UNIT_DESCRIPTION    + " TEXT,"
            + UNIT_VISIT_TYPE_ID   + " TEXT, "
            + UNIT_UNIT_UOM    + " TEXT,"
            + UNIT_SCORE_REFERENCES   + " TEXT, "
            + UNIT_CREATED_BY     + " TEXT, "
            + UNIT_CREATED_TIME      + " TEXT, "
            + UNIT_UPDATE_BY      + " TEXT, "
            + UNIT_UPDATE_TIME      + " TEXT, "
            + UNIT_DELETE_STATUS      + " TEXT,"
            + UNIT_REFERENCES_NUMBER     + " TEXT )";
	
	//visit_type
	String CREATE_TM_VISIT_TYPE = "CREATE TABLE " + TM_VISIT_TYPE  + "("
            + VISIT_TYPE_ID    + " TEXT PRIMARY KEY, "
            + VISIT_TYPE_DESCRIPTION     + " TEXT,"
            + VISIT_TYPE_CREATED_BY     + " TEXT, "
            + VISIT_TYPE_CREATED_TIME      + " TEXT, "
            + VISIT_TYPE_UPDATE_BY      + " TEXT, "
            + VISIT_TYPE_UPDATE_TIME      + " TEXT, "
            + VISIT_TYPE_DELETE_STATUS      + " TEXT )";
	
	//tr image
	String CREATE_TR_IMAGE = "CREATE TABLE " + TR_IMAGE   + "("
            + IMAGE_NAME     + " TEXT, "
            + IMAGE_DATE      + " TEXT,"
            + IMAGE_ID      + " TEXT, "
            + IMAGE_PATH       + " TEXT,"
            + IMAGE_SERVER_PATH      + " TEXT, "
            + IMAGE_IMAGE_TYPE_ID       + " TEXT,"
            + IMAGE_LONGITUDE      + " TEXT, "
            + IMAGE_LATITUDE       + " TEXT,"
            + IMAGE_CREATED_BY     + " TEXT, "
            + IMAGE_CREATED_TIME      + " TEXT, "
            + IMAGE_UPDATE_BY      + " TEXT, "
            + IMAGE_UPDATE_TIME      + " TEXT, "
            + IMAGE_DELETE_STATUS      + " TEXT, PRIMARY KEY("+IMAGE_NAME+","+IMAGE_DATE+","+IMAGE_ID+","+IMAGE_IMAGE_TYPE_ID+"))";
	
	//visit_env
	
	String CREATE_TR_VISIT_ENVIRONMENT = "CREATE TABLE " + TR_VISIT_ENVIRONMENT   + "("
            + VE_VISIT_DATE    + " TEXT,"
            + VE_CHILD_ID      + " TEXT,"
            + VE_ENVIRONMENT_ID + " TEXT, "
            + VE_ENVIRONMENT_SCORE       + " TEXT, PRIMARY KEY("+VE_VISIT_DATE+","+VE_CHILD_ID+","+VE_ENVIRONMENT_ID+"))";
	
	
	//visit_facility
	String CREATE_TR_VISIT_FACILITY = "CREATE TABLE " + TR_VISIT_FACILITY   + "("
            + VF_VISIT_DATE    + " TEXT, "
            + VF_CHILD_ID      + " TEXT,"
            + VF_FACILITY_ID      + " TEXT, "
            + VF_FACILITY_COST_ID       + " TEXT, "
            + VF_FACILITY_QUANTITY       + " TEXT, PRIMARY KEY("+VF_VISIT_DATE+","+VF_CHILD_ID+","+VF_FACILITY_ID+","+VF_FACILITY_COST_ID+"))";
	
	//visit
	String CREATE_TR_VISIT = "CREATE TABLE " + TR_VISIT   + "("
            + VISIT_DATE     + " TEXT, "
            + VISIT_CHILD_ID      + " TEXT,"
            + VISIT_COMPLAINT_ID      + " TEXT, "
            + VISIT_COMPLAINT_STATUS_ID       + " TEXT,"
            + VISIT_VISIT_TYPE_ID      + " TEXT, "
            + VISIT_ACTION       + " TEXT,"
            + VISIT_NOTE      + " TEXT, "
            + VISIT_HEIGHT       + " TEXT,"
            + VISIT_WEIGHT      + " TEXT,"
            + VISIT_LILA      + " TEXT, "
            + VISIT_BMI_SCORE       + " TEXT,"
            + VISIT_DRUG_TAKEN      + " TEXT, "
            + VISIT_REMINDER_ID       + " TEXT,"
            + VISIT_CREATE_TIME      + " TEXT, "
            + VISIT_CREATE_BY       + " TEXT, "
            + VISIT_UPDATE_TIME       + " TEXT, "
            + VISIT_UPDATE_BY       + " TEXT, "
            + VISIT_DELETE_STATUS       + " TEXT, PRIMARY KEY("+VISIT_DATE+","+VISIT_CHILD_ID+","+VISIT_COMPLAINT_ID+"))";
	
	/**/
	 String CREATE_TM_ABSENT = "CREATE TABLE " + TM_ABSENT + "("
		      + ABSENT_EVENT_ID + " TEXT PRIMARY KEY," 
		   + ABSENT_EMPLOYEE_ID + " TEXT,"
		      + ABSENT_CHILD_ID + " TEXT, " 
		   + ABSENT_START_DATE + " TEXT," 
		      + ABSENT_EVENT_DESCRIPTION + " TEXT, " 
		   + ABSENT_CREATED_BY + " TEXT," 
		   + ABSENT_CREATED_TIME + " TEXT," 
		   + ABSENT_UPDATE_BY + " TEXT," 
		      + ABSENT_UPDATE_TIME + " TEXT" + ")";
		 
		 String CREATE_TM_AUTHORITY = "CREATE TABLE " + TM_AUTHORITY + "("
		      + AUTHORITY_AUTHORITY_ID + " TEXT PRIMARY KEY," 
		   + AUTHORITY_AUTHORITY_STATUS + " TEXT,"
		      + AUTHORITY_MENU_ID + " TEXT, " 
		   + AUTHORITY_CREATED_BY + " TEXT," 
		      + AUTHORITY_CREATED_TIME + " TEXT, " 
		   + AUTHORITY_UPDATE_BY + " TEXT," 
		   + AUTHORITY_UPDATE_TIME + " TEXT" + ")";
		 
		 String CREATE_TM_BMI_CALC = "CREATE TABLE " + TM_BMI_CALC + "(" 
		      + BMI_CALC_START_AGE + " TEXT PRIMARY KEY," 
		   + BMI_CALC_END_AGE + " TEXT,"
		      + BMI_CALC_GENDER + " TEXT, " 
		   + BMI_CALC_START_BMI + " TEXT," 
		      + BMI_CALC_END_BMI + " TEXT, " 
		   + BMI_CALC_SCORE + " TEXT," 
		   + BMI_CALC_CREATED_BY + " TEXT," 
		      + BMI_CALC_CREATED_TIME + " TEXT, " 
		   + BMI_CALC_UPDATE_BY + " TEXT," 
		   + BMI_CALC_UPDATE_TIME + " TEXT" + ")";
		 
		 String CREATE_TM_EMPLOYEE = "CREATE TABLE " + TM_EMPLOYEE + "(" 
		      + EMPLOYEE_EMPLOYEE_ID + " TEXT PRIMARY KEY," 
		   + EMPLOYEE_USERNAME + " TEXT,"
		      + EMPLOYEE_PASSWORD + " TEXT, " 
		   + EMPLOYEE_AUTHORITY_ID + " TEXT," 
		      + EMPLOYEE_GROUP_ID + " TEXT, " 
		   + EMPLOYEE_CREATED_BY + " TEXT," 
		   + EMPLOYEE_CREATED_TIME + " TEXT," 
		      + EMPLOYEE_UPDATE_BY + " TEXT, " 
		   + EMPLOYEE_UPDATE_TIME + " TEXT" + ")";
		 
		 String CREATE_TM_EVENT = "CREATE TABLE " + TM_EVENT + "("
		      + EVENT_EVENT_ID + " TEXT PRIMARY KEY," 
		   + EVENT_EVENT_DESCRIPTION + " TEXT,"
		      + EVENT_START_DATE + " TEXT, " 
		   + EVENT_END_DATE + " TEXT," 
		      + EVENT_CREATED_BY + " TEXT, " 
		   + EVENT_CREATED_TIME + " TEXT," 
		   + EVENT_UPDATE_BY + " TEXT," 
		      + EVENT_UPDATE_TIME + " TEXT" + ")";
		 
		 String CREATE_TM_GROUP = "CREATE TABLE " + TM_GROUP + "("
		      + GROUP_GROUP_ID + " TEXT PRIMARY KEY," 
		   + GROUP_GROUP_DESCRIPTION + " TEXT,"
		      + GROUP_CREATED_BY + " TEXT, " 
		   + GROUP_CREATED_TIME + " TEXT," 
		      + GROUP_UPDATE_BY + " TEXT, " 
		   + GROUP_UPDATE_TIME + " TEXT" + ")";
		 
		 String CREATE_TM_MENU = "CREATE TABLE " + TM_MENU + "("
		      + MENU_MENU_ID + " TEXT PRIMARY KEY," 
		   +  MENU_MENU_DESCRIPTION + " TEXT,"
		      + MENU_CREATED_BY + " TEXT, " 
		   + MENU_CREATED_TIME + " TEXT," 
		      + MENU_UPDATE_BY + " TEXT, " 
		   + MENU_UPDATE_TIME + " TEXT" + ")";
		 
		 String CREATE_TM_MOVE_CHILD_GROUP = "CREATE TABLE " + TM_MOVE_CHILD_GROUP + "("
		      + MOVE_CHILD_GROUP_DISTRICT_ID + " TEXT PRIMARY KEY," 
		   + MOVE_CHILD_GROUP_GROUP_ID + " TEXT,"
		      + MOVE_CHILD_GROUP_CHILD_ID + " TEXT, " 
		   + MOVE_CHILD_GROUP_CREATED_BY + " TEXT," 
		      + MOVE_CHILD_GROUP_CREATED_TIME + " TEXT, " 
		      + MOVE_CHILD_GROUP_UPDATE_BY + " TEXT, " 
		   + MOVE_CHILD_GROUP_UPDATE_TIME + " TEXT" + ")";
		 
		 String CREATE_TM_PARAMETER = "CREATE TABLE " + TM_PARAMETER + "("
		      + PARAMETER_PARAM_GROUP + " TEXT PRIMARY KEY," 
		   + PARAMETER_PARAM_ID + " TEXT,"
		      + PARAMETER_REMINDER_DATE + " TEXT, " 
		   + PARAMETER_START_DATE + " TEXT," 
		      + PARAMETER_END_DATE + " TEXT, " 
		      + PARAMETER_CREATED_BY + " TEXT, " 
		      + PARAMETER_CREATED_TIME + " TEXT, " 
		      + PARAMETER_UPDATE_BY + " TEXT, " 
		   + PARAMETER_UPDATE_TIME + " TEXT" + ")";
		 
		 String CREATE_TM_REASON = "CREATE TABLE " + TM_REASON + "("
		      + REASON_REASON_ID + " TEXT PRIMARY KEY," 
		   + REASON_REASON_DESCRIPTION + " TEXT,"
		      + REASON_CREATED_BY + " TEXT, " 
		   + REASON_CREATED_TIME + " TEXT," 
		      + REASON_UPDATE_BY + " TEXT, " 
		      + REASON_UPDATE_TIME + " TEXT" + ")";
		 
		 String CREATE_TR_LABRESULT = "CREATE TABLE " + TR_LABRESULT + "("
		      + LABRESULT_CHILD_ID + " TEXT PRIMARY KEY," 
		   + LABRESULT_DATE_TIME + " TEXT,"
		      + LABRESULT_UNIT_TYPE + " TEXT, " 
		   + LABRESULT_UNIT_ID + " TEXT," 
		      + LABRESULT_CREATED_BY + " TEXT, " 
		      + LABRESULT_CREATED_TIME + " TEXT, " 
		      + LABRESULT_UPDATE_BY + " TEXT, " 
		      + LABRESULT_UPDATE_TIME + " TEXT" + ")";
		 
		 String CREATE_TR_NONACTIVE = "CREATE TABLE " + TR_NONACTIVE + "("
		      + NONACTIVE_CHILD_ID + " TEXT PRIMARY KEY," 
		   + NONACTIVE_REASON_ID + " TEXT,"
		      + NONACTIVE_CREATED_BY + " TEXT, " 
		   + NONACTIVE_CREATED_TIME + " TEXT," 
		      + NONACTIVE_UPDATE_BY + " TEXT, " 
		      + NONACTIVE_UPDATE_TIME + " TEXT" + ")";
		 
		 String CREATE_TR_NOTIFICATION = "CREATE TABLE " + TR_NOTIFICATION + "("
		      + NOTIFICATION_CHILD_ID + " TEXT PRIMARY KEY," 
		   + NOTIFICATION_REASON_ID + " TEXT,"
		      + NOTIFICATION_CREATED_BY + " TEXT, " 
		   + NOTIFICATION_CREATED_TIME + " TEXT," 
		      + NOTIFICATION_UPDATE_BY + " TEXT, " 
		      + NOTIFICATION_UPDATE_TIME + " TEXT" + ")";
		 
		 String CREATE_TM_REMINDER = "CREATE TABLE " + TM_REMINDER + "("
			        + REMINDER_REMINDER_ID + " TEXT PRIMARY KEY," 
			     + REMINDER_REMINDER_GROUP_ID + " TEXT,"
			        + REMINDER_REMINDER_DATE + " TEXT, " 
			     + REMINDER_START_DATE + " TEXT," 
			        + REMINDER_END_DATE + " TEXT, " 
			     + REMINDER_CREATE_TIME + " TEXT," 
			        + CAREGIVER_DELETE_STATUS + " TEXT" + ")";
	
	public Database(Context context) {
		super(context, nama_database, null, versi_database);
	}

	
	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			
			db.execSQL(TABLE_SEQ);
			db.execSQL(CREATE_TABLE_CAREGIVER);
			db.execSQL(CREATE_TABLE_CHILD_FACILITY);
			db.execSQL(CREATE_TM_COST_FACILITY);
			db.execSQL(CREATE_TM_CHILD);
			db.execSQL(CREATE_TM_CLASS);
			db.execSQL(CREATE_TM_DISTRICT);
			db.execSQL(CREATE_TM_SUBDISTRICT);
			db.execSQL(CREATE_TM_FACILITY);
			db.execSQL(CREATE_TM_PARENT_STATUS);
			db.execSQL(CREATE_TM_DRUG_TYPE);
			db.execSQL(CREATE_TM_DRUG_STATUS);
			db.execSQL(CREATE_TM_DRUG_DOSE);
			db.execSQL(CREATE_TR_CHILD_DRUG_TYPE);
			db.execSQL(CREATE_TM_UNIT);
			db.execSQL(CREATE_TM_COMPLAINT);
			db.execSQL(CREATE_TM_COMPLAINT_STATUS);
			db.execSQL(CREATE_TM_ENVIRONMENT);
			db.execSQL(CREATE_TM_IMAGE_TYPE);
			db.execSQL(CREATE_TM_VISIT_TYPE);
			db.execSQL(CREATE_TR_IMAGE);
			db.execSQL(CREATE_TR_VISIT);
			db.execSQL(CREATE_TR_VISIT_ENVIRONMENT);
			db.execSQL(CREATE_TR_VISIT_FACILITY);
			db.execSQL(CREATE_TM_REMINDER);
						
			/******************************DUMMY DATA*********************************************/
			ContentValues values = new ContentValues();
			

			try
			{
				db.beginTransaction();
				
				//seq
				values.put(seq_number, "0");
				db.insert(SEQ, null, values);
				values.clear();
				
				//caregiver
				values.put(CAREGIVER_ID, "CA000");
				values.put(CAREGIVER_DESCRIPTION, "-");
				values.put(CAREGIVER_CREATED_BY, "-");
				values.put(CAREGIVER_CREATED_TIME, "-");
				values.put(CAREGIVER_UPDATE_BY, "-");
				values.put(CAREGIVER_UPDATE_TIME, "-");
				values.put(CAREGIVER_DELETE_STATUS, "-");
				db.insert(TM_CAREGIVER, null, values);
				
				values.put(CAREGIVER_ID, "CA001");
				values.put(CAREGIVER_DESCRIPTION, "Kakek");
				values.put(CAREGIVER_CREATED_BY, "-");
				values.put(CAREGIVER_CREATED_TIME, "-");
				values.put(CAREGIVER_UPDATE_BY, "-");
				values.put(CAREGIVER_UPDATE_TIME, "-");
				values.put(CAREGIVER_DELETE_STATUS, "-");
				db.insert(TM_CAREGIVER, null, values);

				values.put(CAREGIVER_ID, "CA002");
				values.put(CAREGIVER_DESCRIPTION, "Nenek");
				values.put(CAREGIVER_CREATED_BY, "-");
				values.put(CAREGIVER_CREATED_TIME, "-");
				values.put(CAREGIVER_UPDATE_BY, "-");
				values.put(CAREGIVER_UPDATE_TIME, "-");
				values.put(CAREGIVER_DELETE_STATUS, "-");
				db.insert(TM_CAREGIVER, null, values);

				values.put(CAREGIVER_ID, "CA003");
				values.put(CAREGIVER_DESCRIPTION, "Orang Tua");
				values.put(CAREGIVER_CREATED_BY, "-");
				values.put(CAREGIVER_CREATED_TIME, "-");
				values.put(CAREGIVER_UPDATE_BY, "-");
				values.put(CAREGIVER_UPDATE_TIME, "-");
				values.put(CAREGIVER_DELETE_STATUS, "-");
				db.insert(TM_CAREGIVER, null, values);

				values.put(CAREGIVER_ID, "CA004");
				values.put(CAREGIVER_DESCRIPTION, "Paman");
				values.put(CAREGIVER_CREATED_BY, "-");
				values.put(CAREGIVER_CREATED_TIME, "-");
				values.put(CAREGIVER_UPDATE_BY, "-");
				values.put(CAREGIVER_UPDATE_TIME, "-");
				values.put(CAREGIVER_DELETE_STATUS, "-");
				db.insert(TM_CAREGIVER, null, values);

				values.put(CAREGIVER_ID, "CA005");
				values.put(CAREGIVER_DESCRIPTION, "Bibi");
				values.put(CAREGIVER_CREATED_BY, "-");
				values.put(CAREGIVER_CREATED_TIME, "-");
				values.put(CAREGIVER_UPDATE_BY, "-");
				values.put(CAREGIVER_UPDATE_TIME, "-");
				values.put(CAREGIVER_DELETE_STATUS, "-");
				db.insert(TM_CAREGIVER, null, values);
				
				values.put(CAREGIVER_ID, "CA006");
				values.put(CAREGIVER_DESCRIPTION, "Tetangga");
				values.put(CAREGIVER_CREATED_BY, "-");
				values.put(CAREGIVER_CREATED_TIME, "-");
				values.put(CAREGIVER_UPDATE_BY, "-");
				values.put(CAREGIVER_UPDATE_TIME, "-");
				values.put(CAREGIVER_DELETE_STATUS, "-");
				db.insert(TM_CAREGIVER, null, values);
				
				values.clear();
				
				//class
				values.put(CLASS_ID, "CL000");
				values.put(CLASS_DESCRIPTION, "-");
				values.put(CLASS_CREATED_BY, "-");
				values.put(CLASS_CREATED_TIME, "-");
				values.put(CLASS_UPDATE_BY, "-");
				values.put(CLASS_UPDATE_TIME, "-");
				values.put(CLASS_DELETE_STATUS, "-");
				db.insert(TM_CLASS, null, values);
				
				values.put(CLASS_ID, "CL001");
				values.put(CLASS_DESCRIPTION, "PAUD");
				values.put(CLASS_CREATED_BY, "-");
				values.put(CLASS_CREATED_TIME, "-");
				values.put(CLASS_UPDATE_BY, "-");
				values.put(CLASS_UPDATE_TIME, "-");
				values.put(CLASS_DELETE_STATUS, "-");
				db.insert(TM_CLASS, null, values);
	
				values.put(CLASS_ID, "CL002");
				values.put(CLASS_DESCRIPTION, "TK1");
				values.put(CLASS_CREATED_BY, "-");
				values.put(CLASS_CREATED_TIME, "-");
				values.put(CLASS_UPDATE_BY, "-");
				values.put(CLASS_UPDATE_TIME, "-");
				values.put(CLASS_DELETE_STATUS, "-");
				db.insert(TM_CLASS, null, values);
				
				values.put(CLASS_ID, "CL003");
				values.put(CLASS_DESCRIPTION, "TK2");
				values.put(CLASS_CREATED_BY, "-");
				values.put(CLASS_CREATED_TIME, "-");
				values.put(CLASS_UPDATE_BY, "-");
				values.put(CLASS_UPDATE_TIME, "-");
				values.put(CLASS_DELETE_STATUS, "-");
				db.insert(TM_CLASS, null, values);
				
				values.put(CLASS_ID, "CL004");
				values.put(CLASS_DESCRIPTION, "TK3");
				values.put(CLASS_CREATED_BY, "-");
				values.put(CLASS_CREATED_TIME, "-");
				values.put(CLASS_UPDATE_BY, "-");
				values.put(CLASS_UPDATE_TIME, "-");
				values.put(CLASS_DELETE_STATUS, "-");
				db.insert(TM_CLASS, null, values);
	
				values.put(CLASS_ID, "CL005");
				values.put(CLASS_DESCRIPTION, "1 SD");
				values.put(CLASS_CREATED_BY, "-");
				values.put(CLASS_CREATED_TIME, "-");
				values.put(CLASS_UPDATE_BY, "-");
				values.put(CLASS_UPDATE_TIME, "-");
				values.put(CLASS_DELETE_STATUS, "-");
				db.insert(TM_CLASS, null, values);
	
				values.put(CLASS_ID, "CL006");
				values.put(CLASS_DESCRIPTION, "2 SD");
				values.put(CLASS_CREATED_BY, "-");
				values.put(CLASS_CREATED_TIME, "-");
				values.put(CLASS_UPDATE_BY, "-");
				values.put(CLASS_UPDATE_TIME, "-");
				values.put(CLASS_DELETE_STATUS, "-");
				db.insert(TM_CLASS, null, values);
	
				values.put(CLASS_ID, "CL007");
				values.put(CLASS_DESCRIPTION, "3 SD");
				values.put(CLASS_CREATED_BY, "-");
				values.put(CLASS_CREATED_TIME, "-");
				values.put(CLASS_UPDATE_BY, "-");
				values.put(CLASS_UPDATE_TIME, "-");
				values.put(CLASS_DELETE_STATUS, "-");
				db.insert(TM_CLASS, null, values);
	
				values.put(CLASS_ID, "CL008");
				values.put(CLASS_DESCRIPTION, "4 SD");
				values.put(CLASS_CREATED_BY, "-");
				values.put(CLASS_CREATED_TIME, "-");
				values.put(CLASS_UPDATE_BY, "-");
				values.put(CLASS_UPDATE_TIME, "-");
				values.put(CLASS_DELETE_STATUS, "-");
				db.insert(TM_CLASS, null, values);
	
				values.put(CLASS_ID, "CL009");
				values.put(CLASS_DESCRIPTION, "5 SD");
				values.put(CLASS_CREATED_BY, "-");
				values.put(CLASS_CREATED_TIME, "-");
				values.put(CLASS_UPDATE_BY, "-");
				values.put(CLASS_UPDATE_TIME, "-");
				values.put(CLASS_DELETE_STATUS, "-");
				db.insert(TM_CLASS, null, values);
	
				values.put(CLASS_ID, "CL010");
				values.put(CLASS_DESCRIPTION, "6 SD");
				values.put(CLASS_CREATED_BY, "-");
				values.put(CLASS_CREATED_TIME, "-");
				values.put(CLASS_UPDATE_BY, "-");
				values.put(CLASS_UPDATE_TIME, "-");
				values.put(CLASS_DELETE_STATUS, "-");
				db.insert(TM_CLASS, null, values);
				
				values.clear();
				
				//district
				   values.put(DISTRICT_ID, "DT000");
				   values.put(DISTRICT_DESCRIPTION, "-");
				   values.put(DISTRICT_CREATED_BY, "-");
				   values.put(DISTRICT_CREATED_TIME, "-");
				   values.put(DISTRICT_UPDATE_BY, "-");
				   values.put(DISTRICT_UPDATE_TIME, "-");
				   values.put(DISTRICT_DELETE_STATUS, "-");
				   db.insert(TM_DISTRICT, null, values);
				   
				   values.put(DISTRICT_ID, "DT001");
				   values.put(DISTRICT_DESCRIPTION, "Jakarta Pusat");
				   values.put(DISTRICT_CREATED_BY, "-");
				   values.put(DISTRICT_CREATED_TIME, "-");
				   values.put(DISTRICT_UPDATE_BY, "-");
				   values.put(DISTRICT_UPDATE_TIME, "-");
				   values.put(DISTRICT_DELETE_STATUS, "-");
				   db.insert(TM_DISTRICT, null, values);
	
				   values.put(DISTRICT_ID, "DT002");
				   values.put(DISTRICT_DESCRIPTION, "Jakarta Barat");
				   values.put(DISTRICT_CREATED_BY, "-");
				   values.put(DISTRICT_CREATED_TIME, "-");
				   values.put(DISTRICT_UPDATE_BY, "-");
				   values.put(DISTRICT_UPDATE_TIME, "-");
				   values.put(DISTRICT_DELETE_STATUS, "-");
				   db.insert(TM_DISTRICT, null, values);
	
				   values.put(DISTRICT_ID, "DT003");
				   values.put(DISTRICT_DESCRIPTION, "Jakarta Selatan");
				   values.put(DISTRICT_CREATED_BY, "-");
				   values.put(DISTRICT_CREATED_TIME, "-");
				   values.put(DISTRICT_UPDATE_BY, "-");
				   values.put(DISTRICT_UPDATE_TIME, "-");
				   values.put(DISTRICT_DELETE_STATUS, "-");
				   db.insert(TM_DISTRICT, null, values);
	
				   values.put(DISTRICT_ID, "DT004");
				   values.put(DISTRICT_DESCRIPTION, "Jakarta Timur");
				   values.put(DISTRICT_CREATED_BY, "-");
				   values.put(DISTRICT_CREATED_TIME, "-");
				   values.put(DISTRICT_UPDATE_BY, "-");
				   values.put(DISTRICT_UPDATE_TIME, "-");
				   values.put(DISTRICT_DELETE_STATUS, "-");
				   db.insert(TM_DISTRICT, null, values);
	
				   values.put(DISTRICT_ID, "DT005");
				   values.put(DISTRICT_DESCRIPTION, "Jakarta Utara");
				   values.put(DISTRICT_CREATED_BY, "-");
				   values.put(DISTRICT_CREATED_TIME, "-");
				   values.put(DISTRICT_UPDATE_BY, "-");
				   values.put(DISTRICT_UPDATE_TIME, "-");
				   values.put(DISTRICT_DELETE_STATUS, "-");
				   db.insert(TM_DISTRICT, null, values);
				   
				values.clear();
				
				   //subdistrict
				   String district_id[] = { "DT000", "DT001", "DT002", "DT003", "DT004","DT005" };
				   
				   String subdistrict_jakpus[] = { "Gambir", "Tanah Abang",
				     "Menteng", "Senen", "Cempaka Putih", "Johar Baru",
				     "Kemayoran", "Sawah Besar" };
				   String subdistrict_jaksel[] = { "Kebayoran Baru",
				     "Kebayoran Lama", "Pesanggrahan", "Cilandak",
				     "Pasar Minggu", "Jagakarsa", "Mampang Prapatan",
				     "Pancoran", "Tebet", "Setiabudi" };
				   String subdistrict_jakut[] = { "Koja", "Kelapa Gading",
				     "Tanjung Priok", "Pademangan", "Penjaringan",
				     "Cilincing" };
				   String subdistrict_jaktim[] = { "Matraman", "Pulo Gadung",
				     "Jatinegara", "Duren Sawit", "Kramat Jati", "Makasar",
				     "Pasar Rebo", "Ciracas", "Cipayung", "Cakung" };
				   String subdistrict_jakbar[] = { "Cengkareng",
				     "Grogol Petamburan", "Kalideres", "Kebon Jeruk",
				     "Kembangan", "Palmerah", "Taman Sari", "Tambora" };
	
				   
				   
				   for (String district : district_id) {
					    if (district.equals("DT001")) {
					     for (String sub_jakpus : subdistrict_jakpus) {
					     
					     
					      values.put(SUBDISTRICT_DISTRICT_ID, "DT001");
					      values.put(SUBDISTRICT_DESCRIPTION, sub_jakpus);
					      values.put(SUBDISTRICT_CREATED_BY, "-");
					      values.put(SUBDISTRICT_CREATED_TIME, "-");
					      values.put(SUBDISTRICT_UPDATE_BY, "-");
					      values.put(SUBDISTRICT_UPDATE_TIME, "-");
					      values.put(SUBDISTRICT_DELETE_STATUS, "-");
					      db.insert(TM_SUBDISTRICT, null, values);
					     }
					    } else if (district.equals("DT002")) {
					     for (String sub_jakbar : subdistrict_jakbar) {
		
					      values.put(SUBDISTRICT_DISTRICT_ID, "DT002");
					      values.put(SUBDISTRICT_DESCRIPTION, sub_jakbar);
					      values.put(SUBDISTRICT_CREATED_BY, "-");
					      values.put(SUBDISTRICT_CREATED_TIME, "-");
					      values.put(SUBDISTRICT_UPDATE_BY, "-");
					      values.put(SUBDISTRICT_UPDATE_TIME, "-");
					      values.put(SUBDISTRICT_DELETE_STATUS, "-");
					      db.insert(TM_SUBDISTRICT, null, values);
					     }
					    } else if (district.equals("DT003")) {
					     for (String sub_jaksel : subdistrict_jaksel) {
		
					      values.put(SUBDISTRICT_DISTRICT_ID, "DT003");
					      values.put(SUBDISTRICT_DESCRIPTION, sub_jaksel);
					      values.put(SUBDISTRICT_CREATED_BY, "-");
					      values.put(SUBDISTRICT_CREATED_TIME, "-");
					      values.put(SUBDISTRICT_UPDATE_BY, "-");
					      values.put(SUBDISTRICT_UPDATE_TIME, "-");
					      values.put(SUBDISTRICT_DELETE_STATUS, "-");
					      db.insert(TM_SUBDISTRICT, null, values);
					     }
					    } else if (district.equals("DT005")) {
					     for (String sub_jaktim : subdistrict_jaktim) {
		
					      values.put(SUBDISTRICT_DISTRICT_ID, "DT005");
					      values.put(SUBDISTRICT_DESCRIPTION, sub_jaktim);
					      values.put(SUBDISTRICT_CREATED_BY, "-");
					      values.put(SUBDISTRICT_CREATED_TIME, "-");
					      values.put(SUBDISTRICT_UPDATE_BY, "-");
					      values.put(SUBDISTRICT_UPDATE_TIME, "-");
					      values.put(SUBDISTRICT_DELETE_STATUS, "-");
					      db.insert(TM_SUBDISTRICT, null, values);
					     }
					    } else if (district.equals("DT004")) {
					     for (String sub_jakut : subdistrict_jakut) {
		
					      values.put(SUBDISTRICT_DISTRICT_ID, "DT004");
					      values.put(SUBDISTRICT_DESCRIPTION, sub_jakut);
					      values.put(SUBDISTRICT_CREATED_BY, "-");
					      values.put(SUBDISTRICT_CREATED_TIME, "-");
					      values.put(SUBDISTRICT_UPDATE_BY, "-");
					      values.put(SUBDISTRICT_UPDATE_TIME, "-");
					      values.put(SUBDISTRICT_DELETE_STATUS, "-");
					      db.insert(TM_SUBDISTRICT, null, values);
					     }
					    } else {
					     values.put(SUBDISTRICT_DISTRICT_ID, "DT000");
					     values.put(SUBDISTRICT_DESCRIPTION, "-");
					     values.put(SUBDISTRICT_CREATED_BY, "-");
					     values.put(SUBDISTRICT_CREATED_TIME, "-");
					     values.put(SUBDISTRICT_UPDATE_BY, "-");
					     values.put(SUBDISTRICT_UPDATE_TIME, "-");
					     values.put(SUBDISTRICT_DELETE_STATUS, "-");
					     db.insert(TM_SUBDISTRICT, null, values);
					    }
				   }
				   
				   values.clear();	
				   
					//parent_status
						values.put(PARENT_STATUS_DESCRIPTION, "HIDUP POSITIF");
						values.put(PARENT_STATUS_CREATED_BY, "-");
						values.put(PARENT_STATUS_CREATED_TIME, "-");
						values.put(PARENT_STATUS_UPDATE_BY, "-");
						values.put(PARENT_STATUS_UPDATE_TIME, "-");
						values.put(PARENT_STATUS_DELETE_STATUS, "-");
						db.insert(TM_PARENT_STATUS, null, values);
		
						values.put(PARENT_STATUS_DESCRIPTION, "HIDUP NEGATIF");
						values.put(PARENT_STATUS_CREATED_BY, "-");
						values.put(PARENT_STATUS_CREATED_TIME, "-");
						values.put(PARENT_STATUS_UPDATE_BY, "-");
						values.put(PARENT_STATUS_UPDATE_TIME, "-");
						values.put(PARENT_STATUS_DELETE_STATUS, "-");
						db.insert(TM_PARENT_STATUS, null, values);
		
						values.put(PARENT_STATUS_DESCRIPTION, "MENINGGAL POSITIF");
						values.put(PARENT_STATUS_CREATED_BY, "-");
						values.put(PARENT_STATUS_CREATED_TIME, "-");
						values.put(PARENT_STATUS_UPDATE_BY, "-");
						values.put(PARENT_STATUS_UPDATE_TIME, "-");
						values.put(PARENT_STATUS_DELETE_STATUS, "-");
						db.insert(TM_PARENT_STATUS, null, values);
		
						values.put(PARENT_STATUS_DESCRIPTION, "MENINGGAL NEGATIF");
						values.put(PARENT_STATUS_CREATED_BY, "-");
						values.put(PARENT_STATUS_CREATED_TIME, "-");
						values.put(PARENT_STATUS_UPDATE_BY, "-");
						values.put(PARENT_STATUS_UPDATE_TIME, "-");
						values.put(PARENT_STATUS_DELETE_STATUS, "-");
						db.insert(TM_PARENT_STATUS, null, values);
						
						
						values.clear();
						
						//drug_type
						values.put(DRUG_TYPE_ID, "DTY000");
						values.put(DRUG_TYPE_DESCRIPTION, "-");
						values.put(DRUG_TYPE_CREATED_BY, "-");
						values.put(DRUG_TYPE_CREATED_TIME, "-");
						values.put(DRUG_TYPE_UPDATE_BY, "-");
						values.put(DRUG_TYPE_UPDATE_TIME, "-");
						values.put(DRUG_TYPE_DELETE_STATUS, "-");
						db.insert(TM_DRUG_TYPE, null, values);
						
						values.put(DRUG_TYPE_ID, "DTY001");
						values.put(DRUG_TYPE_DESCRIPTION, "Zidovudin");
						values.put(DRUG_TYPE_CREATED_BY, "-");
						values.put(DRUG_TYPE_CREATED_TIME, "-");
						values.put(DRUG_TYPE_UPDATE_BY, "-");
						values.put(DRUG_TYPE_UPDATE_TIME, "-");
						values.put(DRUG_TYPE_DELETE_STATUS, "-");
						db.insert(TM_DRUG_TYPE, null, values);
		
						values.put(DRUG_TYPE_ID, "DTY002");
						values.put(DRUG_TYPE_DESCRIPTION, "Hiviral");
						values.put(DRUG_TYPE_CREATED_BY, "-");
						values.put(DRUG_TYPE_CREATED_TIME, "-");
						values.put(DRUG_TYPE_UPDATE_BY, "-");
						values.put(DRUG_TYPE_UPDATE_TIME, "-");
						values.put(DRUG_TYPE_DELETE_STATUS, "-");
						db.insert(TM_DRUG_TYPE, null, values);
		
						values.put(DRUG_TYPE_ID, "DTY003");
						values.put(DRUG_TYPE_DESCRIPTION, "Stafudin");
						values.put(DRUG_TYPE_CREATED_BY, "-");
						values.put(DRUG_TYPE_CREATED_TIME, "-");
						values.put(DRUG_TYPE_UPDATE_BY, "-");
						values.put(DRUG_TYPE_UPDATE_TIME, "-");
						values.put(DRUG_TYPE_DELETE_STATUS, "-");
						db.insert(TM_DRUG_TYPE, null, values);
						
						values.put(DRUG_TYPE_ID, "DTY004");
						values.put(DRUG_TYPE_DESCRIPTION, "Efavirens");
						values.put(DRUG_TYPE_CREATED_BY, "-");
						values.put(DRUG_TYPE_CREATED_TIME, "-");
						values.put(DRUG_TYPE_UPDATE_BY, "-");
						values.put(DRUG_TYPE_UPDATE_TIME, "-");
						values.put(DRUG_TYPE_DELETE_STATUS, "-");
						db.insert(TM_DRUG_TYPE, null, values);
						
						values.put(DRUG_TYPE_ID, "DTY005");
						values.put(DRUG_TYPE_DESCRIPTION, "Mylan");
						values.put(DRUG_TYPE_CREATED_BY, "-");
						values.put(DRUG_TYPE_CREATED_TIME, "-");
						values.put(DRUG_TYPE_UPDATE_BY, "-");
						values.put(DRUG_TYPE_UPDATE_TIME, "-");
						values.put(DRUG_TYPE_DELETE_STATUS, "-");
						db.insert(TM_DRUG_TYPE, null, values);
						
						values.put(DRUG_TYPE_ID, "DTY006");
						values.put(DRUG_TYPE_DESCRIPTION, "Ciplan");
						values.put(DRUG_TYPE_CREATED_BY, "-");
						values.put(DRUG_TYPE_CREATED_TIME, "-");
						values.put(DRUG_TYPE_UPDATE_BY, "-");
						values.put(DRUG_TYPE_UPDATE_TIME, "-");
						values.put(DRUG_TYPE_DELETE_STATUS, "-");
						db.insert(TM_DRUG_TYPE, null, values);
						
						values.clear();
						
						
						//drug_status
					       values.put(DRUG_STATUS_ID, "DS000");
					       values.put(DRUG_STATUS_DESCRIPTION, "-");
					       values.put(DRUG_STATUS_CREATED_BY, "-");
					       values.put(DRUG_STATUS_CREATED_TIME, "-");
					       values.put(DRUG_STATUS_UPDATE_BY, "-");
					       values.put(DRUG_STATUS_UPDATE_TIME, "-");
					       values.put(DRUG_STATUS_DELETE_STATUS, "-");
					       db.insert(TM_DRUG_STATUS, null, values);
					       
					       
					       values.put(DRUG_STATUS_ID, "DS001");
					       values.put(DRUG_STATUS_DESCRIPTION, "Lini 1");
					       values.put(DRUG_STATUS_CREATED_BY, "-");
					       values.put(DRUG_STATUS_CREATED_TIME, "-");
					       values.put(DRUG_STATUS_UPDATE_BY, "-");
					       values.put(DRUG_STATUS_UPDATE_TIME, "-");
					       values.put(DRUG_STATUS_DELETE_STATUS, "-");
					       db.insert(TM_DRUG_STATUS, null, values);
					       
					       values.put(DRUG_STATUS_ID, "DS002");
					       values.put(DRUG_STATUS_DESCRIPTION, "Lini 2");
					       values.put(DRUG_STATUS_CREATED_BY, "-");
					       values.put(DRUG_STATUS_CREATED_TIME, "-");
					       values.put(DRUG_STATUS_UPDATE_BY, "-");
					       values.put(DRUG_STATUS_UPDATE_TIME, "-");
					       values.put(DRUG_STATUS_DELETE_STATUS, "-");
					       db.insert(TM_DRUG_STATUS, null, values);
		
					       values.put(DRUG_STATUS_ID, "DS003");
					       values.put(DRUG_STATUS_DESCRIPTION, "Lini 3");
					       values.put(DRUG_STATUS_CREATED_BY, "-");
					       values.put(DRUG_STATUS_CREATED_TIME, "-");
					       values.put(DRUG_STATUS_UPDATE_BY, "-");
					       values.put(DRUG_STATUS_UPDATE_TIME, "-");
					       values.put(DRUG_STATUS_DELETE_STATUS, "-");
					       db.insert(TM_DRUG_STATUS, null, values);
					       
					       values.put(DRUG_STATUS_ID, "DS004");
					       values.put(DRUG_STATUS_DESCRIPTION, "Belum Tertangani");
					       values.put(DRUG_STATUS_CREATED_BY, "-");
					       values.put(DRUG_STATUS_CREATED_TIME, "-");
					       values.put(DRUG_STATUS_UPDATE_BY, "-");
					       values.put(DRUG_STATUS_UPDATE_TIME, "-");
					       values.put(DRUG_STATUS_DELETE_STATUS, "-");
					       db.insert(TM_DRUG_STATUS, null, values);
					       
					       values.clear();
					       
						      //drug_dose
					       values.put(DRUG_DOSE_ID, "DD000");
					       values.put(DRUG_DOSE_DESCRIPTION, "-");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Pagi");
					       db.insert(TM_DRUG_DOSE, null, values);
					       
					       values.put(DRUG_DOSE_ID, "DD001");
					       values.put(DRUG_DOSE_DESCRIPTION, "1/2");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Pagi");
					       db.insert(TM_DRUG_DOSE, null, values);
		
					       values.put(DRUG_DOSE_ID, "DD002");
					       values.put(DRUG_DOSE_DESCRIPTION, "1");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Pagi");
					       db.insert(TM_DRUG_DOSE, null, values);
		
					       values.put(DRUG_DOSE_ID, "DD003");
					       values.put(DRUG_DOSE_DESCRIPTION, "1 1/2");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Pagi");
					       db.insert(TM_DRUG_DOSE, null, values);
		
					       values.put(DRUG_DOSE_ID, "DD004");
					       values.put(DRUG_DOSE_DESCRIPTION, "2");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Pagi");
					       db.insert(TM_DRUG_DOSE, null, values);
					       
					       values.put(DRUG_DOSE_ID, "DD005");
					       values.put(DRUG_DOSE_DESCRIPTION, "2 1/2");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Pagi");
					       db.insert(TM_DRUG_DOSE, null, values);
		
					       values.put(DRUG_DOSE_ID, "DD006");
					       values.put(DRUG_DOSE_DESCRIPTION, "3");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Pagi");
					       db.insert(TM_DRUG_DOSE, null, values);
		
					       
					       values.put(DRUG_DOSE_ID, "DD007");
					       values.put(DRUG_DOSE_DESCRIPTION, "3 1/2");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Pagi");
					       db.insert(TM_DRUG_DOSE, null, values);
					       
					       values.put(DRUG_DOSE_ID, "DD008");
					       values.put(DRUG_DOSE_DESCRIPTION, "1 SDM");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Pagi");
					       db.insert(TM_DRUG_DOSE, null, values);
					       
					       values.put(DRUG_DOSE_ID, "DD009");
					       values.put(DRUG_DOSE_DESCRIPTION, "2 SDM");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Pagi");
					       db.insert(TM_DRUG_DOSE, null, values);
					       
					       values.put(DRUG_DOSE_ID, "DD010");
					       values.put(DRUG_DOSE_DESCRIPTION, "3 SDM");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Pagi");
					       db.insert(TM_DRUG_DOSE, null, values);
					       
					       //dosis malam
					       values.put(DRUG_DOSE_ID, "DD011");
					       values.put(DRUG_DOSE_DESCRIPTION, "-");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Malam");
					       db.insert(TM_DRUG_DOSE, null, values);
					       
					       values.put(DRUG_DOSE_ID, "DD012");
					       values.put(DRUG_DOSE_DESCRIPTION, "1/2");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Malam");
					       db.insert(TM_DRUG_DOSE, null, values);
		
					       values.put(DRUG_DOSE_ID, "DD013");
					       values.put(DRUG_DOSE_DESCRIPTION, "1");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Malam");
					       db.insert(TM_DRUG_DOSE, null, values);
		
					       values.put(DRUG_DOSE_ID, "DD014");
					       values.put(DRUG_DOSE_DESCRIPTION, "1 1/2");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Malam");
					       db.insert(TM_DRUG_DOSE, null, values);
		
					       values.put(DRUG_DOSE_ID, "DD015");
					       values.put(DRUG_DOSE_DESCRIPTION, "2");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Malam");
					       db.insert(TM_DRUG_DOSE, null, values);
					       
					       values.put(DRUG_DOSE_ID, "DD016");
					       values.put(DRUG_DOSE_DESCRIPTION, "2 1/2");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Malam");
					       db.insert(TM_DRUG_DOSE, null, values);
		
					       values.put(DRUG_DOSE_ID, "DD017");
					       values.put(DRUG_DOSE_DESCRIPTION, "3");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Malam");
					       db.insert(TM_DRUG_DOSE, null, values);
		
					       
					       values.put(DRUG_DOSE_ID, "DD018");
					       values.put(DRUG_DOSE_DESCRIPTION, "3 1/2");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Malam");
					       db.insert(TM_DRUG_DOSE, null, values);
					       
					       values.put(DRUG_DOSE_ID, "DD019");
					       values.put(DRUG_DOSE_DESCRIPTION, "1 SDM");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Malam");
					       db.insert(TM_DRUG_DOSE, null, values);
					       
					       values.put(DRUG_DOSE_ID, "DD020");
					       values.put(DRUG_DOSE_DESCRIPTION, "2 SDM");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Malam");
					       db.insert(TM_DRUG_DOSE, null, values);
					       
					       values.put(DRUG_DOSE_ID, "DD021");
					       values.put(DRUG_DOSE_DESCRIPTION, "3 SDM");
					       values.put(DRUG_DOSE_CREATED_BY, "-");
					       values.put(DRUG_DOSE_CREATED_TIME, "-");
					       values.put(DRUG_DOSE_UPDATE_BY, "-");
					       values.put(DRUG_DOSE_UPDATE_TIME, "-");
					       values.put(DRUG_DOSE_DELETE_STATUS, "-");
					       values.put(DRUG_DOSE_TIME,"Malam");
					       db.insert(TM_DRUG_DOSE, null, values);
					       
					       values.clear();
					       
					       //cost facility
					       final String[] fasilitas = { "FA001", "FA002", "FA003" };

					       final String[] fasilitas_susu = { "Bebelac 4-Madu-800 gr",
					          "Chilschool-4-soya-300 gr", "Dancow 1+-Vanilla-800 gr",
					          "Dancow 5+-Vanilla-800 gr", "Dancow-1+-Madu-800 gr",
					          "Dancow-1+-Vanilla-800 gr", "Dancow-3+-Madu-800 gr",
					          "Dancow-5+-Madu-800 gr", "Dancow-5+-Madu-800 gr",
					          "Dancow-Datita3/5-1000 gr",
					          "Dancow-Fullcream-Choc-800 gr",
					          "Dancow-Fullcream-Vanilla-800  gr",
					          "Ensure-FOS-Chocolate-1000 gr",
					          "Frisian-Flag-123-800 gr",
					          "Frisian-Flag-123-Madu-800 gr",
					          "Frisian-Flag-123-Vanilla-800 gr",
					          "Frisian-Flag-456-800 gr", "Nutrilon-Soya-4-400 gr",
					          "Pediasure-900 gr", "SGM-2-Vanilla-1000 gr",
					          "SGM-3-Madu-1000 gr", "SGM-3-Vanilla-1000 gr",
					          "SGM-4-Madu-1000 gr", "SGM-4-Vanilla-1000 gr",
					          "SGM-LLM-400 gr", "UHT-Choc 125ml", "UHT-Straw 125ml" };
					       final String[] fasilitas_vitamin = { "Vidorant", "Sangobion" };
					       final String[] fasilitas_popok = { "Diaper-Goon-pants-XXL38",
					          "Diaper-Pampers-pants-L36", "Diaper-Pampers-pants-M42",
					          "Diaper-Pampers-pants-XL32" };

					       
					        for (String facility : fasilitas) {
					         if (facility.equals("FA001")) {
					          values.put(COST_FACILITY_FACILITY_COST_ID, "FC001");
					          values.put(COST_FACILITY_FACILITY_ID, "FA001");
					          values.put(COST_FACILITY_FACILITY_BRAND_NAME,
					            "Bebelac 4-Madu-800 gr");
					          values.put(COST_FACILITY_FACILITY_PRICE, "0");
					          values.put(COST_FACILITY_CREATE_TIME, "-");
					          values.put(COST_FACILITY_CREATE_BY, "-");
					          values.put(COST_FACILITY_UPDATE_TIME, "-");
					          values.put(COST_FACILITY_UPDATE_BY, "-");
					          values.put(COST_FACILITY_DELETE_STATUS, "-");
					          db.insert(TM_COST_FACILITY, null, values);

					          values.put(COST_FACILITY_FACILITY_COST_ID, "FC002");
					          values.put(COST_FACILITY_FACILITY_ID, "FA001");
					          values.put(COST_FACILITY_FACILITY_BRAND_NAME,
					            "Chilschool-4-soya-300 gr");
					          values.put(COST_FACILITY_FACILITY_PRICE, "0");
					          values.put(COST_FACILITY_CREATE_TIME, "-");
					          values.put(COST_FACILITY_CREATE_BY, "-");
					          values.put(COST_FACILITY_UPDATE_TIME, "-");
					          values.put(COST_FACILITY_UPDATE_BY, "-");
					          values.put(COST_FACILITY_DELETE_STATUS, "-");
					          db.insert(TM_COST_FACILITY, null, values);

					          values.put(COST_FACILITY_FACILITY_COST_ID, "FC003");
					          values.put(COST_FACILITY_FACILITY_ID, "FA001");
					          values.put(COST_FACILITY_FACILITY_BRAND_NAME,
					            "Dancow 1+-Vanilla-800 gr");
					          values.put(COST_FACILITY_FACILITY_PRICE, "0");
					          values.put(COST_FACILITY_CREATE_TIME, "-");
					          values.put(COST_FACILITY_CREATE_BY, "-");
					          values.put(COST_FACILITY_UPDATE_TIME, "-");
					          values.put(COST_FACILITY_UPDATE_BY, "-");
					          values.put(COST_FACILITY_DELETE_STATUS, "-");
					          db.insert(TM_COST_FACILITY, null, values);

					          values.put(COST_FACILITY_FACILITY_COST_ID, "FC004");
					          values.put(COST_FACILITY_FACILITY_ID, "FA001");
					          values.put(COST_FACILITY_FACILITY_BRAND_NAME,"Ensure-FOS-Chocolate-1000 gr");
					          values.put(COST_FACILITY_FACILITY_PRICE, "0");
					          values.put(COST_FACILITY_CREATE_TIME, "-");
					          values.put(COST_FACILITY_CREATE_BY, "-");
					          values.put(COST_FACILITY_UPDATE_TIME, "-");
					          values.put(COST_FACILITY_UPDATE_BY, "-");
					          values.put(COST_FACILITY_DELETE_STATUS, "-");
					          db.insert(TM_COST_FACILITY, null, values);

					          values.put(COST_FACILITY_FACILITY_COST_ID, "FC005");
					          values.put(COST_FACILITY_FACILITY_ID, "FA001");
					          values.put(COST_FACILITY_FACILITY_BRAND_NAME, "Pediasure-900 gr");
					          values.put(COST_FACILITY_FACILITY_PRICE, "0");
					          values.put(COST_FACILITY_CREATE_TIME, "-");
					          values.put(COST_FACILITY_CREATE_BY, "-");
					          values.put(COST_FACILITY_UPDATE_TIME, "-");
					          values.put(COST_FACILITY_UPDATE_BY, "-");
					          values.put(COST_FACILITY_DELETE_STATUS, "-");
					          db.insert(TM_COST_FACILITY, null, values);

					         } else if (facility.equals("FA002")) {

					          values.put(COST_FACILITY_FACILITY_COST_ID, "FC006");
					          values.put(COST_FACILITY_FACILITY_ID, "FA002");
					          values.put(COST_FACILITY_FACILITY_BRAND_NAME, "Vidorant");
					          values.put(COST_FACILITY_FACILITY_PRICE, "0");
					          values.put(COST_FACILITY_CREATE_TIME, "-");
					          values.put(COST_FACILITY_CREATE_BY, "-");
					          values.put(COST_FACILITY_UPDATE_TIME, "-");
					          values.put(COST_FACILITY_UPDATE_BY, "-");
					          values.put(COST_FACILITY_DELETE_STATUS, "-");
					          db.insert(TM_COST_FACILITY, null, values);

					          values.put(COST_FACILITY_FACILITY_COST_ID, "FC007");
					          values.put(COST_FACILITY_FACILITY_ID, "FA002");
					          values.put(COST_FACILITY_FACILITY_BRAND_NAME, "Sangobion");
					          values.put(COST_FACILITY_FACILITY_PRICE, "0");
					          values.put(COST_FACILITY_CREATE_TIME, "-");
					          values.put(COST_FACILITY_CREATE_BY, "-");
					          values.put(COST_FACILITY_UPDATE_TIME, "-");
					          values.put(COST_FACILITY_UPDATE_BY, "-");
					          values.put(COST_FACILITY_DELETE_STATUS, "-");
					          db.insert(TM_COST_FACILITY, null, values);

					         } else {
					          
					          values.put(COST_FACILITY_FACILITY_COST_ID, "FC008");
					          values.put(COST_FACILITY_FACILITY_ID, "FA003");
					          values.put(COST_FACILITY_FACILITY_BRAND_NAME, "Diaper-Goon-pants-XXL38");
					          values.put(COST_FACILITY_FACILITY_PRICE, "0");
					          values.put(COST_FACILITY_CREATE_TIME, "-");
					          values.put(COST_FACILITY_CREATE_BY, "-");
					          values.put(COST_FACILITY_UPDATE_TIME, "-");
					          values.put(COST_FACILITY_UPDATE_BY, "-");
					          values.put(COST_FACILITY_DELETE_STATUS, "-");
					          db.insert(TM_COST_FACILITY, null, values);
					          
					          values.put(COST_FACILITY_FACILITY_COST_ID, "FC009");
					          values.put(COST_FACILITY_FACILITY_ID, "FA003");
					          values.put(COST_FACILITY_FACILITY_BRAND_NAME, "Diaper-Pampers-pants-L36");
					          values.put(COST_FACILITY_FACILITY_PRICE, "0");
					          values.put(COST_FACILITY_CREATE_TIME, "-");
					          values.put(COST_FACILITY_CREATE_BY, "-");
					          values.put(COST_FACILITY_UPDATE_TIME, "-");
					          values.put(COST_FACILITY_UPDATE_BY, "-");
					          values.put(COST_FACILITY_DELETE_STATUS, "-");
					          db.insert(TM_COST_FACILITY, null, values);
					          
					          values.put(COST_FACILITY_FACILITY_COST_ID, "FC010");
					          values.put(COST_FACILITY_FACILITY_ID, "FA003");
					          values.put(COST_FACILITY_FACILITY_BRAND_NAME, "Diaper-Pampers-pants-M42");
					          values.put(COST_FACILITY_FACILITY_PRICE, "0");
					          values.put(COST_FACILITY_CREATE_TIME, "-");
					          values.put(COST_FACILITY_CREATE_BY, "-");
					          values.put(COST_FACILITY_UPDATE_TIME, "-");
					          values.put(COST_FACILITY_UPDATE_BY, "-");
					          values.put(COST_FACILITY_DELETE_STATUS, "-");
					          db.insert(TM_COST_FACILITY, null, values);
					          
					          values.put(COST_FACILITY_FACILITY_COST_ID, "FC011");
					          values.put(COST_FACILITY_FACILITY_ID, "FA003");
					          values.put(COST_FACILITY_FACILITY_BRAND_NAME, "Diaper-Pampers-pants-XL32");
					          values.put(COST_FACILITY_FACILITY_PRICE, "0");
					          values.put(COST_FACILITY_CREATE_TIME, "-");
					          values.put(COST_FACILITY_CREATE_BY, "-");
					          values.put(COST_FACILITY_UPDATE_TIME, "-");
					          values.put(COST_FACILITY_UPDATE_BY, "-");
					          values.put(COST_FACILITY_DELETE_STATUS, "-");
					          db.insert(TM_COST_FACILITY, null, values);
					          
					          values.clear();
					          
					          //complaint
					          values.put(COMPLAINT_ID, "CM000");
					          values.put(COMPLAINT_DESCRIPTION, "-");
					          values.put(COMPLAINT_CREATED_BY , "-");
					          values.put(COMPLAINT_CREATED_TIME , "-");
					          values.put(COMPLAINT_UPDATE_BY , "-");
					          values.put(COMPLAINT_UPDATE_TIME , "-");
					          values.put(COMPLAINT_DELETE_STATUS , "-");
					          db.insert(TM_COMPLAINT, null, values);
								
								values.put(COMPLAINT_ID, "CM001");
								values.put(COMPLAINT_DESCRIPTION, "ISPA");
								values.put(COMPLAINT_CREATED_BY , "-");
								values.put(COMPLAINT_CREATED_TIME , "-");
								values.put(COMPLAINT_UPDATE_BY , "-");
								values.put(COMPLAINT_UPDATE_TIME , "-");
								values.put(COMPLAINT_DELETE_STATUS , "-");
								db.insert(TM_COMPLAINT, null, values);
								
								values.put(COMPLAINT_ID, "CM002");
								values.put(COMPLAINT_DESCRIPTION, "Kulit");
								values.put(COMPLAINT_CREATED_BY , "-");
								values.put(COMPLAINT_CREATED_TIME , "-");
								values.put(COMPLAINT_UPDATE_BY , "-");
								values.put(COMPLAINT_UPDATE_TIME , "-");
								values.put(COMPLAINT_DELETE_STATUS , "-");
								db.insert(TM_COMPLAINT, null, values);

								values.put(COMPLAINT_ID, "CM003");
								values.put(COMPLAINT_DESCRIPTION, "TB");
								values.put(COMPLAINT_CREATED_BY , "-");
								values.put(COMPLAINT_CREATED_TIME , "-");
								values.put(COMPLAINT_UPDATE_BY , "-");
								values.put(COMPLAINT_UPDATE_TIME , "-");
								values.put(COMPLAINT_DELETE_STATUS , "-");
								db.insert(TM_COMPLAINT, null, values);

								values.put(COMPLAINT_ID, "CM004");
								values.put(COMPLAINT_DESCRIPTION, "MSI");
								values.put(COMPLAINT_CREATED_BY , "-");
								values.put(COMPLAINT_CREATED_TIME , "-");
								values.put(COMPLAINT_UPDATE_BY , "-");
								values.put(COMPLAINT_UPDATE_TIME , "-");
								values.put(COMPLAINT_DELETE_STATUS , "-");
								db.insert(TM_COMPLAINT, null, values);

								values.put(COMPLAINT_ID, "CM005");
								values.put(COMPLAINT_DESCRIPTION, "Pencernaan");
								values.put(COMPLAINT_CREATED_BY , "-");
								values.put(COMPLAINT_CREATED_TIME , "-");
								values.put(COMPLAINT_UPDATE_BY , "-");
								values.put(COMPLAINT_UPDATE_TIME , "-");
								values.put(COMPLAINT_DELETE_STATUS , "-");
								db.insert(TM_COMPLAINT, null, values);
								
								values.clear();
								
								//complaint status
								values.put(COMPLAINT_STATUS_ID, "CS000");
								values.put(COMPLAINT_STATUS_DESCRIPTION , "-");
								values.put(COMPLAINT_STATUS_CREATED_BY , "-");
								values.put(COMPLAINT_STATUS_CREATED_TIME , "-");
								values.put(COMPLAINT_STATUS_UPDATE_BY , "-");
								values.put(COMPLAINT_STATUS_UPDATE_TIME , "-");
								values.put(COMPLAINT_STATUS_DELETE_STATUS , "-");
								db.insert(TM_COMPLAINT_STATUS , null, values);
								
								values.put(COMPLAINT_STATUS_ID, "CS001");
								values.put(COMPLAINT_STATUS_DESCRIPTION , "Sudah Tertangani");
								values.put(COMPLAINT_STATUS_CREATED_BY , "-");
								values.put(COMPLAINT_STATUS_CREATED_TIME , "-");
								values.put(COMPLAINT_STATUS_UPDATE_BY , "-");
								values.put(COMPLAINT_STATUS_UPDATE_TIME , "-");
								values.put(COMPLAINT_STATUS_DELETE_STATUS , "-");
								db.insert(TM_COMPLAINT_STATUS , null, values);
								
								values.put(COMPLAINT_STATUS_ID, "CS002");
								values.put(COMPLAINT_STATUS_DESCRIPTION , "Belum Tertangani");
								values.put(COMPLAINT_STATUS_CREATED_BY , "-");
								values.put(COMPLAINT_STATUS_CREATED_TIME , "-");
								values.put(COMPLAINT_STATUS_UPDATE_BY , "-");
								values.put(COMPLAINT_STATUS_UPDATE_TIME , "-");
								values.put(COMPLAINT_STATUS_DELETE_STATUS , "-");
								db.insert(TM_COMPLAINT_STATUS , null, values);

								values.put(COMPLAINT_STATUS_ID, "CS003");
								values.put(COMPLAINT_STATUS_DESCRIPTION , "Sembuh");
								values.put(COMPLAINT_STATUS_CREATED_BY , "-");
								values.put(COMPLAINT_STATUS_CREATED_TIME , "-");
								values.put(COMPLAINT_STATUS_UPDATE_BY , "-");
								values.put(COMPLAINT_STATUS_UPDATE_TIME , "-");
								values.put(COMPLAINT_STATUS_DELETE_STATUS , "-");
								db.insert(TM_COMPLAINT_STATUS , null, values);

								values.put(COMPLAINT_STATUS_ID, "CS004");
								values.put(COMPLAINT_STATUS_DESCRIPTION , "Putus Obat");
								values.put(COMPLAINT_STATUS_CREATED_BY , "-");
								values.put(COMPLAINT_STATUS_CREATED_TIME , "-");
								values.put(COMPLAINT_STATUS_UPDATE_BY , "-");
								values.put(COMPLAINT_STATUS_UPDATE_TIME , "-");
								values.put(COMPLAINT_STATUS_DELETE_STATUS , "-");
								db.insert(TM_COMPLAINT_STATUS , null, values);
								
								values.clear();
								
								values.put(VISIT_TYPE_ID, "VT000");
								values.put(VISIT_TYPE_DESCRIPTION , "-");
								values.put(VISIT_TYPE_CREATED_BY, "-");
								values.put(VISIT_TYPE_CREATED_TIME , "-");
								values.put(VISIT_TYPE_UPDATE_BY, "-");
								values.put(VISIT_TYPE_UPDATE_TIME , "-");
								db.insert(TM_VISIT_TYPE , null, values);
								
								values.put(VISIT_TYPE_ID, "VT001");
								values.put(VISIT_TYPE_DESCRIPTION , "Home Visit");
								values.put(VISIT_TYPE_CREATED_BY, "-");
								values.put(VISIT_TYPE_CREATED_TIME , "-");
								values.put(VISIT_TYPE_UPDATE_BY, "-");
								values.put(VISIT_TYPE_UPDATE_TIME , "-");
								db.insert(TM_VISIT_TYPE , null, values);
								
								values.put(VISIT_TYPE_ID, "VT002");
								values.put(VISIT_TYPE_DESCRIPTION , "Pos Visit");
								values.put(VISIT_TYPE_CREATED_BY, "-");
								values.put(VISIT_TYPE_CREATED_TIME , "-");
								values.put(VISIT_TYPE_UPDATE_BY, "-");
								values.put(VISIT_TYPE_UPDATE_TIME , "-");
								db.insert(TM_VISIT_TYPE, null, values);

								values.put(VISIT_TYPE_ID, "VT003");
								values.put(VISIT_TYPE_DESCRIPTION , "Lab Result");
								values.put(VISIT_TYPE_CREATED_BY, "-");
								values.put(VISIT_TYPE_CREATED_TIME , "-");
								values.put(VISIT_TYPE_UPDATE_BY, "-");
								values.put(VISIT_TYPE_UPDATE_TIME , "-");
								db.insert(TM_VISIT_TYPE, null, values);
								
								values.clear();
								
								values.put(IMAGE_TYPE_ID , "1");
								values.put(IMAGE_TYPE_DESCRIPTION , "Anak");
								values.put(IMAGE_TYPE_CREATED_BY , "-");
								values.put(IMAGE_TYPE_CREATED_TIME , "-");
								values.put(IMAGE_TYPE_UPDATE_BY , "-");
								values.put(IMAGE_TYPE_UPDATE_TIME , "-");
								values.put(IMAGE_TYPE_DELETE_STATUS , "-");
								db.insert(TM_IMAGE_TYPE , null, values);
								
								values.put(IMAGE_TYPE_ID , "2");
								values.put(IMAGE_TYPE_DESCRIPTION , "Rumah");
								values.put(IMAGE_TYPE_CREATED_BY , "-");
								values.put(IMAGE_TYPE_CREATED_TIME , "-");
								values.put(IMAGE_TYPE_UPDATE_BY , "-");
								values.put(IMAGE_TYPE_UPDATE_TIME , "-");
								values.put(IMAGE_TYPE_DELETE_STATUS , "-");
								db.insert(TM_IMAGE_TYPE , null, values);
								
								values.put(IMAGE_TYPE_ID , "3");
								values.put(IMAGE_TYPE_DESCRIPTION , "Acara");
								values.put(IMAGE_TYPE_CREATED_BY , "-");
								values.put(IMAGE_TYPE_CREATED_TIME , "-");
								values.put(IMAGE_TYPE_UPDATE_BY , "-");
								values.put(IMAGE_TYPE_UPDATE_TIME , "-");
								values.put(IMAGE_TYPE_DELETE_STATUS , "-");
								db.insert(TM_IMAGE_TYPE , null, values);
								
								values.clear();
								
								values.put(ENVIRONMENT_ID , "ENV001");
								values.put(ENVIRONMENT_DESCRIPTION , "Rapi");
								values.put(ENVIRONMENT_CREATED_BY , "-");
								values.put(ENVIRONMENT_CREATED_TIME , "-");
								values.put(ENVIRONMENT_UPDATE_BY , "-");
								values.put(ENVIRONMENT_UPDATE_TIME , "-");
								values.put(ENVIRONMENT_DELETE_STATUS , "-");
								db.insert(TM_ENVIRONMENT , null, values);
								
								values.put(ENVIRONMENT_ID , "ENV002");
								values.put(ENVIRONMENT_DESCRIPTION , "Resik");
								values.put(ENVIRONMENT_CREATED_BY , "-");
								values.put(ENVIRONMENT_CREATED_TIME , "-");
								values.put(ENVIRONMENT_UPDATE_BY , "-");
								values.put(ENVIRONMENT_UPDATE_TIME , "-");
								values.put(ENVIRONMENT_DELETE_STATUS , "-");
								db.insert(TM_ENVIRONMENT , null, values);
								
								values.put(ENVIRONMENT_ID , "ENV003");
								values.put(ENVIRONMENT_DESCRIPTION , "Terawat");
								values.put(ENVIRONMENT_CREATED_BY , "-");
								values.put(ENVIRONMENT_CREATED_TIME , "-");
								values.put(ENVIRONMENT_UPDATE_BY , "-");
								values.put(ENVIRONMENT_UPDATE_TIME , "-");
								values.put(ENVIRONMENT_DELETE_STATUS , "-");
								db.insert(TM_ENVIRONMENT , null, values);
								
								values.put(ENVIRONMENT_ID , "ENV004");
								values.put(ENVIRONMENT_DESCRIPTION , "Rajin");
								values.put(ENVIRONMENT_CREATED_BY , "-");
								values.put(ENVIRONMENT_CREATED_TIME , "-");
								values.put(ENVIRONMENT_UPDATE_BY , "-");
								values.put(ENVIRONMENT_UPDATE_TIME , "-");
								values.put(ENVIRONMENT_DELETE_STATUS , "-");
								db.insert(TM_ENVIRONMENT , null, values);

								values.put(ENVIRONMENT_ID , "ENV005");
								values.put(ENVIRONMENT_DESCRIPTION , "Ringkas");
								values.put(ENVIRONMENT_CREATED_BY , "-");
								values.put(ENVIRONMENT_CREATED_TIME , "-");
								values.put(ENVIRONMENT_UPDATE_BY , "-");
								values.put(ENVIRONMENT_UPDATE_TIME , "-");
								values.put(ENVIRONMENT_DELETE_STATUS , "-");
								db.insert(TM_ENVIRONMENT , null, values);
								
								values.clear();
								
								values.put(REMINDER_REMINDER_ID, "R001");
						        values.put(REMINDER_REMINDER_GROUP_ID, "-");
						        values.put(REMINDER_REMINDER_DATE, "-");
						        values.put(REMINDER_START_DATE, "-");
						        values.put(REMINDER_END_DATE, "-");
						        values.put(REMINDER_CREATE_TIME, "-");
						        db.insert(TM_REMINDER, null, values);
						        db.execSQL("DROP TABLE IF EXISTS " + TM_REMINDER);
								
								
					         }
					  }
				   
				
				db.setTransactionSuccessful();
			}  
			finally {
		            db.endTransaction();
		    }
						       



		} catch (SQLException se) {
			Log.v(" Oncreate SQLException",
					Log.getStackTraceString(se));
		} catch (Exception e) {
			Log.v(" Oncreate Exception",
					Log.getStackTraceString(e));
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			
			db.execSQL("DROP TABLE IF EXISTS " + TM_CAREGIVER);
			db.execSQL("DROP TABLE IF EXISTS " + TM_CHILD_FACILITY);
			db.execSQL("DROP TABLE IF EXISTS " + TM_COST_FACILITY);
			db.execSQL("DROP TABLE IF EXISTS " + TM_CHILD);
			db.execSQL("DROP TABLE IF EXISTS " + TM_CLASS);
			db.execSQL("DROP TABLE IF EXISTS " + TM_DISTRICT);
			db.execSQL("DROP TABLE IF EXISTS " + TM_SUBDISTRICT);
			db.execSQL("DROP TABLE IF EXISTS " + TM_FACILITY);
			db.execSQL("DROP TABLE IF EXISTS " + TM_PARENT_STATUS);
			db.execSQL("DROP TABLE IF EXISTS " + TM_DRUG_TYPE);
			db.execSQL("DROP TABLE IF EXISTS " + TM_DRUG_STATUS);
			db.execSQL("DROP TABLE IF EXISTS " + TM_DRUG_DOSE);
			db.execSQL("DROP TABLE IF EXISTS " + TR_CHILD_DRUG_TYPE);
			onCreate(db);
		} catch (SQLException se) {
			Log.v(" onUpgrade SQLException",Log.getStackTraceString(se));
		} catch (Exception e) {
			Log.v(" onUpgrade Exception",Log.getStackTraceString(e));
	}

	}
	
	/************************************************************************************************************/
	/**************************************-CRUD OPERATION-******************************************************/
	/************************************************************************************************************/
	
	//---------------------------------------SEQUENCE----------------------------------------------------------//
	public void UpdateSeq(String current_num, String update_num)
	{
		Log.e("UpdateSeq", "");
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(seq_number, update_num);
		try{
			db.update(SEQ, values, "seq_num=" + current_num, null);
		}catch (SQLiteException se) {
				 Log.v(" UpdateSeq Exception",Log.getStackTraceString(se));
		} catch (Exception e) {
				Log.v(" UpdateSeq Exception",Log.getStackTraceString(e));
		} finally {
				db.close();
		}
	}
	
	public Integer GetSeq()
	{
		String result="";
		SQLiteDatabase db = this.getReadableDatabase();
		try{
			Cursor mCursor = db.rawQuery(
					"SELECT seq_num   FROM  SEQ ", null);
			if (mCursor != null) {
				mCursor.moveToFirst();
				result = mCursor.getString(0);
			}
			mCursor.close();
		}catch (SQLiteException se) {
			 Log.v("GetSeq",Log.getStackTraceString(se));
		} catch (Exception e) {
			Log.v("GetSeq",Log.getStackTraceString(e));
		} finally {
			db.close();
		}
		int result_int = Integer.parseInt(result);
		return result_int;
		
	}
	
	//---------------------------------------TM_CAREGIVER----------------------------------------------------------//
		
	public ArrayList<String> getAllDataCareGiver() {
		ArrayList<String> allData = new ArrayList<String>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = null;
		try{
		cursor = db.query(TM_CAREGIVER , new String[] { CAREGIVER_ID ,
				CAREGIVER_DESCRIPTION , CAREGIVER_CREATED_BY , CAREGIVER_CREATED_TIME ,
				CAREGIVER_UPDATE_BY , CAREGIVER_UPDATE_TIME ,CAREGIVER_DELETE_STATUS }, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			allData.add(cursor.getString(1));
			cursor.moveToNext();
		}
			cursor.close();
		}catch (SQLiteException se) {
			 Log.v(" getAllDataCareGiver Exception",Log.getStackTraceString(se));
		} catch (Exception e) {
			Log.v(" getAllDataCareGiver Exception",Log.getStackTraceString(e));
		} finally {
			db.close();
		}
		return allData;
	}

	public void deleteAllDataCareGiver() {
		SQLiteDatabase db=this.getWritableDatabase();
		db.delete(TM_CAREGIVER, null, null); 
		db.close();
	}

	// get id dosis arv
	public String getIdCaregiver(String caregiver) {
		SQLiteDatabase db = this.getReadableDatabase();
		String id = "";
		try{
			Cursor mCursor = db.rawQuery(
					"SELECT  CAREGIVER_id  FROM  TM_CAREGIVER WHERE CAREGIVER_description= '"
							+ caregiver + "'", null);
			if (mCursor != null) {
				mCursor.moveToFirst();
				id = mCursor.getString(0);
			}
			mCursor.close();
		}catch (SQLiteException se) {
			 Log.v("getIdCaregiver",Log.getStackTraceString(se));
		} catch (Exception e) {
			Log.v("getIdCaregiver",Log.getStackTraceString(e));
		} finally {
			db.close();
		}
		return id;
	}

	// get caregiver
	public String getNameCaregiver(String id) {
		SQLiteDatabase db = this.getReadableDatabase();
		String name = "";
		try{
			Cursor mCursor = db.rawQuery(
					"SELECT  CAREGIVER_description  FROM  TM_CAREGIVER WHERE CAREGIVER_id= '"
							+ id + "'", null);
			if (mCursor != null) {
				mCursor.moveToFirst();
				name = mCursor.getString(mCursor.getColumnIndex(CAREGIVER_DESCRIPTION));
			}
			mCursor.close();
		}catch (SQLiteException se) {
			 Log.v("getNameCaregiver",Log.getStackTraceString(se));
		} catch (Exception e) {
			Log.v("getNameCaregiver",Log.getStackTraceString(e));
		} finally {
			db.close();
		}
		return name;
	}

	
	
	
	
	
	//---------------------------------------CHILD FACILITY----------------------------------------------------------//
	public void InsertFasilitasAnak(String child_id, String fasilitas_id, String fasilitas_cost_id) {
		 SQLiteDatabase db = this.getWritableDatabase();
		  try {
				ContentValues values = new ContentValues();
				values.put(CHILD_FACILITY_CHILD_ID , child_id);
				values.put(CHILD_FACILITY_FACILITY_ID , fasilitas_id);
				values.put(CHILD_FACILITY_FACILITY_COST_ID , fasilitas_cost_id);
				
				// menambahkan nama tabel bila tidak akan error
				// db.delete(NAMA_TABEL, null, null);
				db.insert(TM_CHILD_FACILITY, null, values);
				Log.v("Insert tabel child facility", "child_id " + child_id+ " fasilitas id " + fasilitas_id + " fasilitas_cost_id "+ fasilitas_cost_id);
				db.close();
		  }
		  catch (SQLiteException se) {
				 Log.v("InsertFasilitasAnak",Log.getStackTraceString(se));
		  } catch (Exception e) {
				Log.v("InsertFasilitasAnak",Log.getStackTraceString(e));
		  } finally {
				db.close();
		  }
	}
	

	public void DeleteFasilitasAnak(String child_id) {
		// delete first
		SQLiteDatabase db=this.getWritableDatabase();
		try {
			db.delete(TM_CHILD_FACILITY, CHILD_FACILITY_CHILD_ID + " = ?",
					new String[] { child_id });
			db.close();
			// insert the new
		} catch (Exception e) {
			Log.e("Eror delete fasilitas anak", e.toString());
		}

	}

	public ArrayList<ChildFacility_Model> getSemuaFasilitasAnak(String child_id) {
		Log.e("getSemuaFasilitasAnak", "running");
		ArrayList<ChildFacility_Model> data = new ArrayList<ChildFacility_Model>();
		SQLiteDatabase db = this.getReadableDatabase();
		try{
			Cursor mCursor = db.rawQuery(
							"SELECT  CF_child_id,CF_facility_id,CF_facility_cost_id  FROM  TM_CHILD_FACILITY WHERE CF_child_id= '"+ child_id + "'", null);
			if (mCursor != null) {
				mCursor.moveToFirst();
				while (!mCursor.isAfterLast()) {
					// parse data into model
					Log.e("fasilitas anak", " id anak " + mCursor.getString(0)+ " id fasilitas " + mCursor.getString(1)+ " id cost fasilitas " + mCursor.getString(2));
					data.add(parseDataFasilitasAnak(mCursor));
					mCursor.moveToNext();
				}
			}
			else
			{
				Log.e("Fasilitas Anak Null", "");
				ChildFacility_Model model = new ChildFacility_Model();
				model.setChild_id(child_id);
				model.setFacility_cost_id("-");
				data.add(model);
			}
				mCursor.close();
		}catch (SQLiteException se) {
			 Log.v(" getSemuaFasilitasAnak Exception",Log.getStackTraceString(se));
		} catch (Exception e) {
			Log.v(" getSemuaFasilitasAnak Exception",Log.getStackTraceString(e));
		} finally {
			db.close();
		}
		return data;
	}

	private ChildFacility_Model parseDataFasilitasAnak(Cursor cursor) {
		ChildFacility_Model curData = new ChildFacility_Model();
		curData.setChild_id(cursor.getString(cursor.getColumnIndex(CHILD_FACILITY_CHILD_ID )));
		curData.setFacility_id(cursor.getString(cursor.getColumnIndex(CHILD_FACILITY_FACILITY_ID )));
		curData.setFacility_cost_id(cursor.getString(cursor.getColumnIndex(CHILD_FACILITY_FACILITY_COST_ID )));
		

		return curData;
	}
	
	//------------------------------------COST FACILITY-----------------------------------------------------//
	public CharSequence[] getAllBrandName(String id_facility) {
		ArrayList<String> data = new ArrayList<String>();
		SQLiteDatabase db = this.getReadableDatabase();
		CharSequence[] data_result;
		data_result = data.toArray(new CharSequence[data.size()]);
		try {
			Cursor mCursor = db.rawQuery(
					"SELECT  COST_FACILITY_FACILITY_BRAND_NAME  FROM  TM_COST_FACILITY WHERE COST_FACILITY_FACILITY_ID= '"
							+ id_facility + "'", null);
			if (mCursor != null) {
				mCursor.moveToFirst();
				data.add(mCursor.getString(0));
				while (mCursor.moveToNext()) {
					data.add(mCursor.getString(0));
				}
				data_result = data.toArray(new CharSequence[data.size()]);
			} else {
				Log.e("get all brand name", "no data");
			}
			mCursor.close();
		} catch (SQLiteException se) {
			 Log.v(" getAllBrandName Exception",Log.getStackTraceString(se));
		} catch (Exception e) {
			Log.v(" getAllBrandName Exception",Log.getStackTraceString(e));
		} finally {
			db.close();
		}
		return data_result;
	}

	// get id dosis arv
	public String getIdCostFacility(String nama) {
		Log.e("getIdCostFacility", "");
		SQLiteDatabase db = this.getReadableDatabase();
		String id = "";
		try{
			Cursor mCursor = db.rawQuery(
					"SELECT  COST_FACILITY_FACILITY_COST_ID  FROM  TM_COST_FACILITY WHERE COST_FACILITY_FACILITY_BRAND_NAME= '"
							+ nama + "'", null);
			if (mCursor != null) {
				mCursor.moveToFirst();
				id = mCursor.getString(0);
			}
			mCursor.close();
		}catch (SQLiteException se) {
			 Log.v(" getIdCostFacility Exception",Log.getStackTraceString(se));
		} catch (Exception e) {
			Log.v(" getIdCostFacility Exception",Log.getStackTraceString(e));
		} finally {
			db.close();
		}
		return id;
	}

	// get dose name
	public String getNameCostFacility(String id) {
		Log.e("getNameCostFacility", "");
		SQLiteDatabase db = this.getReadableDatabase();
		String name = "";
		try{
			Cursor mCursor = db.rawQuery(
					"SELECT  COST_FACILITY_FACILITY_BRAND_NAME  FROM  TM_COST_FACILITY WHERE COST_FACILITY_FACILITY_COST_ID= '"
							+ id + "'", null);
			if (mCursor != null) {
				mCursor.moveToFirst();
				name = mCursor.getString(mCursor
						.getColumnIndex(COST_FACILITY_FACILITY_BRAND_NAME));
			}
			mCursor.close();
		}catch (SQLiteException se) {
			 Log.v(" getNameCostFacility Exception",Log.getStackTraceString(se));
		} catch (Exception e) {
			Log.v(" getNameCostFacility Exception",Log.getStackTraceString(e));
		} finally {
			db.close();
		}
		return name;
	}

	
	//---------------------------------------CHILD----------------------------------------------------------//
	// insertt data anak
		public void InsertDataAnak(
				String child_id,
				String child_name,
				String child_bod,
				String child_gender,
				String blood_type,
				String father_name,
				String mother_name,
				String caregiver_name,
				String child_address,
				String caregiver_phone,
				String school_name,
				String image_path,
				String id_statusARV, String id_typeARV, String id_dosisARV,
				String id_class, String id_caregiver, String id_facility,
				String id_status_ayah, String id_status_ibu, String id_subdistrict,
				String id_school_subdistrict,
				String group_id,
				String status_anak,
				String drug_taken,
				String image_name,
				String image_server_path,
				String param_id,
				String created_by,
				String created_time
		) {
			
			SQLiteDatabase db = this.getWritableDatabase();
			
			ContentValues values = new ContentValues();
			values.put(CHILD_ID, child_id);
			values.put(CHILD_NAME, child_name);
			values.put(CHILD_BOD, child_bod);
			values.put(CHILD_GENDER, child_gender);
			values.put(CHILD_BLOOD_TYPE, blood_type);
			values.put(CHILD_FATHER_NAME, father_name);
			values.put(CHILD_MOTHER_NAME, mother_name);
			values.put(CHILD_CAREGIVER_NAME, caregiver_name);
			values.put(CHILD_ADDRESS, child_address);
			values.put(CHILD_CAREGIVER_PHONE, caregiver_phone);
			values.put(CHILD_SCHOOL_NAME, school_name);
			values.put(CHILD_IMAGE_PATH, image_path);
			values.put(CHILD_DRUG_TYPE_ID, id_typeARV);
			values.put(CHILD_DRUG_STATUS_ID, id_statusARV);
			values.put(CHILD_DRUG_DOSE_ID, id_dosisARV);
			values.put(CHILD_CLASS_ID, id_class);
			values.put(CHILD_CAREGIVER_ID, id_caregiver);
			values.put(CHILD_FACILITY_ID, id_facility);
			values.put(CHILD_DAD_STATUS_ID, id_status_ayah);
			values.put(CHILD_MOM_STATUS_ID, id_status_ibu);
			values.put(CHILD_SUBDISTRICT_ID, id_subdistrict);
			values.put(CHILD_SCHOOL_SUBDISTRICT_ID, id_school_subdistrict);
			values.put(CHILD_GROUP_ID, group_id);
			values.put(CHILD_STATUS, status_anak);
			values.put(CHILD_DRUG_TAKEN, drug_taken);
			values.put(CHILD_IMAGE_NAME, image_name);
			values.put(CHILD_IMAGE_SERVER_PATH, image_server_path);
			values.put(CHILD_PARAM_ID, param_id);
			values.put(CHILD_CREATED_BY, created_by);
			values.put(CHILD_CREATED_TIME, created_time);

			try {
				// menambahkan nama tabel bila tidak akan error
				// db.delete(NAMA_TABEL, null, null);
				db.insert(TM_CHILD, null, values);
				
				Log.i("Insert Tabel Anak", "ID "+child_id+" nama " + child_name +" bod "+child_bod +" gender "+ child_gender +" blood type "+blood_type+" father name "+father_name +" mother name "+mother_name +" caregiver name "+caregiver_name+ " child add "+child_address
						+" phone caregiver "+caregiver_phone+" school name "+school_name+" status arv "+id_statusARV+" id class "+id_class+" id_cargiver "+id_caregiver+" id_faceility "+id_facility+" id status ayah "+id_status_ayah+" id status ibu "+id_status_ibu+" id subdistrict "+id_subdistrict+" school subdist "+id_school_subdistrict+" group id "+group_id+" status_anak "+status_anak+" drug taken "+drug_taken +" image name "+image_name +" image path "+image_path+" image_server_path "+image_server_path +" param id "+param_id +" created by "+created_by + " created time "+created_time);
			 }catch (SQLiteException se) {
				 Log.v("InsertDataAnak",Log.getStackTraceString(se));
			 }catch (Exception e) {
				Log.v("InsertDataAnak",Log.getStackTraceString(e));
			 } finally {
				db.close();
			}
		}
		
		public Child_Model SearchChild(String nama) {
			Child_Model model_child = new Child_Model();
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor;
			Child_Model tm = new Child_Model();
			try{
				 cursor = db.rawQuery(
								" Select CHILD_id,CHILD_name,CHILD_bod,CHILD_gender,CHILD_blood_type,CHILD_father_name,CHILD_mother_name,CHILD_caregiver_name,CHILD_address,CHILD_caregiver_phone,CHILD_school_name,CHILD_image_path,CHILD_drug_dose_id,CHILD_drug_status_id,CHILD_drug_type_id,CHILD_class_id,CHILD_caregiver_id,CHILD_facility_id,CHILD_dad_status_id,CHILD_mom_status_id,CHILD_subdistrict_id,CHILD_school_subdistrict_id FROM  TM_CHILD WHERE CHILD_name = '"
										+ nama + "'", null);
				if (cursor != null) {
					cursor.moveToFirst();
					tm = parseDataAnak(cursor);
				}
			cursor.close();
			}catch (SQLiteException se) {
				 Log.v(" SearchChild Exception",Log.getStackTraceString(se));
			} catch (Exception e) {
				Log.v(" SearchChild Exception",Log.getStackTraceString(e));
			} finally {
				db.close();
			}
				return tm;
		}

		// ambil semua data dari table
		public ArrayList<Child_Model> getSemuaDataAnak() {
			Log.e("Get Semua Data Anak", "");
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor;
			ArrayList<Child_Model> allData = new ArrayList<Child_Model>();
			cursor = null;
			try {
				cursor = db.query(TM_CHILD, new String[] {
						CHILD_ID,
						CHILD_NAME,
						CHILD_BOD,
						CHILD_GENDER,
						CHILD_BLOOD_TYPE,
						CHILD_FATHER_NAME,
						CHILD_MOTHER_NAME,
						CHILD_CAREGIVER_NAME,
						CHILD_ADDRESS,
						CHILD_CAREGIVER_PHONE,
						CHILD_SCHOOL_NAME,
						CHILD_IMAGE_PATH,
						CHILD_DRUG_DOSE_ID, CHILD_DRUG_STATUS_ID, CHILD_DRUG_TYPE_ID,
						CHILD_CLASS_ID, CHILD_CAREGIVER_ID, CHILD_FACILITY_ID,
						CHILD_DAD_STATUS_ID, CHILD_MOM_STATUS_ID, CHILD_SUBDISTRICT_ID,
						CHILD_SCHOOL_SUBDISTRICT_ID }, null, null, null, null, null);
	
				cursor.moveToFirst();
				while (!cursor.isAfterLast()) {
					allData.add(parseDataAnak(cursor));
					cursor.moveToNext();
			}

			cursor.close();
			}catch (SQLiteException se) {
				 Log.v(" getSemuaDataAnak Exception",Log.getStackTraceString(se));
			} catch (Exception e) {
				Log.v(" getSemuaDataAnak Exception",Log.getStackTraceString(e));
			} finally {
				db.close();
			}
			return allData;
		}

		private Child_Model parseDataAnak(Cursor cursor) {
			Child_Model curData = new Child_Model();
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

		public Child_Model getChildIdentityById(String id) {
			Child_Model model_child = new Child_Model();
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor;
			Child_Model tm = new Child_Model();
			try{
				 cursor = db.rawQuery(
								" Select CHILD_id,CHILD_name,CHILD_bod,CHILD_gender,CHILD_blood_type,CHILD_father_name,CHILD_mother_name,CHILD_caregiver_name,CHILD_address,CHILD_caregiver_phone,CHILD_school_name,CHILD_image_path,CHILD_drug_dose_id,CHILD_drug_status_id,CHILD_drug_type_id,CHILD_class_id,CHILD_caregiver_id,CHILD_facility_id,CHILD_dad_status_id,CHILD_mom_status_id,CHILD_subdistrict_id,CHILD_school_subdistrict_id FROM  TM_CHILD WHERE CHILD_id = '"
										+ id + "'", null);
				if (cursor != null) {
					cursor.moveToFirst();
					tm = parseDataAnak(cursor);
				}
			cursor.close();
			}catch (SQLiteException se) {
				 Log.v(" getChildIdentityById Exception",Log.getStackTraceString(se));
			} catch (Exception e) {
				Log.v(" getChildIdentityById Exception",Log.getStackTraceString(e));
			} finally {
				db.close();
			}
				return tm;
		}

		public void updateChildIdentityById(String child_id, String child_name,
				String child_bod, String child_gender, String blood_type,
				String father_name, String mother_name, String caregiver_name,
				String child_address, String caregiver_phone, String school_name,
				
				String id_status_ayah, String id_status_ibu, String id_caregiver,
				String id_subdistrict, String id_school_subdistrict, String id_class,
				String path_picture,String update_by, String update_time
				// String drug_taken
				// ,String image_name , String image_path , String image_server_path,
				// String created_by , String created_time ,String update_by , String
				// update_time
				) 
		{
			Log.e("updateChildIdentityById", "");
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(CHILD_NAME, child_name);
			values.put(CHILD_BOD, child_bod);
			values.put(CHILD_GENDER, child_gender);
			values.put(CHILD_BLOOD_TYPE, blood_type);
			values.put(CHILD_FATHER_NAME, father_name);
			values.put(CHILD_MOTHER_NAME, mother_name);
			values.put(CHILD_CAREGIVER_NAME, caregiver_name);
			values.put(CHILD_ADDRESS, child_address);
			values.put(CHILD_CAREGIVER_PHONE, caregiver_phone);
			values.put(CHILD_SCHOOL_NAME, school_name);
			values.put(CHILD_DAD_STATUS_ID, id_status_ayah);
			values.put(CHILD_MOM_STATUS_ID, id_status_ibu);
			values.put(CHILD_CAREGIVER_ID, id_caregiver);
			values.put(CHILD_SUBDISTRICT_ID, id_subdistrict);
			values.put(CHILD_SCHOOL_SUBDISTRICT_ID, id_school_subdistrict);
			values.put(CHILD_CLASS_ID, id_class);
			values.put(CHILD_IMAGE_PATH, path_picture);
			values.put(CHILD_UPDATE_BY,update_by);
			values.put(CHILD_UPDATE_TIME,update_time);
			try{
				db.update(TM_CHILD, values, "CHILD_id = ?", new String[]{child_id});
			//db.update(TM_CHILD, values, "CHILD_id "+"=" + child_id, null);
			}catch (SQLiteException se) {
				 Log.v(" updateChildIdentityById Exception",Log.getStackTraceString(se));
			} catch (Exception e) {
				Log.v(" updateChildIdentityById Exception",Log.getStackTraceString(e));
			} finally {
				db.close();
			}
		}

		//get child name
		 public ArrayList<String> getAllChildName() {
			Log.e("getAllChildName", "");
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor;
			ArrayList<String> allData = new ArrayList<String>();
			cursor = null;
			try{
				cursor = db.query(TM_CHILD, new String[] { 
						CHILD_ID,
						CHILD_NAME
			    }, null, null, null, null, null);
	
			  cursor.moveToFirst();
			  while (!cursor.isAfterLast()) {
				  allData.add(cursor.getString(cursor.getColumnIndex(CHILD_ID)));
				  allData.add(cursor.getString(cursor.getColumnIndex(CHILD_NAME)));
				  cursor.moveToNext();
			  }
	
			  cursor.close();
			}catch (SQLiteException se) {
				 Log.v(" getAllChildName Exception",Log.getStackTraceString(se));
			} catch (Exception e) {
				Log.v(" getAllChildName Exception",Log.getStackTraceString(e));
			} finally {
				db.close();
			}
			
		  return allData;
		}
		 
		 public String getLastInsertedChild()
		 {
			 Log.e("getLastInsertedChild", "");
			 SQLiteDatabase db = this.getReadableDatabase();
			 Cursor cursor;
			 String id = "";
			 try{
				 cursor = db.rawQuery("SELECT  CHILD_id FROM  TM_CHILD", null); 
				 if(cursor != null && cursor.moveToLast())
				 {
					 id = cursor.getString(cursor.getColumnIndex(CHILD_ID));
				 }
				 cursor.close();
			}catch (SQLiteException se) {
				 Log.v(" getAllChildName Exception",Log.getStackTraceString(se));
			} catch (Exception e) {
				Log.v(" getAllChildName Exception",Log.getStackTraceString(e));
			} finally {
				db.close();
			}
			 return id;
		 }
	
	
	
	//---------------------------------------CLASS----------------------------------------------------------//
		  // query data kelas
		   public ArrayList<String> getDataKelas() {
			    ArrayList<String> allData = new ArrayList<String>();
			    SQLiteDatabase db = this.getReadableDatabase();
			    Cursor cursor = null;
			    try{
				    cursor = db.query(TM_CLASS, new String[] { CLASS_ID,
				      CLASS_DESCRIPTION, CLASS_CREATED_BY, CLASS_CREATED_TIME,
				      CLASS_UPDATE_BY, CLASS_UPDATE_TIME,CLASS_DELETE_STATUS }, null, null, null, null, null);
		
				    cursor.moveToFirst();
				    while (!cursor.isAfterLast()) {
				     allData.add(cursor.getString(1));
		
				     cursor.moveToNext();
			    
				   }
				    cursor.close();
			    }catch (SQLiteException se) {
					 Log.v(" Data Kelas Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" Data Kelas Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
			    return allData;
		   }
			

		// get id kelas
		   public String getIdKelas(String kelas_deskripsi) {
			    String id = "";
			    SQLiteDatabase db = this.getReadableDatabase();
			    try{
				    Cursor mCursor = db.rawQuery(
				      "SELECT  Class_id  FROM  TM_CLASS WHERE CLASS_description= '"
				        + kelas_deskripsi + "'", null);
				    if (mCursor != null) {
				     mCursor.moveToFirst();
				     id = mCursor.getString(0);
				    }
				    mCursor.close();
			    }catch (SQLiteException se) {
					 Log.v(" id Kelas Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" id kelas Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
			    return id;
		   }

		// get name kelas
		   public String getNameKelas(String id) {
			    String name = "";
			    SQLiteDatabase db = this.getReadableDatabase();
			    try {
				    Cursor mCursor = db
				      .rawQuery(
				        "SELECT  CLASS_description  FROM  TM_CLASS WHERE Class_id= '"
				          + id + "'", null);
				    if (mCursor != null) {
				     mCursor.moveToFirst();
				     name = mCursor.getString(mCursor.getColumnIndex(CLASS_DESCRIPTION));
				    }
				    mCursor.close();
			    } catch (SQLiteException se) {
					 Log.v(" nama kelas Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" nama Kelas Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
			    return name;
		   }
			
	//---------------------------------------DISTRICT----------------------------------------------------------//
		// get all data
		   public ArrayList<String> getAllDataDistrict() {
			    ArrayList<String> allData = new ArrayList<String>();
			    Cursor cursor = null;
			    SQLiteDatabase db = this.getReadableDatabase();
			    try{
				    cursor = db.query(TM_DISTRICT, new String[] { DISTRICT_ID,
				      DISTRICT_DESCRIPTION, DISTRICT_CREATED_BY, DISTRICT_CREATED_TIME,
				      DISTRICT_UPDATE_BY, DISTRICT_UPDATE_TIME, DISTRICT_DELETE_STATUS }, null, null, null, null, null);
		
				    cursor.moveToFirst();
				    while (!cursor.isAfterLast()) {
				     allData.add(cursor.getString(1));
		
				     cursor.moveToNext();
				    }
		
				    cursor.close();
			    } catch (SQLiteException se) {
					 Log.v(" all data dist Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" all data dist Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
			    return allData;
		   }
		   
		   public String getIdDistrict(String district) {
			    String id = "";
			    SQLiteDatabase db = this.getReadableDatabase();
			    try{
				    Cursor mCursor = db.rawQuery(
				      "SELECT  DISTRICT_id  FROM  TM_DISTRICT WHERE DISTRICT_description= '"
				        + district + "'", null);
				    if (mCursor != null) {
				     mCursor.moveToFirst();
				     id = mCursor.getString(mCursor.getColumnIndex(DISTRICT_ID));
				    }
				    mCursor.close();
			    }catch(Exception e){
				     e.printStackTrace();
				     Log.e("id failed", e.toString());
			    }
			    return id;
		   }

		   public String getNameDistrict(String id) {
			    String name = "";
			    SQLiteDatabase db = this.getReadableDatabase();
			    Cursor mCursor = db.rawQuery(
			      "SELECT  DISTRICT_description  FROM  TM_DISTRICT WHERE DISTRICT_id= '"
			        + id + "'", null);
			    if (mCursor != null) {
			     mCursor.moveToFirst();
			     name = mCursor.getString(mCursor
			       .getColumnIndex(DISTRICT_DESCRIPTION));
			    }
			    mCursor.close();
			    return name;
			   }
	//---------------------------------------SUBDISTRICT----------------------------------------------------------//
			
		   public String getIdSubDistrict(String subdistrict) {
			   Log.e("getIdSubDistrict","");
				String id = "";
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db.rawQuery(
							"SELECT  SUBDISTRICT_id  FROM  TM_SUBDISTRICT WHERE SUBDISTRICT_description= '"
									+ subdistrict + "'", null);
					if (mCursor != null) {
						mCursor.moveToFirst();
						id = mCursor.getString(0);
					}
					mCursor.close();
				}finally
			    {
			    	db.close();
			    }
				return id;
			}
			// get all data
		   public ArrayList<String> getAllDataSubdistrict() {
			    ArrayList<String> allData = new ArrayList<String>();
			    SQLiteDatabase db = this.getReadableDatabase();
			    Cursor cursor = null;
			    try{
				    cursor = db.query(TM_SUBDISTRICT, new String[] { SUBDISTRICT_DISTRICT_ID,
				      SUBDISTRICT_DESCRIPTION, SUBDISTRICT_CREATED_BY, SUBDISTRICT_CREATED_TIME,
				      SUBDISTRICT_UPDATE_BY, SUBDISTRICT_UPDATE_TIME, SUBDISTRICT_DELETE_STATUS }, null, null, null, null, null);
	
				    cursor.moveToFirst();
				    while (!cursor.isAfterLast()) {
				     allData.add(cursor.getString(1));
				     cursor.moveToNext();
				    }
				    cursor.close();
			    }finally
			    {
			    	db.close();
			    }
			    return allData;
			   }

			// get caregiver
		   public String getNameSubDistrict(String id) {
			    String name = "";
			    SQLiteDatabase db = this.getReadableDatabase();
			    try{
				    Cursor mCursor = db.rawQuery(
				      "SELECT  SUBDISTRICT_description  FROM  TM_SUBDISTRICT WHERE SUBDISTRICT_id= '"
				        + id + "'", null);
				    if (mCursor != null) {
				     mCursor.moveToFirst();
				     name = mCursor.getString(mCursor
				       .getColumnIndex(SUBDISTRICT_DESCRIPTION));
				    }
				    mCursor.close();
			    }finally{
			    	db.close();
			    }
			  return name;
		   }
		   
			//get id of district
		   public String getIDDistrict(String id_subdistrict){
			    String name = "";
			    SQLiteDatabase db = this.getReadableDatabase();
			    try{
				    Cursor mCursor = db.rawQuery(
				      "SELECT  DISTRICT_id  FROM  TM_SUBDISTRICT WHERE SUBDISTRICT_id= '"
				        + id_subdistrict + "'", null);
				    if (mCursor != null) {
				     mCursor.moveToFirst();
				     name = mCursor.getString(mCursor
				       .getColumnIndex(DISTRICT_ID));
				    }
				    mCursor.close();
			    }finally{
			    	db.close();
			    }
			    return name;
			}
		   
		   public ArrayList<String> getAllNameSubDistrict(String id) {
			    ArrayList<String> district =  new ArrayList<String>();
			    SQLiteDatabase db = this.getReadableDatabase();
			    try {
				     Cursor mCursor = db.rawQuery(
				       "SELECT  SUBDISTRICT_description  FROM  TM_SUBDISTRICT WHERE SUBDISTRICT_district_id= '"+ id + "'", null);
				     
				     mCursor.moveToFirst();
				     while (!mCursor.isAfterLast()) {
				      district.add(mCursor.getString(mCursor.getColumnIndex(SUBDISTRICT_DESCRIPTION)));
				      mCursor.moveToNext();
				     }
				     mCursor.close();
				     
				     
			    } catch (Exception e) {
			    	e.printStackTrace();
			    	Log.e("Failed to find all name district", e.toString());
			    }finally{
			    	db.close();
			    }
			    return district;
			 }
			
		   public ArrayList<String> getAllDistrict() {
			    SQLiteDatabase db = this.getReadableDatabase();
			    ArrayList<String> district =  new ArrayList<String>();
			    try {
				     Cursor mCursor = db.rawQuery(
				       "SELECT  SUBDISTRICT_description  FROM  TM_SUBDISTRICT ", null);
				     
				     mCursor.moveToFirst();
				     while (!mCursor.isAfterLast()) {
				      district.add(mCursor.getString(mCursor.getColumnIndex(SUBDISTRICT_DESCRIPTION)));
				      mCursor.moveToNext();
				     }
				     mCursor.close();
			    } catch (Exception e) {
			     e.printStackTrace();
			     Log.e("Failed to find all name district", e.toString());
			    }finally{
			    	db.close();
			    }
			    return district;
		   }
			
	//---------------------------------------FACILITY----------------------------------------------------------//
			// get all data facility
		   public ArrayList<String> getAllDataFacility() {
			    ArrayList<String> allData = new ArrayList<String>();
			    SQLiteDatabase db = this.getReadableDatabase();
			    Cursor cursor = null;
			    try{
				    cursor = db.query(TM_FACILITY, new String[] { FACILITY_ID,
				      FACILITY_DESCRIPTION, FACILITY_CREATED_BY, FACILITY_CREATED_TIME,
				      FACILITY_UPDATE_BY, FACILITY_UPDATE_TIME, FACILITY_DELETE_STATUS }, null, null, null, null, null);
	
				    cursor.moveToFirst();
				    while (!cursor.isAfterLast()) {
				     allData.add(cursor.getString(1));
	
				     cursor.moveToNext();
				    }
	
				    cursor.close();
			    }finally{
			    	db.close();
			    }
			    return allData;
			   }

		   public void deleteAllFacility() {
			    SQLiteDatabase db = this.getReadableDatabase();
			    db.delete(TM_FACILITY, null, null);
			    db.close();
			   }

			// get id type arv
		  
		   public String getIdFacility(String facility) {
			    SQLiteDatabase db = this.getReadableDatabase();
			    String id = "";
			    try{
				    Cursor mCursor = db.rawQuery(
				      "SELECT  FACILITY_id  FROM  TM_FACILITY WHERE FACILITY_description= '"
				        + facility + "'", null);
				    if (mCursor != null) {
				     mCursor.moveToFirst();
				     id = mCursor.getString(mCursor.getColumnIndex(FACILITY_ID));
				    }
				    mCursor.close();
			    }finally{
			    	db.close();
			    }
			    
			    return id;
		   }

		   public String getNameFacility(String id) {
			    String name = "";
			    SQLiteDatabase db = this.getReadableDatabase();
			    try{
				    Cursor mCursor = db.rawQuery(
				      "SELECT  FACILITY_description  FROM  TM_FACILITY WHERE FACILITY_id= '"
				        + id + "'", null);
				    if (mCursor != null) {
				     mCursor.moveToFirst();
				     name = mCursor.getString(mCursor
				       .getColumnIndex(FACILITY_DESCRIPTION));
				    }
				    mCursor.close();
			    }finally{
			    	db.close();
			    }
			    return name;
			   }

	//---------------------------------------PARENT_STATUS----------------------------------------------------------//
			// get all data status parent
		   public ArrayList<String> getAllDataParentStatus() {
			    ArrayList<String> allData = new ArrayList<String>();
			    SQLiteDatabase db = this.getReadableDatabase();
			    Cursor cursor = null;
			    try{
				    cursor = db.query(TM_PARENT_STATUS, new String[] { PARENT_STATUS_ID,
				      PARENT_STATUS_DESCRIPTION, PARENT_STATUS_CREATED_BY,
				      PARENT_STATUS_CREATED_TIME, PARENT_STATUS_UPDATE_BY, PARENT_STATUS_UPDATE_TIME, PARENT_STATUS_DELETE_STATUS }, null, null,
				      null, null, null);
	
				    cursor.moveToFirst();
				    while (!cursor.isAfterLast()) {
				     allData.add(cursor.getString(1));
	
				     cursor.moveToNext();
				    }
	
				    cursor.close();
			    }finally{
			    	db.close();
			    }
			    return allData;
			   }

			// get id parent
		   public String getIdParentStatus(String type_arv) {
			    String id = "";
			    SQLiteDatabase db = this.getReadableDatabase();
			    try{
				    Cursor mCursor = db
				      .rawQuery(
				        "SELECT  PARENT_STATUS_id  FROM  TM_PARENT_STATUS WHERE PARENT_STATUS_descriptionPARENT_STATUS_description= '"
				          + type_arv + "'", null);
				    if (mCursor != null) {
				     mCursor.moveToFirst();
				     id = mCursor.getString(0);
				    }
				    mCursor.close();
			    }finally{
			    	db.close();
			    }
			    return id;
			   }

			// get name status parent
		   public String getNameStatusParent(String id) {
			    String name = "";
			    SQLiteDatabase db = this.getReadableDatabase();
			    try{
				    Cursor mCursor = db.rawQuery(
				      "SELECT  PARENT_STATUS_description  FROM  TM_PARENT_STATUS WHERE PARENT_STATUS_id= '"
				        + id + "'", null);
				    if (mCursor != null) {
				     mCursor.moveToFirst();
				     name = mCursor.getString(mCursor.getColumnIndex(PARENT_STATUS_DESCRIPTION));
				    }
				    mCursor.close();
				}catch(Exception e){
			     name="";
			    }finally{
			    	db.close();
			    }
			    return name;
		}
		   
	//---------------------------------------DRUG_TYPE----------------------------------------------------------//
			// query data type arv
		   public ArrayList<String> getAllDataARVType() {
			    ArrayList<String> allData = new ArrayList<String>();
			    Cursor cursor = null;
			    SQLiteDatabase db = this.getReadableDatabase();
			    try{
				    cursor = db.query(TM_DRUG_TYPE , new String[] { DRUG_TYPE_ID ,
				    		DRUG_TYPE_DESCRIPTION , DRUG_TYPE_DRUG_STATUS_ID , DRUG_TYPE_CREATED_BY , DRUG_TYPE_CREATED_TIME ,
				    		DRUG_TYPE_UPDATE_BY , DRUG_TYPE_UPDATE_TIME , DRUG_TYPE_DELETE_STATUS  }, null, null, null, null, null);
	
				    cursor.moveToFirst();
				    while (!cursor.isAfterLast()) {
				     allData.add(cursor.getString(1));
	
				     cursor.moveToNext();
				    }
	
				    cursor.close();
			    }finally{
			    	db.close();
			    }
			    return allData;
			}
		   
			public void deleteAllDrugType() {
				SQLiteDatabase db = this.getWritableDatabase();
			    db.delete(TM_DRUG_TYPE, null, null);
			    db.close();
			}
			
			
			// get id id arv
			public String getIdTypeARV(String type_arv) {
				String id = "";
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db.rawQuery(
							"SELECT  DRUG_TYPE_id  FROM  TM_DRUG_TYPE WHERE DRUG_TYPE_description= '"
									+ type_arv + "'", null);
					if (mCursor != null) {
						mCursor.moveToFirst();
						id = mCursor.getString(0);
					}
					mCursor.close();
				}finally{
					db.close();
				}
				return id;
			}

			// get name type arv
			public String getNameTypeARV(String id) {
				String name = "";
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db.rawQuery(
							"SELECT  DRUG_TYPE_description  FROM  TM_DRUG_TYPE WHERE DRUG_TYPE_id= '"
									+ id + "'", null);
					if (mCursor != null) {
						mCursor.moveToFirst();
						name = mCursor.getString(mCursor.getColumnIndex(DRUG_TYPE_DESCRIPTION));
					}
					mCursor.close();
				}finally{
					db.close();
				}
				return name;
			}
	//---------------------------------------DRUG_STATUS----------------------------------------------------------//
			// query data status
			public ArrayList<String> getAllDataARVStatus() {
				ArrayList<String> allData = new ArrayList<String>();
				Cursor cursor = null;
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					cursor = db.query(TM_DRUG_STATUS, new String[] { DRUG_STATUS_ID,
							DRUG_STATUS_DESCRIPTION, DRUG_STATUS_CREATED_BY, DRUG_STATUS_CREATED_TIME,
							DRUG_STATUS_UPDATE_BY, DRUG_STATUS_UPDATE_TIME }, null, null, null, null, null);
	
					cursor.moveToFirst();
					while (!cursor.isAfterLast()) {
						allData.add(cursor.getString(1));
	
						cursor.moveToNext();
					}
	
					cursor.close();
				}finally{
					db.close();
				}
				return allData;
			}

			public  void deleteAllARVStatus() {
				SQLiteDatabase db = this.getWritableDatabase();
				db.delete(TM_DRUG_STATUS, null, null);
				db.close();
			}

			public String getIdStatusARV(String status_arv) {
				String id = "";
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db.rawQuery(
							"SELECT  DRUG_STATUS_id  FROM  TM_DRUG_STATUS WHERE DRUG_STATUS_description= '"
									+ status_arv + "'", null);
					if (mCursor != null) {
						mCursor.moveToFirst();
						id = mCursor.getString(0);
					}
					mCursor.close();
				}finally{
					db.close();
				}
				return id;
			}

			// 
			public String getNameStatusARV(String id) {
				String name = "";
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db.rawQuery(
							"SELECT  DRUG_STATUS_description  FROM  TM_DRUG_STATUS WHERE DRUG_STATUS_id= '"
									+ id + "'", null);
					if (mCursor != null) {
						mCursor.moveToFirst();
						name = mCursor.getString(mCursor.getColumnIndex(DRUG_STATUS_DESCRIPTION));
					}
					mCursor.close();
				}finally{
					db.close();
				}
				return name;
			}
			
	//---------------------------------------DRUG_DOSE----------------------------------------------------------//
			// query data kelas
			public ArrayList<String> getAllDataARVDose() {
				ArrayList<String> allData = new ArrayList<String>();
				Cursor cursor = null;
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					cursor = db.query(TM_DRUG_DOSE, new String[] { DRUG_DOSE_ID,
							DRUG_DOSE_DESCRIPTION, DRUG_DOSE_CREATED_BY, DRUG_DOSE_CREATED_TIME,
							DRUG_DOSE_UPDATE_BY, DRUG_DOSE_UPDATE_TIME }, null, null, null, null, null);
	
					cursor.moveToFirst();
					while (!cursor.isAfterLast()) {
						allData.add(cursor.getString(1));
	
						cursor.moveToNext();
					}
	
					cursor.close();
				}finally{
					db.close();
				}
				return allData;
			}
			
			//get dosis pagi
			public ArrayList<String> getDataDosisPagi() {
				ArrayList<String> allData = new ArrayList<String>();
				Cursor cursor = null;
				String time = "Pagi";
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					 cursor = db.rawQuery(
								"SELECT  DRUG_DOSE_description  FROM  TM_DRUG_DOSE WHERE DRUG_DOSE_drug_dose_time= '"
										+ time + "'", null);
					 if(cursor != null)
					 {
						cursor.moveToFirst();
						while (!cursor.isAfterLast()) {
							allData.add(cursor.getString(0));
							cursor.moveToNext();
						}
					 }
	
					cursor.close();
				}finally{
					db.close();
				}
				return allData;
			}
			
			//get dosis malam
			public ArrayList<String> getDataDosisMalam() {
				ArrayList<String> allData = new ArrayList<String>();
				Cursor cursor = null;
				String time = "Malam";
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					 cursor = db.rawQuery(
								"SELECT  DRUG_DOSE_description  FROM  TM_DRUG_DOSE WHERE DRUG_DOSE_drug_dose_time= '"
										+ time + "'", null);
					 if(cursor != null)
					 {
						cursor.moveToFirst();
						while (!cursor.isAfterLast()) {
							allData.add(cursor.getString(0));
							cursor.moveToNext();
						}
					 }
					cursor.close();
				}finally{
					db.close();
				}
				return allData;
			}

			public  void deleteAllARVDose() {
				SQLiteDatabase db = this.getWritableDatabase();
				db.delete(TM_DRUG_DOSE, null, null);
				db.close();
			}

			// get id dosis arv
			public String getIdDosisARV(String dosis_arv,String time) {
				String id = "";
				Cursor mCursor;
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					if(time.equals("Pagi"))
					{
						 mCursor = db.rawQuery(
								"SELECT  DRUG_DOSE_id  FROM  TM_DRUG_DOSE WHERE DRUG_DOSE_description= '"
										+ dosis_arv + "' and DRUG_DOSE_drug_dose_time= '"+time+"'", null);
						if (mCursor != null) {
							mCursor.moveToFirst();
							id = mCursor.getString(0);
						}
					}
					else
					{
						mCursor = db.rawQuery(
								"SELECT  DRUG_DOSE_id  FROM  TM_DRUG_DOSE WHERE DRUG_DOSE_description= '"
										+ dosis_arv + "' and DRUG_DOSE_drug_dose_time= '"+time+"'", null);
						if (mCursor != null) {
							mCursor.moveToFirst();
							id = mCursor.getString(0);
						}
					}
					mCursor.close();
				}finally{
					db.close();
				}
				return id;
			}

			// get dose name
			public String getNameDose(String id) {
				String name = "";
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db.rawQuery(
							"SELECT  DRUG_DOSE_description  FROM  TM_DRUG_DOSE WHERE DRUG_DOSE_id= '"+ id + "'", null);
					if (mCursor != null) {
						mCursor.moveToFirst();
						name = mCursor.getString(mCursor.getColumnIndex(DRUG_DOSE_DESCRIPTION));
					}
					mCursor.close();
				}finally{
					db.close();
				}
				return name;
			}
			
			
	//---------------------------------------TR_CHILD_DRUG_TYPE----------------------------------------------------------//
			//get all child arv
			public ArrayList<ChildARV_Model> getSemuaARVAnak(String child_id) {
				ArrayList<ChildARV_Model> data = new ArrayList<ChildARV_Model>();
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db.rawQuery("SELECT  TR_CDT_child_id,TR_CDT_drug_type_id,TR_CDT_drug_dose_id  FROM  TR_CHILD_DRUG_TYPE WHERE TR_CDT_child_id= '"+ child_id + "'", null);
					if (mCursor != null) {
						mCursor.moveToFirst();
						while (!mCursor.isAfterLast()) {
							// parse data into model
							Log.e("arv anak", " id anak " + mCursor.getString(0)+ " type " + mCursor.getString(1)+ " status " + mCursor.getString(2));
							data.add(parseDataChildARV(mCursor));
							mCursor.moveToNext();
						}
					}
						mCursor.close();
				}catch (SQLiteException se) {
					 Log.v(" getSemuaARVAnak Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" getSemuaARVAnak Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
				return data;
			}
			
			public ChildARV_Model parseDataChildARV(Cursor cursor)
			{
				ChildARV_Model cur = new ChildARV_Model();
				cur.SetChildID(cursor.getString(0));
				cur.SetARVTypeID(cursor.getString(1));
				cur.SetARVDoseID(cursor.getString(2));
				return cur;
			}
			
			//insert child arvs
			public void InsertARVAnak(String child_id, String type_id, String dose_id, String created_by, String created_time ) {
				 SQLiteDatabase db = this.getWritableDatabase();
				  try {
						ContentValues values = new ContentValues();
						values.put(CK_DT_CHILD_ID , child_id);
						values.put(CK_DT_DRUG_TYPE_ID , type_id);
						values.put(CK_DT_DRUG_DOSE_ID , dose_id);
						values.put(CK_DT_CREATED_BY, created_by);
						values.put(CK_DT_CREATED_TIME, created_time);
						
						// menambahkan nama tabel bila tidak akan error
						// db.delete(NAMA_TABEL, null, null);
						db.insert(TR_CHILD_DRUG_TYPE, null, values);
						Log.v("Insert tabel TR_CHILD_DRUG_TYPE", "child_id " + child_id
									+ " type_id " + type_id + " dose_id "
									+ dose_id);
						db.close();
				  }
				  catch (SQLiteException se) {
						 Log.v("InsertARVAnak",Log.getStackTraceString(se));
				  } catch (Exception e) {
						Log.v("InsertARVAnak",Log.getStackTraceString(e));
				  } finally {
						db.close();
				  }
			}
			
			public void DeleteARVAnak(String child_id)
			{
				SQLiteDatabase db=this.getWritableDatabase();
				try {
					db.delete(TR_CHILD_DRUG_TYPE, CK_DT_CHILD_ID + " = ?",
							new String[] { child_id });
					Log.e("Delete ARV Anak", "");
					db.close();
					
				} catch (Exception e) {
					Log.e("Eror delete arv anak", e.toString());
				}
			}
			public void UpdateARVAnak(String child_id, String type_id, String dose_id, String created_by, String created_time)
			{
				//delete first and the insert the new one
				DeleteARVAnak(child_id);
				// insert the new one
				InsertARVAnak(child_id, type_id, dose_id, created_by, created_time);
				
			}
			
	
	//---------------------------------------TM_VISIT_TYPE----------------------------------------------------------//
			public ArrayList<String> getDataVisitType() {
				ArrayList<String> allData = new ArrayList<String>();
				Cursor cursor = null;
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					cursor = db.query(TM_VISIT_TYPE, new String[] { VISIT_TYPE_ID ,
							VISIT_TYPE_DESCRIPTION , VISIT_TYPE_CREATED_BY , VISIT_TYPE_CREATED_TIME ,
							VISIT_TYPE_UPDATE_BY , VISIT_TYPE_UPDATE_TIME  }, null, null, null, null, null);
			
					cursor.moveToFirst();
					while (!cursor.isAfterLast()) {
						allData.add(cursor.getString(1));
						cursor.moveToNext();
						
					}
					cursor.close();
				}catch (SQLiteException se) {
						 Log.v(" getDataVisitType Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
						Log.v(" getDataVisitType Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
					return allData;
			}
		
			//membuat array pada table layout
			// get id kelas
			public String getIdVisitType(String visit_type) {
				String id = "";
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db.rawQuery(
									"SELECT  VISIT_TYPE_id  FROM  TM_VISIT_TYPE  WHERE VISIT_TYPE_description= '"
											+ visit_type + "'", null);
							if (mCursor != null) {
								mCursor.moveToFirst();
								id = mCursor.getString(0);
							}
					mCursor.close();
				}catch (SQLiteException se) {
					 Log.v(" getIdVisitType Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" getIdVisitType Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
				
				return id;
			}
		
			// get name kelas
			public String getNameVisitType(String id) {
				String name = "";
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db.rawQuery(
											"SELECT  VISIT_TYPE_description  FROM  TM_VISIT_TYPE WHERE VISIT_TYPE_id= '"
													+ id + "'", null);
							if (mCursor != null) {
								mCursor.moveToFirst();
								name = mCursor.getString(mCursor.getColumnIndex(VISIT_TYPE_DESCRIPTION));
							}
							mCursor.close();
				}catch (SQLiteException se) {
					 Log.v(" getNameVisitType Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" getNameVisitType Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
				return name;
			}
			
	//---------------------------------------COMPLAINT STATUS----------------------------------------------------------//
			public ArrayList<String> getDataComplaintStatus() {
				ArrayList<String> allData = new ArrayList<String>();
				Cursor cursor = null;
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					cursor = db.query(TM_COMPLAINT_STATUS , new String[] { COMPLAINT_STATUS_ID ,
							COMPLAINT_STATUS_DESCRIPTION , COMPLAINT_STATUS_CREATED_BY , COMPLAINT_STATUS_CREATED_TIME ,
							COMPLAINT_STATUS_UPDATE_BY , COMPLAINT_STATUS_UPDATE_TIME  }, null, null, null, null, null);
	
					cursor.moveToFirst();
					while (!cursor.isAfterLast()) {
						allData.add(cursor.getString(1));
						cursor.moveToNext();
					}
	
					cursor.close();
				}catch (SQLiteException se) {
					 Log.v(" getDataComplaintStatus Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" getDataComplaintStatus Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
				return allData;
			}

			
			//membuat array pada table layout
			// get id kelas
			public String getIdComplaintStatus(String name) {
				String id = "";
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db.rawQuery(
							"SELECT  COMPLAINT_STATUS_id  FROM  TM_COMPLAINT_STATUS WHERE COMPLAINT_STATUS_description= '"
									+ name + "'", null);
					if (mCursor != null) {
						mCursor.moveToFirst();
						id = mCursor.getString(0);
					}
					mCursor.close();
				}catch (SQLiteException se) {
					 Log.v(" getIdComplaintStatus Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" getIdComplaintStatus Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
				return id;
			}

			// get name kelas
			public String getNameComplaintStatus(String id) {
				String name = "";
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db.rawQuery(
									"SELECT  COMPLAINT_STATUS_description  FROM  TM_COMPLAINT_STATUS WHERE COMPLAINT_STATUS_id= '"
											+ id + "'", null);
					if (mCursor != null) {
						mCursor.moveToFirst();
						name = mCursor.getString(mCursor.getColumnIndex(COMPLAINT_STATUS_DESCRIPTION));
					}
					mCursor.close();
				}catch (SQLiteException se) {
					 Log.v(" getNameComplaintStatus Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" getNameComplaintStatus Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
				return name;
			}
			
			public String getIdComp(String name) {
			    String id="";
			    SQLiteDatabase db = this.getReadableDatabase();
			    try{
			     Cursor mCursor = db.rawQuery(
			       "SELECT  COMPLAINT_id  FROM  TM_COMPLAINT  where COMPLAINT_description like '%"+name+"%'" , null);
			     if (mCursor != null) {
			      mCursor.moveToFirst();
			      id=mCursor.getString(0);
			     }
			     mCursor.close();
			    }catch (SQLiteException se) {
			      Log.v(" getIdImageType Exception",Log.getStackTraceString(se));
			    } catch (Exception e) {
			     Log.v(" getIdImageType Exception",Log.getStackTraceString(e));
			    } finally {
			     db.close();
			    }


			    return id;
			   }
			//to search complaint and action
			   public ArrayList<Visit_Model> searchComplaintAction(String id_comp, String id_anak, String tindakan) {
			    ArrayList<Complaint_Model> prseDataComplaint = new ArrayList<Complaint_Model>();
			    
			    ArrayList<Visit_Model> data = new ArrayList<Visit_Model>();
			    ArrayList<String> CreatTime = new ArrayList<String>();
			    ArrayList<String> vis_typ_id = new ArrayList<String>();
			    Visit_Model model = new Visit_Model();
			    SQLiteDatabase db = this.getReadableDatabase();
			    
			    String tvStat = null;
			    int j;
			    
			    ArrayList<String> all_date = new ArrayList<String>();
			    try{
			     
			     Cursor mCursor = db
			       .rawQuery(
			         "SELECT * FROM  TR_VISIT  where VISIT_complaint_id = '"+id_comp+"' and VISIT_child_id = '"+id_anak+"' or VISIT_action like '%"+tindakan+"%'" , null);
			     mCursor.moveToFirst();
			     while (!mCursor.isAfterLast()) {
			      prseDataComplaint.add(parseDataComplaintModel(mCursor));
			      CreatTime.add(mCursor.getString(mCursor.getColumnIndex(VISIT_CREATE_TIME )));
			      vis_typ_id.add(mCursor.getString(mCursor.getColumnIndex(VISIT_VISIT_TYPE_ID )));
			      model.setComplaints(prseDataComplaint);
			      model.setCreatTime(CreatTime);
			      model.setVisTypId(vis_typ_id);
			      model.setChildID(mCursor.getString(mCursor.getColumnIndex(VISIT_CHILD_ID )));
			      model.setVisitTypeID(mCursor.getString(mCursor.getColumnIndex(VISIT_VISIT_TYPE_ID)));
			      model.setVisitDate(mCursor.getString(mCursor.getColumnIndex(VISIT_DATE)));
			      data.add(model);
			      
			      mCursor.moveToNext();
			     }
			 
			     mCursor.close();
			     
			    }catch (SQLiteException se) {
			      Log.v(" getNameComplaint Exception",Log.getStackTraceString(se));
			    
			    } catch (Exception e) {
			     Log.v(" getNameComplaint Exception",Log.getStackTraceString(e));
			    } finally {
			     db.close();
			    }
			    return data;
			    
			   }
			
	//--------------------------------------------COMPLAINT -----------------------------------------------------//
			public ArrayList<String> getDataComplaint() {
				ArrayList<String> allData = new ArrayList<String>();
				Cursor cursor = null;
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					cursor = db.query(TM_COMPLAINT , new String[] { COMPLAINT_ID ,
							COMPLAINT_DESCRIPTION }, null, null, null, null, null);
	
					cursor.moveToFirst();
					while (!cursor.isAfterLast()) {
						allData.add(cursor.getString(1));
						cursor.moveToNext();
					}
	
					cursor.close();
				}catch (SQLiteException se) {
					 Log.v(" getDataComplaint Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" getDataComplaint Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
				return allData;
			}

			
			public String getIdComplaint(String name) {
				String id = "";
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db.rawQuery(
							"SELECT  COMPLAINT_id  FROM  TM_COMPLAINT WHERE COMPLAINT_description= '"
									+ name + "'", null);
					if (mCursor != null) {
						mCursor.moveToFirst();
						id = mCursor.getString(0);
					}
					mCursor.close();
				}catch (SQLiteException se) {
					 Log.v(" getIdComplaint Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" getIdComplaint Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}


				return id;
			}

			// get name kelas
			public String getNameComplaint(String id) {
				String name = "";
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db
							.rawQuery(
									"SELECT  COMPLAINT_description  FROM  TM_COMPLAINT WHERE COMPLAINT_id= '"
											+ id + "'", null);
					if (mCursor != null) {
						mCursor.moveToFirst();
						name = mCursor.getString(mCursor.getColumnIndex(COMPLAINT_DESCRIPTION));
					}
					mCursor.close();
				}catch (SQLiteException se) {
					 Log.v(" getNameComplaint Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" getNameComplaint Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
				return name;
			}
			
		//---------------------------------------ENVIRONMENT----------------------------------------------------------//
			public String getIdEnvironment(String name) {
				String id = "";
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db.rawQuery(
							"SELECT  ENVIRONMENT_id  FROM  TM_ENVIRONMENT WHERE ENVIRONMENT_description= '"
									+ name + "'", null);
					if (mCursor != null) {
						mCursor.moveToFirst();
						id = mCursor.getString(0);
					}
					mCursor.close();
				}catch (SQLiteException se) {
					 Log.v(" getIdEnvironment Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" getIdEnvironment Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}


				return id;
			}

			// get name kelas
			public String getNameEnvironment(String id) {
				String name = "";
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db
							.rawQuery(
									"SELECT  ENVIRONMENT_description  FROM  TM_ENVIRONMENT WHERE ENVIRONMENT_id= '"
											+ id + "'", null);
					if (mCursor != null) {
						mCursor.moveToFirst();
						name = mCursor.getString(mCursor.getColumnIndex(ENVIRONMENT_DESCRIPTION ));
					}
					mCursor.close();
				}catch (SQLiteException se) {
					 Log.v(" getNameEnvironment Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" getNameEnvironment Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
				return name;
			}
			
		//---------------------------------------IMAGE TYPE----------------------------------------------------------//
			
			public String getIdImageType(String name) {
				String id = "";
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db.rawQuery(
							"SELECT  IMAGE_TYPE_id  FROM  TM_IMAGE_TYPE  WHERE IMAGE_TYPE_description= '"
									+ name + "'", null);
					if (mCursor != null) {
						mCursor.moveToFirst();
						id = mCursor.getString(0);
					}
					mCursor.close();
				}catch (SQLiteException se) {
					 Log.v(" getIdImageType Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" getIdImageType Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}


				return id;
			}

			// get name kelas
			public String getNameImageType(String id) {
				String name = "";
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db
							.rawQuery(
									"SELECT  IMAGE_TYPE_description  FROM  TM_IMAGE_TYPE  WHERE IMAGE_TYPE_id= '"
											+ id + "'", null);
					if (mCursor != null) {
						mCursor.moveToFirst();
						name = mCursor.getString(mCursor.getColumnIndex(IMAGE_TYPE_DESCRIPTION  ));
					}
					mCursor.close();
				}catch (SQLiteException se) {
					 Log.v(" getNameImageType Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" getNameImageType Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
				return name;
			}
		//---------------------------------------IMAGE----------------------------------------------------------//
			public void InsertPhoto(
					String image_name,
					String image_date, 
					String image_id, 
					String image_path, 
					String image_server_path, 
					String image_type_id, 
					String image_longitude,
					String image_latitude,
					String cb, String ct)
			{
				 SQLiteDatabase db = this.getWritableDatabase();
				  try {
						ContentValues values = new ContentValues();
						values.put(IMAGE_NAME  , image_name);
						values.put(IMAGE_DATE  , image_date);
						values.put(IMAGE_ID  , image_id);
						values.put(IMAGE_PATH , image_path);
						values.put(IMAGE_SERVER_PATH , image_server_path);
						values.put(IMAGE_IMAGE_TYPE_ID   , image_type_id);
						values.put(IMAGE_LONGITUDE   , image_longitude);
						values.put(IMAGE_LATITUDE   , image_latitude);
						values.put(IMAGE_CREATED_BY  , cb);
						values.put(IMAGE_CREATED_TIME , ct);
						db.insert(TR_IMAGE , null, values);
						
						Log.e("InsertPhoto ke tabel", "image name "+image_name+" date "+image_date+" image_id "+image_id+" image_path "+image_path+" server_path "+image_server_path+" image_type_id "+image_type_id+" image_longitude "+image_longitude+" image_latitude "+image_latitude+" cb "+cb+" ct "+ct);
						
						db.close();
				  }
				  catch (SQLiteException se) {
						 Log.v("InsertPhoto",Log.getStackTraceString(se));
				  } catch (Exception e) {
						Log.v("InsertPhoto",Log.getStackTraceString(e));
				  } finally {
						db.close();
				  }
			  }
			
			public void DeletePhoto(ArrayList<Image_Model> foto)
			{
				SQLiteDatabase db=this.getWritableDatabase();
				try {
					for(Image_Model mdl : foto)
					{
						db.delete(TR_IMAGE, IMAGE_NAME + " =? and IMAGE_date='"+ mdl.GetImage_date() + "' and IMAGE_id='"+ mdl.GetImage_id() + "' and IMAGE_image_type_id='"+ mdl.GetImage_type_id()+ "'", new String[] {mdl.GetImage_name()});
					}
					// insert the new
				} catch (Exception e) {
					Log.e("Eror delete fasilitas anak", e.toString());
				}finally
				{
					db.close();
				}
			}
			
			public void UpdatePhoto(
					String image_name,
					String image_date, 
					String image_id, 
					String image_path, 
					String image_server_path, 
					String image_type_id, 
					String image_longitude,
					String image_latitude,
					String ub, String ut, String cb, String ct)
			{
				 SQLiteDatabase db = this.getWritableDatabase();
				  try {
						ContentValues values = new ContentValues();
						values.put(IMAGE_NAME  , image_name);
						values.put(IMAGE_DATE  , image_date);
						values.put(IMAGE_ID  , image_id);
						values.put(IMAGE_PATH , image_path);
						values.put(IMAGE_SERVER_PATH , image_server_path);
						values.put(IMAGE_IMAGE_TYPE_ID   , image_type_id);
						values.put(IMAGE_LONGITUDE   , image_longitude);
						values.put(IMAGE_LATITUDE   , image_latitude);
						values.put(IMAGE_UPDATE_BY  , ub);
						values.put(IMAGE_UPDATE_TIME , ut);
						values.put(IMAGE_CREATED_BY, cb);
						values.put(IMAGE_CREATED_TIME, ct);
						db.insert(TR_IMAGE , null, values);
						
						Log.e("Update ke tabel", "image name "+image_name+" date "+image_date+" image_id "+image_id+" image_path "+image_path+" server_path "+image_server_path+" image_type_id "+image_type_id+" image_longitude "+image_longitude+" image_latitude "+image_latitude+" ub "+ub+" ut "+ut);
						
						
				  }
				  catch (SQLiteException se) {
						 Log.v("InsertPhoto",Log.getStackTraceString(se));
				  } catch (Exception e) {
						Log.v("InsertPhoto",Log.getStackTraceString(e));
				  } finally {
						db.close();
				  }
			  }
			
			
			//belum menangani update
			public ArrayList<Image_Model> GetAllPathFotoKunjunganAnak(String image_date, String image_id)
			{
				ArrayList<Image_Model> photo = new ArrayList<Image_Model>();
				
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db.rawQuery("SELECT  *  FROM  TR_IMAGE  WHERE IMAGE_date= '"+ image_date + "' and IMAGE_id= '"+image_id+ "' and IMAGE_image_type_id= '1'", null);
					if (mCursor != null) {
						mCursor.moveToFirst();
						while (!mCursor.isAfterLast()) {
							photo.add(parseDataImageModel(mCursor));
							mCursor.moveToNext();
						}
					}
					mCursor.close();
				}catch (SQLiteException se) {
					 Log.v(" GetAllPathFotoKunjunganAnak Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" GetAllPathFotoKunjunganAnak Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
				return photo;
			}
			
						
			public ArrayList<Image_Model> GetAllPathFotoRumahAnak(String image_date, String image_id)
			{
				ArrayList<Image_Model> photo = new ArrayList<Image_Model>();
				
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db.rawQuery(
									"SELECT  *  FROM  TR_IMAGE  WHERE IMAGE_date= '"+ image_date + "' and IMAGE_id= '"+image_id+ "' and IMAGE_image_type_id= '2'", null);
					mCursor.moveToFirst();
					if (mCursor != null) {
						while (!mCursor.isAfterLast()) {
							photo.add(parseDataImageModel(mCursor));
							mCursor.moveToNext();
						}
					}
					mCursor.close();
				}catch (SQLiteException se) {
					 Log.v(" GetAllPathFotoKunjunganAnak Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" GetAllPathFotoKunjunganAnak Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
				return photo;
			}
			
			public ArrayList<Image_Model> GetAllPathFoto(String image_date, String image_id)
			{
				ArrayList<Image_Model> photo = new ArrayList<Image_Model>();
				
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db.rawQuery("SELECT IMAGE_name,IMAGE_date,IMAGE_id,IMAGE_path,IMAGE_server_path,IMAGE_image_type_id,IMAGE_longitude,IMAGE_latitude   FROM  TR_IMAGE  WHERE IMAGE_date= '"+ image_date + "' and IMAGE_id= '"+image_id+ "'", null);
					
					if (mCursor != null ) {
						 mCursor.moveToFirst() ;
						while (!mCursor.isAfterLast()) {
							photo.add(parseDataImageModel(mCursor));
							mCursor.moveToNext();
						}
					}
					mCursor.close();
				}catch (SQLiteException se) {
					 Log.v(" GetAllPathFoto Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" GetAllPathFoto Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
				return photo;
				
			}
			
			public Image_Model GetPathFoto(String image_date, String image_id)
			{
				Image_Model photo = new Image_Model();
				
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db.rawQuery("SELECT  *  FROM  TR_IMAGE  WHERE IMAGE_date= '"+ image_date + "' and IMAGE_id= '"+image_id+ "'", null);
					
					if (mCursor != null ) {
						 mCursor.moveToFirst() ;
						 photo.SetImagePath(mCursor.getString(mCursor.getColumnIndex(IMAGE_PATH)));
						 //photo = mCursor.getString(mCursor.getColumnIndex(IMAGE_PATH));
						
					}
					mCursor.close();
				}catch (SQLiteException se) {
					 Log.v(" GetPathFoto Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" GetPathFoto Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
				return photo;
				
			}
			
			public Image_Model parseDataImageModel(Cursor cur)
			{
				Image_Model data  = new Image_Model();
				data.SetImageDate(cur.getString(1));
				data.SetImageId(cur.getString(2));
				data.SetImageLatitude(cur.getString(7));
				data.SetImageLongitude(cur.getString(6));
				data.SetImageName(cur.getString(0));
				data.SetImagePath(cur.getString(3));
				data.SetImageServerPath(cur.getString(4));
				data.SetImageTypeId(cur.getString(5));
				return data;
			} 

			
			

		
		
		//---------------------------------------VISIT----------------------------------------------------------//
			//simpan kunjungan anak
			public void SimpanKunjunganAnak(String id_child, String complaint_id, String complaint_status_id, String visit_date, String visit_type_id, String drug_taken, String height,
					String lila, String weight, String action, String note, String reminder_id, String cb, String ct,String bmi_score)
			{
				SQLiteDatabase db = this.getWritableDatabase();
				
				try{	
					
					ContentValues val = new ContentValues();
					val.put(VISIT_CHILD_ID , id_child);
					val.put(VISIT_COMPLAINT_ID , complaint_id);
					val.put(VISIT_COMPLAINT_STATUS_ID, complaint_status_id);
					val.put(VISIT_DATE , visit_date);
					val.put(VISIT_VISIT_TYPE_ID , visit_type_id);
					val.put(VISIT_DRUG_TAKEN , drug_taken);
					val.put(VISIT_HEIGHT , height);
					val.put(VISIT_LILA , lila);
					val.put(VISIT_WEIGHT, weight);
					val.put(VISIT_ACTION , action);	
					val.put(VISIT_NOTE , note);
					val.put(VISIT_REMINDER_ID, reminder_id);
					val.put(VISIT_CREATE_BY ,  cb);
					val.put(VISIT_CREATE_TIME , ct);
					val.put(VISIT_BMI_SCORE , bmi_score);
					
					db.insert(TR_VISIT , null, val);
				}catch (SQLiteException se) {
					 Log.v(" SimpanKunjunganAnak Exception",Log.getStackTraceString(se));
				}catch (Exception e) {
					Log.v(" SimpanKunjunganAnak Exception",Log.getStackTraceString(e));
				}finally {
					db.close();
				}
			}
			public int CountAllVisit(String id_child)
			{
				int i = 0;
				SQLiteDatabase db = this.getWritableDatabase();
				
				try{
					Cursor mCursor = db.rawQuery(
							"SELECT *  FROM  TR_VISIT WHERE VISIT_child_id= '"+ id_child + "' ", null);
					if (mCursor != null ) {
						mCursor.moveToFirst();
						while (!mCursor.isAfterLast()) {
							i++;
							mCursor.moveToNext();
						}
					}
				
				}catch (SQLiteException se) {
					 Log.v(" SimpanKunjunganAnak Exception",Log.getStackTraceString(se));
				}catch (Exception e) {
					Log.v(" SimpanKunjunganAnak Exception",Log.getStackTraceString(e));
				}finally {
					db.close();
				}
				return i;
			}
			
			public ArrayList<String> getAllVisitDate(String id_child) {
			     ArrayList<String> data = new ArrayList<String>();
			     SQLiteDatabase db = this.getReadableDatabase();
			     try{
			        Cursor mCursor = db.rawQuery("SELECT VISIT_date  FROM  TR_VISIT WHERE VISIT_child_id= '"+id_child+ "'", null);
			         if (mCursor != null) {
			           mCursor.moveToFirst();
			           while (!mCursor.isAfterLast()) {
			            data.add(mCursor.getString(mCursor.getColumnIndex(VISIT_DATE)));
			           
			            
			            mCursor.moveToNext();
			           }
			         }
			          mCursor.close();
			        }catch (SQLiteException se) {
			          Log.v(" get8VisitDate Exception",Log.getStackTraceString(se));
			        } catch (Exception e) {
			         Log.v(" get8VisitDate Exception",Log.getStackTraceString(e));
			        } finally {
			         db.close();
			        }
			     Collections.reverse(data);
			     if(data.size()>8)
			          {
			           int lastIndex = data.size() - 1;
			         data.remove(lastIndex);
			          }
			        return data;
			   }
			
			//delete kunjungan anak
			public void DeleteKunjunganAnak(String id_child, String visit_date, ArrayList<Complaint_Model> complaints)
			{
				SQLiteDatabase db=this.getWritableDatabase();
				try {
					for(Complaint_Model mdl : complaints)
					{
						db.delete(TR_VISIT, VISIT_CHILD_ID + " = ? AND VISIT_date= '"+ visit_date + "' AND  VISIT_complaint_id='"+ mdl.GetKeluhan() + "'",
								new String[] { id_child });
						Log.e("Deleting Kunjungan Lama","");
					}
					
					db.close();
					// insert the new
				} catch (Exception e) {
					Log.e("Eror DeleteKunjunganAnak", e.toString());
				}
			}
			
			public Visit_Model GetSpesificVisit(String id_child, String visit_date)
			{
				Log.e("Get Latest Visit", "true");
				ArrayList<Complaint_Model> arr_complaint_model = new ArrayList<Complaint_Model>();
				ArrayList<Visit_Facility> arr_fasilitas_anak = new ArrayList<Visit_Facility>();
				ArrayList<Image_Model> arr_foto = new ArrayList<Image_Model>();
				ArrayList<Environment_Model> arr_env = new ArrayList<Environment_Model>();
				Image_Model img = new Image_Model();
				Visit_Model model = new Visit_Model();
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					
					Cursor mCursor = db.rawQuery(
							"SELECT *  FROM  TR_VISIT WHERE VISIT_child_id= '"+ id_child + "' AND VISIT_date= '"+ visit_date + "'", null);
					if (mCursor != null ) {
						mCursor.moveToFirst();
						
						//parse data
						model.setVisitDate(mCursor.getString(mCursor.getColumnIndex(VISIT_DATE )));
						model.setChildID(mCursor.getString(mCursor.getColumnIndex(VISIT_CHILD_ID )));
						model.setVisitTypeID(mCursor.getString(mCursor.getColumnIndex(VISIT_VISIT_TYPE_ID)));
						model.setNote(mCursor.getString(mCursor.getColumnIndex(VISIT_NOTE )));
						model.setHeight(mCursor.getString(mCursor.getColumnIndex(VISIT_HEIGHT )));
						model.setWeight(mCursor.getString(mCursor.getColumnIndex(VISIT_WEIGHT )));
						model.setLILA(mCursor.getString(mCursor.getColumnIndex(VISIT_LILA )));
						model.setBMIScore(mCursor.getString(mCursor.getColumnIndex(VISIT_BMI_SCORE )));
						model.setARVTaken(mCursor.getString(mCursor.getColumnIndex(VISIT_DRUG_TAKEN )));
						model.setReminder_ID(mCursor.getString(mCursor.getColumnIndex(VISIT_REMINDER_ID )));
						mCursor.close();
						//get all keluhan
						arr_complaint_model = GetAllComplaintAnak(model.GetChildID(),model.GetVisitDate());
						model.setComplaints(arr_complaint_model);
						//get all fasilitas
						arr_fasilitas_anak = GetAllFasilitasAnak(model.GetVisitDate(), model.GetChildID());
						model.setFasilitasKunjungan(arr_fasilitas_anak);
						//get all foto anak
						arr_foto = GetAllPathFoto(model.GetVisitDate(), model.GetChildID());
						model.setPhotos(arr_foto);
						//get environment model
						arr_env = GetEnvironmentVisitChild(model.GetVisitDate(), model.GetChildID());
						model.setEnvironments(arr_env);
						
					}
					else
					{
						model.setVisitDate("00-00-00");
						model.setChildID(id_child);
						model.setNote("-");
						model.setHeight("-");
						model.setWeight("-");
						model.setLILA("-");
						model.setBMIScore("-");
						model.setARVTaken("-");
						model.setReminder_ID("-");
						model.setBMIScore("Kosong");
						
						//keluhan
						Complaint_Model a = new Complaint_Model();
						a.SetKeluhan("kosong");a.SetStatusKeluhan("kosong");a.SetTindakan("kosong");arr_complaint_model.add(a);
						Complaint_Model b = new Complaint_Model();
						b.SetKeluhan("kosong");b.SetStatusKeluhan("kosong");b.SetTindakan("kosong");arr_complaint_model.add(b);
						Complaint_Model c = new Complaint_Model();
						c.SetKeluhan("kosong");c.SetStatusKeluhan("kosong");c.SetTindakan("kosong");arr_complaint_model.add(c);
						//foto
						img.SetImagePath("");arr_foto.add(img);
						model.setPhotos(arr_foto);
						
						
					}
				}catch (SQLiteException se) {
					 Log.v(" GetLatestVisit Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" GetLatestVisit Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
				return model;
			}
			
			
			
			
			//update kunjungan anak
			public void UpdateToTableVisit(String id_child, String complaint_id, String complaint_status_id, String visit_date, String visit_type_id, String drug_taken, String height,
					String lila, String weight, String action, String note, String reminder_id, String cb, String ct, String bmi_score, String ub, String ut)
			{
				SQLiteDatabase db = this.getWritableDatabase();
				
				try{	
					ContentValues val = new ContentValues();
					val.put(VISIT_COMPLAINT_ID , complaint_id);
					val.put(VISIT_COMPLAINT_STATUS_ID, complaint_status_id);
					val.put(VISIT_VISIT_TYPE_ID , visit_type_id);
					val.put(VISIT_DRUG_TAKEN , drug_taken);
					val.put(VISIT_HEIGHT , height);
					val.put(VISIT_LILA , lila);
					val.put(VISIT_WEIGHT, weight);
					val.put(VISIT_ACTION , action);
					val.put(VISIT_NOTE , note);
					val.put(VISIT_REMINDER_ID, reminder_id);
					val.put(VISIT_CREATE_BY ,  cb);
					val.put(VISIT_CREATE_TIME , ct);
					val.put(VISIT_BMI_SCORE , bmi_score);
					val.put(VISIT_UPDATE_BY, ub);
					val.put(VISIT_UPDATE_TIME, ut);
					val.put(VISIT_CREATE_BY, cb);
					val.put(VISIT_CREATE_TIME, ct);
					db.insert(TR_VISIT , null, val);
					
					
					
					//db.update(TR_VISIT, val, ("VISIT_child_id = ? AND VISIT_date = "+ visit_date),new String[] { id_child });
					
				}catch (SQLiteException se) {
					 Log.v(" UpdateToTableVisit Exception",Log.getStackTraceString(se));
				}catch (Exception e) {
					Log.v(" UpdateToTableVisit Exception",Log.getStackTraceString(e));
				}finally {
					db.close();
				}
			}
			
			//getkunjungan anak terakhir
			public Visit_Model GetLatestVisit(String id_child)
			{
				Log.e("Get Latest Visit", "true");
				ArrayList<Complaint_Model> arr_complaint_model = new ArrayList<Complaint_Model>();
				ArrayList<Visit_Facility> arr_fasilitas_anak = new ArrayList<Visit_Facility>();
				ArrayList<Image_Model> arr_foto = new ArrayList<Image_Model>();
				ArrayList<Environment_Model> arr_env = new ArrayList<Environment_Model>();
				Image_Model img = new Image_Model();
				Visit_Model model = new Visit_Model();
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					
					Cursor mCursor = db.rawQuery(
							"SELECT *  FROM  TR_VISIT WHERE VISIT_child_id= '"+ id_child + "' ", null);
					if (mCursor != null && mCursor.moveToFirst()) {
						mCursor.moveToLast();
						
						//parse data
						model.setVisitDate(mCursor.getString(mCursor.getColumnIndex(VISIT_DATE )));
						model.setChildID(mCursor.getString(mCursor.getColumnIndex(VISIT_CHILD_ID )));
						model.setVisitTypeID(mCursor.getString(mCursor.getColumnIndex(VISIT_VISIT_TYPE_ID)));
						model.setNote(mCursor.getString(mCursor.getColumnIndex(VISIT_NOTE )));
						model.setHeight(mCursor.getString(mCursor.getColumnIndex(VISIT_HEIGHT )));
						model.setWeight(mCursor.getString(mCursor.getColumnIndex(VISIT_WEIGHT )));
						model.setLILA(mCursor.getString(mCursor.getColumnIndex(VISIT_LILA )));
						model.setBMIScore(mCursor.getString(mCursor.getColumnIndex(VISIT_BMI_SCORE )));
						model.setARVTaken(mCursor.getString(mCursor.getColumnIndex(VISIT_DRUG_TAKEN )));
						model.setReminder_ID(mCursor.getString(mCursor.getColumnIndex(VISIT_REMINDER_ID )));
						mCursor.close();
						//get all keluhan
						arr_complaint_model = GetAllComplaintAnak(model.GetChildID(),model.GetVisitDate());
						model.setComplaints(arr_complaint_model);
						//get all fasilitas
						arr_fasilitas_anak = GetAllFasilitasAnak(model.GetVisitDate(), model.GetChildID());
						model.setFasilitasKunjungan(arr_fasilitas_anak);
						//get all foto anak
						arr_foto = GetAllPathFoto(model.GetVisitDate(), model.GetChildID());
						model.setPhotos(arr_foto);
						//get environment model
						arr_env = GetEnvironmentVisitChild(model.GetVisitDate(), model.GetChildID());
						model.setEnvironments(arr_env);
						
					}
					else
					{
						model.setVisitDate("00-00-00");
						model.setChildID(id_child);
						model.setNote("-");
						model.setHeight("-");
						model.setWeight("-");
						model.setLILA("-");
						model.setBMIScore("-");
						model.setARVTaken("-");
						model.setReminder_ID("-");
						model.setBMIScore("Kosong");
						
						//keluhan
						Complaint_Model a = new Complaint_Model();
						a.SetKeluhan("kosong");a.SetStatusKeluhan("kosong");a.SetTindakan("kosong");arr_complaint_model.add(a);
						Complaint_Model b = new Complaint_Model();
						b.SetKeluhan("kosong");b.SetStatusKeluhan("kosong");b.SetTindakan("kosong");arr_complaint_model.add(b);
						Complaint_Model c = new Complaint_Model();
						c.SetKeluhan("kosong");c.SetStatusKeluhan("kosong");c.SetTindakan("kosong");arr_complaint_model.add(c);
						//foto
						img.SetImagePath("");arr_foto.add(img);
						model.setPhotos(arr_foto);
						
						
					}
				}catch (SQLiteException se) {
					 Log.v(" GetLatestVisit Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" GetLatestVisit Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
				return model;
			}
			
			//get all complaint
			public ArrayList<Complaint_Model> GetAllComplaintAnak(String id_child, String visit_date)
			{
				ArrayList<Complaint_Model> arr = new ArrayList<Complaint_Model>();
				Complaint_Model complaint = new Complaint_Model();
				SQLiteDatabase db = this.getReadableDatabase();
				try{
					Cursor mCursor = db.rawQuery(
							"SELECT *  FROM  TR_VISIT WHERE VISIT_child_id= '"+ id_child + "' and VISIT_date= '"+visit_date+"'", null);
					if (mCursor != null) {
						mCursor.moveToFirst();
						while (!mCursor.isAfterLast()) {
							
							Log.e("Retrieve complaints", "keluhan"+complaint.GetKeluhan()+" status "+complaint.GetStatusKeluhan()+" tindakan "+complaint.GetTindakan());
							arr.add(parseDataComplaintModel(mCursor));
							mCursor.moveToNext();
						}
					}
					mCursor.close();
				}catch (SQLiteException se) {
					 Log.v(" GetAllComplaintAnak Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" GetAllComplaintAnak Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
				return arr;
				
			}
			
			public Complaint_Model parseDataComplaintModel(Cursor cur)
			{
				Complaint_Model data  = new Complaint_Model();
				data.SetKeluhan(cur.getString(cur.getColumnIndex(VISIT_COMPLAINT_ID )));
				data.SetStatusKeluhan(cur.getString(cur.getColumnIndex(VISIT_COMPLAINT_STATUS_ID)));
				data.SetTindakan(cur.getString(cur.getColumnIndex(VISIT_ACTION )));
				return data;
			}
			
			 public void InsertBMIScore(String child_id, String bmi_score) {
			     SQLiteDatabase db = this.getWritableDatabase();
			      try {
			      ContentValues values = new ContentValues();
			      values.put(VISIT_CHILD_ID , child_id);
			      values.put(VISIT_BMI_SCORE , bmi_score);
			      
			      db.insert(TR_VISIT, null, values);
			      Log.v("Insert tabel TR_VISIT", "child_id " + child_id
			         + " child_id " + child_id + " bmi_score "
			         + bmi_score);
			      db.close();
			      }
			      catch (SQLiteException se) {
			       Log.v("InsertBMIScore",Log.getStackTraceString(se));
			      } catch (Exception e) {
			      Log.v("InsertBMIScore",Log.getStackTraceString(e));
			      } finally {
			      db.close();
			     }
			   }
			
			 public ArrayList<Visit_Model> get8VisitDate(String id_child) {
			     ArrayList<Visit_Model> data = new ArrayList<Visit_Model>();
			     SQLiteDatabase db = this.getReadableDatabase();
			     try{
			        Cursor mCursor = db.rawQuery("SELECT DISTINCT VISIT_date, VISIT_child_id, VISIT_visit_type_id  FROM  TR_VISIT WHERE VISIT_child_id= '"+id_child+ "'", null);
			         if (mCursor != null) {
			           mCursor.moveToFirst();
			           while (!mCursor.isAfterLast()) {
			            data.add(parseDataVisit(mCursor));
			            mCursor.moveToNext();
			           }
			         }
			          mCursor.close();
			        }catch (SQLiteException se) {
			          Log.v(" get8VisitDate Exception",Log.getStackTraceString(se));
			        } catch (Exception e) {
			         Log.v(" get8VisitDate Exception",Log.getStackTraceString(e));
			        } finally {
			         db.close();
			        }
			        return data;
			   }
			 
			public Visit_Model parseDataVisit(Cursor c)
			{
				 Visit_Model cur = new Visit_Model();
				 cur.setVisitDate(c.getString(c.getColumnIndex(VISIT_DATE )));
				 cur.setChildID(c.getString(c.getColumnIndex(VISIT_CHILD_ID )));
				 cur.setVisitTypeID(c.getString(c.getColumnIndex(VISIT_VISIT_TYPE_ID)));
				    
				 return cur;
		    }
			
			//search keluhan tindakan dan status berdasarkan anak
			public ArrayList<Visit_Model> SearchKeluhan(String complaint_status_id,String action,String child_id) {
			    ArrayList<Visit_Model> data = new ArrayList<Visit_Model>();
			    ArrayList<Complaint_Model> data1 = new ArrayList<Complaint_Model>();
			    Complaint_Model curData = new Complaint_Model();
			    Visit_Model curData1 = new Visit_Model();
			    SQLiteDatabase db = this.getReadableDatabase();
			    try{
			     Cursor mCursor = db.rawQuery(
			         "SELECT  VISIT_complaint_id,VISIT_complaint_status_id,VISIT_action,VISIT_create_time FROM TR_VISIT WHERE VISIT_child_id= '"
			           + child_id + "' AND (VISIT_complaint_id LIKE '"
			           + complaint_status_id + "' OR VISIT_action LIKE '"
			           +  action + "')"
			           , null);
			     if (mCursor != null) {
			      mCursor.moveToFirst();
			      while (!mCursor.isAfterLast()) {
				       // parse data into model
				       curData.SetKeluhan(mCursor.getString(mCursor.getColumnIndex(VISIT_COMPLAINT_ID )));
				       curData.SetStatusKeluhan(mCursor.getString(mCursor.getColumnIndex(VISIT_COMPLAINT_STATUS_ID )));
				       curData.SetTindakan(mCursor.getString(mCursor.getColumnIndex(VISIT_ACTION )));
				       
				       data1.add(curData);
				       curData1.setComplaints(data1);
				       
				       data.add(curData1);
				       mCursor.moveToNext();
			      }
			     }
			      mCursor.close();
			    }catch (SQLiteException se) {
			      Log.v(" SearchKeluhan Exception",Log.getStackTraceString(se));
			    } catch (Exception e) {
			     Log.v(" SearchKeluhan Exception",Log.getStackTraceString(e));
			    } finally {
			     db.close();
			    }
			    return data;
			 }
			public Visit_Model getBMITwoLatestVisit(String id_child) {
				   Visit_Model curData1 = new Visit_Model();
				   SQLiteDatabase db = this.getReadableDatabase();
				   try{
				        Cursor mCursor = db.rawQuery(
				            "SELECT * FROM TR_VISIT WHERE VISIT_child_id ='" +  id_child + "'", null);
				        if (mCursor!=null) {
					         mCursor.moveToLast();
					         mCursor.moveToPrevious();
					        	 curData1.setVisitDate(mCursor.getString(mCursor.getColumnIndex(VISIT_DATE)));
					        	 curData1.setBMIScore(mCursor.getString(mCursor.getColumnIndex(VISIT_BMI_SCORE)));
				         }
				        else
				        {
				        	 curData1.setVisitDate("Kosong");
				        	 curData1.setBMIScore("Kosong");
				        }
				       }catch (SQLiteException se) {
				         Log.v(" getBMITwoLatestVisit Exception",Log.getStackTraceString(se));
				       } catch (Exception e) {
				        Log.v(" getBMITwoLatestVisit Exception",Log.getStackTraceString(e));
				       } finally {
				        db.close();
				       }
				       
				       return curData1;
				       
				   }

		
		//---------------------------------------VISIT ENV----------------------------------------------------------//
			// insert to tabel tr visit environment   
			   public void InsertEnvironmentScore(String visit_date, String child_id,String environment_id,String enviroment_score)
			   {
				     SQLiteDatabase db = this.getWritableDatabase();
				      try {
					      ContentValues values = new ContentValues();
					      values.put(VE_VISIT_DATE , visit_date);
					      values.put(VE_CHILD_ID , child_id);
					      values.put(VE_ENVIRONMENT_ID , environment_id);
					      values.put(VE_ENVIRONMENT_SCORE , enviroment_score);
					      db.insert(TR_VISIT_ENVIRONMENT , null, values);
					      db.close();
				      }
				      catch (SQLiteException se) {
				    	  Log.v("InsertEnvironmentScore",Log.getStackTraceString(se));
				      } catch (Exception e) {
				    	  Log.v("InsertEnvironmentScore",Log.getStackTraceString(e));
				      } finally {
				    	  db.close();
				      }
			     }
			   
			   public int CountALLEnvironmentScore(String id_child, String visit_date) {
				   int score = 0;
				   String sc = "";
				   SQLiteDatabase db = this.getReadableDatabase();
				   try{
					   Cursor mCursor = db.rawQuery(
							   "SELECT  VE_environment_score  FROM  TR_VISIT_ENVIRONMENT WHERE VE_visit_date= '"+ visit_date + "' and VE_child_id= '" + id_child + "'", null);
					   
					  
					   
					   if (mCursor != null) {
						   	mCursor.moveToFirst();
//						   	sc = mCursor.getString(mCursor.getColumnIndex(VE_ENVIRONMENT_SCORE));
//						   	score += Integer.parseInt(sc);
//						   	mCursor.moveToNext();
						    while (!mCursor.isAfterLast()) {
						    	sc = mCursor.getString(mCursor.getColumnIndex(VE_ENVIRONMENT_SCORE));
						    	score += Integer.parseInt(sc);
								mCursor.moveToNext();
							}
					   }
					   else
					   {
						   score = 0;
					   }
					   mCursor.close();
				   }catch (SQLiteException se) {
					   Log.v(" CountALLEnvironmentScore Exception",Log.getStackTraceString(se));
				   } catch (Exception e) {
					   Log.v(" CountALLEnvironmentScore Exception",Log.getStackTraceString(e));
				   } finally {
					   db.close();
				   }
				   	return score;
			   }
			 
			   
			   public int GetEnvironmentScore(String id_child, String visit_date,String environment_id) {
				   int score = 0;
				   String sc = "";
				   SQLiteDatabase db = this.getReadableDatabase();
				   try{
					   Cursor mCursor = db.rawQuery(
							   	"SELECT VE_environment_score FROM  TR_VISIT_ENVIRONMENT WHERE VE_visit_date= '"
							   			+ visit_date + "' and VE_child_id= '" + id_child
							   			+ "' and VE_environment_id= '" + environment_id + "'", null);
					   if (mCursor != null) {
						   mCursor.moveToFirst();
						   sc = mCursor.getString(mCursor.getColumnIndex(VE_ENVIRONMENT_SCORE));
						   score = Integer.parseInt(sc);
					   }
					   mCursor.close();
				    }catch (SQLiteException se) {
				      Log.v(" getNameImageType Exception",Log.getStackTraceString(se));
				    } catch (Exception e) {
				     Log.v(" getNameImageType Exception",Log.getStackTraceString(e));
				    } finally {
				     db.close();
				    }
				    return score;
			   }
			 
			   
			   public void UpdateEnvironmentScore(String id_child, String visit_date,String environment_id, String score) {
				   SQLiteDatabase db = this.getReadableDatabase();
				   try{
				     ContentValues values = new ContentValues();
				     values.put(VE_ENVIRONMENT_SCORE, score);
				     
				     db.update(TR_VISIT_ENVIRONMENT, values, ("VE_visit_date = ? AND VE_child_id = '"
				       + id_child + "' AND VE_environment_id = '" + environment_id+"'"),
				       new String[] { visit_date });
				     Log.e("Update Environment Score", "");
			     }catch (Exception e) {
			      Log.e("Update Env Score gagal", ""+e.toString());
			     }
			    }
			   
			   public ArrayList<Environment_Model> GetEnvironmentVisitChild(String date, String child_id)
			   {
				   ArrayList<Environment_Model> data = new ArrayList<Environment_Model>();
				   SQLiteDatabase db = this.getReadableDatabase();
					try{
						Cursor mCursor = db.rawQuery(
										"SELECT VE_environment_id,VE_environment_score  FROM  TR_VISIT_ENVIRONMENT WHERE VE_visit_date= '"
												+ date + "' and VE_child_id= '"+child_id+ "'", null);
						if (mCursor != null) {
							mCursor.moveToFirst();
							while (!mCursor.isAfterLast()) {
								// parse data into model
//								Log.e("fasilitas kunjungan anak", " id anak ");
								data.add(parseDataVisitEnv(mCursor));
								mCursor.moveToNext();
							}
						}
							mCursor.close();
					}catch (SQLiteException se) {
						 Log.v(" GetEnvironmentVisitChild Exception",Log.getStackTraceString(se));
					} catch (Exception e) {
						Log.v(" GetEnvironmentVisitChild Exception",Log.getStackTraceString(e));
					} finally {
						db.close();
					}
					return data;
			   }
			   
			   public Environment_Model parseDataVisitEnv(Cursor c)
				{
				   Environment_Model cur = new Environment_Model();
					cur.SetEnvironment_ID(c.getString(c.getColumnIndex(VE_ENVIRONMENT_ID)));
					cur.SetEnvironment_Score(c.getString(c.getColumnIndex(VE_ENVIRONMENT_SCORE)));

					return cur;
				}
			   
			   
			   
		//---------------------------------------VISIT FACILITY----------------------------------------------------------//
		public void InsertFasilitasKunjunganAnak(String visit_date, String child_id, String facility_id, String facility_cost_id, String qty)
		{ 
			SQLiteDatabase db = this.getWritableDatabase();
			  try {
					ContentValues values = new ContentValues();
					values.put(VF_VISIT_DATE  , visit_date);
					values.put(VF_CHILD_ID   , child_id);
					values.put(VF_FACILITY_ID   , facility_id);
					values.put(VF_FACILITY_COST_ID  , facility_cost_id);
					values.put(VF_FACILITY_QUANTITY  , qty);
					db.insert(TR_VISIT_FACILITY  , null, values);
					
					Log.e("InsertFasilitasKunjunganAnak", "visit_date "+visit_date+" child_id "+child_id+" facility_id "+facility_id+" facility_cost_id "+facility_cost_id+" qty "+qty);
					
					db.close();
			  }
			  catch (SQLiteException se) {
					 Log.v("InsertFasilitasKunjunganAnak",Log.getStackTraceString(se));
			  } catch (Exception e) {
					Log.v("InsertFasilitasKunjunganAnak",Log.getStackTraceString(e));
			  } finally {
					db.close();
			  }
	  }
	
		
		public void UpdateJumlahFasilitasKunjunganAnak(String visit_date, String child_id, String facility_id, String facility_cost_id, String qty)
		{
			Log.e("UpdateJumlahFasilitasKunjunganAnak", "quantity => "+qty);
			SQLiteDatabase db = this.getWritableDatabase();
			
			ContentValues values = new ContentValues();
			values.put(VF_FACILITY_QUANTITY, qty);
			try{
				db.update(TR_VISIT_FACILITY , values, "VF_visit_date=" + visit_date+" and VF_child_id=" + child_id+" and VF_facility_id="+facility_id+" and VF_facility_cost_id="+facility_cost_id, null);
			}catch (SQLiteException se) {
					 Log.v(" UpdateJumlahFasilitasKunjunganAnak Exception",Log.getStackTraceString(se));
			} catch (Exception e) {
					Log.v(" UpdateJumlahFasilitasKunjunganAnak Exception",Log.getStackTraceString(e));
			} finally {
					db.close();
			}
		}
			
		public ArrayList<Visit_Facility> GetAllFasilitasAnak(String date, String child_id)
		{
			ArrayList<Visit_Facility> data = new ArrayList<Visit_Facility>();
			SQLiteDatabase db = this.getReadableDatabase();
			try{
				Cursor mCursor = db.rawQuery(
								"SELECT *  FROM  TR_VISIT_FACILITY WHERE VF_visit_date= '"
										+ date + "' and VF_child_id= '"+child_id+ "'", null);
				if (mCursor != null) {
					mCursor.moveToFirst();
					while (!mCursor.isAfterLast()) {
						// parse data into model
//						Log.e("fasilitas kunjungan anak", " id anak ");
						data.add(parseDataVisitFacility(mCursor));
						mCursor.moveToNext();
					}
				}
					mCursor.close();
			}catch (SQLiteException se) {
				 Log.v(" GetAllFasilitasAnak Exception",Log.getStackTraceString(se));
			} catch (Exception e) {
				Log.v(" GetAllFasilitasAnak Exception",Log.getStackTraceString(e));
			} finally {
				db.close();
			}
			return data;
		}
		
		public Visit_Facility parseDataVisitFacility(Cursor c)
		{
			Visit_Facility cur = new Visit_Facility();
			cur.SetVisitDate(c.getString(c.getColumnIndex(VF_VISIT_DATE )));
			cur.setChild_id(c.getString(c.getColumnIndex(VF_CHILD_ID )));
			cur.setFacility_id(c.getString(c.getColumnIndex(VF_FACILITY_ID )));
			cur.setFacility_cost_id(c.getString(c.getColumnIndex(VF_FACILITY_COST_ID )));
			cur.SetFacilityQty(c.getString(c.getColumnIndex(VF_FACILITY_QUANTITY)));

			return cur;
		}
		
		
			
		//---------------------------------------CAREGIVER----------------------------------------------------------//
		
		//---------------------------------------CAREGIVER----------------------------------------------------------//
			
	
	
	
	
	
	
	
	
}
