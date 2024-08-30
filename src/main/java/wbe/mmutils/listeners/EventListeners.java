package wbe.mmutils.listeners;

import org.bukkit.plugin.PluginManager;
import wbe.mmutils.MMUtils;

public class EventListeners {

    private MMUtils plugin = MMUtils.getInstance();

    public void initializeListeners() {
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new MythicMobDeathListeners(), plugin);
    }
}
