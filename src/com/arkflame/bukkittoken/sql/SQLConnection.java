package com.arkflame.bukkittoken.sql;

// Logic is contained in the SQLController
public class SQLConnection extends SQLController {
    final static String URI = "jdbc:mysql://127.0.0.1:3306/arkflame";
    final static String USERNAME = "root";
    final static String PASSWORD = "";
    private boolean closed = false;

    public SQLConnection() {
        new Thread(new SQLConnectionTask(this));

        addTable("users");
        addColumn("nickname", "VARCHAR");
        addColumn("bukkit_token", "INT");
    }

    boolean isClosed() {
        return closed;
    }

    public void close() {
        this.closed = true;
    }
}
