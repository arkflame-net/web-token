package com.arkflame.webtoken.mongodb;

import java.util.UUID;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

// Cointains all the logic for the SQLConnection
public class MongoDBController extends MongoDBConnection {
    private final String databaseName;

    public MongoDBController(final String databaseName) {
        super(databaseName);
        this.databaseName = databaseName;
    }

    Document findOneAndUpdate(final String collectionName, final UUID uuid, final Document replacement) {
        final MongoDatabase database = client.getDatabase(databaseName);
        final MongoCollection<Document> collection = database.getCollection(collectionName);
        final Document filter = new Document("uuid", uuid.toString());
        final Document document = collection.findOneAndUpdate(filter, replacement);

        return document;
    }

    Document find(final String collectionName, final UUID uuid) {
        final MongoDatabase database = client.getDatabase(databaseName);
        final MongoCollection<Document> collection = database.getCollection(collectionName);
        final Document filter = new Document("uuid", uuid.toString());
        final Document document = collection.find(filter).first();

        if (document != null) {
            return document;
        } else {
            return new Document("uuid", uuid.toString());
        }
    }

    public int generateToken(final UUID uuid) {
        final int token = (int) (Math.random() * 99999999);
        final Document document = find("users", uuid);

        document.put("token", token);

        findOneAndUpdate("users", uuid, document);

        return token;
    }
}
