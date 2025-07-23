package org.mellurboo.mellurboosVanish.resources;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class strings {
    public String
        youHaveVanished,
        youHaveUnVanished,
        worldQuitMessage;

    public boolean AssignStrings(FileConfiguration config) {
        Bukkit.getLogger().info("[Mellurboo's Vanish] Plugin applying strings");
        try{
            youHaveVanished     = config.getString("strings.youHaveVanished");
            youHaveUnVanished   = config.getString("strings.youHaveUnVanished");
            worldQuitMessage    = config.getString("strings.worldQuitMessage");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
