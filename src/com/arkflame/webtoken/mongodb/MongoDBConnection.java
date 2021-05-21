package com.arkflame.webtoken.mongodb;

import com.mongodb.client.MongoClient;

// Logic is contained in the SQLController
class MongoDBConnection {
    final static String URI = "mongodb://127.0.0.1:27017";
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
