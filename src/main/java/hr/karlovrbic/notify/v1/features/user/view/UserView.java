package hr.karlovrbic.notify.v1.features.user.view;

import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.features.user.presenters.UserPresenter;
import hr.karlovrbic.notify.v1.features.user.requests.UserCreateRequest;
import hr.karlovrbic.notify.v1.features.user.requests.UserLoginRequest;

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
        return createPresenter().getEventByCreatorId(followerId);
    }

    @PUT
    @Path("/{id}/followed/{eventId}")
    public Response followEvent(@PathParam("id") long followerId) {
        return createPresenter().getEventByCreatorId(followerId);
    }

    private IUser.Presenter createPresenter() {
        if (this.presenter == null) {
            this.presenter = new UserPresenter(this);
        }
        return this.presenter;
    }
}
