package puf.m2.basket.model.support;

public enum ComparisonOperator {
    equal,
    less,
    lessOrEqual,
    greater,
    greaterOrEqual,
    notEqual;
    
    @Override
    public String toString() {
        switch (this) {
        case equal:
            return "=";
        case less:
            return "<";
        case lessOrEqual:
            return "<=";
        case greater:
            return ">";
        case greaterOrEqual:
            return ">=";
        case notEqual:
            return "<>";
        }
        return null;
    }
}
