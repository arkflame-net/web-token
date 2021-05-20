package com.arkflame.bukkittoken.sql;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.arkflame.bukkittoken.BukkitToken;

class SQLConnectionTask implements Runnable {
    private SQLConnection sqlConnection;

    SQLConnectionTask(final SQLConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    @Override
    public void run() {
        while (!sqlConnection.isClosed()) {
            BukkitToken.getInstance().getLogger().info("Established SQL connection to " + SQLConnection.URI);

            try {
                sqlConnection.setConnection(DriverManager.getConnection(SQLConnection.URI, SQLConnection.USERNAME, SQLConnection.PASSWORD));
            } catch (final SQLException e) {
                
            }

            BukkitToken.getInstance().getLogger().info("Closed SQL connection to " + SQLConnection.URI);
        }
    }
}
