package poly.edu.vn.mot_nhieu.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import poly.edu.vn.mot_nhieu.DTO.DTO_order;
import poly.edu.vn.mot_nhieu.DTO.DTO_order_detail;
import poly.edu.vn.mot_nhieu.DTO.DTO_service;
import poly.edu.vn.mot_nhieu.SQL.Dbhelper;

public class DAO_order_detail {
    SQLiteDatabase db;
    Dbhelper dbhelper;

    public DAO_order_detail(Context context){
        dbhelper = new Dbhelper(context);
    }
    public void open(){
        db = dbhelper.getWritableDatabase();
    }
    public long insertRow(DTO_order_detail obj){
        ContentValues val = new ContentValues();
        val.put(DTO_order_detail.COL_ORDER_ID,obj.getORDER_ID());
        val.put(DTO_order_detail.COL_SERVICE_ID,obj.getSERVICE_ID());

        long res = db.insert(DTO_order_detail.TEN_TABLE,null,val);
        return res;
    }
    public ArrayList<DTO_order_detail> selectAll(){
        ArrayList<DTO_order_detail> list = new ArrayList<>();
        Cursor cs  =db.query(DTO_order_detail.TEN_TABLE,null,null,null,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                DTO_order_detail obj = new DTO_order_detail();
                obj.setORDER_ID(cs.getInt(0));
                obj.setSERVICE_ID(cs.getInt(1));
                list.add(obj);
                cs.moveToNext();
            }
        }
        return list;
    }
    public ArrayList<DTO_order_detail> selectByID(int id_user){
            ArrayList<DTO_order_detail> list  = new ArrayList<>();
            String[] dulieu = {id_user+""};
            String select = "select tb_order_detail.ORDER_ID  from tb_order inner join tb_order_detail on tb_order.ID_ORDER = tb_order_detail.ORDER_ID  where tb_order.USER_ID = ? group by ORDER_ID";
            Cursor cs = db.rawQuery(select,dulieu);
            if(cs.moveToFirst()){
                while(!cs.isAfterLast()){
                    DTO_order_detail obj = new DTO_order_detail();
                    obj.setORDER_ID(cs.getInt(0));
                    list.add(obj);
                    cs.moveToNext();
                }
            }
            return list;
    }

    public ArrayList<DTO_service> listService (int ORDER_ID){
        ArrayList<DTO_service> list = new ArrayList<>();
        String[] duLieu = {ORDER_ID+""};
        String select = "select tb_service.ID , tb_service.NAME , tb_service.PRICE  from tb_order_detail inner join tb_service on tb_order_detail.SERVICE_ID = tb_service.ID where tb_order_detail.ORDER_ID = ?";
        Cursor cs  =db.rawQuery(select,duLieu);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                DTO_service obj = new DTO_service();
                obj.setID(cs.getInt(0));
                obj.setNAME(cs.getString(1));
                obj.setPRICE(cs.getFloat(2));

                list.add(obj);
                cs.moveToNext();
            }
        }
        return  list;
    }
}
