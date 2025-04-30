package appiumTests.screens

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.SelenideElement
import io.qameta.allure.Step
import org.openqa.selenium.By

class TasksScreen {

    //Text labels and Headers

    val tasksHeader:SelenideElement
        get() = element(By.xpath("(//android.widget.TextView[@text=\"Tasks\"])[1]"))

    val tasksCompleteText:SelenideElement
        get() = element(By.xpath("//android.widget.TextView[@text=\"Wow! All tasks are completed!\"]"))

    // Buttons

    val createTaskButton:SelenideElement
        get() = element(By.xpath("//android.widget.Button"))

    val completeTaskButton:SelenideElement
        get() = element(By.xpath("//android.widget.RadioButton"))

    fun getTasksByCategory(categoryName: String): SelenideElement {
        return element(By.xpath("//android.widget.TextView[@text=\"${categoryName}\"]"))
    }

    fun getTaskInList(taskName: String): SelenideElement {
        return element(By.xpath("//android.widget.TextView[@text=\"${taskName}\"]"))
    }

    @Step("Create new task")
    fun createNewTask(){
        createTaskButton.should(Condition.visible).click()
    }

    @Step("Select '{categoryName}' tasks")
    fun selectCategoryTasks(categoryName: String){
        getTasksByCategory(categoryName).should(Condition.visible).click()
    }

    @Step("Open task '{taskName}' for edit")
    fun editTask(categoryName: String){
        getTaskInList(categoryName).should(Condition.visible).click()
    }

    @Step("Complete '{taskName}'")
    fun completeTask(){
        completeTaskButton.should(Condition.visible).click()
    }

}