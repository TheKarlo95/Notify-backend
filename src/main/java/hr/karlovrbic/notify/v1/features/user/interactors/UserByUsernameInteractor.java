package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.model.entity.User;
import hr.karlovrbic.notify.v1.model.json.UserResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public class UserByUsernameInteractor implements IUser.GetByUsernameInteractor {

    @SuppressWarnings("unchecked")
    @Override
    public Response get(String username) {
        Response response = null;

        List<User> users = JPAEMProvider.getEntityManager()
                .createNamedQuery("User.selectByUsername")
                .setParameter("username", username)
                .getResultList();

        if (users == null || users.isEmpty()) {
            response = Response.status(Response.Status.NO_CONTENT).build();
        } else if (users.size() == 1) {
            User user = users.get(0);

            UserResponse body = UserResponse.fromEntity(user);
            response = Response.ok(body, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            response = Response.noContent().build();
        }

        JPAEMProvider.close();
        return response;
    }
}
