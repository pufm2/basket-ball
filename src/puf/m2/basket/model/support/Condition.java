package puf.m2.basket.model.support;

import puf.m2.basket.model.support.BooleanOperator;

public class Condition {
    private String prop, value;
    private Condition nextCondition;
    private BooleanOperator boolOp;
    private ComparisonOperator comparisonOp;

    public Condition(String prop, String value) {
        this(prop, value, ComparisonOperator.equal);
    }

    public Condition(String prop, String value, ComparisonOperator comparisonOp) {
        this.prop = prop;
        this.value = value;

        this.comparisonOp = comparisonOp;
    }

    public Condition and(Condition condition) {
        boolOp = BooleanOperator.and;
        nextCondition = condition;
        return condition;
    }

    public Condition or(Condition condition) {
        boolOp = BooleanOperator.or;
        nextCondition = condition;
        return condition;

    }

    @Override
    public String toString() {
        String str = prop + comparisonOp + "'" + value + "'";
        if (nextCondition != null) {
            str += " " + boolOp.name() + " " + nextCondition; 
        }

        return str;
    }

}
