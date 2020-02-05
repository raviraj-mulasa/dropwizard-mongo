package net.geekscore.services;

import net.geekscore.core.EntityStore;
import net.geekscore.core.entities.Employer;
import net.geekscore.core.stores.PersonEntityStore;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;


public class PersonServiceImpl implements PersonService {

    private final Logger logger = this.logger();

    private final PersonEntityStore personEntityStore;

    private final EntityStore<Employer> employerEntityStore;

    @Inject
    public PersonServiceImpl(
            @NotNull  PersonEntityStore personEntityStore
            ,@NotNull EntityStore<Employer> employerEntityStore
    ) {
        this.personEntityStore = personEntityStore;
        this.employerEntityStore = employerEntityStore;
    }

    @Override
    public void test() {
        this.personEntityStore.x();
        logger.info("Reaching PERSON SERVICE IMPL ");
    }


}
