package hr.karlovrbic.notify.v1.features.event.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.event.IEvent;
import hr.karlovrbic.notify.v1.features.event.response.EventResponse;
import hr.karlovrbic.notify.v1.model.entity.Event;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public class EventByIdInteractor implements IEvent.GetByIdInteractor {

    @Override
    public Response get(Long id) {
        Event event = JPAEMProvider.getEntityManager().find(Event.class, id);

        Response response = null;
        if (event != null) {
            EventResponse body = EventResponse.fromEntity(event);
            response = Response.ok(body, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            response = Response.status(Response.Status.NO_CONTENT).build();
        }

        JPAEMProvider.close();
        return response;
    }
}
