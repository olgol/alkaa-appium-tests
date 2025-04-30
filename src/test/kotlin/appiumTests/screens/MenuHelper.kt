package appiumTests.screens

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.SelenideElement
import io.qameta.allure.Step
import org.openqa.selenium.By

class MenuHelper {

    // Headers


    // Menu
    fun getMenuItem(menuItem: String): SelenideElement {
        return element(By.xpath("//android.widget.TextView[@text=\"${menuItem}\"]"))
    }


    @Step("Goto '{menuItem}' screen")
    fun gotoScreen(menuItem: String){
        getMenuItem(menuItem).should(Condition.visible).click()
    }

}