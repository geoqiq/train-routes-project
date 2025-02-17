package org.example.trenuricaps.validators;

public class ComboBoxValidator implements Validator<String> {
    @Override
    public void validate(String entity) throws ValidationException {
        if (entity == null || entity.isEmpty()) {
            throw new ValidationException("Input cannot be null or empty!");
        }
    }
}
