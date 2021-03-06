package hr.karlovrbic.notify.v1.features.user.view;

import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.features.user.presenters.UserPresenter;
import hr.karlovrbic.notify.v1.features.user.requests.TokenUpdateRequest;
import hr.karlovrbic.notify.v1.features.user.requests.UserCreateRequest;
import hr.karlovrbic.notify.v1.features.user.requests.UserLoginRequest;
import hr.karlovrbic.notify.v1.http.PATCH;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Karlo Vrbic on 07.01.17..
 */
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserView implements IUser.View {

    private IUser.Presenter presenter;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(UserCreateRequest request) {
        return createPresenter().createUser(request);
    }

    @GET
    public Response getAll() {
        return createPresenter().getAllUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(UserLoginRequest request) {
        return createPresenter().loginUser(request);
    }

    @GET
    @Path("/{id}")
    public Response getUsersById(@PathParam("id") long id) {
        return createPresenter().getUserById(id);
    }

    @PATCH
    @Path("/{id}/token")
    public Response updateToken(@PathParam("id") long id, TokenUpdateRequest request) {
        return createPresenter().updateToken(id, request);
    }

    @GET
    @Path("/name/{username}")
    public Response getUsersById(@PathParam("username") String username) {
        return createPresenter().getUserByUsername(username);
    }

    @GET
    @Path("/{id}/events")
    public Response getEventsByCreatorId(@PathParam("id") long creatorId) {
        return createPresenter().getEventByCreatorId(creatorId);
    }

    @GET
    @Path("/{id}/followed")
    public Response getEventsByFollowerId(@PathParam("id") long followerId) {
        return createPresenter().getEventByFollowerId(followerId);
    }

    @PATCH
    @Path("/{id}/follow/{eventId}")
    public Response followEvent(@PathParam("id")Long userId, @PathParam("eventId")Long eventId) {
        return createPresenter().followEvent(userId, eventId);
    }

    @PATCH
    @Path("/{id}/unfollow/{eventId}")
    public Response unfollowEvent(@PathParam("id")Long userId, @PathParam("eventId")Long eventId) {
        return createPresenter().unfollowEvent(userId, eventId);
    }

    private IUser.Presenter createPresenter() {
        if (this.presenter == null) {
            this.presenter = new UserPresenter(this);
        }
        return this.presenter;
    }
}
