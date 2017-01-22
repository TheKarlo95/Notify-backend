package hr.karlovrbic.notify.v1.features.message.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.message.IMessage;
import hr.karlovrbic.notify.v1.features.message.response.MessageResponse;
import hr.karlovrbic.notify.v1.model.entity.Message;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class MessageByEventIdInteractor implements IMessage.GetByEventIdInteractor {

    @SuppressWarnings("unchecked")
    @Override
    public Response get(Long eventId) {
        List<Message> messages = JPAEMProvider.getEntityManager()
                .createNamedQuery("Message.selectByEventId")
                .setParameter("eventId", eventId)
                .getResultList();

        Response response = null;
        if (messages != null && !messages.isEmpty()) {
            List<MessageResponse> body = messages.stream()
                    .map(MessageResponse::fromEntity)
                    .collect(Collectors.toList());

            response = Response.ok(body, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            response = Response.noContent().build();
        }

        JPAEMProvider.close();
        return response;
    }
}
