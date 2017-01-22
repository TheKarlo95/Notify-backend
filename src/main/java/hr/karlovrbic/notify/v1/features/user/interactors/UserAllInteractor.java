package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.model.entity.User;
import hr.karlovrbic.notify.v1.model.json.UserResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class UserAllInteractor implements IUser.GetAllInteractor {

    @SuppressWarnings("unchecked")
    @Override
    public Response getAll() {
        List<User> users = JPAEMProvider.getEntityManager().createNamedQuery("User.selectAll").getResultList();

        Response response = null;
        if (users != null && !users.isEmpty()) {
            List<UserResponse> body = users.stream()
                    .map(UserResponse::fromEntity)
                    .collect(Collectors.toList());

            response = Response.ok(body, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            response = Response.noContent().build();
        }

        JPAEMProvider.close();
        return response;
    }
}
