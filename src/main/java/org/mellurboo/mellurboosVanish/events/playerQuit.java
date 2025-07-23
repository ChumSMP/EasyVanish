package org.mellurboo.mellurboosVanish.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.mellurboo.mellurboosVanish.MellurboosVanish;
import org.mellurboo.mellurboosVanish.commands.vanishManager;

public class playerQuit implements Listener {
    private final MellurboosVanish plugin;
    public playerQuit(MellurboosVanish plugin) { this.plugin = plugin; }

    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {
        vanishManager vanishManager = plugin.getVanishManager();

        if (vanishManager.isVanished(event.getPlayer())) {
            vanishManager.unvanishPlayer(event.getPlayer());
        }
    }
}
