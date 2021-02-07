package test_suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import page_objects_pattern.RegistrationTest;
import training_camp.AlertElementTest;
import training_camp.TrainingCampTest;

@RunWith(Suite.class)
@SuiteClasses({
	RegistrationTest.class,
	AlertElementTest.class,
	TrainingCampTest.class
})
public class TestsSuit {

}
