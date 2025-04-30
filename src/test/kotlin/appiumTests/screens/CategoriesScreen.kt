package appiumTests.screens

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.SelenideElement
import io.qameta.allure.Step
import org.openqa.selenium.By

class CategoriesScreen {

    val categoriesHeader:SelenideElement
        get() = element(By.xpath("(//android.widget.TextView[@text=\"Categories\"])[1]"))


    val categoryAddButton:SelenideElement
        get() = element(By.xpath("//android.widget.Button"))


    //android.widget.TextView[@text="Work"]

    fun getCategoryInList(categoryName: String): SelenideElement {
        return element(By.xpath("//android.widget.TextView[@text=\"${categoryName}\"]"))
    }

    @Step("Goto Edit {categoryName} category")
    fun editCategory(categoryName: String){
        getCategoryInList(categoryName).should(Condition.visible).click()
    }

    @Step("Add new category")
    fun addNewCategory(){
        categoryAddButton.should(Condition.visible).click()
    }
}