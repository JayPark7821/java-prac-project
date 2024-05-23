package kr.jay.migration.application.user;

/**
 * AlreadyAgreedException
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/23/24
 */
public class AlreadyAgreedException extends RuntimeException{

    public AlreadyAgreedException(String message) {
        super(message);
    }
}
