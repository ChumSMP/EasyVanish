package org.mellurboo.mellurboosVanish.commands;

import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.mellurboo.mellurboosVanish.MellurboosVanish;
import github.scarsz.discordsrv.api.*;

import java.util.HashSet;
import java.util.Set;

public class vanish implements CommandExecutor {
    private final MellurboosVanish plugin;
    public vanish(MellurboosVanish plugin) { this.plugin = plugin; }

    public Set<Player> vanishedPlayers = new HashSet<>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(commandSender instanceof Player))                     { commandSender.sendMessage("You must be a player to use this command"); return true; }
        if (!(commandSender.hasPermission("easyvanish.vanish"))) { commandSender.sendMessage("§cYou do not have permission to use this command"); return true; }

        Player playerToVanish = null;
        if (strings.length != 0) {
            if (strings[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                commandSender.sendMessage("§aReloaded config");
                return true;
            }

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.equals(Bukkit.getPlayer(strings[0]))) {
                    playerToVanish = player;
                }
            }

            if (playerToVanish == null){
                commandSender.sendMessage("Player not vanished as they're not online");
                return true;
            }
        }else {
            playerToVanish = ((Player) commandSender).getPlayer();
        }

        Bukkit.getLogger().info("[Mellurboo's Vanish] Vanishing " + playerToVanish.getName());
        togglePlayerVanish(playerToVanish);

        return true;
    }

    /// Toggle the vanish status of a given player to true or false
    public void togglePlayerVanish(Player player) {
        vanishManager vanishManager = plugin.getVanishManager();
        if (!vanishManager.isVanished(player)) {
            /// to make it more believable we send a quit message
            Bukkit.broadcastMessage(plugin.getStrings().worldQuitMessage.replace("%player%", player.getName()));

            if (plugin.pluginHook.usingSupportedPlugin("DiscordSRV")) {
                DiscordSRV.getPlugin().sendLeaveMessage(player, plugin.getStrings().worldQuitMessage);
            }
            vanishManager.vanishPlayer(player);
        }else {
            Bukkit.broadcastMessage(plugin.getStrings().worldJoinMessage.replace("%player%", player.getName()));

            if (plugin.pluginHook.usingSupportedPlugin("DiscordSRV")) {
                DiscordSRV.getPlugin().sendJoinMessage(player, plugin.getStrings().worldJoinMessage);
            }
            vanishManager.unvanishPlayer(player);
        }
    }
}
