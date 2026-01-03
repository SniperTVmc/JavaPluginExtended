package fr.snipertvmc.javapluginextended.spigot.infrastructure.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface JPESpigotCommand {
	String NAME();
}
