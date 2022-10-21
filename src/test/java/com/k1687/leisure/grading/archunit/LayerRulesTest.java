package com.k1687.leisure.grading.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class LayerRulesTest {

    private JavaClasses javaClasses;

    @BeforeEach
    public void setup(){
        javaClasses = new ClassFileImporter().importPackages("com.k1687.leisure.grading");
    }

    @Test
    public void layerAccessViolationsTest(){
        ArchRule rule = noClasses()
                .that().resideInAPackage("..repository..")
                .should().dependOnClassesThat().resideInAPackage("..controller..");

        rule.check(javaClasses);
    }

    //@Test
    // TODO
    public void noCyclesTest(){
        ArchRule rule = slices().matching("com.k1687.leisure.grading.(*)..")
                .should().beFreeOfCycles();

        rule.check(javaClasses);
    }

    @Test
    public void controllerNamingConvention(){
        ArchRule rule = classes().that().resideInAPackage("..controller..")
                .and().areAnnotatedWith(RestController.class)
                .should().haveSimpleNameEndingWith("Controller");

        rule.check(javaClasses);
    }

    @Test
    public void repositoryNamingConvention(){
        ArchRule rule = classes().that().areInterfaces().and().resideInAPackage("..repository..")
                //.and().implement(CrudRepository.class)
                .should().haveSimpleNameEndingWith("Repository");

        rule.check(javaClasses);
    }

    @Test
    public void layerArchitectureRules(){
        Architectures.LayeredArchitecture rule = layeredArchitecture().consideringAllDependencies()
                .layer("Controller").definedBy("..controller..")
                .layer("Service").definedBy("..service..")
                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller");

        rule.check(javaClasses);
    }
}
