package mk.ukim.finki.test.model.exceptions;

public class CategoryNotFound extends RuntimeException {
    public CategoryNotFound(Long id) {
        super(String.format("Category with ID: %d was not found!", id));
    }
}
