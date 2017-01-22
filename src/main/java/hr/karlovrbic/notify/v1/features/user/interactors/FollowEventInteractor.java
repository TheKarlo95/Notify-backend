package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.model.entity.Event;
import hr.karlovrbic.notify.v1.model.entity.User;
import hr.karlovrbic.notify.v1.model.json.UserResponse;

import javax.persistence.EntityManager;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public class FollowEventInteractor implements IUser.FollowEventInteractor {

    @Override
    public UserResponse get(Long userId, Long eventId) {
        EntityManager em = JPAEMProvider.getEntityManager();
        User user = em.find(User.class, userId);
        Event event = em.getReference(Event.class, eventId);
        user.getSubscribedEvents().add(event);

        UserResponse response = null;
        if (user != null) {
            response = UserResponse.fromEntity(user);
        }

        JPAEMProvider.close();
        return response;
    }
}
