package hr.karlovrbic.notify.v1.features.comment.view;

import hr.karlovrbic.notify.v1.features.comment.IComment;
import hr.karlovrbic.notify.v1.features.comment.presenters.CommentPresenter;
import hr.karlovrbic.notify.v1.features.comment.requests.CommentCreateRequest;
import hr.karlovrbic.notify.v1.model.json.CommentJson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Karlo Vrbic on 12.01.17..
 */
@Path("events/{eventId}/messages/{messageId}/comments")
@Produces(MediaType.APPLICATION_JSON)
public class CommentView implements IComment.View {

    private IComment.Presenter presenter;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createComment(CommentCreateRequest request) {
        CommentJson json = createPresenter().createComment(request);

        if (json != null) {
            return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.ok().build();
        }
    }

    @GET
    public Response getCommentByEventId(@PathParam("messageId") long messageId) {
        List<CommentJson> json = createPresenter().getCommentByMessageId(messageId);

        if (json != null && !json.isEmpty()) {
            return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.ok().build();
        }
    }

    @GET
    @Path("/{commentId}")
    public Response getCommentByCommentId(@PathParam("commentId") long commentId) {
        CommentJson json = createPresenter().getCommentByCommentId(commentId);

        if (json != null) {
            return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.ok().build();
        }
    }

    private IComment.Presenter createPresenter() {
        if (this.presenter == null) {
            this.presenter = new CommentPresenter(this);
        }
        return this.presenter;
    }
}
