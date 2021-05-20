package com.arkflame.bukkittoken.mongodb;

import java.util.logging.Logger;

import com.arkflame.bukkittoken.BukkitToken;
import com.mongodb.client.MongoClients;

class MongoDBConnectionTask implements Runnable {
    private MongoDBConnection mongoDBConnection;

    MongoDBConnectionTask(final MongoDBConnection mongoDBConnection) {
        this.mongoDBConnection = mongoDBConnection;
    }

    @Override
    public void run() {
        final Logger logger = BukkitToken.getInstance().getLogger();

        while (!mongoDBConnection.closed) {
            logger.info("Established MongoDB connection to " + MongoDBConnection.URI);

            mongoDBConnection.setConnection(MongoClients.create(MongoDBConnection.URI));

            logger.info("Closed MongoDB connection to " + MongoDBConnection.URI);
        }
    }
}
