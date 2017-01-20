package hr.karlovrbic.notify.v1.features.shared;

import hr.karlovrbic.notify.v1.dao.DAOException;

public interface ResponseListener<T> {
        void onSuccess(T result);

        void onError(DAOException exception);
    }