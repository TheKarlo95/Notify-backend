package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.features.user.requests.UserLoginRequest;
import hr.karlovrbic.notify.v1.model.entity.User;
import hr.karlovrbic.notify.v1.model.json.UserResponse;

import java.util.List;
import java.util.Objects;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public class UserLoginInteractor implements IUser.LoginInteractor {

    @SuppressWarnings("unchecked")
    @Override
    public UserResponse login(UserLoginRequest userLoginRequest) {
        List<User> users = JPAEMProvider.getEntityManager()
                .createNamedQuery("User.selectByUsername")
                .setParameter("username", userLoginRequest.getUsername())
                .getResultList();

        User user = null;
        if (users != null && users.size() == 1) {
            user = users.get(0);
        }
        UserResponse response = null;
        if (user != null && Objects.equals(userLoginRequest.getPassword(), user.getPassword())) {
            response = UserResponse.fromEntity(user);
        }

        JPAEMProvider.close();
        return response;
    }
}
