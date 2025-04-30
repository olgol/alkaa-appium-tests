package appiumTests

import appiumTests.screens.CategoriesScreen
import appiumTests.screens.EditCategoryScreen
import appiumTests.screens.EditTaskScreen
import appiumTests.screens.MenuHelper
import appiumTests.screens.SearchTaskScreen
import appiumTests.screens.TasksScreen
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.openqa.selenium.remote.DesiredCapabilities
import java.lang.Thread.sleep
import java.net.URL
import kotlin.test.assertEquals


class AndroidUITest {

    val menuHelper = MenuHelper()

    val categoriesScreen = CategoriesScreen()
    val editCategoryScreen = EditCategoryScreen()
    val editTaskScreen = EditTaskScreen()
    val searchTaskScreen = SearchTaskScreen()
    val tasksScreen = TasksScreen()

    @BeforeEach
    fun beforEach() {
        val appPath = "${System.getProperty("user.dir")}/app/alkaa.apk"
        val caps = DesiredCapabilities()
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android")
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554")
        caps.setCapability(MobileCapabilityType.APP, appPath)
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2")
        caps.setCapability(MobileCapabilityType.FULL_RESET, true);
        caps.setCapability("newCommandTimeout", 300)

        val driver = AndroidDriver(URL("http://127.0.0.1:4723/wd/hub"), caps)

        WebDriverRunner.setWebDriver(driver)
    }

    // Categories tests
    @Test
    fun createNewCategory() {

        val newCategoryName ="New category"

        menuHelper.gotoScreen("Categories")
        categoriesScreen.addNewCategory()
        editCategoryScreen.createCategory(newCategoryName)
        assertEquals(categoriesScreen.getCategoryInList(newCategoryName).exists(), true)
        categoriesScreen.editCategory(newCategoryName)
        editCategoryScreen.deleteCategory()
    }

    @Test
    fun updateExistingCategory() {

        val oldCategoryName ="Work"
        val newCategoryName ="Relax"

        menuHelper.gotoScreen("Categories")
        categoriesScreen.editCategory(oldCategoryName)
        editCategoryScreen.editCategory(newCategoryName)
        assertEquals(categoriesScreen.getCategoryInList(newCategoryName).exists(), true)
        categoriesScreen.editCategory(newCategoryName)
        editCategoryScreen.editCategory(oldCategoryName)
        assertEquals(categoriesScreen.getCategoryInList(oldCategoryName).exists(), true)

    }

    @Test
    fun deleteCategory() {

        val newCategoryName ="Category for delete"

        menuHelper.gotoScreen("Categories")
        categoriesScreen.addNewCategory()
        editCategoryScreen.createCategory(newCategoryName)
        assertEquals(categoriesScreen.getCategoryInList(newCategoryName).exists(), true)
        categoriesScreen.editCategory(newCategoryName)
        editCategoryScreen.deleteCategory()
        assertEquals(categoriesScreen.getCategoryInList(newCategoryName).exists(), false)
    }

    //Tasks tests

    @Test
    fun createNewTaskWithCategory() {
        val taskName = "Task with category"
        val taskCategory = "Personal"

        tasksScreen.createNewTask()
        editTaskScreen.addTaskWithCategory(taskName,taskCategory)
        tasksScreen.selectCategoryTasks(taskCategory)
        assertEquals(tasksScreen.getTaskInList(taskName).exists(), true)
        tasksScreen.completeTask()
    }

    @Test
    fun createNewTaskWithoutCategory() {
        val taskName = "Task without category"
        val taskCategory = "Personal"

        tasksScreen.createNewTask()
        editTaskScreen.addTaskWoCategory(taskName)
        tasksScreen.getTasksByCategory(taskCategory)
        assertEquals(tasksScreen.getTaskInList(taskName).exists(), true)
        tasksScreen.completeTask()
    }

    @Test
    fun createTaskWithDescription() {
        val taskName = "Task with description"
        val taskCategory = "Personal"
        val taskDescription = "Task description"

        tasksScreen.createNewTask()
        editTaskScreen.addTaskWithCategory(taskName,taskCategory)
        tasksScreen.selectCategoryTasks(taskCategory)
        tasksScreen.editTask(taskName)
        editTaskScreen.addTaskDescription(taskDescription)
        assertEquals(tasksScreen.getTaskInList(taskName).exists(), true)
        tasksScreen.completeTask()

    }

    @Test
    fun completeTask() {
        val taskName = "Task for complete"
        val taskCategory = "Personal"

        tasksScreen.createNewTask()
        editTaskScreen.addTaskWithCategory(taskName,taskCategory)
        tasksScreen.getTasksByCategory(taskCategory)
        assertEquals(tasksScreen.getTaskInList(taskName).exists(), true)
        tasksScreen.completeTask()
        assertEquals(tasksScreen.getTaskInList(taskName).exists(), false)
    }

    @Test
    fun searchTask() {
        val taskName = "Task for Search"
        val taskCategory = "Personal"

        tasksScreen.createNewTask()
        editTaskScreen.addTaskWithCategory(taskName,taskCategory)
        menuHelper.gotoScreen("Search")
        searchTaskScreen.runSearchTasks(taskName)
        assertEquals(searchTaskScreen.getTaskInList(taskName).exists(), true)
        searchTaskScreen.openTaskFromList(taskName)
        editTaskScreen.editScreenBackButton.click()
        menuHelper.gotoScreen("Tasks")
        tasksScreen.selectCategoryTasks(taskCategory)
        tasksScreen.completeTask()
    }

    @AfterEach
    fun tearDown() {
        Selenide.closeWebDriver()
    }
}