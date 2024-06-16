package com.smartjob.createuser.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorTest {

    @Test
    public void testIsValidEmail_ValidEmail() {
        assertTrue(EmailValidator.isValidEmail("test@example.com"));
        assertTrue(EmailValidator.isValidEmail("user.name+tag+sorting@example.com"));
        assertTrue(EmailValidator.isValidEmail("user_name@example.co.uk"));
        assertTrue(EmailValidator.isValidEmail("user-name@domain.co"));
    }

    @Test
    public void testIsValidEmail_InvalidEmail() {
        assertFalse(EmailValidator.isValidEmail("plainaddress"));
        assertFalse(EmailValidator.isValidEmail("@missingusername.com"));
        assertFalse(EmailValidator.isValidEmail("sdddsdmissingusername.com"));
    }

    @Test
    public void testIsValidEmail_NullEmail() {
        assertFalse(EmailValidator.isValidEmail(null));
    }

    @Test
    public void testIsValidEmail_EmptyString() {
        assertFalse(EmailValidator.isValidEmail(""));
    }
}
