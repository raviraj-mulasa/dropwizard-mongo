package net.geekscore.mongo;

import com.mongodb.client.MongoClient;
import io.dropwizard.setup.Environment;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class MongoDBSettings {

    private String hostname = "localhost";

    @Min(1024)
    private int port = 27017;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String database;

    @NotEmpty
    private String authDatabase;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getAuthDatabase() {
        return this.authDatabase;
    }

    public void setAuthDatabase(String authDatabase) {
        this.authDatabase = authDatabase;
    }

    public MongoClient build(Environment environment) {
        MongoClientManager mongoClientManager = new MongoClientManager(this);
        environment.lifecycle().manage(mongoClientManager);
        return mongoClientManager.getMongoClient();
    }
}
