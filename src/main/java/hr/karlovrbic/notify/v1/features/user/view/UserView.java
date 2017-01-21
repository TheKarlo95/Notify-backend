package hr.karlovrbic.notify.v1.features.user.view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hr.karlovrbic.notify.v1.features.user.IUser;
import hr.karlovrbic.notify.v1.features.user.presenters.UserPresenter;
import hr.karlovrbic.notify.v1.features.user.requests.UserCreateRequest;
import hr.karlovrbic.notify.v1.features.user.requests.UserLoginRequest;
import hr.karlovrbic.notify.v1.model.json.EventJson;
import hr.karlovrbic.notify.v1.model.json.UserResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Karlo Vrbic on 07.01.17..
 */
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserView implements IUser.View {

    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    private IUser.Presenter presenter;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(UserCreateRequest request) {
        UserResponse json = createPresenter().createUser(request);

        if (json != null) {
            return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).status(201).build();
        } else {
            return Response.ok().build();
        }
    }

    @GET
    public Response getAll() {
        List<UserResponse> json = createPresenter().getAllUsers();

        if (json != null && !json.isEmpty()) {
            return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.ok().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(UserLoginRequest request) {
        UserResponse json = createPresenter().loginUser(request);

        if (json != null) {
            return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.ok().build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getUsersById(@PathParam("id") long id) {
        UserResponse json = createPresenter().getUserById(id);

        if (json != null) {
            return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.ok().build();
        }
    }

    @GET
    @Path("/name/{username}")
    public Response getUsersById(@PathParam("username") String username) {
        UserResponse json = createPresenter().getUserByUsername(username);

        if (json != null) {
            return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.ok().build();
        }
    }

    @GET
    @Path("/{id}/events")
    public Response getEventsByCreatorId(@PathParam("id") long creatorId) {
        List<EventJson> json = createPresenter().getEventByCreatorId(creatorId);

        if (json != null) {
            return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.ok().build();
        }
    }

    private IUser.Presenter createPresenter() {
        if (this.presenter == null) {
            this.presenter = new UserPresenter(this);
        }
        return this.presenter;
    }
}
