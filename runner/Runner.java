package runner;

import org.testng.annotations.DataProvider;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features ="src/test/java/feature" , glue={"steps"}, monochrome=true)
public class Runner extends AbstractTestNGCucumberTests {

	@DataProvider
    public Object[][] scenarios() {
       return super.scenarios();
    }

}
