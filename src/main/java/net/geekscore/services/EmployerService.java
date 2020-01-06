package net.geekscore.services;

import com.mongodb.client.MongoCollection;
import net.geekscore.core.Employer;
import net.geekscore.mongo.MongoDB;
import net.geekscore.mongo.MongoRepository;

public class EmployerService implements MongoRepository<Employer> {
    @Override
    public MongoCollection<Employer> collection() {
        return MongoDB.INSTANCE.database().getCollection("employer", Employer.class);
    }
}
