package org.mellurboo.mellurboosVanish.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.mellurboo.mellurboosVanish.MellurboosVanish;
import org.mellurboo.mellurboosVanish.commands.vanishManager;

public class playerJoin implements Listener {
    private final MellurboosVanish plugin;
    public playerJoin(MellurboosVanish plugin) { this.plugin = plugin; }

    /// If someone is vanished and a new player joins we need to handle the logic
    /// that keeps the vanished player hidden, so everytime someone is vanished we hide them like this
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        for (Player vanished : plugin.getVanishManager().getVanishedList()) {
            p.hidePlayer(vanished);
        }
    }
}
