package wbe.mmutils;

import java.util.Collection;
import io.lumine.mythic.core.spawning.spawners.MythicSpawner;
import io.lumine.mythic.core.spawning.spawners.SpawnerManager;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandListener implements CommandExecutor {
    private MMUtils plugin;

    public CommandListener(MMUtils plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase(this.plugin.getConfig().getString("command")))
            if (!sender.hasPermission("mmutils.jefes")) {
                sender.sendMessage(this.plugin.getConfig().getString("nopermissions").replace("&", "§"));
            } else {
                MythicBukkit mm = MythicBukkit.inst();
                SpawnerManager spawners = mm.getSpawnerManager();
                Collection<MythicSpawner> t = spawners.getSpawners();
                sender.sendMessage(this.plugin.getConfig().getString("header").replace("&", "§"));
                        String name = "";
                for (MythicSpawner s : t) {
                    name = s.getTypeName();
                    Boolean blacklist = Boolean.valueOf(false);
                    String[] blackmobs = (String[])this.plugin.getConfig().getStringList("blacklist").toArray((Object[])new String[0]);
                    byte b;
                    int i;
                    String[] arrayOfString1;
                    for (i = (arrayOfString1 = blackmobs).length, b = 0; b < i; ) {
                        String str = arrayOfString1[b];
                        if (str.equalsIgnoreCase(name)) {
                            blacklist = Boolean.valueOf(true);
                            break;
                        }
                        b++;
                    }
                    if (!blacklist.booleanValue()) {
                        String tiempo = "";
                        int total = s.getRemainingCooldownSeconds();
                        if (total > 0) {
                            int horas = total / 3600;
                            int minutos = (total - 3600 * horas) / 60;
                            int segundos = total - horas * 3600 + minutos * 60;
                            String seg = "";
                            String m = "";
                            String h = "";
                            if (horas > 0)
                                h = String.valueOf(horas) + "h ";
                            if (minutos > 0)
                                m = String.valueOf(minutos) + "m ";
                            if (segundos > 0)
                                seg = String.valueOf(segundos) + "s";
                            tiempo = String.valueOf(h) + m + seg;
                            sender.sendMessage(this.plugin.getConfig().getString("oncooldown").replace("%boss_name%", name).replace("%boss_cooldown%", tiempo).replace("&", "§"));
                            continue;
                        }
                        sender.sendMessage(this.plugin.getConfig().getString("available").replace("%boss_name%", name).replace("&", "§"));
                    }
                }
                sender.sendMessage(this.plugin.getConfig().getString("footer").replace("&", "§"));
            }
        return false;
    }
}

