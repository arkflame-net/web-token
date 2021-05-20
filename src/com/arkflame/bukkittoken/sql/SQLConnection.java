package com.arkflame.bukkittoken.sql;

import java.sql.Connection;

// Logic is contained in the SQLController
public class SQLConnection extends SQLController {
    final static String URI = "jdbc:mysql://127.0.0.1:3306/arkflame";
    final static String USERNAME = "root";
    final static String PASSWORD = "";
    private Connection connection;
    private boolean closed = false;

    public SQLConnection() {
        new Thread(new SQLConnectionTask(this));

        addColumn(connection, "nickname");
        addColumn(connection, "bukkit_token");
    }

    public void setConnection(final Connection connection) {
        this.connection = connection;
    }

    boolean isClosed() {
        return closed;
    }

    public void close() {
        this.closed = true;
    }

    public int generateToken(final String nickname) {
        return super.generateToken(connection, nickname);
    }
}
