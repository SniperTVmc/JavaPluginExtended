package fr.snipertvmc.javapluginextended.universal.infrastructure.models;

public interface JPELogger {


	// -------------------------------------------------- //


	void info(String message);
	void log(String message);

	void warning(String message);
	void warn(String message);

	void error(String message);
	void severe(String message);

	void debug(String message);


	// -------------------------------------------------- //
}
