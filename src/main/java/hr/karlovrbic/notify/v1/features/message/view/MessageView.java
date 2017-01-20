package hr.karlovrbic.notify.v1.features.message.view;

import hr.karlovrbic.notify.v1.features.message.IMessage;
import hr.karlovrbic.notify.v1.features.message.presenters.MessagePresenter;
import hr.karlovrbic.notify.v1.features.message.requests.MessageCreateRequest;
import hr.karlovrbic.notify.v1.model.json.MessageJson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Karlo Vrbic on 12.01.17..
 */
@Path("events/{eventId}/messages")
@Produces(MediaType.APPLICATION_JSON)
public class MessageView implements IMessage.View {

    private IMessage.Presenter presenter;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMessage(MessageCreateRequest request) {
        MessageJson json = createPresenter().createMessage(request);

        if (json != null) {
            return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.ok().build();
        }
    }

    @GET
    public Response getMessageByEventId(@PathParam("eventId") long eventId) {
        List<MessageJson> json = createPresenter().getMessageByEventId(eventId);

        if (json != null && !json.isEmpty()) {
            return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.ok().build();
        }
    }

    @GET
    @Path("/{messageId}")
    public Response getMessageByMessageId(@PathParam("messageId") long messageId) {
        MessageJson json = createPresenter().getMessageByMessageId(messageId);

        if (json != null) {
            return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.ok().build();
        }
    }

    private IMessage.Presenter createPresenter() {
        if (this.presenter == null) {
            this.presenter = new MessagePresenter(this);
        }
        return this.presenter;
    }
}
