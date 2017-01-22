package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.model.entity.Event;
import hr.karlovrbic.notify.v1.model.entity.User;
import hr.karlovrbic.notify.v1.model.json.UserResponse;

import javax.persistence.EntityManager;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public class FollowEventInteractor implements IUser.FollowEventInteractor {

    @Override
    public Response get(Long userId, Long eventId) {
        EntityManager em = JPAEMProvider.getEntityManager();
        User user = em.find(User.class, userId);
        Event event = em.getReference(Event.class, eventId);

        Response response = null;
        if (user == null  || event == null) {
            Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            user.getSubscribedEvents().add(event);

            UserResponse body = UserResponse.fromEntity(user);
            response = Response.ok(body, MediaType.APPLICATION_JSON_TYPE).build();
        }

        JPAEMProvider.close();
        return response;
    }
}
