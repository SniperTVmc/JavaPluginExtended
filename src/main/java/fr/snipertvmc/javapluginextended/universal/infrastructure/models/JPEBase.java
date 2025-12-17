package fr.snipertvmc.javapluginextended.universal.infrastructure.models;

import fr.snipertvmc.javapluginextended.universal.infrastructure.annotations.JPE;
import org.bukkit.plugin.java.JavaPlugin;

public class JPEBase extends JavaPlugin {


	// -------------------------------------------------- //


	private final JPELogger logger;
	private final JPEDescription jpeDescription;


	// -------------------------------------------------- //


	public JPEBase(JPELogger logger) {
		super();

		this.logger = logger;


		// Annotation check
		if (!this.getClass().isAnnotationPresent(JPE.class)) {
			throw new IllegalStateException(
					"\n[JPE-Framework] Critical Error: The Class " + this.getClass().getSimpleName() +
							" must be annotated with @JPE !"
			);
		}

		JPE jpeAnnotation = this.getClass().getAnnotation(JPE.class);
		jpeDescription = new JPEDescription(jpeAnnotation);
	}


	// -------------------------------------------------- //


	public JPELogger getJPELogger() {
		return this.logger;
	}

	public JPEDescription getJPEDescription() {
		return this.jpeDescription;
	}


	// -------------------------------------------------- //
}
