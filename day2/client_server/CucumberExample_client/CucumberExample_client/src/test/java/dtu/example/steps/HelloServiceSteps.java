package dtu.example.steps;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/*
	Replace the class with your own step definition
   	classes.
 */
public class HelloServiceSteps {
	String result;
	HelloService service = new HelloService();
	
	@When("I call the hello service")
	public void iCallTheHelloService() {
		result = service.hello();
	}
	/*
	在 @Then("I get the answer {string}") 这个步骤里，Cucumber会把你在 .feature 场景中写的字符串参数
	（比如 "Hello RESTEasy"）自动传入到方法参数 string，
	然后用 assertEquals(string, result) 来比较“期望值 vs 实际值”。
	*/
	@Then("I get the answer {string}")
	public void iGetTheAnswer(String string) {
		assertEquals(string, result);
	}
}