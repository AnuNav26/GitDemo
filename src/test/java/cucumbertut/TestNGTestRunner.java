package cucumbertut;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src\\test\\java\\cucumbertut",glue="AutomationLearning.stepDefinition",monochrome=true,tags="@Regression",plugin= {"html:target/cucumbertut.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
