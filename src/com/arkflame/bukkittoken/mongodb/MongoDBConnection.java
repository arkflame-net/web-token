package com.arkflame.bukkittoken.mongodb;

import com.mongodb.client.MongoClient;

// Logic is contained in the SQLController
class MongoDBConnection {
    final static String URI = "mongodb://hostOne:27017";
    final String databaseName;
    boolean closed = false;
    MongoClient client;

    MongoDBConnection(final String databaseName) {
        this.databaseName = databaseName;
        new Thread(new MongoDBConnectionTask(this));
    }

    void setConnection(final MongoClient client) {
        this.client = client;
    }

    public void close() {
        this.closed = true;
    }
}
