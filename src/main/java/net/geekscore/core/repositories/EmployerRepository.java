package net.geekscore.core.repositories;

import com.mongodb.client.MongoCollection;
import net.geekscore.core.entities.Employer;
import net.geekscore.mongo.MongoDB;
import net.geekscore.mongo.MongoRepository;

public class EmployerRepository implements MongoRepository<Employer> {
    @Override
    public MongoCollection<Employer> collection() {
        return MongoDB.INSTANCE.database().getCollection("employer", Employer.class);
    }
}
