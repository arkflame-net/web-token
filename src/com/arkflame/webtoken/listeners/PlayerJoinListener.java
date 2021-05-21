package com.arkflame.webtoken.listeners;

import com.arkflame.webtoken.tokenplayer.TokenPlayerManager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(final PlayerJoinEvent event) {
        TokenPlayerManager.add(event.getPlayer());
    }
}
