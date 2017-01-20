package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.model.entity.User;
import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.model.json.UserJson;

import javax.validation.constraints.NotNull;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public class UserByIdInteractor implements IUser.GetByIdInteractor {

    @Override
    public UserJson get(@NotNull Long id) {
        User user = JPAEMProvider.getEntityManager().find(User.class, id);
        JPAEMProvider.close();

        UserJson userJson = null;
        if (user != null) {
            userJson = user.toJson();
        }

        return userJson;
    }
}
