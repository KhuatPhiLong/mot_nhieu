package poly.edu.vn.mot_nhieu.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import poly.edu.vn.mot_nhieu.DTO.DTO_order;
import poly.edu.vn.mot_nhieu.SQL.Dbhelper;

public class DAO_order {
    SQLiteDatabase db;
    Dbhelper dbhelper;

    public DAO_order(Context context){
        dbhelper = new Dbhelper(context);
    }
    public void open(){
        db = dbhelper.getWritableDatabase();
    }
    public long insertRow(DTO_order obj){
        ContentValues val =new ContentValues();
        val.put(DTO_order.COL_USER_ID,obj.getUSER_ID());
        val.put(DTO_order.COL_ORDER_TIME,obj.getORDER_TIME());

        long res = db.insert(DTO_order.TEN_TALBE,null,val);
        return res;
    }
    public DTO_order layDTOOrder(){
        DTO_order obj = new DTO_order();
        String select = "SELECT * FROM tb_order ORDER by ID_ORDER DESC LIMIT 1";
        Cursor cs = db.rawQuery(select,null);
        if(cs.moveToFirst()){
            obj.setID_ORDER(cs.getInt(0));
            obj.setUSER_ID(cs.getInt(1));
            obj.setORDER_TIME(cs.getString(2));
        }
        return obj;
    }

}
