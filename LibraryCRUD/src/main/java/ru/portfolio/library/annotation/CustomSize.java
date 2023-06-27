package ru.portfolio.library.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomSize {
    int min() default 1900;
    String message() default "from 1900 to present year";
    String maxMessage() default "max should be calculated dynamically";
}
