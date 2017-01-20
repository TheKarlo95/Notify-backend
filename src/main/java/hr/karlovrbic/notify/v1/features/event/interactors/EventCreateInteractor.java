package hr.karlovrbic.notify.v1.features.event.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.event.IEvent;
import hr.karlovrbic.notify.v1.features.event.requests.EventCreateRequest;
import hr.karlovrbic.notify.v1.model.entity.Event;
import hr.karlovrbic.notify.v1.model.entity.User;
import hr.karlovrbic.notify.v1.model.json.EventJson;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class EventCreateInteractor implements IEvent.CreateInteractor {

    @Override
    public EventJson create(@NotNull EventCreateRequest request) {
        EntityManager em = JPAEMProvider.getEntityManager();
        Event event = toEntity(em, request);
        em.persist(event);

        EventJson eventJson = event.toJson();

        JPAEMProvider.close();
        return eventJson;
    }

    private static Event toEntity(EntityManager em, EventCreateRequest request) {
        User creator = em.getReference(User.class, request.getCreator().getId());

        return new Event(creator,
                request.getTitle(),
                request.getDescription(),
                null,
                null,
                null);
    }
}
