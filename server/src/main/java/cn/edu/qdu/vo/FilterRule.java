package cn.edu.qdu.vo;

/**
 * Created by Adam on 2019/5/11 17:26.
 */
public class FilterRule {

    private String field;//字段名
    private String op;//条件
    private String data;//字段值

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
