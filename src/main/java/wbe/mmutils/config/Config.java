package wbe.mmutils.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Config {

    private FileConfiguration config;

    public List<String> blacklist = new ArrayList<>();

    public Config(FileConfiguration config) {
        this.config = config;

        blacklist = config.getStringList("Config.blacklist");
    }
}
