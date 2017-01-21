package hr.karlovrbic.notify.v1.features.user.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.features.user.requests.UserCreateRequest;
import hr.karlovrbic.notify.v1.model.entity.User;
import hr.karlovrbic.notify.v1.model.json.UserResponse;

import javax.persistence.EntityManager;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class UserCreateInteractor implements IUser.CreateInteractor {

    @Override
    public UserResponse create(UserCreateRequest userCreateRequest) {
        User user = userCreateRequest.toEntity();

        EntityManager em = JPAEMProvider.getEntityManager();
        em.persist(user);
        JPAEMProvider.close();

        UserResponse response = null;
        if (user != null) {
            response = UserResponse.fromEntity(user);
        }

        return response;
    }
}
