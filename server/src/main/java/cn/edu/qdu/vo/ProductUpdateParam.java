package cn.edu.qdu.vo;

/**
 * Created by Adam on 2019/5/11 20:27.
 */
public class ProductUpdateParam {

    private String id;
    private int state;
    private String oper;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }
}
