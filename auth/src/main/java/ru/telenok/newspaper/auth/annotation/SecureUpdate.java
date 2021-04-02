package ru.telenok.newspaper.auth.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecureUpdate {
    String[] value();
}
