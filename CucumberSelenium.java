//1)LoginSteps.java

package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	
	@Given("user is on login page")
	public void user_is_on_login_page() {
	    System.out.println("Inside step - User is on login page");
	}
	@When("user enters username and password")
	public void user_enters_username_and_password() {
		System.out.println("Inside step - User enters username and password");
	}
	@And("clicks on login button")
	public void clicks_on_login_button() {
		System.out.println("Inside step - Clicks on login button");
	}
	@Then("user is navigated to the home page")
	public void user_is_navigated_to_the_home_page() {
		System.out.println("Inside step - User is navigated to the home page");
	}

}

//2)SwagLabs.java

package StepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.*;

public class SwagLabs {

	WebDriver driver = null;
	
	@SuppressWarnings("deprecation")
	@Given("browser is open")
	public void browser_is_open() {
	    System.out.println("Inside step - Browser is open");
	    System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
	    driver = new ChromeDriver();
	    
	    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	    driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    
	}

	@And("user is on swaglab page")
	public void user_is_on_swaglab_page() {
		System.out.println("Inside step - User is on swag lab page");
        
		driver.navigate().to("https://www.saucedemo.com/");
	}

	@When("user enter valid login credentials")
	public void user_enter_valid_login_credentials() throws InterruptedException {
		System.out.println("Inside step - User enter valid credentials");
		driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
		Thread.sleep(2000);
		
	}

	@And("hits enter")
	public void hits_enter() throws InterruptedException {
		System.out.println("Inside step - User hits enter");
		driver.findElement(By.name("login-button")).click();
		Thread.sleep(2000);
	}
	
	@Then("user is getting logged in")
	public void user_is_getting_logged_in() {
		System.out.println("Inside step - User is logged in");
	}
	
	@When("user selects dropdown list")
	public void user_selects_dropdown_list() throws InterruptedException {
		driver.findElement(By.className("product_sort_container")).click();
		Thread.sleep(2000);
		Select drpoptions = new Select(driver.findElement(By.className("product_sort_container")));
		drpoptions.selectByVisibleText("Name (Z to A)");
	}
	
	@When("user add item to cart and checkout")
	public void user_add_item_to_cart_and_checkout() throws InterruptedException {
		driver.findElement(By.name("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
		Thread.sleep(2000);
		driver.findElement(By.className("shopping_cart_badge")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("checkout")).click();
		Thread.sleep(2000);	
		driver.findElement(By.id("first-name")).sendKeys("Shraddha");
		Thread.sleep(2000);
		driver.findElement(By.id("last-name")).sendKeys("Solkar");
		Thread.sleep(2000);
		driver.findElement(By.id("postal-code")).sendKeys("411041");
		Thread.sleep(2000);
	}
	
	@And("click continue and finish")
	public void click_continue_and_finish() throws InterruptedException {
		driver.findElement(By.id("continue")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("finish")).click();
		Thread.sleep(2000);
	}

	@And("logout")
	public void logout() throws InterruptedException {
		driver.findElement(By.id("react-burger-menu-btn")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("logout_sidebar_link")).click();
		Thread.sleep(2000);
		driver.close();
	}
	
}

//3)TestRunner.java

package StepDefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features", glue= {"StepDefinitions"}, monochrome = true,
    plugin={"pretty","junit:target/JUnitReports/report.xml",
    "json:target/JSONReports/report.json",
    "html:target/HtmlReports/report.html"},
tags="@Smoketest"
	
)
public class TestRunner {

}

//test/resources
//Features

//1)login.feature

# Author
# Date
# Description
@SmokeFeature
Feature: Feature to test login functionality
@Smoketest
  Scenario: Check login successful with valid credentials
    Given user is on login page
    When user enters username and password
    And clicks on login button
    Then user is navigated to the home page
    
//2)SwagLabFeature.java

Feature: Feature to test login of SwagLabs

Scenario: Validate Swaglab login is working

Given browser is open
And user is on swaglab page
When user enter valid login credentials
And hits enter
When user selects dropdown list
When user add item to cart and checkout
And click continue and finish
And logout
Then user is getting logged in

