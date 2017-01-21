package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.model.entity.User;
import hr.karlovrbic.notify.v1.model.json.UserResponse;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public class UserByIdInteractor implements IUser.GetByIdInteractor {

    @Override
    public UserResponse get(Long id) {
        User user = JPAEMProvider.getEntityManager().find(User.class, id);

        UserResponse response = null;
        if (user != null) {
            response = UserResponse.fromEntity(user);
        }

        JPAEMProvider.close();
        return response;
    }
}
