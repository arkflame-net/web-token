package com.arkflame.bukkittoken.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Cointains all the logic for the SQLConnection
class SQLController {
    boolean hasToken(final Connection connection, final String nickname) {
        try {
            final PreparedStatement stmt = connection.prepareStatement("select users where nickname = ?");
            
            stmt.setString(1, nickname);

            final int result = stmt.executeUpdate();

            if (result > 0) {
                return true;
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    int generateToken(final Connection connection, final String nickname) {
        try {
            final String action = hasToken(connection, nickname) ? "update" : "insert";
            final PreparedStatement stmt = connection.prepareStatement(action + " users set bukkit_token = ? where nickname = ?");
            final int token = (int) (99999 * Math.random());
            
            stmt.setInt(1, token);
            stmt.setString(2, nickname);

            final int result = stmt.executeUpdate();

            if (result > 0) {
                return token;
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
