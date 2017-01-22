package hr.karlovrbic.notify.v1.features.event.view;

import hr.karlovrbic.notify.v1.features.event.IEvent;
import hr.karlovrbic.notify.v1.features.event.presenters.EventPresenter;
import hr.karlovrbic.notify.v1.features.event.requests.EventCreateRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Karlo Vrbic on 07.01.17..
 */
@Path("events")
@Produces(MediaType.APPLICATION_JSON)
public class EventView implements IEvent.View {

    private IEvent.Presenter presenter;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(EventCreateRequest request) {
        return createPresenter().createEvent(request);
    }

    @GET
    public Response getAll() {
        return createPresenter().getAllEvents();
    }

    @GET
    @Path("/{id}")
    public Response getEventById(@PathParam("id") long id) {
        return createPresenter().getEventById(id);
    }

    private IEvent.Presenter createPresenter() {
        if (this.presenter == null) {
            this.presenter = new EventPresenter(this);
        }
        return this.presenter;
    }
}
