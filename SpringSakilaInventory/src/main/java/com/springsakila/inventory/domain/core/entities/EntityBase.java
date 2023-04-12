package com.springsakila.inventory.domain.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Transient;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class EntityBase<E> {

    @Transient
    @JsonIgnore
    @SuppressWarnings("unchecked")
    public Set<ConstraintViolation<E>> getErrors() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        return validator.validate((E) this);
    }

    @JsonIgnore
    @Transient
    public String getErrorsMessage() {
        Set<ConstraintViolation<E>> lst = getErrors();
        if (lst.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder("ERRORS:");
        getErrorsFields().forEach((fld, err) -> sb.append(" ").append(fld).append(": ").append(err).append("."));
        return sb.toString();
    }

    @JsonIgnore
    @Transient
    public Map<String, String> getErrorsFields() {
        Set<ConstraintViolation<E>> lst = getErrors();
        if (lst.isEmpty())
            return null;
        Map<String, String> errors = new HashMap<>();
        lst.stream().sorted(Comparator.comparing(a -> a.getPropertyPath().toString()))
                .forEach(item -> errors.put(item.getPropertyPath().toString(),
                        (errors.containsKey(item.getPropertyPath().toString()) ?
                                errors.get(item.getPropertyPath().toString()) + ", " : "")
                                + item.getMessage()));
        return errors;
    }

    @Transient
    @JsonIgnore
    public boolean isValid() {
        return getErrors().size() == 0;
    }

    @Transient
    @JsonIgnore
    public boolean isInvalid() {
        return !isValid();
    }

}