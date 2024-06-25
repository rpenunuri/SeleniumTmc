package com.automation.tests.homepage;
import com.automation.pages.AllVehiclesPage;
import com.automation.pages.HomePage;
import com.automation.tests.BaseSystemCase;
import org.testng.annotations.Test;


public class UserCanNavigateToAllVehiclesAndFilterThemByMSRPE2E extends BaseSystemCase {

    @Test
    public void testCase() {
        HomePage homePage = goToHomePage();
        AllVehiclesPage allVehiclesPage = homePage.clickExploreAllVehiclesLink();

        allVehiclesPage.filterByMSRP("20000", "30000")
                .assertThatAllVehiclesAreBetweenPrices(20000, 30000);

            
    }
}
