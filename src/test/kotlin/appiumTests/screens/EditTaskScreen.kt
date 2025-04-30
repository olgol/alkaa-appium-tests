package appiumTests.screens

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.SelenideElement
import io.qameta.allure.Step
import org.openqa.selenium.By

class EditTaskScreen {

    val taskNameTextEdit:SelenideElement
        get() = element(By.xpath("//android.widget.EditText"))

    val taskAddButton:SelenideElement
        get() = element(By.xpath("//android.widget.TextView[@text=\"Add\"]"))

    val taskDescriptionTextEdit:SelenideElement
        get() = element(By.xpath("//android.widget.ScrollView/android.widget.EditText[2]/android.view.View[2]"))

    val alarmButton:SelenideElement
        get() = element(By.xpath("//android.view.View[@content-desc=\"Alarm\"]"))

    val noAlarmCategoryButton:SelenideElement
        get() = element(By.xpath("//android.widget.TextView[@text=\"No alarm\"]"))

    val editScreenBackButton:SelenideElement
        get() = element(By.xpath("//android.widget.Button"))

    fun getCategoryButton(taskCategory: String): SelenideElement {
        return element(By.xpath("//android.widget.TextView[@text=\"${taskCategory}\"]"))
    }

    @Step("Select '{taskCategory}' tasks")
    fun selectPersonalTasks(taskCategory: String){
        getCategoryButton(taskCategory).should(Condition.visible).click()
    }

    @Step("Add task '{taskName}' without category tasks")
    fun addTaskWoCategory(taskName: String){

        taskNameTextEdit.should(Condition.visible).value = taskName
        taskNameTextEdit.text.equals(taskName)

        taskAddButton.should(Condition.visible).click()
    }

    @Step("Add task '{taskName}' with '{taskCategory}' task category")
    fun addTaskWithCategory(taskName: String, taskCategory: String){

        taskNameTextEdit.should(Condition.visible).value = taskName
        taskNameTextEdit.text.equals(taskName)

        getCategoryButton(taskCategory).should(Condition.visible).click()

        taskAddButton.should(Condition.visible).click()
    }

    @Step("Add task description '{taskName}' tp task")
    fun addTaskDescription(taskDescription: String){

        taskDescriptionTextEdit.should(Condition.visible).value = taskDescription
        taskNameTextEdit.text.equals(taskDescription)

        editScreenBackButton.should(Condition.visible).click()
    }


}