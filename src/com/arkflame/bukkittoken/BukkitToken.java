package com.arkflame.bukkittoken;

import com.arkflame.bukkittoken.commands.TokenCommandExecutor;
import com.arkflame.bukkittoken.listeners.PlayerJoinListener;
import com.arkflame.bukkittoken.mongodb.MongoDBController;

import org.bukkit.plugin.java.JavaPlugin;

public class BukkitToken extends JavaPlugin {
    private static BukkitToken instance;
    private MongoDBController mongoDBController = null;

    @Override
    public void onEnable() {
        BukkitToken.instance = this;
        mongoDBController = new MongoDBController("arkflame");

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getCommand("token").setExecutor(new TokenCommandExecutor(mongoDBController));
    }

    @Override
    public void onDisable() {
        if (mongoDBController != null) {
            mongoDBController.close();
        }
    }

    public static JavaPlugin getInstance() {
        return BukkitToken.instance;
    }
}