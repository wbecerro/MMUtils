package wbe.mmutils.listeners;

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import wbe.mmutils.MMUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MythicMobDeathListeners implements Listener {

    @EventHandler
    public void showDropsOnDeath(MythicMobDeathEvent event) {
        List<String> bosses = MMUtils.config.blacklist;
        ActiveMob mob = event.getMob();

        if(bosses.contains(mob.getMobType())) {
            return;
        }

        if(event.getKiller() == null) {
            return;
        }

        List<ItemStack> drops = event.getDrops();
        Bukkit.getServer().broadcastMessage(MMUtils.messages.header);
        Bukkit.getServer().broadcastMessage(MMUtils.messages.bossDropsHeader
                .replace("%boss%", mob.getDisplayName())
                .replace("%player%", event.getKiller().getName()));

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

            Bukkit.getServer().broadcastMessage(MMUtils.messages.dropLine
                    .replace("%item%", drop.getItemMeta().getDisplayName())
                    .replace("%amount%", String.valueOf(drop.getAmount())));
        }

        Bukkit.getServer().broadcastMessage(MMUtils.messages.footer);
    }
}
