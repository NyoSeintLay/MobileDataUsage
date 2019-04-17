package com.nyoseintlay.mobiledatausage.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nyoseintlay.mobiledatausage.model.DataUsageByQuarter;
import com.nyoseintlay.mobiledatausage.model.DataUsageByYear;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteAssetHelper {
    public static final String DATABASE_NAME = "mobiledatausage.db";
    public static final int DATABASE_VERSION = 1;
    private final SQLiteDatabase db = getWritableDatabase();

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void closeDatabase() {
        db.close();
    }

    /* DataUsageByYear */
    public long setDataUsageByYear(DataUsageByYear dataUsageByYear){
        ContentValues values = new ContentValues();
        values.put("id_year", dataUsageByYear.getYear());
        values.put("volume_year", dataUsageByYear.getVolume_of_mobile_data_per_year());
        values.put("isDecreased", (dataUsageByYear.getDecreasedVolume()==true)?1:0);
        return db.insert("DataByYear", null, values);
    }

    public ArrayList<DataUsageByYear> getDataUsageByYear() {
        Cursor cursor = db.rawQuery("SELECT * FROM DataByYear", null);
        ArrayList<DataUsageByYear> dataUsageByYearArrayList = new ArrayList<>();
        if (cursor.getCount() <= 0) {
            cursor.close();
        } else {
            while (cursor.moveToNext()) {
                DataUsageByYear dataUsageByYear = new DataUsageByYear();
                dataUsageByYear.setYear(cursor.getInt(cursor.getColumnIndex("id_year")));
                dataUsageByYear.setVolume_of_mobile_data_per_year(cursor.getDouble(cursor.getColumnIndex("volume_year")));
                dataUsageByYear.setDecreasedVolume((cursor.getInt(cursor.getColumnIndex("isDecreased")) == 1) ? true : false);
                dataUsageByYear.setDataUsageByQuarterArrayList(getDataUsageByQuarter(dataUsageByYear.getYear()));
                dataUsageByYearArrayList.add(dataUsageByYear);
            }
            cursor.close();
        }
        return dataUsageByYearArrayList;
    }

    /* DataUsageByQuarter */
    public long setDataUsageByQuarter(int id_year,DataUsageByQuarter dataUsageByQuarter){
        ContentValues values = new ContentValues();
        values.put("id_quarter", dataUsageByQuarter.get_id());
        values.put("volume_quarter", dataUsageByQuarter.getVolume_of_mobile_data());
        values.put("quarter", dataUsageByQuarter.getQuarter());
        values.put("id_year",id_year);
        return db.insert("DataByQuarter", null, values);
    }

    public ArrayList<DataUsageByQuarter> getDataUsageByQuarter(Integer id_year) {
        Cursor cursor = db.rawQuery("SELECT * FROM DataByQuarter WHERE id_year="+id_year, null);
        ArrayList<DataUsageByQuarter> dataUsageByQuarterArrayList = new ArrayList<>();
        if (cursor.getCount() <= 0) {
            cursor.close();
        } else {
            while (cursor.moveToNext()) {
                DataUsageByQuarter dataUsageByQuarter = new DataUsageByQuarter();
                dataUsageByQuarter.set_id(cursor.getInt(cursor.getColumnIndex("id_quarter")));
                dataUsageByQuarter.setVolume_of_mobile_data(cursor.getDouble(cursor.getColumnIndex("volume_quarter")));
                dataUsageByQuarter.setQuarter((cursor.getString(cursor.getColumnIndex("quarter"))));
                dataUsageByQuarterArrayList.add(dataUsageByQuarter);
            }
            cursor.close();
        }
        return dataUsageByQuarterArrayList;
    }
}