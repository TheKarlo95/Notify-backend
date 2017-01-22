package hr.karlovrbic.notify.v1.features.user.presenters;

import hr.karlovrbic.notify.v1.features.event.response.EventResponse;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.features.user.interactors.*;
import hr.karlovrbic.notify.v1.features.user.requests.UserCreateRequest;
import hr.karlovrbic.notify.v1.features.user.requests.UserLoginRequest;
import hr.karlovrbic.notify.v1.model.json.UserResponse;
import hr.karlovrbic.notify.v1.utils.UserChecker;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;
import java.util.Objects;

/**
 * Created by Karlo Vrbic on 05.01.17..
 */
public final class UserPresenter implements IUser.Presenter {

    private IUser.View view;
    private IUser.CreateInteractor createInteractor;
    private IUser.LoginInteractor loginInteractor;
    private IUser.GetAllInteractor getAllInteractor;
    private IUser.GetByIdInteractor getByIdInteractor;
    private IUser.GetByUsernameInteractor getByUsernameInteractor;
    private IUser.GetEventsByCreatorIdInteractor getEventsByCreatorIdInteractor;
    private IUser.GetEventsByCreatorIdInteractor getEventsByFollowerIdInteractor;
    private IUser.FollowEventInteractor followEventInteractor;


    public UserPresenter(IUser.View view) {
        this.view = view;
        this.createInteractor = new UserCreateInteractor();
        this.loginInteractor = new UserLoginInteractor();
        this.getAllInteractor = new UserAllInteractor();
        this.getByIdInteractor = new UserByIdInteractor();
        this.getByUsernameInteractor = new UserByUsernameInteractor();
        this.getEventsByCreatorIdInteractor = new EventsByCreatorIdInteractor();
        this.getEventsByFollowerIdInteractor = new EventsByFollowerIdInteractor();
        this.followEventInteractor = new FollowEventInteractor();
    }

    @Override
    public UserResponse createUser(UserCreateRequest request) {
        if (isValidRequest(request)) {
            return createInteractor.create(request);
        } else {
            return null;
        }
    }

    @Override
    public UserResponse loginUser(UserLoginRequest request) {
        if (isValidLoginRequest(request)) {
            return loginInteractor.login(request);
        } else {
            return null;
        }
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserResponse> jsons = getAllInteractor.getAll();
        if (jsons != null && !jsons.isEmpty()) {
            return jsons;
        } else {
            return null;
        }
    }

    @Override
    public UserResponse getUserById(Long id) {
        if (id > 0L) {
            return getByIdInteractor.get(id);
        } else {
            return null;
        }
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        if (UserChecker.isValidUserName(username)) {
            return getByUsernameInteractor.get(username);
        } else {
            return null;
        }
    }

    @Override
    public List<EventResponse> getEventByCreatorId(Long creatorId) {
        if (creatorId > 0L) {
            return getEventsByCreatorIdInteractor.get(creatorId);
        } else {
            return null;
        }
    }

    @Override
    public List<EventResponse> getEventByFollowerId(Long followerId) {
        if (followerId > 0L) {
            return getEventsByFollowerIdInteractor.get(followerId);
        } else {
            return null;
        }
    }

    @Override
    public UserResponse followEvent(Long userId, Long eventId) {
        if (userId > 0L && eventId > 0L) {
            return followEventInteractor.get(userId, eventId);
        } else {
            return null;
        }
    }

    private static boolean isValidRequest(UserCreateRequest request) {
        return UserChecker.isValidUserName(request.getUsername())
                && Objects.equals(request.getPassword(), request.getPasswordConfirmation())
                && UserChecker.isValidPassword(request.getPassword())
                && EmailValidator.getInstance().isValid(request.getEmail())
                && UserChecker.isValidName(request.getName())
                && UserChecker.isValidSurname(request.getSurname());
    }

    private boolean isValidLoginRequest(UserLoginRequest request) {
        return UserChecker.isValidUserName(request.getUsername())
                && UserChecker.isValidPassword(request.getPassword());
    }
}
