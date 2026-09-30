package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.CommonPage;

import java.util.List;

public class CommonStepdefs extends CommonPage {

    @Given("I go to url {string}")
    public static void iGoToUrl(String url) {
        getUrl(url);
    }

    @Given("I select the {string} link")
    public void iSelectTheLink(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

    @Then("I should be on the {string} page")
    public void i_should_be_on_the_page(String expectedPage) {

        String pageTitle = driver.getTitle();
        String pageHeading = driver.findElement(By.tagName("h1")).getText();

        Assert.assertTrue("Expected '" + expectedPage + "' but page title was '" + pageTitle + "' and heading was '" + pageHeading + "'",
                pageTitle.contains(expectedPage) || pageHeading.equals(expectedPage)
        );
    }

    @Then("I click the Start now button")
    public void i_click_the_start_now_button() {
        driver.findElement(By.linkText("Start now")).click();
    }

    @When("I search for company {string} and view the company details")
    public void i_search_for_company_and_view_the_company_details(String companyName) {
        driver.findElement(By.id("site-search-text")).sendKeys(companyName);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Then("I validate that the details are displayed")
    public void iValidateThatTheDetailsAreDisplayed(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);

        String CompanyStatusExpected = data.get(1).get(1);
        String CompanyTypeExpected = data.get(2).get(1);
        String IncorporatedExpected = data.get(3).get(1);

        System.out.println("CompanyStatus " + CompanyStatusExpected);
        System.out.println("CompanyType " + CompanyTypeExpected);
        System.out.println("Incorporated " + IncorporatedExpected);

        String CompanyStatusActual = driver.findElement(By.id("company-status")).getText();
        String CompanyTypeActual = driver.findElement(By.id("company-type")).getText();
        String IncorporatedActual = driver.findElement(By.id("company-creation-date")).getText();

        Assert.assertEquals(CompanyStatusExpected, CompanyStatusActual);
        Assert.assertEquals(CompanyTypeExpected, CompanyTypeActual);
        Assert.assertEquals(IncorporatedExpected, IncorporatedActual);
    }

    @When("I search for AWRS URN {string}")
    public void i_search_for_awrs_urn(String urn) {
       driver.findElement(By.id("query")).sendKeys(urn);
    }

    @When("I click the continue button")
    public void i_click_the_continue_button() {
        driver.findElement(By.id("search")).click();
    }

    @Then("I should see the {string} page")
    public void i_should_see_the_page(String page) {
        String pageHeading = driver.findElement(By.id("not-found")).getText();

        Assert.assertTrue(pageHeading.toLowerCase().contains(page.toLowerCase())
        );
    }
}