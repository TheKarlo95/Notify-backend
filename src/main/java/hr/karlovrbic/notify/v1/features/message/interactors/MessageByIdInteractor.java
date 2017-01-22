package hr.karlovrbic.notify.v1.features.message.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.message.IMessage;
import hr.karlovrbic.notify.v1.features.message.response.MessageResponse;
import hr.karlovrbic.notify.v1.model.entity.Message;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class MessageByIdInteractor implements IMessage.GetByIdInteractor {

    @Override
    public Response get(Long id) {
        Message message = JPAEMProvider.getEntityManager().find(Message.class, id);

        Response response = null;
        if (message != null) {
            MessageResponse body = MessageResponse.fromEntity(message);
            response = Response.ok(body, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            response = Response.noContent().build();
        }
        JPAEMProvider.close();

        return response;
    }
}
