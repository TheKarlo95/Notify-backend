package hr.karlovrbic.notify.v1.features.user;

import hr.karlovrbic.notify.v1.features.shared.IBase;
import hr.karlovrbic.notify.v1.features.user.requests.UserCreateRequest;
import hr.karlovrbic.notify.v1.features.user.requests.UserLoginRequest;
import hr.karlovrbic.notify.v1.model.json.EventJson;
import hr.karlovrbic.notify.v1.model.json.UserJson;
import hr.karlovrbic.notify.v1.model.json.UserResponse;

import java.util.List;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public interface IUser {

    interface View extends IBase.View {
    }

    interface Presenter extends IBase.Presenter {
        UserJson createUser(UserCreateRequest request);

        UserJson loginUser(UserLoginRequest request);

        List<UserJson> getAllUsers();

        UserJson getUserById(Long id);

        UserJson getUserByUsername(String username);

        List<EventJson> getEventByCreatorId(Long creatorId);
    }

    interface CreateInteractor extends IBase.Interactor {
        UserResponse create(UserCreateRequest userCreateRequest);
    }

    interface GetAllInteractor extends IBase.Interactor {
        List<UserResponse> getAll();
    }

    interface GetByIdInteractor extends IBase.Interactor {
        UserResponse get(Long id);
    }

    interface GetByUsernameInteractor extends IBase.Interactor {
        UserResponse get(String username);
    }

    interface GetEventsByCreatorIdInteractor extends IBase.Interactor {
        List<EventJson> get(Long creatorId);
    }
}
