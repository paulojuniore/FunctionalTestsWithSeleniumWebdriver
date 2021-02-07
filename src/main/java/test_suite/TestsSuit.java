package test_suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import page_objects_pattern.TesteCadastro;
import training_camp.AlertElementTest;
import training_camp.TrainingCampTest;

@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastro.class,
	AlertElementTest.class,
	TrainingCampTest.class
})
public class TestsSuit {

}
