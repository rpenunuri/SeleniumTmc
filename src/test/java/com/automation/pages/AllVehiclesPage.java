package com.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.assertj.core.api.Assertions.assertThat;

public class AllVehiclesPage extends BaseWebPageEntity {
    
    @FindBy(css = "h4.matches")
    private WebElement vehicleMatches;

    @FindBy(css = "input[id$='minprice-field']")
    private WebElement minMSRPInput;

    @FindBy(css = "input[id$='maxprice-field']")
    private WebElement maxMSRPInput;

    @FindBy(css = ".vehicles-grid .vehicle-card.selected")
    private List<WebElement> vehicleCards;

    public AllVehiclesPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected ExpectedCondition<Boolean> readyCondition() {
        return ExpectedConditions.titleContains("New Toyota Cars For Sale | New Car Prices");
    }

    public String getVechileMatchesText() {
        return getText(this.vehicleMatches);
    }

    public AllVehiclesPage filterByMSRP(String minMSRP, String maxMSRP) {
        String currentMatchesText = getVechileMatchesText();
        setMinMSRP(minMSRP);
        setMaxMSRP(maxMSRP);
        waitForTextToChange(this.vehicleMatches, currentMatchesText);
        return this;
    }

    public AllVehiclesPage setMinMSRP(String minMSRP) {
        doubleClick(this.minMSRPInput);
        sendKeys(this.minMSRPInput, minMSRP + Keys.ENTER);
        return this;
    }

    public AllVehiclesPage setMaxMSRP(String maxMSRP) {
        doubleClick(this.maxMSRPInput);
        sendKeys(this.maxMSRPInput, maxMSRP + Keys.ENTER);
        return this;
    }

    public void assertThatAllVehiclesAreBetweenPrices(Integer minMSRP, Integer maxMSRP) {
        for (WebElement vehicleCard : this.vehicleCards) {
            String msrpText = vehicleCard.findElement(By.cssSelector(".meta .header")).getText();
            
            int msrpValue = Integer.parseInt(msrpText.replaceAll("[^0-9]", ""));

            assertThat(msrpValue).isBetween(minMSRP, maxMSRP);
        }
    }
}
