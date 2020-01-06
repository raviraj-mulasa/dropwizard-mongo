package net.geekscore.mongo;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.geekscore.core.domain.Address;
import net.geekscore.core.domain.AddressMixIn;
import net.geekscore.core.domain.Person;
import net.geekscore.core.domain.PersonMixIn;
import org.bson.types.ObjectId;

public class MongoJacksonModule extends SimpleModule {

    public MongoJacksonModule() {
        super("entityModule", new Version(0,0,1,null, null, null));
        setMixInAnnotation(Person.class, PersonMixIn.class);
        setMixInAnnotation(Address.class, AddressMixIn.class);
        addSerializer(ObjectId.class, new ObjectIdSerializer());
        addDeserializer(ObjectId.class, new ObjectIdDeSerializer());
    }
}
