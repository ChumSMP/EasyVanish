package org.mellurboo.mellurboosVanish.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.mellurboo.mellurboosVanish.MellurboosVanish;

import java.util.HashSet;
import java.util.Set;

public class vanishManager {
    private final MellurboosVanish plugin;
    public vanishManager(MellurboosVanish plugin) { this.plugin = plugin; }

    private final Set<Player> vanished = new HashSet<>();

    /// Helper function for vanish player logic
    public void vanishPlayer(Player player) {
        vanished.add(player);

        /// We will hide the player from the rest of the server by using invis and hide player to remove them from tab
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, false, false));

        /// for admins they should be able to see an outline, if enabled
        if (plugin.getSettings().glowForAdmins){
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1, false, false));
        }

        for (Player online : Bukkit.getOnlinePlayers()) {
            if (!online.equals(player) && !online.hasPermission("easyvanish.seeVanished")) {
                online.hidePlayer(plugin, player);
            }
        }
    }

    /// Helper function for unvanish logic
    public void unvanishPlayer(Player player) {
        vanished.remove(player);

        player.removePotionEffect(PotionEffectType.INVISIBILITY);
        player.removePotionEffect(PotionEffectType.GLOWING);
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (!online.equals(player)) {
                online.showPlayer(plugin, player);
            }
        }
    }

    /// is this player vanished??
    public boolean isVanished(Player player) {
        Bukkit.getLogger().info("[Mellurboo's Vanish] is vanished on " + player.getName() + " : " + vanished.contains(player));
        return vanished.contains(player);
    }

    /// get the list
    public Set<Player> getVanishedList() {
        return vanished;
    }
}
