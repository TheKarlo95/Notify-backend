package hr.karlovrbic.notify.v1.features.event.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.event.IEvent;
import hr.karlovrbic.notify.v1.model.entity.Event;
import hr.karlovrbic.notify.v1.model.json.EventJson;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class EventAllInteractor implements IEvent.GetAllInteractor {

    @SuppressWarnings("unchecked")
    @Override
    public List<EventJson> getAll() {
        List<Event> events = JPAEMProvider.getEntityManager()
                .createNamedQuery("Event.selectAll")
                .getResultList();

        List<EventJson> eventJsons = null;
        if (!(events == null || events.isEmpty())) {
            eventJsons = events.stream()
                    .map(Event::toJson)
                    .collect(Collectors.toList());
        }

        JPAEMProvider.close();
        return eventJsons;
    }
}
