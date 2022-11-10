package poly.edu.vn.mot_nhieu.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collection;

import poly.edu.vn.mot_nhieu.DTO.DTO_cart;
import poly.edu.vn.mot_nhieu.DTO.DTO_service;
import poly.edu.vn.mot_nhieu.SQL.Dbhelper;

public class DAO_cart {
    SQLiteDatabase db;
    Dbhelper dbhelper;
    public DAO_cart(Context context){
        dbhelper = new Dbhelper(context);
    }
    public void open(){
        db = dbhelper.getWritableDatabase();
    }

    public long insertRow(DTO_cart obj){
        ContentValues val = new ContentValues();
        val.put(DTO_cart.COl_ID_SERVICE,obj.getID_SERVICE());
        val.put(DTO_cart.COl_ID_USER,obj.getID_USER());

        long res = db.insert(DTO_cart.TEN_TALBE,null,val);
        return res;
    }
    public int deleteRow(DTO_cart obj){
        String[] check = new String[]{obj.getID_CART()+""};
        int res = db.delete(DTO_cart.TEN_TALBE,"ID_CART = ?",check);
        return res;
    }
    public ArrayList<DTO_cart> selectAll(int ID_USER){
        ArrayList<DTO_cart> list = new ArrayList<>();
        String dieuKien = "ID_USER = ?";
        String[] layDuLieu = {ID_USER+""};
        Cursor cs = db.query(DTO_cart.TEN_TALBE,null,dieuKien,layDuLieu,null, null,null);
        if(cs.moveToFirst()){
            while (!cs.isAfterLast()){
                DTO_cart obj = new DTO_cart();
                obj.setID_CART(cs.getInt(0));
                obj.setID_SERVICE(cs.getInt(1));
                list.add(obj);
                cs.moveToNext();
            }
        }
        return list;
    }
}
