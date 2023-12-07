package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.AccessibleObject;

public class CategoryTest {
    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiateACategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var excpectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, excpectedIsActive);
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(excpectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMassage = "'name' should not be null";
        final var expectedDescription = "A categoria mais assistida";
        final var excpectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, excpectedIsActive);


        final var actualException = Assertions.assertThrows(DomainException.class,
                () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMassage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = "   ";
        final var expectedErrorCount = 1;
        final var expectedErrorMassage = "'name' should not be empty";
        final var expectedDescription = "A categoria mais assistida";
        final var excpectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, excpectedIsActive);


        final var actualException = Assertions.assertThrows(DomainException.class,
                () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMassage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLangthLessThan3_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = "Fi ";
        final var expectedErrorCount = 1;
        final var expectedErrorMassage = "'name' must be betwenn 3 and 255 characteres";
        final var expectedDescription = "A categoria mais assistida";
        final var excpectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, excpectedIsActive);


        final var actualException = Assertions.assertThrows(DomainException.class,
                () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMassage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLangthMoreThan255_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = """ 
                A leitura nos ajuda a aprender sobre diferentes culturas, história, ciência, tecnologia e muito mais.
                Ela nos permite desenvolver nosso pensamento crítico, criatividade e capacidade de resolver problemas.
                A leitura também pode nos ajudar a relaxar e desestressar.""";
        final var expectedErrorCount = 1;
        final var expectedErrorMassage = "'name' must be betwenn 3 and 255 characteres";
        final var expectedDescription = "A categoria mais assistida";
        final var excpectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, excpectedIsActive);


        final var actualException = Assertions.assertThrows(DomainException.class,
                () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMassage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidEmptyDescription_whenCallNewCategoryAndValidate_thenShouldReceiveOK() {
        final var expectedName = "Filmes";
        final var expectedDescription = "  ";
        final var expectedIsActive = true;

        final var actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidFalseIsActive_whenCallNewCategoryAndValidate_thenShouldReceiveOK() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;

        final var actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNotNull(actualCategory.getDeletedAt());
    }
}
