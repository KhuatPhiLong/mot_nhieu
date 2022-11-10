package poly.edu.vn.mot_nhieu.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper extends SQLiteOpenHelper {
    public static String NAME = "longlakdf";
    public static int VERSION = 10;

    public Dbhelper(Context context) {
        super(context, NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql_user = "CREATE TABLE tb_user (ID_USER INTEGER NOT NULL,NAME TEXT NOT NULL,PASSWORD TEXT NOT NULL,PRIMARY KEY(ID_USER AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sql_user);
        String sql_insert = "INSERT INTO tb_user VALUES(1,'long','long');";
        sqLiteDatabase.execSQL(sql_insert);
        String sql_service = "CREATE TABLE tb_service (ID INTEGER NOT NULL,NAME TEXT NOT NULL,PRICE FLOAT NOT NULL,PRIMARY KEY(ID AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sql_service);
        String sql_order = "CREATE TABLE tb_order (ID_ORDER INTEGER NOT NULL,USER_ID INTEGER REFERENCES tb_user(ID_USER),ORDER_TIME TEXT,PRIMARY KEY(ID_ORDER AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sql_order);
        String sql_detail = "CREATE TABLE tb_order_detail (ORDER_ID INTEGER NOT NULL REFERENCES tb_order(ID_ORDER),SERVICE_ID INTEGER NOT NULL REFERENCES tb_service(ID));";
        sqLiteDatabase.execSQL(sql_detail);
        String sql_cart = "CREATE TABLE tb_cart (ID_CART INTEGER NOT NULL,ID_SERVICE INTEGER NOT NULL REFERENCES tb_service(ID), ID_USER INTEGER REFERENCES tb_user(ID_USER) ,PRIMARY KEY(ID_CART AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sql_cart);

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_user");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_service");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_order");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_order_detail");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_cart");
        onCreate(sqLiteDatabase);
    }
}
