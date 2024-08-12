package wbe.mmutils;

import org.bukkit.plugin.java.JavaPlugin;

public class MMUtils extends JavaPlugin {
    private final CommandListener commandListener = new CommandListener(this);

    private EventListeners eventListeners = new EventListeners(this);

    public void onEnable() {
        saveDefaultConfig();
        getCommand(getConfig().getString("command")).setExecutor(this.commandListener);
        getLogger().info("MMUtils activado con éxito.");
        getServer().getPluginManager().registerEvents(this.eventListeners, this);
        saveConfig();
    }

    public void onDisable() {
        getLogger().info("MMUtils desactivado con éxito.");}
}
