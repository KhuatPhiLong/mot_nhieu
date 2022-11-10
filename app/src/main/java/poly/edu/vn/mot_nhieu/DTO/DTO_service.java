package poly.edu.vn.mot_nhieu.DTO;

public class DTO_service {
    private int ID;
    private String NAME;
    private float PRICE;

    public final static String TEN_TABLE ="tb_service";
    public final static String COL_NAME_SERVICE = "NAME";
    public final static String COL_PRICE_SERVICE = "PRICE";

    public DTO_service() {
    }

    public DTO_service(int ID, String NAME, float PRICE) {
        this.ID = ID;
        this.NAME = NAME;
        this.PRICE = PRICE;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public float getPRICE() {
        return PRICE;
    }

    public void setPRICE(float PRICE) {
        this.PRICE = PRICE;
    }
}
