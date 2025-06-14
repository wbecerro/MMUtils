package wbe.mmutils.commands;

import java.util.Collection;
import io.lumine.mythic.core.spawning.spawners.MythicSpawner;
import io.lumine.mythic.core.spawning.spawners.SpawnerManager;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wbe.mmutils.MMUtils;
import wbe.mmutils.util.Utilities;

public class CommandListener implements CommandExecutor {

    private MMUtils plugin = MMUtils.getInstance();

    private Utilities utilities = new Utilities();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("Bosses")) {
            Player player = null;
            if(sender instanceof Player) {
                player = (Player) sender;
            }

            if(args.length == 0 || args[0].equalsIgnoreCase("help")) {
                if(!sender.hasPermission("mmutils.command.help")) {
                    sender.sendMessage(MMUtils.messages.noPermission);
                    return false;
                }

                for(String line : MMUtils.messages.help) {
                    sender.sendMessage(line.replace("&", "§"));
                }
            } else if(args[0].equalsIgnoreCase("list")) {
                if(!sender.hasPermission("mmutils.command.list")) {
                    sender.sendMessage(MMUtils.messages.noPermission);
                    return false;
                }

                MythicBukkit mythicBukkit = MythicBukkit.inst();
                SpawnerManager spawnerManager = mythicBukkit.getSpawnerManager();
                Collection<MythicSpawner> spawners = spawnerManager.getSpawners();
                sender.sendMessage(MMUtils.messages.header);

                if(spawners.isEmpty()) {
                    sender.sendMessage(MMUtils.messages.noSpawners);
                } else {
                    for(MythicSpawner spawner : spawners) {
                        String spawnerMob = spawner.getTypeName();
                        if(utilities.isInBlacklist(spawnerMob)) {
                            continue;
                        }

                        String time = utilities.getRemainingTime(spawner.getRemainingCooldownSeconds());
                        sender.sendMessage(time.replace("%boss_name%", spawnerMob));
                    }
                }

                sender.sendMessage(MMUtils.messages.footer);
            } else if(args[0].equalsIgnoreCase("reload")) {
                if(!sender.hasPermission("mmutils.command.reload")) {
                    sender.sendMessage(MMUtils.messages.noPermission);
                    return false;
                }

                plugin.reloadConfiguration();
                sender.sendMessage(MMUtils.messages.reload);
            }
        }
        return true;
    }
}

