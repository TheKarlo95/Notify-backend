package hr.karlovrbic.notify.v1.features.message;

import hr.karlovrbic.notify.v1.features.message.requests.MessageCreateRequest;
import hr.karlovrbic.notify.v1.features.shared.IBase;
import hr.karlovrbic.notify.v1.model.json.MessageJson;

import java.util.List;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public interface IMessage {

    interface View extends IBase.View {
    }

    interface Presenter extends IBase.Presenter {
        MessageJson createMessage(MessageCreateRequest eventJson);

        List<MessageJson> getMessageByEventId(Long eventId);

        MessageJson getMessageByMessageId(Long id);
    }

    interface CreateInteractor extends IBase.Interactor {
        MessageJson create(MessageCreateRequest eventJson);
    }

    interface GetByEventIdInteractor extends IBase.Interactor {
        List<MessageJson> get(Long eventId);
    }

    interface GetByIdInteractor extends IBase.Interactor {
        MessageJson get(Long id);
    }
}
