package com.automation.steps;

import com.automation.actions.BrowserActions;
import com.automation.actions.CommandActions;
import com.automation.actions.SetupActions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SearchSteps extends PathLocators{


    @Given("^I go to the website \"([^\"]*)\"$")
    public void i_go_to_website(String website) throws Throwable {
        BrowserActions stepActions = new BrowserActions(SetupActions.getDriver());
        stepActions.goToUrl(website);
    }

    @And("^I find and click on the link \"([^\"]*)\"$")
    public void i_find_and_click_on_the_link(String textButton) throws Throwable {
        CommandActions myCommand = new CommandActions(SetupActions.getDriver());
        WebElement elem = myCommand.findLink(textButton);
        assertTrue(elem.getText().toLowerCase().contains(textButton));
        elem.click();
    }

    @When("^I click on \"([^\"]*)\"$")
    public void i_click_on(String button) throws Throwable {
        CommandActions myCommand = new CommandActions(SetupActions.getDriver());
        assertTrue(myCommand.findX(myLocators(button)));
        myCommand.clickButton(myLocators(button));
    }

    @When("^I input \"([^\"]*)\" to the field \"([^\"]*)\"$")
    public void i_input_to_the_field(String input, String field) throws Throwable {
        CommandActions myCommand = new CommandActions(SetupActions.getDriver());
        WebElement elem = myCommand.findInputfield(myLocators(field));
        elem.sendKeys(input);

        Thread.sleep(2000);
//        elem.sendKeys(Keys.ENTER);
    }

    @When("^I input \"([^\"]*)\" to the final field \"([^\"]*)\"$")
    public void i_input_to_the_final_field(String input, String field) throws Throwable {
        CommandActions myCommand = new CommandActions(SetupActions.getDriver());
        myCommand.findX(myLocators(field));
        WebElement elem = myCommand.findInputfield(myLocators(field));
        elem.sendKeys(input);
        elem.sendKeys(Keys.RETURN);
        Thread.sleep(2000);
        elem.sendKeys(Keys.ENTER);
    }

    @Then("^I can see the link with text \"([^\"]*)\"$")
    public void i_can_see_the_link_with_text(String text) throws Throwable {
        CommandActions myCommand = new CommandActions(SetupActions.getDriver());
//        myCommand.findX(//*[@id="__next"]/main/section[1]/div/div/div[1]/h1)
        List<WebElement> elem = myCommand.findLinkText(text);
        assertTrue(elem.get(0).getText().contains(text));
        List<String> links = new ArrayList<String>();
        for(WebElement elements:elem){
            String tests = elements.getAttribute("href");
            links.add(tests);
        }

//        FileWriter csvWriter = new FileWriter(System.getProperty("user.dir")+ "\\src\\test\\resources\\file.csv", true);
//        csvWriter.append("Job Salary");
//        csvWriter.append(",");
//        csvWriter.append("Job Title");
//        csvWriter.append(",");
//        csvWriter.append("Job type");
//        csvWriter.append(",");
//        csvWriter.append("Job Location");
//        csvWriter.append(",");
//        csvWriter.append("Employer");
//        csvWriter.append("\n");


        for(String link:links){
            myCommand.goToPage(link);
            if(myCommand.findX(myLocators("I Accept")))
                myCommand.clickButton(myLocators("I Accept"));

            String mySalary = myCommand.getText(myLocators("Salary"));
            String myJobTitle = myCommand.getText(myLocators("Job title"));
            String myJobType = myCommand.getText(myLocators("Job type"));
            String myJobLocation = myCommand.getText(myLocators("Job location"));
            String myEmployer = myCommand.getText(myLocators("Employer"));


//            csvWriter.append(mySalary);
//            csvWriter.append(",");
//            csvWriter.append(myJobTitle);
//            csvWriter.append(",");
//            csvWriter.append(myJobType);
//            csvWriter.append(",");
//            csvWriter.append(myJobLocation);
//            csvWriter.append(",");
//            csvWriter.append(myEmployer);

            Thread.sleep(5000);
        }
//        csvWriter.close();
    }

    @And("I click on the {string} link")
    public void iClickOnTheLink(String link) {
        CommandActions myCommand = new CommandActions(SetupActions.getDriver());
        WebElement elem = myCommand.findText(myLocators(link));
        elem.click();
    }

    @And("I find the employer details")
    public void iFindTheEmployerDetails() {
        CommandActions myCommand = new CommandActions(SetupActions.getDriver());
        String myCompanyWebsite = myCommand.myElem(myLocators("Company Website")).getAttribute("href");
        String numberOfEmployees = myCommand.getText(myLocators("Number of Employees"));
        String companyName = myCommand.myElem(myLocators("Job title")).getAttribute("title");

    }
}
