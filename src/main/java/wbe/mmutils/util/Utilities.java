package wbe.mmutils.util;

import wbe.mmutils.MMUtils;

public class Utilities {

    public boolean isInBlacklist(String mob) {
        if(MMUtils.config.blacklist.contains(mob)) {
            return true;
        }
        return false;
    }

    public String getRemainingTime(int totalTime) {
        if(totalTime > 0) {
            int hours = totalTime / 3600;
            int minutes = (totalTime - 3600 * hours) / 60;
            int seconds = totalTime - hours * 3600 - minutes * 60;
            String time = "";
            if(hours > 0) {
                time += hours + "h ";
            }
            if(minutes > 0) {
                time += minutes + "m ";
            }
            if(seconds > 0) {
                time += seconds + "s";
            }
            return MMUtils.messages.onCooldown.replace("%boss_cooldown%", time);
        } else {
            return MMUtils.messages.isAvailable;
        }
    }
}
