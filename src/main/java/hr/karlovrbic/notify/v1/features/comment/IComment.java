package hr.karlovrbic.notify.v1.features.comment;

import com.sun.istack.internal.NotNull;
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
        CommentJson createComment(@NotNull CommentCreateRequest eventJson);

        List<CommentJson> getCommentByMessageId(@NotNull Long eventId);

        CommentJson getCommentByCommentId(@NotNull Long id);
    }

    interface CreateInteractor extends IBase.Interactor {
        CommentJson create(@NotNull CommentCreateRequest eventJson);
    }

    interface GetByMessageIdInteractor extends IBase.Interactor {
        List<CommentJson> get(@NotNull Long eventId);
    }

    interface GetByIdInteractor extends IBase.Interactor {
        CommentJson get(@NotNull Long id);
    }
}
