package mk.ukim.finki.test.model.exceptions;

public class InvalidUsernameOrPassword extends RuntimeException {
    public InvalidUsernameOrPassword() {
        super(String.format("The username or password was not found!"));
    }
}
