package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.features.user.requests.UserCreateRequest;
import hr.karlovrbic.notify.v1.model.entity.User;
import hr.karlovrbic.notify.v1.model.json.UserResponse;

import javax.persistence.EntityManager;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class UserCreateInteractor implements IUser.CreateInteractor {

    @Override
    public Response create(UserCreateRequest userCreateRequest) {
        User user = userCreateRequest.toEntity();

        EntityManager em = JPAEMProvider.getEntityManager();
        em.persist(user);

        Response response = null;
        if (user != null) {
            UserResponse body = UserResponse.fromEntity(user);
            response = Response.ok(body, MediaType.APPLICATION_JSON_TYPE).status(Response.Status.CREATED).build();
        } else {
            response = Response.status(Response.Status.NO_CONTENT).build();
        }

        JPAEMProvider.close();
        return response;
    }
}
