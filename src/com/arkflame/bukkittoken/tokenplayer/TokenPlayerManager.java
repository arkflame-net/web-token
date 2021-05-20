package com.arkflame.bukkittoken.tokenplayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

public class TokenPlayerManager {
    private static final Map<UUID, TokenPlayer> TOKEN_PLAYERS = new HashMap<>();

    public static final TokenPlayer get(final UUID uuid) {
        return TOKEN_PLAYERS.getOrDefault(uuid, null);
    }

    public static final TokenPlayer get(final Player player) {
        return get(player.getUniqueId());
    }

    public static final TokenPlayer add(final UUID uuid) {
        return TOKEN_PLAYERS.put(uuid, new TokenPlayer());
    }

    public static final TokenPlayer add(final Player player) {
        return add(player.getUniqueId());
    }

    public static final TokenPlayer remove(final UUID uuid) {
        return TOKEN_PLAYERS.remove(uuid);
    }

    public static final TokenPlayer remove(final Player player) {
        return remove(player.getUniqueId());
    }
}
