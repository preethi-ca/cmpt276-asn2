package com.cmpt276.asn2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class StaffRatingTest {

    // test #1: overall score
    @Test
    public void testOverallScore() {
        StaffRating rating = new StaffRating(
                "Preethi Chidambaram",
                "pca115@sfu.ca",
                RoleType.TA,
                10,
                9,
                8,
                "The best TA I've ever had!");

        assertEquals(9.0, rating.getOverallScore(), 0.000001);
    }

    // test #2: TA display title
    @Test
    public void testDisplayTitle() {
        StaffRating rating = new StaffRating(
                "Preethi Chidambaram",
                "pca115@sfu.ca",
                RoleType.TA,
                10,
                9,
                8,
                "The best TA I've ever had!");

        assertEquals("Teaching Assistant", rating.getDisplayTitle());
    }

    // test #3: invalid email
    @Test
    public void testInvalidEmail() {
        Validator validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        StaffRating rating = new StaffRating(
                "Preethi Chidambaram",
                "pca115@",
                RoleType.TA,
                10,
                9,
                8,
                "The best TA I've ever had!");

        assertFalse(validator.validate(rating).isEmpty());
    }

    // test #4: score above 10
    @Test
    public void testInvalidScore() {
        Validator validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        StaffRating rating = new StaffRating(
                "Preethi Chidambaram",
                "pca115@sfu.ca",
                RoleType.TA,
                11,
                9,
                8,
                "The best TA I've ever had!");

        assertFalse(validator.validate(rating).isEmpty());
    }

    // test #5: empty name
    @Test
    public void testMissingName() {
        Validator validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        StaffRating rating = new StaffRating(
                "",
                "pca115@sfu.ca",
                RoleType.TA,
                10,
                9,
                8,
                "The best TA I've ever had!");

        assertFalse(validator.validate(rating).isEmpty());
    }
}