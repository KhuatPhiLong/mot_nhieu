package poly.edu.vn.mot_nhieu.DTO;

public class DTO_order_detail {
    private int ORDER_ID;
    private int SERVICE_ID;

    public static final String TEN_TABLE ="tb_order_detail";
    public static final String COL_ORDER_ID = "ORDER_ID";
    public static final String COL_SERVICE_ID = "SERVICE_ID";

    public DTO_order_detail(int ORDER_ID, int SERVICE_ID) {
        this.ORDER_ID = ORDER_ID;
        this.SERVICE_ID = SERVICE_ID;
    }

    public DTO_order_detail() {
    }

    public int getORDER_ID() {
        return ORDER_ID;
    }

    public void setORDER_ID(int ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }

    public int getSERVICE_ID() {
        return SERVICE_ID;
    }

    public void setSERVICE_ID(int SERVICE_ID) {
        this.SERVICE_ID = SERVICE_ID;
    }
}
