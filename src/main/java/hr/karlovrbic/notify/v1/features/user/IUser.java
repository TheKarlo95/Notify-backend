package hr.karlovrbic.notify.v1.features.user;

import hr.karlovrbic.notify.v1.features.shared.IBase;
import hr.karlovrbic.notify.v1.features.user.requests.UserCreateRequest;
import hr.karlovrbic.notify.v1.model.json.EventJson;
import hr.karlovrbic.notify.v1.model.json.UserJson;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public interface IUser {

    interface View extends IBase.View {
    }

    interface Presenter extends IBase.Presenter {
        UserJson createUser(@NotNull UserCreateRequest userJson);

        List<UserJson> getAllUsers();

        UserJson getUserById(@NotNull Long id);

        UserJson getUserByUsername(@NotNull String username);

        List<EventJson> getEventByCreatorId(@NotNull Long creatorId);
    }

    interface CreateInteractor extends IBase.Interactor {
        UserJson create(@NotNull UserCreateRequest userCreateRequest);
    }

    interface GetAllInteractor extends IBase.Interactor {
        List<UserJson> getAll();
    }

    interface GetByIdInteractor extends IBase.Interactor {
        UserJson get(@NotNull Long id);
    }

    interface GetByUsernameInteractor extends IBase.Interactor {
        UserJson get(@NotNull String username);
    }

    interface GetEventsByCreatorIdInteractor extends IBase.Interactor {
        List<EventJson> get(@NotNull Long creatorId);
    }
}
