package org.mellurboo.mellurboosVanish.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.mellurboo.mellurboosVanish.MellurboosVanish;

public class playerChat implements Listener {
    private final MellurboosVanish plugin;
    public playerChat(MellurboosVanish plugin) { this.plugin = plugin; }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (plugin.getVanishManager().isVanished(event.getPlayer()) &&
            !plugin.getSettings().allowChatWhileVanished) {

            event.getPlayer().sendMessage("§cChatting while in Vanish is disabled on this server, enable it in config.yml");
            event.setCancelled(true);
        }
    }
}
