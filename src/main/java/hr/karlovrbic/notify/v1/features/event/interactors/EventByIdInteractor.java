package hr.karlovrbic.notify.v1.features.event.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.event.IEvent;
import hr.karlovrbic.notify.v1.model.entity.Event;
import hr.karlovrbic.notify.v1.model.json.EventJson;

import javax.validation.constraints.NotNull;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public class EventByIdInteractor implements IEvent.GetByIdInteractor {

    @Override
    public EventJson get(@NotNull Long id) {
        Event event = JPAEMProvider.getEntityManager().find(Event.class, id);

        EventJson eventJson = null;
        if (event != null) {
            eventJson = event.toJson();
        }

        JPAEMProvider.close();
        return eventJson;
    }
}
