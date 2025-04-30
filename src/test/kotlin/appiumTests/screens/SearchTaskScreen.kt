package appiumTests.screens

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.SelenideElement
import io.qameta.allure.Step
import org.openqa.selenium.By

class SearchTaskScreen {

    val searchTextField:SelenideElement
        get() = element(By.xpath("//android.widget.EditText"))

    val searchButton:SelenideElement
        get() = element(By.xpath("//android.view.View[@content-desc=\"Search\"]"))

    fun getTaskInList(taskName: String): SelenideElement {
        return element(By.xpath("//android.widget.TextView[@text=\"${taskName}\"]"))
    }

    @Step("Search text {searchText}")
    fun runSearchTasks(searchText: String){

        searchTextField.should(Condition.visible).click()
        searchTextField.should(Condition.visible).value = searchText
        searchTextField.should(Condition.visible).text.equals(searchText)

        searchButton.should(Condition.visible).click()
    }

    @Step("Open task {taskName} from search list")
    fun openTaskFromList(taskName: String){

        getTaskInList(taskName).should(Condition.visible).click()
    }
}