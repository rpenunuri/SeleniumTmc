package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BaseWebPageEntity {

    @FindBy(linkText = "Explore All Vehicles")
    private WebElement exploreAllVehiclesLink;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected ExpectedCondition<Boolean> readyCondition() {
        return ExpectedConditions.titleContains("New Cars, Trucks, SUVs & Hybrids | Toyota Official Site");
    }

    public AllVehiclesPage clickExploreAllVehiclesLink() {
        click(exploreAllVehiclesLink);
        return page().allVehiclesPage();

    }
}
