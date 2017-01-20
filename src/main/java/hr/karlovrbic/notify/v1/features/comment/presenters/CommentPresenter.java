package hr.karlovrbic.notify.v1.features.comment.presenters;

import hr.karlovrbic.notify.v1.features.comment.IComment;
import hr.karlovrbic.notify.v1.features.comment.interactors.CommentByIdInteractor;
import hr.karlovrbic.notify.v1.features.comment.interactors.CommentByMessageIdInteractor;
import hr.karlovrbic.notify.v1.features.comment.interactors.CommentCreateInteractor;
import hr.karlovrbic.notify.v1.features.comment.requests.CommentCreateRequest;
import hr.karlovrbic.notify.v1.model.json.CommentJson;

import java.util.List;

/**
 * Created by Karlo Vrbic on 12.01.17..
 */
public class CommentPresenter implements IComment.Presenter {

    private IComment.View view;
    private IComment.CreateInteractor createInteractor;
    private IComment.GetByMessageIdInteractor getByMessageIdInteractor;
    private IComment.GetByIdInteractor getByIdInteractor;

    public CommentPresenter(IComment.View view) {
        this.view = view;
        this.createInteractor = new CommentCreateInteractor();
        this.getByMessageIdInteractor = new CommentByMessageIdInteractor();
        this.getByIdInteractor = new CommentByIdInteractor();
    }

    @Override
    public CommentJson createComment(CommentCreateRequest request) {
        if (isValidRequest(request)) {
            return createInteractor.create(request);
        } else {
            return null;
        }
    }

    @Override
    public List<CommentJson> getCommentByMessageId(Long messageId) {
        List<CommentJson> jsons = null;

        if (messageId > 0L) {
            jsons = getByMessageIdInteractor.get(messageId);
            if (jsons != null && !jsons.isEmpty()) {
                return jsons;
            }
        }

        return jsons;
    }

    @Override
    public CommentJson getCommentByCommentId(Long id) {
        if (id > 0L) {
            return getByIdInteractor.get(id);
        } else {
            return null;
        }
    }

    private boolean isValidRequest(CommentCreateRequest request) {
        return request.getContent() != null && !request.getContent().trim().isEmpty();
    }
}
