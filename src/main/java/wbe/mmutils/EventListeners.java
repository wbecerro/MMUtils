package wbe.mmutils;

import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventListeners implements Listener {

    private MMUtils plugin;

    private FileConfiguration config;
    public EventListeners(MMUtils plugin) {
        this.plugin = plugin;
        this.config = this.plugin.getConfig();
    }

    @EventHandler
    public void showDropsOnDeath(MythicMobDeathEvent e) {
        List<String> bosses = config.getStringList("blacklist");
        ActiveMob mob = e.getMob();

        if(bosses.contains(mob.getMobType())) {
            return;
        }

        List<ItemStack> drops = e.getDrops();
        Bukkit.getServer().broadcastMessage(config.getString("header").replace("&", "§"));
        Bukkit.getServer().broadcastMessage(config.getString("bossDropsHeader").replace("&", "§")
                .replace("%boss%", mob.getDisplayName())
                .replace("%player%", e.getKiller().getName()));

        for(ItemStack drop : drops) {
            if(drop.getType().equals(Material.DRAGON_EGG)) {
                ItemMeta meta = drop.getItemMeta();
                meta.setDisplayName("§d§lHuevo de Dragón");
                List<String> lore = new ArrayList<>(Arrays.asList("§d", "§7Huevo con la esencia de un dragón", "§7legendario extinto hace mucho tiempo."));
                meta.setLore(lore);
                drop.setItemMeta(meta);
            }
            if(!drop.getItemMeta().hasDisplayName()) {
                continue;
            }
            Bukkit.getServer().broadcastMessage(config.getString("dropLine").replace("&", "§")
                    .replace("%item%", drop.getItemMeta().getDisplayName())
                    .replace("%amount%", String.valueOf(drop.getAmount())));
        }
        Bukkit.getServer().broadcastMessage(config.getString("footer").replace("&", "§"));

    }
}
