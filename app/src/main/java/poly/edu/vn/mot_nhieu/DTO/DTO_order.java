package poly.edu.vn.mot_nhieu.DTO;

public class DTO_order {
    private int ID_ORDER;
    private int USER_ID;
    private String ORDER_TIME;

    public final static String TEN_TALBE = "tb_order";
    public final static String COL_USER_ID = "USER_ID";
    public final static String COL_ORDER_TIME = "ORDER_TIME";
    public final static String COL_ID_ORDER = "ID_ORDER";

    public DTO_order() {
    }

    public DTO_order(int ID_ORDER, int USER_ID, String ORDER_TIME) {
        this.ID_ORDER = ID_ORDER;
        this.USER_ID = USER_ID;
        this.ORDER_TIME = ORDER_TIME;
    }

    public int getID_ORDER() {
        return ID_ORDER;
    }

    public void setID_ORDER(int ID_ORDER) {
        this.ID_ORDER = ID_ORDER;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getORDER_TIME() {
        return ORDER_TIME;
    }

    public void setORDER_TIME(String ORDER_TIME) {
        this.ORDER_TIME = ORDER_TIME;
    }
}
