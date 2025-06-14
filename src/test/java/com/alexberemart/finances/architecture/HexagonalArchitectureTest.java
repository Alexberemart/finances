package com.alexberemart.finances.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;

class HexagonalArchitectureTest {

    private static final String DOMAIN_PACKAGE = "..domain..";
    private static final String APPLICATION_PACKAGE = "..application..";
    private static final String INFRASTRUCTURE_PACKAGE = "..infraestructure..";

    @Test
    void domainShouldNotDependOnInfrastructure() {
        // Good practice: The domain layer must be independent of infrastructure.
        JavaClasses classes = new ClassFileImporter().importPackages("com.alexberemart.finances");
        ArchRuleDefinition.noClasses()
            .that().resideInAPackage(DOMAIN_PACKAGE)
            .should().dependOnClassesThat().resideInAPackage(INFRASTRUCTURE_PACKAGE)
            .check(classes);
    }

    @Test
    void domainShouldNotDependOnApplication() {
        // Good practice: The domain layer must be independent of the application layer.
        JavaClasses classes = new ClassFileImporter().importPackages("com.alexberemart.finances");
        ArchRuleDefinition.noClasses()
            .that().resideInAPackage(DOMAIN_PACKAGE)
            .should().dependOnClassesThat().resideInAPackage(APPLICATION_PACKAGE)
            .check(classes);
    }

    @Test
    void infrastructureShouldDependOnDomain() {
        // Good practice: Infrastructure can depend on domain, but not the other way around.
        JavaClasses classes = new ClassFileImporter().importPackages("com.alexberemart.finances");
        ArchRuleDefinition.classes()
            .that().resideInAPackage(INFRASTRUCTURE_PACKAGE)
            .and().areNotAnnotatedWith(Configuration.class) // Exclude @Configuration
            .and().haveNameNotMatching(".*\\$.*") // Exclude inner classes
            .should().dependOnClassesThat().resideInAPackage(DOMAIN_PACKAGE)
            .check(classes);
    }

    @Test
    void applicationShouldDependOnDomain() {
        // Good practice: The application layer can depend on the domain layer.
        JavaClasses classes = new ClassFileImporter().importPackages("com.alexberemart.finances");
        ArchRuleDefinition.classes()
            .that().resideInAPackage(APPLICATION_PACKAGE)
            .should().dependOnClassesThat().resideInAPackage(DOMAIN_PACKAGE)
            .allowEmptyShould(true) // Avoids warnings if application layer is empty
            .check(classes);
    }

    @Test
    void domainShouldNotDependOnSpring() {
        // Good practice: The domain layer must not depend on Spring or any framework.
        JavaClasses classes = new ClassFileImporter().importPackages("com.alexberemart.finances");
        ArchRuleDefinition.noClasses()
            .that().resideInAPackage(DOMAIN_PACKAGE)
            .should().dependOnClassesThat().resideInAnyPackage("org.springframework..")
            .check(classes);
    }

    @Test
    void applicationShouldNotDependOnSpring() {
        // Good practice: The application layer must not depend on Spring or any framework.
        JavaClasses classes = new ClassFileImporter().importPackages("com.alexberemart.finances");
        ArchRuleDefinition.noClasses()
            .that().resideInAPackage(APPLICATION_PACKAGE)
            .should().dependOnClassesThat().resideInAnyPackage("org.springframework..")
            .allowEmptyShould(true) // Avoids warnings if application layer is empty
            .check(classes);
    }
}
