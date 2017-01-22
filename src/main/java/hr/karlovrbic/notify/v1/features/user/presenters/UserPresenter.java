package hr.karlovrbic.notify.v1.features.user.presenters;

import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.features.user.interactors.*;
import hr.karlovrbic.notify.v1.features.user.requests.UserCreateRequest;
import hr.karlovrbic.notify.v1.features.user.requests.UserLoginRequest;
import hr.karlovrbic.notify.v1.utils.UserChecker;
import org.apache.commons.validator.routines.EmailValidator;

import javax.ws.rs.core.Response;
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
    private IUser.UnfollowEventInteractor unfollowEventInteractor;


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
        this.unfollowEventInteractor = new UnfollowEventInteractor();
    }

    @Override
    public Response createUser(UserCreateRequest request) {
        if (isValidRequest(request)) {
            return createInteractor.create(request);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Override
    public Response loginUser(UserLoginRequest request) {
        if (isValidLoginRequest(request)) {
            return loginInteractor.login(request);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Override
    public Response getAllUsers() {
        return getAllInteractor.getAll();
    }

    @Override
    public Response getUserById(Long id) {
        if (id > 0L) {
            return getByIdInteractor.get(id);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Override
    public Response getUserByUsername(String username) {
        if (username == null || username.isEmpty()) {
            return getByUsernameInteractor.get(username);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Override
    public Response getEventByCreatorId(Long creatorId) {
        if (creatorId > 0L) {
            return getEventsByCreatorIdInteractor.get(creatorId);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Override
    public Response getEventByFollowerId(Long followerId) {
        if (followerId > 0L) {
            return getEventsByFollowerIdInteractor.get(followerId);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Override
    public Response followEvent(Long userId, Long eventId) {
        if (userId > 0L && eventId > 0L) {
            return followEventInteractor.follow(userId, eventId);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Override
    public Response unfollowEvent(Long userId, Long eventId) {
        if (userId > 0L && eventId > 0L) {
            return unfollowEventInteractor.unfollow(userId, eventId);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
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
