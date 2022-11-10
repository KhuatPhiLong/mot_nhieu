package poly.edu.vn.mot_nhieu.DTO;

public class DTO_user {
    private int ID_USER;
    private String NAME;
    private String PASSWORD;

    public final static String TEN_TABLE ="tb_user";
    public final static String COL_NAME = "NAME";
    public final static String COL_PASSWORD = "PASSWORD";


    public DTO_user(int ID_USER, String NAME) {
        this.ID_USER = ID_USER;
        this.NAME = NAME;
    }

    public DTO_user(int ID_USER, String NAME, String PASSWORD) {
        this.ID_USER = ID_USER;
        this.NAME = NAME;
        this.PASSWORD = PASSWORD;
    }

    public DTO_user() {
    }

    public int getID_USER() {
        return ID_USER;
    }

    public void setID_USER(int ID_USER) {
        this.ID_USER = ID_USER;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
