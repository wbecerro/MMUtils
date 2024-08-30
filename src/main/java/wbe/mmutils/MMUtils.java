package wbe.mmutils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import wbe.mmutils.commands.CommandListener;
import wbe.mmutils.config.Config;
import wbe.mmutils.config.Messages;
import wbe.mmutils.listeners.EventListeners;

import java.io.File;

public class MMUtils extends JavaPlugin {

    private FileConfiguration configuration;

    private CommandListener commandListener;

    private EventListeners eventListeners;

    public static Config config;

    public static Messages messages;

    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("MMUtils enabled correctly.");
        reloadConfiguration();

        commandListener = new CommandListener();
        getCommand("bosses").setExecutor(this.commandListener);
        eventListeners = new EventListeners();
        this.eventListeners.initializeListeners();
    }

    public void onDisable() {
        getLogger().info("MMUtils disabled correctly.");
    }

    public static MMUtils getInstance() {
        return getPlugin(MMUtils.class);
    }

    public void reloadConfiguration() {
        if(!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }
        reloadConfig();
        configuration = getConfig();
        messages = new Messages(configuration);
        config = new Config(configuration);
    }
}
