package hr.karlovrbic.notify.v1.features.message.view;

import hr.karlovrbic.notify.v1.features.message.IMessage;
import hr.karlovrbic.notify.v1.features.message.presenters.MessagePresenter;
import hr.karlovrbic.notify.v1.features.message.requests.MessageCreateRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Karlo Vrbic on 12.01.17..
 */
@Path("events/{eventId}/messages")
@Produces(MediaType.APPLICATION_JSON)
public class MessageView implements IMessage.View {

    private IMessage.Presenter presenter;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMessage(MessageCreateRequest request, @PathParam("eventId") Long eventId) {
        return createPresenter().createMessage(request, eventId);
    }

    @GET
    public Response getMessageByEventId(@PathParam("eventId") long eventId) {
        return createPresenter().getMessageByEventId(eventId);
    }

    @GET
    @Path("/{messageId}")
    public Response getMessageByMessageId(@PathParam("messageId") long messageId) {
        return createPresenter().getMessageByMessageId(messageId);
    }

    private IMessage.Presenter createPresenter() {
        if (this.presenter == null) {
            this.presenter = new MessagePresenter(this);
        }
        return this.presenter;
    }
}
