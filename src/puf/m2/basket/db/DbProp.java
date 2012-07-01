package puf.m2.basket.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//This annotation is used to annotate if a property is mapped to db or not
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DbProp {

}
