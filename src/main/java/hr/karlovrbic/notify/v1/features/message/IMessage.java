package hr.karlovrbic.notify.v1.features.message;

import hr.karlovrbic.notify.v1.features.message.requests.MessageCreateRequest;
import hr.karlovrbic.notify.v1.features.shared.IBase;
import hr.karlovrbic.notify.v1.model.json.MessageJson;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public interface IMessage {

    interface View extends IBase.View {
    }

    interface Presenter extends IBase.Presenter {
        MessageJson createMessage(@NotNull MessageCreateRequest eventJson);

        List<MessageJson> getMessageByEventId(@NotNull Long eventId);

        MessageJson getMessageByMessageId(@NotNull Long id);
    }

    interface CreateInteractor extends IBase.Interactor {
        MessageJson create(@NotNull MessageCreateRequest eventJson);
    }

    interface GetByEventIdInteractor extends IBase.Interactor {
        List<MessageJson> get(@NotNull Long eventId);
    }

    interface GetByIdInteractor extends IBase.Interactor {
        MessageJson get(@NotNull Long id);
    }
}
