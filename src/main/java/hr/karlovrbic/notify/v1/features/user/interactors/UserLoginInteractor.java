package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.features.user.requests.UserLoginRequest;
import hr.karlovrbic.notify.v1.model.entity.User;
import hr.karlovrbic.notify.v1.model.json.UserResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public class UserLoginInteractor implements IUser.LoginInteractor {

    @SuppressWarnings("unchecked")
    @Override
    public Response login(UserLoginRequest userLoginRequest) {
        List<User> users = JPAEMProvider.getEntityManager()
                .createNamedQuery("User.selectByUsername")
                .setParameter("username", userLoginRequest.getUsername())
                .getResultList();

        Response response = null;
        if (users == null || users.isEmpty()) {
            response = Response.status(Response.Status.NO_CONTENT).build();
        } else if (users.size() == 1) {
            User user = users.get(0);

            if (user != null) {
                UserResponse body = UserResponse.fromEntity(user);

                if (Objects.equals(userLoginRequest.getPassword(), user.getPassword())) {
                    response = Response.ok(body, MediaType.APPLICATION_JSON_TYPE).build();
                } else {
                    response = Response.status(Response.Status.UNAUTHORIZED).build();
                }
            }
        } else {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        JPAEMProvider.close();
        return response;
    }
}
