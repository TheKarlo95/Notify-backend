package hr.karlovrbic.notify.v1.features.message.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.message.IMessage;
import hr.karlovrbic.notify.v1.features.message.requests.MessageCreateRequest;
import hr.karlovrbic.notify.v1.model.entity.Event;
import hr.karlovrbic.notify.v1.model.entity.Message;
import hr.karlovrbic.notify.v1.model.json.MessageJson;

import javax.persistence.EntityManager;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class MessageCreateInteractor implements IMessage.CreateInteractor {

    private static Message toEntity(EntityManager em, MessageCreateRequest request) {
        Event event = em.getReference(Event.class, request.getEvent().getId());

        return new Message(request.getContent(),
                event,
                null);
    }

    @Override
    public MessageJson create(MessageCreateRequest request) {
        EntityManager em = JPAEMProvider.getEntityManager();
        Message message = toEntity(em, request);
        em.persist(message);

        MessageJson messageJson = message.toJson();

        JPAEMProvider.close();
        return messageJson;
    }
}
