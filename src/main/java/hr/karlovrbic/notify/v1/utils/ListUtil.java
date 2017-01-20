package hr.karlovrbic.notify.v1.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karlo Vrbic on 02.11.16..
 */
public class ListUtil {

    private ListUtil() {
    }

    public static <E> List<E> getNonNull(List<E> list) {
        if (list == null) {
            return new ArrayList<>(0);
        } else {
            return list;
        }
    }
}
