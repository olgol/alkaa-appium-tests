package appiumTests.screens

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.SelenideElement
import io.qameta.allure.Step
import org.openqa.selenium.By

class EditCategoryScreen {

    val categoryNameTextEdit:SelenideElement
        get() = element(By.xpath("//android.widget.EditText"))

    val categorySaveButton:SelenideElement
        get() = element(By.xpath("//android.widget.TextView[@text=\"Save\"]"))

    val categoryDeleteButton:SelenideElement
        get() = element(By.xpath("//android.view.View[@content-desc=\"Remove\"]"))

    val confirmCategoryDeleteButton:SelenideElement
        get() = element(By.xpath("//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button"))


    @Step("Edit {categoryName} category")
    fun editCategory(categoryName: String){

        categoryNameTextEdit.should(Condition.visible).clear()
        createCategory(categoryName)
    }

    @Step("Create {categoryName} category")
    fun createCategory(categoryName: String){

        categoryNameTextEdit.should(Condition.visible).click()
        categoryNameTextEdit.should(Condition.visible).value = categoryName
        categoryNameTextEdit.text.equals(categoryName)

        categorySaveButton.should(Condition.visible).click()
    }
    @Step("Delete category")
    fun deleteCategory(){
        categoryDeleteButton.should(Condition.visible).click()
        confirmCategoryDeleteButton.should(Condition.visible).click()
    }
}