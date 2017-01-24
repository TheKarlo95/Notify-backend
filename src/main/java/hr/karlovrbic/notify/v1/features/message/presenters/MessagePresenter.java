package hr.karlovrbic.notify.v1.features.message.presenters;

import hr.karlovrbic.notify.v1.features.event.IEvent;
import hr.karlovrbic.notify.v1.features.event.interactors.EventByIdInteractor;
import hr.karlovrbic.notify.v1.features.message.IMessage;
import hr.karlovrbic.notify.v1.features.message.interactors.MessageByEventIdInteractor;
import hr.karlovrbic.notify.v1.features.message.interactors.MessageByIdInteractor;
import hr.karlovrbic.notify.v1.features.message.interactors.MessageCreateInteractor;
import hr.karlovrbic.notify.v1.features.message.interactors.SendNotificationInteractor;
import hr.karlovrbic.notify.v1.features.message.requests.MessageCreateRequest;
import hr.karlovrbic.notify.v1.features.message.response.MessageResponse;
import hr.karlovrbic.notify.v1.features.message.response.NotificationRequest;

import javax.ws.rs.core.Response;

/**
 * Created by Karlo Vrbic on 12.01.17..
 */
public class MessagePresenter implements IMessage.Presenter {

    private IMessage.View view;
    private IMessage.CreateInteractor createInteractor;
    private IMessage.SendNotificationInteractor sendNotificationInteractor;
    private IMessage.GetByEventIdInteractor getByEventIdInteractor;
    private IMessage.GetByIdInteractor getByIdInteractor;
    private IEvent.GetByIdInteractor getEventByIdInteractor;

    public MessagePresenter(IMessage.View view) {
        this.view = view;
        this.createInteractor = new MessageCreateInteractor();
        this.sendNotificationInteractor = new SendNotificationInteractor();
        this.getByEventIdInteractor = new MessageByEventIdInteractor();
        this.getByIdInteractor = new MessageByIdInteractor();
        this.getEventByIdInteractor = new EventByIdInteractor();
    }

    @Override
    public Response createMessage(MessageCreateRequest request, Long eventId) {
        if (isValidRequest(request)) {
            Response response = createInteractor.create(request, eventId);

            MessageResponse message = (MessageResponse) response.getEntity();
            MessageResponse.EventShortResponse event = message.getEvent();

            NotificationRequest notification = new NotificationRequest(event.getId(),
                    event.getCreator().getId(),
                    event.getTitle(),
                    message.getContent(),
                    NotificationRequest.Notification.MAIN_CLICK_ACTION);

            sendNotificationInteractor.send(notification);
            return response;
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Override
    public Response getMessageByEventId(Long eventId) {
        if (eventId > 0L) {
            return getByEventIdInteractor.get(eventId);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Override
    public Response getMessageByMessageId(Long id) {
        if (id > 0L) {
            return getByIdInteractor.get(id);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    private boolean isValidRequest(MessageCreateRequest request) {
        return request.getContent() != null && !request.getContent().isEmpty();
    }
}
