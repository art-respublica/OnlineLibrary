package ru.innopolis.uni.course3.exception;

/**
 *
 */
public class ExceptionUtil {

    private ExceptionUtil() {
    }

    public static void checkUserNotFoundWithId(boolean found, int id) throws WrongProcessingOfUserException {
        checkUserNotFound(found, "id=" + id);
    }

    public static <T> T checkUserNotFoundWithId(T object, int id) throws WrongProcessingOfUserException {
        return checkUserNotFound(object, "id=" + id);
    }

    public static <T> T checkUserNotFound(T object, String msg) throws WrongProcessingOfUserException {
        checkUserNotFound(object != null, msg);
        return object;
    }

    public static void checkUserNotFound(boolean found, String msg) throws WrongProcessingOfUserException {
        if (!found) {
            throw new WrongProcessingOfUserException("Something wrong with user " + msg);
        }
    }

    public static void checkBookNotFoundWithId(boolean found, int id) throws WrongProcessingOfBookException {
        checkBookNotFound(found, "id=" + id);
    }

    public static <T> T checkBookNotFoundWithId(T object, int id) throws WrongProcessingOfBookException {
        return checkBookNotFound(object, "id=" + id);
    }

    public static <T> T checkBookNotFound(T object, String msg) throws WrongProcessingOfBookException {
        checkBookNotFound(object != null, msg);
        return object;
    }

    public static void checkBookNotFound(boolean found, String msg) throws WrongProcessingOfBookException {
        if (!found) {
            throw new WrongProcessingOfBookException("Something wrong with book " + msg);
        }
    }
}
