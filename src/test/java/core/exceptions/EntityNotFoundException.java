package core.exceptions;

public class EntityNotFoundException extends NullPointerException {
    public EntityNotFoundException(String entityName){
        super("No entity found with name: " + entityName);
    }
}
