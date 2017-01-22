package hr.karlovrbic.notify.v1.features.message.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.message.IMessage;
import hr.karlovrbic.notify.v1.features.message.requests.MessageCreateRequest;
import hr.karlovrbic.notify.v1.features.message.response.MessageResponse;
import hr.karlovrbic.notify.v1.model.entity.Event;
import hr.karlovrbic.notify.v1.model.entity.Message;

import javax.persistence.EntityManager;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class MessageCreateInteractor implements IMessage.CreateInteractor {

    private static Message toEntity(EntityManager em, MessageCreateRequest request, Long eventId) {
        Event event = em.getReference(Event.class, eventId);

        return new Message(request.getContent(),
                event,
                null);
    }

    @Override
    public Response create(MessageCreateRequest request, Long eventId) {
        EntityManager em = JPAEMProvider.getEntityManager();
        Message message = toEntity(em, request, eventId);
        em.persist(message);

        Response response = null;
        if (message != null) {
            MessageResponse body = MessageResponse.fromEntity(message);
            response = Response.ok(body, MediaType.APPLICATION_JSON_TYPE).status(Response.Status.CREATED).build();
        } else {
            response = Response.noContent().build();
        }

        JPAEMProvider.close();
        return response;
    }
}
