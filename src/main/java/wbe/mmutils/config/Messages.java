package wbe.mmutils.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Messages {

    private FileConfiguration config;

    public String noPermission;
    public String header;
    public String bossDropsHeader;
    public String dropLine;
    public String footer;
    public String isAvailable;
    public String onCooldown;
    public String noSpawners;
    public String reload;
    public List<String> help = new ArrayList<>();

    public Messages(FileConfiguration config) {
        this.config = config;

        noPermission = config.getString("Messages.noPermission").replace("&", "§");
        header = config.getString("Messages.header").replace("&", "§");
        bossDropsHeader = config.getString("Messages.bossDropsHeader").replace("&", "§");
        dropLine = config.getString("Messages.dropLine").replace("&", "§");
        footer = config.getString("Messages.footer").replace("&", "§");
        isAvailable = config.getString("Messages.isAvailable").replace("&", "§");
        onCooldown = config.getString("Messages.onCooldown").replace("&", "§");
        noSpawners = config.getString("Messages.noSpawners").replace("&", "§");
        reload = config.getString("Messages.reload").replace("&", "§");
        help = config.getStringList("Messages.help");
    }
}
