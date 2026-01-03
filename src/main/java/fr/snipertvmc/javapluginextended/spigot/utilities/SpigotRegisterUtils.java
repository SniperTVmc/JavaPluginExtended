package fr.snipertvmc.javapluginextended.spigot.utilities;

import fr.snipertvmc.javapluginextended.spigot.infrastructure.annotations.JPESpigotCommand;
import fr.snipertvmc.javapluginextended.spigot.infrastructure.models.JPESpigotBase;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;

import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarFile;

public class SpigotRegisterUtils {


	// -------------------------------------------------- //


	public static int registerCommands(JPESpigotBase spigotBase, String packageName) {

		try {
			int counter = 0;
			for(Class<?> instance : getClasses(spigotBase, packageName)) {
				if(!CommandExecutor.class.isAssignableFrom(instance)) continue;
				if(instance.getAnnotation(JPESpigotCommand.class) == null) continue;

				JPESpigotCommand command = instance.getAnnotation(JPESpigotCommand.class);
				CommandExecutor commandExecutor = (CommandExecutor) instance.getDeclaredConstructor().newInstance();

				PluginCommand pluginCommand = spigotBase.getCommand(command.NAME());
				if(pluginCommand == null) {
					new IllegalArgumentException("The command '" + command.NAME() + "' is not defined in the plugin.yml file.").printStackTrace();
					continue;
				}

				pluginCommand.setExecutor(commandExecutor);
				counter++;
			}

			return counter;

		} catch(Exception exception) {
			throw new RuntimeException("An error has occurred while registering commands : " + exception.getMessage());
		}
	}


	public static int registerEvents(JPESpigotBase spigotBase, String packageName) {

		try {

			int counter = 0;
			for(Class<?> instance : getClasses(spigotBase, packageName)) {

				if(Listener.class.isAssignableFrom(instance)) {
					counter++;
					Listener listener = (Listener) instance.getDeclaredConstructor().newInstance();
					spigotBase.getServer().getPluginManager().registerEvents(listener, spigotBase);
				}
			}

			return counter;

		} catch(Exception exception) {
			throw new RuntimeException("An error has occurred while registering events : " + exception.getMessage());
		}
	}

	// -------------------------------------------------- //


	private static Set<Class<?>> getClasses(JPESpigotBase spigotBase, String packageName) throws Exception {
		Set<Class<?>> classes = new HashSet<>();

		String path = packageName.replace('.', '/');
		try (JarFile jarFile = new JarFile(spigotBase.getPluginFile())) {
			var entries = jarFile.entries();

			while (entries.hasMoreElements()) {
				var entry = entries.nextElement();
				String name = entry.getName();

				if (name.startsWith(path) && name.endsWith(".class") && !entry.isDirectory()) {
					String className = name.replace('/', '.').replace(".class", "");
					classes.add(Class.forName(className));
				}
			}
		}

		return classes;
	}


	// -------------------------------------------------- //
}
