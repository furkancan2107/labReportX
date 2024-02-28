package com.rf.labrex.validation;

import com.rf.labrex.repository.BaseUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueNumberValid implements ConstraintValidator<UniqueNumber,String> {

    private final BaseUserRepository repository;

    public UniqueNumberValid(BaseUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(repository.existsByIdentificationNumber(s)) return false;
        return true;
    }
}
