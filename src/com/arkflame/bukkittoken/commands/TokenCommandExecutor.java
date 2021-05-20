package com.arkflame.bukkittoken.commands;

import com.arkflame.bukkittoken.sql.SQLConnection;
import com.arkflame.bukkittoken.tokenplayer.TokenPlayer;
import com.arkflame.bukkittoken.tokenplayer.TokenPlayerManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TokenCommandExecutor implements CommandExecutor {
    private final SQLConnection sqlConnection;

    public TokenCommandExecutor(final SQLConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    private String color(final String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final TokenPlayer tokenPlayer = TokenPlayerManager.get((Player) sender);
            final float secondsLeft = tokenPlayer.getLastQueryLeft();

            if (secondsLeft <= 0) {
                final int token = sqlConnection.generateToken(sender.getName());

                if (token > 0) {
                    final String uri = "https://arkflame.com/register?token=" + token;

                    sender.sendMessage(color("&aToken generado! Ingresa a &b" + uri + "&a para crear tu cuenta!"));
                } else {
                    sender.sendMessage(color("&cOcurrio un error al generar el token!"));
                }
            } else {
                sender.sendMessage(color("&cEspera &e" + secondsLeft + "s &c para generar un nuevo token!"));
            }
        } else {
            sender.sendMessage(color("&cEste comando no puede ser usado desde la consola!"));
        }

        return true;
    }
}
