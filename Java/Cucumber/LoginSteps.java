package com.lifeimage.automation.steps.admin;

import com.lifeimage.automation.util.PropertyReader;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.lifeimage.automation.pages.admin.AdminHomePage;
import com.lifeimage.automation.pages.admin.login.LoginPage;
import com.lifeimage.automation.util.StepsContext;
import com.lifeimage.automation.util.WebUtil;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by mafzal on 3/6/16.
 */
public class LoginSteps
{
    private LoginPage signInPage = new LoginPage();
    private AdminHomePage landingHomePage;
    private StepsContext stepsContext;
    private WebDriver driver;

    public LoginSteps(StepsContext stepsContext)
    {
        this.stepsContext = stepsContext;
    }

    @Given("^User is on admin login page$")
    public void user_is_on_admin_login_page() throws Throwable
    {
        driver = stepsContext.getDriver();
        String url = WebUtil.getAdminAppLoginURL();
        if (driver.getCurrentUrl() != null && driver.getCurrentUrl().startsWith(url))
        {
            stepsContext.getDriver().get(url + "/j_spring_security_logout");
        }
        else
        {
           Thread.sleep(2000);
           stepsContext.getDriver().get(url+"/login");
           WebUtil.refreshPage(driver);
        }

    }

    @When("^User enters Username \"([^\"]*)\" on admin login page$")
    public void user_enters_Username(String userName) throws Throwable
    {
        signInPage.fillInUsername(driver, userName);
    }

    @When("^User enters default admin Password$")
    public void user_enters_default_admin_Password() throws Throwable
    {
        signInPage.fillInPassword(driver, new PropertyReader().readProperty("ADMIN_PASSWORD"));
        signInPage.clickSignIn(driver);
    }

    @Then("^User lands on admin home page$")
    public void user_lands_on_admin_home_page() throws Throwable
    {
        WebUtil.waitForElementVisible(driver, By.cssSelector("#content"));

    }

    @When("^User enters Password \"([^\"]*)\" on admin login page$")
    public void user_enters_Password(String password) throws Throwable
    {
        signInPage.fillInPassword(driver, password);
        signInPage.clickSignIn(driver);
    }

    @Then("^User should see incorrect credentials error message on admin login page$")
    public void user_should_see_incorrect_credentials_error_message() throws Throwable
    {
        Assert.assertTrue("Failed to login", signInPage.isIncorrectCredentialErrorMsgExist(driver));
    }
}
