package com.yaksha.training.blog.boundary;


import com.yaksha.training.blog.entity.Blog;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.yaksha.training.blog.utils.MasterData.getBlog;
import static com.yaksha.training.blog.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.blog.utils.TestUtils.boundaryTestFile;
import static com.yaksha.training.blog.utils.TestUtils.currentTest;
import static com.yaksha.training.blog.utils.TestUtils.testReport;
import static com.yaksha.training.blog.utils.TestUtils.yakshaAssert;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testNameNotBlank() throws Exception {
        Blog blog = getBlog();
        blog.setName("");
        Set<ConstraintViolation<Blog>> violations = validator.validate(blog);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testNameNotNull() throws Exception {
        Blog blog = getBlog();
        blog.setName(null);
        Set<ConstraintViolation<Blog>> violations = validator.validate(blog);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testNameMinThree() throws Exception {
        Blog blog = getBlog();
        blog.setName(randomStringWithSize(2));
        Set<ConstraintViolation<Blog>> violations = validator.validate(blog);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testNameMaxTwenty() throws Exception {
        Blog blog = getBlog();
        blog.setName(randomStringWithSize(21));
        Set<ConstraintViolation<Blog>> violations = validator.validate(blog);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testTitleNotBlank() throws Exception {
        Blog blog = getBlog();
        blog.setTitle("");
        Set<ConstraintViolation<Blog>> violations = validator.validate(blog);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testTitleNotNull() throws Exception {
        Blog blog = getBlog();
        blog.setTitle(null);
        Set<ConstraintViolation<Blog>> violations = validator.validate(blog);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testTitleMinThree() throws Exception {
        Blog blog = getBlog();
        blog.setTitle(randomStringWithSize(2));
        Set<ConstraintViolation<Blog>> violations = validator.validate(blog);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testTitleMaxTwenty() throws Exception {
        Blog blog = getBlog();
        blog.setTitle(randomStringWithSize(21));
        Set<ConstraintViolation<Blog>> violations = validator.validate(blog);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testDescriptionNotBlank() throws Exception {
        Blog blog = getBlog();
        blog.setDescription("");
        Set<ConstraintViolation<Blog>> violations = validator.validate(blog);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testDescriptionNotNull() throws Exception {
        Blog blog = getBlog();
        blog.setDescription(null);
        Set<ConstraintViolation<Blog>> violations = validator.validate(blog);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testDescriptionMinThree() throws Exception {
        Blog blog = getBlog();
        blog.setDescription(randomStringWithSize(2));
        Set<ConstraintViolation<Blog>> violations = validator.validate(blog);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testDescriptionMaxTwoHundred() throws Exception {
        Blog blog = getBlog();
        blog.setDescription(randomStringWithSize(201));
        Set<ConstraintViolation<Blog>> violations = validator.validate(blog);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }


}
