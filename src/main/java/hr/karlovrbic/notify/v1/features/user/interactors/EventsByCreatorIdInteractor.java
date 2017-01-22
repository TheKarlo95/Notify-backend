package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.event.response.EventResponse;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.model.entity.Event;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class EventsByCreatorIdInteractor implements IUser.GetEventsByCreatorIdInteractor {

    @SuppressWarnings("unchecked")
    @Override
    public Response get(Long creatorId) {
        List<Event> events = JPAEMProvider.getEntityManager().createNamedQuery("Event.selectByCreatorId")
                .setParameter("creatorId", creatorId)
                .getResultList();


        Response response = null;
        if (events != null && !events.isEmpty()) {
            List<EventResponse> body = events.stream()
                    .map(EventResponse::fromEntity)
                    .collect(Collectors.toList());

            response = Response.ok(body, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            response = Response.noContent().build();
        }

        JPAEMProvider.close();
        return response;
    }
}
