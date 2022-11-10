package poly.edu.vn.mot_nhieu.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import poly.edu.vn.mot_nhieu.DTO.DTO_service;
import poly.edu.vn.mot_nhieu.SQL.Dbhelper;

public class DAO_service {
    SQLiteDatabase db;
    Dbhelper dbhelper;

    public DAO_service(Context context){
        dbhelper = new Dbhelper(context);
    }
    public void open(){
        db = dbhelper.getWritableDatabase();
    }

    public long insertRow(DTO_service obj){
        ContentValues val = new ContentValues();
        val.put(DTO_service.COL_NAME_SERVICE,obj.getNAME());
        val.put(DTO_service.COL_PRICE_SERVICE,obj.getPRICE());

        long res = db.insert(DTO_service.TEN_TABLE,null,val);
        return res;
    }
    public ArrayList<DTO_service> selecAll(){
        ArrayList<DTO_service> list = new ArrayList<>();
        Cursor cs = db.query(DTO_service.TEN_TABLE,null,null,null,null,null,null,null);
        if (cs.moveToFirst()){
            while (!cs.isAfterLast()){
                DTO_service obj = new DTO_service();
                obj.setID(cs.getInt(0));
                obj.setNAME(cs.getString(1));
                obj.setPRICE(cs.getFloat(2));

                list.add(obj);
                cs.moveToNext();
            }
        }
        return list;
    }

    public DTO_service layService(int id){
        DTO_service obj = new DTO_service();
        String dieuKien = "ID = ?";
        String[] layDuLieu = {id+""};
        Cursor cs = db.query(DTO_service.TEN_TABLE,null,dieuKien,layDuLieu,null,null,null);
        if(cs.moveToFirst()){
            obj.setID(cs.getInt(0));
            obj.setNAME(cs.getString(1));
            obj.setPRICE(cs.getFloat(2));
        }
        return obj;
    }

}
