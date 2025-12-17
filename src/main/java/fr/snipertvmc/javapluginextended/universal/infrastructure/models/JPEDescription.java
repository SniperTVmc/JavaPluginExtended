package fr.snipertvmc.javapluginextended.universal.infrastructure.models;

import fr.snipertvmc.javapluginextended.universal.infrastructure.annotations.JPE;

public class JPEDescription {


	// -------------------------------------------------- //

	private final String pluginName;
	private final String pluginVersion;

	private final String pluginDescription;
	private final String pluginAuthor;
	private final String[] pluginAuthors;
	private final String pluginWebsite;


	// -------------------------------------------------- //


	public JPEDescription (JPE jpeAnnotation) {
		this.pluginName = jpeAnnotation.name();
		this.pluginVersion = jpeAnnotation.version();
		this.pluginDescription = jpeAnnotation.description();
		this.pluginAuthor = jpeAnnotation.author();
		this.pluginAuthors = jpeAnnotation.authors();
		this.pluginWebsite = jpeAnnotation.website();
	}


	// -------------------------------------------------- //


	public String getPluginName() {
		return pluginName;
	}
	public String getPluginVersion() {
		return pluginVersion;
	}

	public String getPluginDescription() {
		return pluginDescription;
	}
	public String getPluginAuthor() {
		return pluginAuthor;
	}
	public String[] getPluginAuthors() {
		return pluginAuthors;
	}
	public String getPluginWebsite() {
		return pluginWebsite;
	}


	// -------------------------------------------------- //
}
