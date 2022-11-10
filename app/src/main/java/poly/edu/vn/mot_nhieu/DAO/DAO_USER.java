package poly.edu.vn.mot_nhieu.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import poly.edu.vn.mot_nhieu.DTO.DTO_user;
import poly.edu.vn.mot_nhieu.SQL.Dbhelper;

public class DAO_USER {
    SQLiteDatabase db;
    Dbhelper dbhelper;
    SharedPreferences sharedPreferences;

    public DAO_USER(Context context){
        dbhelper = new Dbhelper(context);
        sharedPreferences = context.getSharedPreferences("layid",Context.MODE_PRIVATE);
    }
    public void open(){
        db = dbhelper.getWritableDatabase();
    }
    public void close(){
        dbhelper.close();
    }
    public long insertRow(DTO_user obj){
        ContentValues val = new ContentValues();
        val.put(DTO_user.COL_NAME,obj.getNAME());
        val.put(DTO_user.COL_PASSWORD,obj.getPASSWORD());
        long res = db.insert(DTO_user.TEN_TABLE,null,val);
        return res;
    }
    public DTO_user layObj(int id){
        DTO_user obj = new DTO_user();
        String dieukien = "ID_USER = ?";
        String[] layDuLieu = {id+""};
        Cursor cs = db.query(DTO_user.TEN_TABLE,null,dieukien,layDuLieu,null,null,null);
        if(cs.moveToFirst()){
            obj.setID_USER(cs.getInt(0));
            obj.setNAME(cs.getString(1));
            obj.setPASSWORD(cs.getString(2));
        }
        return obj;
    }
    public boolean check(String user, String pass){
        String dienkien = "NAME = ? AND PASSWORD = ?";
        String[] layDuLieu = {user.trim(),pass.trim()};
        Cursor cs = db.query(DTO_user.TEN_TABLE,null,dienkien,layDuLieu,null,null,null);
        if(cs.getCount()>0){
            cs.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("id",cs.getInt(0));
            editor.commit();
            return true;
        }
        else{
            return false;
        }
    }
}
