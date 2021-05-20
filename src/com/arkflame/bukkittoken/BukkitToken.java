package com.arkflame.bukkittoken;

import com.arkflame.bukkittoken.commands.TokenCommandExecutor;
import com.arkflame.bukkittoken.listeners.PlayerJoinListener;
import com.arkflame.bukkittoken.sql.SQLConnection;

import org.bukkit.plugin.java.JavaPlugin;

public class BukkitToken extends JavaPlugin {
    private static BukkitToken instance;
    private SQLConnection sqlConnection = null;

    @Override
    public void onEnable() {
        BukkitToken.instance = this;
        sqlConnection = new SQLConnection();

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getCommand("token").setExecutor(new TokenCommandExecutor(sqlConnection));
    }

    @Override
    public void onDisable() {
        if (sqlConnection != null) {
            sqlConnection.close();
        }
    }

    public static JavaPlugin getInstance() {
        return BukkitToken.instance;
    }
}