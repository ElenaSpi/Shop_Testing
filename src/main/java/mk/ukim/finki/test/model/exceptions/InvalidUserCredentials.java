package mk.ukim.finki.test.model.exceptions;

public class InvalidUserCredentials extends RuntimeException {
    public InvalidUserCredentials() {
        super("Invalid user credentials! The username or password was not correct!");
    }
}
