package mk.ukim.finki.test.model.exceptions;

public class ManufacturerNotFound extends RuntimeException {
    public ManufacturerNotFound(Long manufacturerID) {
        super(String.format("Manufacturer with ID %d was not found!", manufacturerID));
    }
}
