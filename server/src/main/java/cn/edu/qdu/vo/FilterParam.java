package cn.edu.qdu.vo;

import java.util.List;

/**
 * Created by Adam on 2019/5/11 17:24.
 */
public class FilterParam {
    //AND或者OR
    private String groupOp;
    private List<FilterRule> rules;

    public String getGroupOp() {
        return groupOp;
    }

    public void setGroupOp(String groupOp) {
        this.groupOp = groupOp;
    }

    public List<FilterRule> getRules() {
        return rules;
    }

    public void setRules(List<FilterRule> rules) {
        this.rules = rules;
    }
}
