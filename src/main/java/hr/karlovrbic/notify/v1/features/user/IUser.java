package hr.karlovrbic.notify.v1.features.user;

import hr.karlovrbic.notify.v1.features.event.response.EventResponse;
import hr.karlovrbic.notify.v1.features.shared.IBase;
import hr.karlovrbic.notify.v1.features.user.requests.UserCreateRequest;
import hr.karlovrbic.notify.v1.features.user.requests.UserLoginRequest;
import hr.karlovrbic.notify.v1.model.json.UserResponse;

import java.util.List;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public interface IUser {

    interface View extends IBase.View {
    }

    interface Presenter extends IBase.Presenter {
        UserResponse createUser(UserCreateRequest request);

        UserResponse loginUser(UserLoginRequest request);

        List<UserResponse> getAllUsers();

        UserResponse getUserById(Long id);

        UserResponse getUserByUsername(String username);

        List<EventResponse> getEventByCreatorId(Long creatorId);

        List<EventResponse> getEventByFollowerId(Long followerId);

        UserResponse followEvent(Long userId, Long eventId);
    }

    interface CreateInteractor extends IBase.Interactor {
        UserResponse create(UserCreateRequest userCreateRequest);
    }

    interface LoginInteractor extends IBase.Interactor {
        UserResponse login(UserLoginRequest userLoginRequest);
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
        List<EventResponse> get(Long creatorId);
    }

    interface GetEventsByFollowerIdInteractor extends IBase.Interactor {
        List<EventResponse> get(Long followerId);
    }

    interface FollowEventInteractor extends IBase.Interactor {
        UserResponse get(Long userId, Long eventId);
    }
}
