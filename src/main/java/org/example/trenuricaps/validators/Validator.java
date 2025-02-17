package org.example.trenuricaps.validators;

public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}