package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.features.user.response.UserResponse;
import hr.karlovrbic.notify.v1.model.entity.User;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public class UserByIdInteractor implements IUser.GetByIdInteractor {

    @Override
    public Response get(Long id) {
        User user = JPAEMProvider.getEntityManager().find(User.class, id);

        Response response = null;
        if (user != null) {
            UserResponse body = UserResponse.fromEntity(user);
            response = Response.ok(body, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            response = Response.noContent().build();
        }

        JPAEMProvider.close();
        return response;
    }
}
