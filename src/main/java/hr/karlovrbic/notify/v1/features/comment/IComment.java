package hr.karlovrbic.notify.v1.features.comment;

import hr.karlovrbic.notify.v1.features.comment.requests.CommentCreateRequest;
import hr.karlovrbic.notify.v1.features.shared.IBase;
import hr.karlovrbic.notify.v1.model.json.CommentJson;

import java.util.List;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
public interface IComment {

    interface View extends IBase.View {
    }

    interface Presenter extends IBase.Presenter {
        CommentJson createComment(CommentCreateRequest eventJson);

        List<CommentJson> getCommentByMessageId(Long eventId);

        CommentJson getCommentByCommentId(Long id);
    }

    interface CreateInteractor extends IBase.Interactor {
        CommentJson create(CommentCreateRequest eventJson);
    }

    interface GetByMessageIdInteractor extends IBase.Interactor {
        List<CommentJson> get(Long eventId);
    }

    interface GetByIdInteractor extends IBase.Interactor {
        CommentJson get(Long id);
    }
}
