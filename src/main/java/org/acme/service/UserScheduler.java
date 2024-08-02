package org.acme.service;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import org.acme.dto.UserDTO;

import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class UserScheduler {

    @Inject
    UserService service;

    AtomicLong cont = new AtomicLong(1);

    @Scheduled(every="5s")
    @ActivateRequestContext
    void addUserSchedules(){
        var id = cont.getAndIncrement();
        service.addRestClient(new UserDTO(id, "user"));
    }

}
