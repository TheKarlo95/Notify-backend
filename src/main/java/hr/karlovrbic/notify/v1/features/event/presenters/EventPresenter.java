package hr.karlovrbic.notify.v1.features.event.presenters;

import hr.karlovrbic.notify.v1.features.event.IEvent;
import hr.karlovrbic.notify.v1.features.event.interactors.EventAllInteractor;
import hr.karlovrbic.notify.v1.features.event.interactors.EventByIdInteractor;
import hr.karlovrbic.notify.v1.features.event.interactors.EventCreateInteractor;
import hr.karlovrbic.notify.v1.features.event.requests.EventCreateRequest;

import javax.ws.rs.core.Response;

/**
 * Created by Karlo Vrbic on 12.01.17..
 */
public class EventPresenter implements IEvent.Presenter {

    private IEvent.View view;
    private IEvent.CreateInteractor createInteractor;
    private IEvent.GetAllInteractor getAllInteractor;
    private IEvent.GetByIdInteractor getByIdInteractor;

    public EventPresenter(IEvent.View view) {
        this.view = view;
        this.createInteractor = new EventCreateInteractor();
        this.getAllInteractor = new EventAllInteractor();
        this.getByIdInteractor = new EventByIdInteractor();
    }

    @Override
    public Response createEvent(EventCreateRequest request) {
        if (isValidRequest(request)) {
            return createInteractor.create(request);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Override
    public Response getAllEvents() {
        return getAllInteractor.getAll();
    }

    @Override
    public Response getEventById(Long id) {
        if (id > 0L) {
            return getByIdInteractor.get(id);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    private static boolean isValidRequest(EventCreateRequest request) {
        String title = request.getTitle();
        String description = request.getDescription();

        return title != null && title.length() >= 3
                && description != null && description.length() >= 10 && description.length() <= 180;
    }
}
