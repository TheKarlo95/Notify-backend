package hr.karlovrbic.notify.v1.features.user.presenters;

import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.features.user.interactors.*;
import hr.karlovrbic.notify.v1.features.user.requests.UserCreateRequest;
import hr.karlovrbic.notify.v1.model.json.EventJson;
import hr.karlovrbic.notify.v1.model.json.UserJson;
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
    private IUser.GetAllInteractor getAllInteractor;
    private IUser.GetByIdInteractor getByIdInteractor;
    private IUser.GetByUsernameInteractor getByUsernameInteractor;
    private IUser.GetEventsByCreatorIdInteractor getEventsByCreatorIdInteractor;

    public UserPresenter(IUser.View view) {
        this.view = view;
        this.createInteractor = new UserCreateInteractor();
        this.getAllInteractor = new UserAllInteractor();
        this.getByIdInteractor = new UserByIdInteractor();
        this.getByUsernameInteractor = new UserByUsernameInteractor();
        this.getEventsByCreatorIdInteractor = new EventsByCreatorIdInteractor();
    }

    private static boolean isValidRequest(UserCreateRequest request) {
        return UserChecker.isValidUserName(request.getUsername())
                && Objects.equals(request.getPassword(), request.getPasswordConfirmation())
                && UserChecker.isValidPassword(request.getPassword())
                && EmailValidator.getInstance().isValid(request.getEmail())
                && UserChecker.isValidName(request.getName())
                && UserChecker.isValidSurname(request.getSurname());
    }

    @Override
    public UserJson createUser(UserCreateRequest request) {
        if (isValidRequest(request)) {
            return createInteractor.create(request);
        } else {
            return null;
        }
    }

    @Override
    public List<UserJson> getAllUsers() {
        List<UserJson> jsons = getAllInteractor.getAll();
        if (jsons != null && !jsons.isEmpty()) {
            return jsons;
        } else {
            return null;
        }
    }

    @Override
    public UserJson getUserById(Long id) {
        if (id > 0L) {
            return getByIdInteractor.get(id);
        } else {
            return null;
        }
    }

    @Override
    public UserJson getUserByUsername(String username) {
        if (UserChecker.isValidUserName(username)) {
            return getByUsernameInteractor.get(username);
        } else {
            return null;
        }
    }

    @Override
    public List<EventJson> getEventByCreatorId(Long creatorId) {
        if (creatorId > 0L) {
            return getEventsByCreatorIdInteractor.get(creatorId);
        } else {
            return null;
        }
    }
}
