package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.event.response.EventResponse;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.model.entity.Event;
import hr.karlovrbic.notify.v1.model.entity.User;

import javax.persistence.EntityManager;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public class FollowEventInteractor implements IUser.FollowEventInteractor {

    @Override
    public Response follow(Long userId, Long eventId) {
        EntityManager em = JPAEMProvider.getEntityManager();
        User user = em.find(User.class, userId);
        Event event = em.getReference(Event.class, eventId);

        Response response = null;
        if (user == null || event == null) {
            Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            if (!exists(user.getSubscribedEvents(), event)) {
                user.getSubscribedEvents().add(event);
                event.getSubscribedUsers().add(user);

                em.merge(user);
                em.merge(event);
            }

            EventResponse body = EventResponse.fromEntity(event);
            response = Response.ok(body, MediaType.APPLICATION_JSON_TYPE).build();
        }

        JPAEMProvider.close();
        return response;
    }

    private static boolean exists(List<Event> eventList, Event event) {
        if (eventList == null && event == null) {
            return false;
        } else {
            for (Event tmp : eventList) {
                if (tmp.equals(event)) {
                    return true;
                }
            }
            return false;
        }
    }
}
