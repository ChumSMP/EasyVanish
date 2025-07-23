package org.mellurboo.mellurboosVanish.resources;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class settings {
    public boolean
        glowForAdmins,
        allowChatWhileVanished;

    public boolean AssignSettings(FileConfiguration config) {
        Bukkit.getLogger().info("[Mellurboo's Vanish] Plugin applying settings.");
        try{
            glowForAdmins               = config.getBoolean("settings.glowForAdmins");
            allowChatWhileVanished      = config.getBoolean("settings.allowChatWhileVanished");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
