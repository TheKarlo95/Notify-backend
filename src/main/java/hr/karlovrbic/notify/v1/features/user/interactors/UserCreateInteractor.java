package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.features.user.requests.UserCreateRequest;
import hr.karlovrbic.notify.v1.features.user.response.UserResponse;
import hr.karlovrbic.notify.v1.model.entity.User;

import javax.persistence.EntityManager;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class UserCreateInteractor implements IUser.CreateInteractor {

    @Override
    public Response create(UserCreateRequest request) {
        User user = request.toEntity();

        EntityManager em = JPAEMProvider.getEntityManager();
        em.persist(user);

        Response response = null;
        if (user != null) {
            UserResponse body = UserResponse.fromEntity(user);
            response = Response.ok(body, MediaType.APPLICATION_JSON_TYPE).status(Response.Status.CREATED).build();
        } else {
            response = Response.noContent().build();
        }

        JPAEMProvider.close();
        return response;
    }
}
