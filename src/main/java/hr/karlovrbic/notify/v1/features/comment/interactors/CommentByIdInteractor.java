package hr.karlovrbic.notify.v1.features.comment.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.comment.IComment;
import hr.karlovrbic.notify.v1.model.entity.Comment;
import hr.karlovrbic.notify.v1.model.json.CommentJson;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class CommentByIdInteractor implements IComment.GetByIdInteractor {

    @Override
    public CommentJson get(Long id) {
        Comment comment = JPAEMProvider.getEntityManager().find(Comment.class, id);

        CommentJson commentJson = null;
        if (comment != null) {
            commentJson = comment.toJson();
        }
        JPAEMProvider.close();

        return commentJson;
    }
}
