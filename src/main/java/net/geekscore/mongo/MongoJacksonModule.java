package net.geekscore.mongo;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.geekscore.core.*;
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
