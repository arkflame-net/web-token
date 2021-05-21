package com.arkflame.webtoken;

import com.arkflame.webtoken.commands.TokenCommandExecutor;
import com.arkflame.webtoken.listeners.PlayerJoinListener;
import com.arkflame.webtoken.mongodb.MongoDBController;

import org.bukkit.plugin.java.JavaPlugin;

public class WebToken extends JavaPlugin {
    private static WebToken instance;
    private MongoDBController mongoDBController = null;

    @Override
    public void onEnable() {
        WebToken.instance = this;
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
        return WebToken.instance;
    }
}