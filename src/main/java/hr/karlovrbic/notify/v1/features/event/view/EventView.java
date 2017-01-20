package hr.karlovrbic.notify.v1.features.event.view;

import hr.karlovrbic.notify.v1.features.event.IEvent;
import hr.karlovrbic.notify.v1.features.event.presenters.EventPresenter;
import hr.karlovrbic.notify.v1.features.event.requests.EventCreateRequest;
import hr.karlovrbic.notify.v1.model.json.EventJson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
        EventJson json = createPresenter().createEvent(request);

        if (json != null) {
            return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.ok().build();
        }
    }

    @GET
    public Response getAll() {
        List<EventJson> json = createPresenter().getAllEvents();

        if (json != null && !json.isEmpty()) {
            return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.ok().build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getEventById(@PathParam("id") long id) {
        EventJson json = createPresenter().getEventById(id);

        if (json != null) {
            return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.ok().build();
        }
    }

    private IEvent.Presenter createPresenter() {
        if (this.presenter == null) {
            this.presenter = new EventPresenter(this);
        }
        return this.presenter;
    }
}
