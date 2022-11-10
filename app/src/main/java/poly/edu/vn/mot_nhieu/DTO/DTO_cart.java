package poly.edu.vn.mot_nhieu.DTO;

public class DTO_cart {
    private int ID_CART;
    private String NAME_CART;
    private float PRICE_CART;
    private int ID_USER;
    private int ID_SERVICE;

    public final static String TEN_TALBE ="tb_cart";
    public final static String COL_NAME_CART ="NAME_CART";
    public final static String COl_PRICE_CART = "PRICE_CART";
    public final static String COl_ID_CART = "ID_CART";
    public final static String COl_ID_USER = "ID_USER";
    public final static String COl_ID_SERVICE = "ID_SERVICE";


    public DTO_cart(int ID_CART, String NAME_CART, float PRICE_CART, int ID_USER, int ID_SERVICE) {
        this.ID_CART = ID_CART;
        this.NAME_CART = NAME_CART;
        this.PRICE_CART = PRICE_CART;
        this.ID_USER = ID_USER;
        this.ID_SERVICE = ID_SERVICE;
    }

    public DTO_cart(int ID_CART, String NAME_CART, float PRICE_CART) {
        this.ID_CART = ID_CART;
        this.NAME_CART = NAME_CART;
        this.PRICE_CART = PRICE_CART;
    }

    public DTO_cart() {
    }

    public DTO_cart(int ID_CART, String NAME_CART, float PRICE_CART, int ID_USER) {
        this.ID_CART = ID_CART;
        this.NAME_CART = NAME_CART;
        this.PRICE_CART = PRICE_CART;
        this.ID_USER = ID_USER;
    }

    public int getID_SERVICE() {
        return ID_SERVICE;
    }

    public void setID_SERVICE(int ID_SERVICE) {
        this.ID_SERVICE = ID_SERVICE;
    }

    public int getID_USER() {
        return ID_USER;
    }

    public void setID_USER(int ID_USER) {
        this.ID_USER = ID_USER;
    }

    public int getID_CART() {
        return ID_CART;
    }

    public void setID_CART(int ID_CART) {
        this.ID_CART = ID_CART;
    }

    public String getNAME_CART() {
        return NAME_CART;
    }

    public void setNAME_CART(String NAME_CART) {
        this.NAME_CART = NAME_CART;
    }

    public float getPRICE_CART() {
        return PRICE_CART;
    }

    public void setPRICE_CART(float PRICE_CART) {
        this.PRICE_CART = PRICE_CART;
    }
}
