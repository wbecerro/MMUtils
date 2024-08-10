package wbe.mmutils;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

public class MMUtils extends JavaPlugin {
    private final CommandListener commandListener = new CommandListener(this);

    private EventListeners eventListeners = new EventListeners(this);

    public Scoreboard scoreboard = this.getServer().getScoreboardManager().getMainScoreboard();

    public void onEnable() {
        saveDefaultConfig();
        getCommand(getConfig().getString("command")).setExecutor(this.commandListener);
        getLogger().info("MMUtils activado con éxito.");
        scoreboard.registerNewTeam("MMUtilsRareItemsTeam");
        scoreboard.getTeam("MMUtilsRareItemsTeam").setPrefix(ChatColor.LIGHT_PURPLE + "");
        getServer().getPluginManager().registerEvents(this.eventListeners, this);
        saveConfig();
    }

    public void onDisable() {
        getLogger().info("MMUtils desactivado con éxito.");
        scoreboard.getTeam("MMUtilsRareItemsTeam").unregister();
    }
}
