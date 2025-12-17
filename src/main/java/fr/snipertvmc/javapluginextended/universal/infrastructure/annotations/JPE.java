package fr.snipertvmc.javapluginextended.universal.infrastructure.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface JPE {
	String name();
	String version();
	String description() default "No description provided.";
	String author() default "Unknown";
	String[] authors() default {};
	String website() default "";
}