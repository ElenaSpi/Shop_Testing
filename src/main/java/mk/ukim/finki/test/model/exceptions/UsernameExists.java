package mk.ukim.finki.test.model.exceptions;

public class UsernameExists extends RuntimeException {
    public UsernameExists() {
        super(String.format("Username already exists!"));
    }
}
