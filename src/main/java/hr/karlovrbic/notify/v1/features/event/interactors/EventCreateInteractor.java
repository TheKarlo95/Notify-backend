package hr.karlovrbic.notify.v1.features.event.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.event.IEvent;
import hr.karlovrbic.notify.v1.features.event.requests.EventCreateRequest;
import hr.karlovrbic.notify.v1.features.event.response.EventResponse;
import hr.karlovrbic.notify.v1.model.entity.Event;
import hr.karlovrbic.notify.v1.model.entity.User;

import javax.persistence.EntityManager;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class EventCreateInteractor implements IEvent.CreateInteractor {

    @Override
    public Response create(EventCreateRequest request) {
        EntityManager em = JPAEMProvider.getEntityManager();
        Event event = toEntity(em, request);

        em.persist(event);

        Response response = null;
        if (event != null) {
            EventResponse body = EventResponse.fromEntity(event);
            response = Response.ok(body, MediaType.APPLICATION_JSON_TYPE).status(Response.Status.CREATED).build();
        } else {
            response = Response.noContent().build();
        }

        JPAEMProvider.close();
        return response;
    }

    private static Event toEntity(EntityManager em, EventCreateRequest request) {
        User creator = em.getReference(User.class, request.getCreator().getId());

        return new Event(creator,
                request.getTitle(),
                request.getDate(),
                request.getDescription(),
                null,
                null,
                null);
    }
}
