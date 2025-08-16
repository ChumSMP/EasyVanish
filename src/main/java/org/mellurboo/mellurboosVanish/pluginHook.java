package org.mellurboo.mellurboosVanish;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class pluginHook {
    public boolean usingSupportedPlugin(String pluginName){
        String plugin = pluginName.toLowerCase();

        switch (plugin){
            case "discordsrv":
                Plugin discordsrv = Bukkit.getPluginManager().getPlugin("DiscordSRV");
                    if (discordsrv != null){ return true; } else { return false; }
            default:
                Bukkit.getLogger().warning("Plugin " + plugin + " not in supported plugins");
                return false;
        }
    }
}
