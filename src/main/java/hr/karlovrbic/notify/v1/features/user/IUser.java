package hr.karlovrbic.notify.v1.features.user;

import hr.karlovrbic.notify.v1.features.shared.IBase;
import hr.karlovrbic.notify.v1.features.user.requests.TokenUpdateRequest;
import hr.karlovrbic.notify.v1.features.user.requests.UserCreateRequest;
import hr.karlovrbic.notify.v1.features.user.requests.UserLoginRequest;

import javax.ws.rs.core.Response;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public interface IUser {

    interface View extends IBase.View {
    }

    interface Presenter extends IBase.Presenter {
        Response createUser(UserCreateRequest request);

        Response loginUser(UserLoginRequest request);

        Response updateToken(long id, TokenUpdateRequest request);

        Response getAllUsers();

        Response getUserById(Long id);

        Response getUserByUsername(String username);

        Response getEventByCreatorId(Long creatorId);

        Response getEventByFollowerId(Long followerId);

        Response followEvent(Long userId, Long eventId);

        Response unfollowEvent(Long userId, Long eventId);
    }

    interface CreateInteractor extends IBase.Interactor {
        Response create(UserCreateRequest request);
    }

    interface LoginInteractor extends IBase.Interactor {
        Response login(UserLoginRequest request);
    }

    interface UpdateTokenInteractor extends IBase.Interactor {
        Response updateToken(Long id, TokenUpdateRequest request);
    }

    interface GetAllInteractor extends IBase.Interactor {
        Response getAll();
    }

    interface GetByIdInteractor extends IBase.Interactor {
        Response get(Long id);
    }

    interface GetByUsernameInteractor extends IBase.Interactor {
        Response get(String username);
    }

    interface GetEventsByCreatorIdInteractor extends IBase.Interactor {
        Response get(Long creatorId);
    }

    interface GetEventsByFollowerIdInteractor extends IBase.Interactor {
        Response get(Long followerId);
    }

    interface FollowEventInteractor extends IBase.Interactor {
        Response follow(Long userId, Long eventId);
    }

    interface UnfollowEventInteractor extends IBase.Interactor {
        Response unfollow(Long userId, Long eventId);
    }
}
