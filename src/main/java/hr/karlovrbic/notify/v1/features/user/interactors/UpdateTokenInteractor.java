package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.features.user.requests.TokenUpdateRequest;
import hr.karlovrbic.notify.v1.model.entity.User;
import hr.karlovrbic.notify.v1.model.json.UserResponse;

import javax.persistence.EntityManager;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public class UpdateTokenInteractor implements IUser.UpdateTokenInteractor {

    @Override
    public Response updateToken(Long id, TokenUpdateRequest request) {
        EntityManager em = JPAEMProvider.getEntityManager();

        User user = em.find(User.class, id);
        user.setToken(request.getToken());

        em.persist(user);

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
