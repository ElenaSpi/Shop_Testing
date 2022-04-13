package mk.ukim.finki.test.model.exceptions;

public class PasswordsDoNotMatch extends RuntimeException {
    public PasswordsDoNotMatch() {
        super(String.format("Passwords do not match! Please try again!"));
    }
}
