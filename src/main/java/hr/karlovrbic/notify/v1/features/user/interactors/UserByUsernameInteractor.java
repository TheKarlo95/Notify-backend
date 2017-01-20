package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.model.entity.User;
import hr.karlovrbic.notify.v1.model.json.UserJson;

import java.util.List;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public class UserByUsernameInteractor implements IUser.GetByUsernameInteractor {

    @SuppressWarnings("unchecked")
    @Override
    public UserJson get(String username) {
        List<User> users = JPAEMProvider.getEntityManager()
                .createNamedQuery("User.selectByUsername")
                .setParameter("username", username)
                .getResultList();

        User user = null;
        if (users != null && users.size() == 1) {
            user = users.get(0);
        }
        UserJson userJson = null;
        if (user != null) {
            userJson = user.toJson();
        }

        return userJson;
    }
}
