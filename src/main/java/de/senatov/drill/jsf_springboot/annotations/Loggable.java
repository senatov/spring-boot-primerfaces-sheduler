package de.senatov.drill.jsf_springboot.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Loggable {
    //for slf4j support
}