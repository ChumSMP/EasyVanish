package org.mellurboo.mellurboosVanish;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.mellurboo.mellurboosVanish.events.playerChat;
import org.mellurboo.mellurboosVanish.events.playerJoin;
import org.mellurboo.mellurboosVanish.events.playerQuit;
import org.mellurboo.mellurboosVanish.resources.settings;
import org.mellurboo.mellurboosVanish.resources.strings;
import org.mellurboo.mellurboosVanish.commands.*;

public final class MellurboosVanish extends JavaPlugin {
    private vanishManager vanishManager;
    public strings Strings;
    public settings Settings;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[Mellurboo's Vanish] Starting...");
        loadConfig();

        vanishManager = new vanishManager(this);

        registerCommands();
        registerEvents();

        Bukkit.getLogger().info("[Mellurboo's Vanish] Plugin has Enabled");
    }

    /// just a wrapper to tidy up register all the plugins commands
    public void registerCommands(){
        Bukkit.getLogger().info("[Mellurboo's Vanish] Registering Commands");
        getCommand("vanish").setExecutor(new vanish(this));
        getCommand("vanish").setTabCompleter(new vanishTAB());
    }

    /// register game events for the listener
    public void registerEvents(){
        Bukkit.getLogger().info("[Mellurboo's Vanish] Registering Events");
        getServer().getPluginManager().registerEvents(new playerJoin(this), this);
        getServer().getPluginManager().registerEvents(new playerQuit(this), this);
        getServer().getPluginManager().registerEvents(new playerChat(this), this);
    }

    public void loadConfig(){
        saveDefaultConfig();
        FileConfiguration config = getConfig();

        Strings = new strings();
        Settings = new settings();
        if (!Strings.AssignStrings(config) || !Settings.AssignSettings(config)) {
            Bukkit.getLogger().info("[Mellurboo's Vanish] Plugin has Failed due to a configuration error.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[Mellurboo's Vanish] Plugin has Shutdown");
    }

    public vanishManager getVanishManager() { return vanishManager; }
    public settings getSettings() { return Settings; }
    public strings getStrings() { return Strings; }

}
