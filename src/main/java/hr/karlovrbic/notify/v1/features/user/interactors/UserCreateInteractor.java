package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.features.user.requests.UserCreateRequest;
import hr.karlovrbic.notify.v1.model.entity.User;
import hr.karlovrbic.notify.v1.model.json.UserJson;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class UserCreateInteractor implements IUser.CreateInteractor {

    @Override
    public UserJson create(@NotNull UserCreateRequest userCreateRequest) {
        User user = userCreateRequest.toEntity();

        EntityManager em = JPAEMProvider.getEntityManager();
        em.persist(user);
        JPAEMProvider.close();

        UserJson userJson = null;
        if (user != null) {
            userJson = user.toJson();
        }

        return userJson;
    }
}
