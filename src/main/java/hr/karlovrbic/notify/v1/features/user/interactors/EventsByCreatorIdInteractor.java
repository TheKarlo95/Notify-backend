package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.model.entity.Event;
import hr.karlovrbic.notify.v1.model.json.EventJson;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class EventsByCreatorIdInteractor implements IUser.GetEventsByCreatorIdInteractor {

    @SuppressWarnings("unchecked")
    @Override
    public List<EventJson> get(@NotNull Long creatorId) {
        List<Event> events = JPAEMProvider.getEntityManager().createNamedQuery("Event.selectByCreatorId")
                .setParameter("creatorId", creatorId)
                .getResultList();
        JPAEMProvider.close();

        List<EventJson> eventJsons = null;

        if (events != null && !events.isEmpty()) {
            eventJsons = events.stream()
                    .map(Event::toJson)
                    .collect(Collectors.toList());
        }

        return eventJsons;
    }
}
