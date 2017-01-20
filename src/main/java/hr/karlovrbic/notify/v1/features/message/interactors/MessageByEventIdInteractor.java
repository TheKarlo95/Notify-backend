package hr.karlovrbic.notify.v1.features.message.interactors;

import hr.karlovrbic.notify.v1.model.json.MessageJson;
import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.message.IMessage;
import hr.karlovrbic.notify.v1.model.entity.Message;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class MessageByEventIdInteractor implements IMessage.GetByEventIdInteractor {

    @SuppressWarnings("unchecked")
    @Override
    public List<MessageJson> get(@NotNull Long eventId) {
        List<Message> messages = JPAEMProvider.getEntityManager()
                .createNamedQuery("Message.selectByEventId")
                .setParameter("eventId", eventId)
                .getResultList();

        List<MessageJson> messageJsons = null;
        if (!(messages == null || messages.isEmpty())) {
            messageJsons = messages.stream()
                    .map(Message::toJson)
                    .collect(Collectors.toList());
        }
        JPAEMProvider.close();

        return messageJsons;
    }
}
