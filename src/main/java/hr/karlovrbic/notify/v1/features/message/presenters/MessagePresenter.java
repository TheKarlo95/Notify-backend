package hr.karlovrbic.notify.v1.features.message.presenters;

import hr.karlovrbic.notify.v1.features.event.IEvent;
import hr.karlovrbic.notify.v1.features.event.interactors.EventByIdInteractor;
import hr.karlovrbic.notify.v1.features.message.IMessage;
import hr.karlovrbic.notify.v1.features.message.interactors.MessageByEventIdInteractor;
import hr.karlovrbic.notify.v1.features.message.interactors.MessageByIdInteractor;
import hr.karlovrbic.notify.v1.features.message.interactors.MessageCreateInteractor;
import hr.karlovrbic.notify.v1.features.message.requests.MessageCreateRequest;
import hr.karlovrbic.notify.v1.model.json.EventJson;
import hr.karlovrbic.notify.v1.model.json.MessageJson;

import java.util.List;

/**
 * Created by Karlo Vrbic on 12.01.17..
 */
public class MessagePresenter implements IMessage.Presenter {

    private IMessage.View view;
    private IMessage.CreateInteractor createInteractor;
    private IMessage.GetByEventIdInteractor getByEventIdInteractor;
    private IMessage.GetByIdInteractor getByIdInteractor;
    private IEvent.GetByIdInteractor getEventByIdInteractor;

    public MessagePresenter(IMessage.View view) {
        this.view = view;
        this.createInteractor = new MessageCreateInteractor();
        this.getByEventIdInteractor = new MessageByEventIdInteractor();
        this.getByIdInteractor = new MessageByIdInteractor();
        this.getEventByIdInteractor = new EventByIdInteractor();
    }

    @Override
    public MessageJson createMessage(MessageCreateRequest request) {
        if (isValidRequest(request)) {
            return createInteractor.create(request);
        } else {
            return null;
        }
    }

    @Override
    public List<MessageJson> getMessageByEventId(Long eventId) {
        List<MessageJson> jsons = null;

        if (eventId > 0L) {
            jsons = getByEventIdInteractor.get(eventId);
            if (jsons != null && !jsons.isEmpty()) {
                return jsons;
            }
        }

        return jsons;
    }

    @Override
    public MessageJson getMessageByMessageId(Long id) {
        if (id > 0L) {
            return getByIdInteractor.get(id);
        } else {
            return null;
        }
    }

    private boolean isValidRequest(MessageCreateRequest request) {
        Long commentCreatorId = request.getCreator().getId();

        EventJson event = getEventByIdInteractor.get(request.getEvent().getId());
        Long eventCreatorId = event.getCreator().getId();

        return commentCreatorId.equals(eventCreatorId)
                && request.getContent() != null && !request.getContent().isEmpty();
    }
}
