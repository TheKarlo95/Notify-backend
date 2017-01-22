package hr.karlovrbic.notify.v1.features.event;

import hr.karlovrbic.notify.v1.features.event.requests.EventCreateRequest;
import hr.karlovrbic.notify.v1.features.shared.IBase;

import javax.ws.rs.core.Response;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public interface IEvent {

    interface View extends IBase.View {
    }

    interface Presenter extends IBase.Presenter {
        Response createEvent(EventCreateRequest request);

        Response getAllEvents();

        Response getEventById(Long id);
    }

    interface CreateInteractor extends IBase.Interactor {
        Response create(EventCreateRequest request);
    }

    interface GetAllInteractor extends IBase.Interactor {
        Response getAll();
    }

    interface GetByIdInteractor extends IBase.Interactor {
        Response get(Long id);
    }
}
