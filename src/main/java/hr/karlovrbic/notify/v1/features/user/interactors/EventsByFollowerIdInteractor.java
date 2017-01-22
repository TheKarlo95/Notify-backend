package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.event.response.EventResponse;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.model.entity.Event;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class EventsByFollowerIdInteractor implements IUser.GetEventsByCreatorIdInteractor {

    @SuppressWarnings("unchecked")
    @Override
    public List<EventResponse> get(Long creatorId) {
        List<Event> events = JPAEMProvider.getEntityManager().createNamedQuery("Event.selectByFollowerId")
                .setParameter("followerId", creatorId)
                .getResultList();

        List<EventResponse> responses = null;

        if (events != null && !events.isEmpty()) {
            responses = events.stream()
                    .map(EventResponse::fromEntity)
                    .collect(Collectors.toList());
        }

        JPAEMProvider.close();
        return responses;
    }
}
