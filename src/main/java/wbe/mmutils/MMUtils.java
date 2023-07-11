package wbe.mmutils;

import org.bukkit.plugin.java.JavaPlugin;

public class MMUtils extends JavaPlugin {
    private final CommandListener commandListener = new CommandListener(this);

    public void onEnable() {
        saveDefaultConfig();
        getCommand(getConfig().getString("command")).setExecutor(this.commandListener);
        getLogger().info("MMUtils activado con éxito.");
        saveConfig();
    }

    public void onDisable() {
        getLogger().info("MMUtils desactivado con éxito.");
    }
}
