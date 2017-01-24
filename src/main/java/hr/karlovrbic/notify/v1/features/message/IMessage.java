package hr.karlovrbic.notify.v1.features.message;

import hr.karlovrbic.notify.v1.features.message.requests.MessageCreateRequest;
import hr.karlovrbic.notify.v1.features.message.response.NotificationRequest;
import hr.karlovrbic.notify.v1.features.shared.IBase;

import javax.ws.rs.core.Response;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public interface IMessage {

    interface View extends IBase.View {
    }

    interface Presenter extends IBase.Presenter {
        Response createMessage(MessageCreateRequest eventJson, Long eventId);

        Response getMessageByEventId(Long eventId);

        Response getMessageByMessageId(Long id);
    }

    interface CreateInteractor extends IBase.Interactor {
        Response create(MessageCreateRequest eventJson, Long eventId);
    }

    interface GetByEventIdInteractor extends IBase.Interactor {
        Response get(Long eventId);
    }

    interface GetByIdInteractor extends IBase.Interactor {
        Response get(Long id);
    }

    interface SendNotificationInteractor {
        void send(NotificationRequest notification);
    }
}
