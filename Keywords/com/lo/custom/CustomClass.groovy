package com.lo.custom
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException


class CustomClass {

	@Keyword
	def login
	import com.kms.katalon.core.annotation.Keyword
	import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
	import com.kms.katalon.core.exception.StepFailedException

	class CustomKeywords {

		/**
		 * Custom Keyword to perform login action.
		 * @param username the username for login
		 * @param password the password for login
		 */
		@Keyword
		def login(admin, admin123) {
			try {
				// Open browser and navigate to the login page
				WebUI.openBrowser('')
				WebUI.navigateToUrl('https://opensource-demo.orangehrmlive.com/')

				// Set username and password
				WebUI.setText(findTestObject('Object Repository/LoginPage/UsernameField'), Admin)
				WebUI.setText(findTestObject('Object Repository/LoginPage/PasswordField'), admin123)

				// Click login button
				WebUI.click(findTestObject('Object Repository/LoginPage/LoginButton'))

				// Add a verification step (optional)
				WebUI.verifyElementPresent(findTestObject('Object Repository/HomePage/WelcomeMessage'), 10)
			} catch (Exception e) {
				throw new StepFailedException('Login failed: ' + e.message)
			} finally {
				// Close the browser
				WebUI.closeBrowser()
			}
		}
	}
}

/**
 * Click element
 * @param to Katalon test object
 */
@Keyword
def clickElement(TestObject to) {
	try {
		WebElement element = WebUiBuiltInKeywords.findWebElement(to)
		KeywordUtil.logInfo("Clicking element")
		element.click()
		KeywordUtil.markPassed("Element has been clicked")
	} catch (WebElementNotFoundException e) {
		KeywordUtil.markFailed("Element not found")
	} catch (Exception e) {
		KeywordUtil.markFailed("Fail to click on element")
	}
}

/**
 * Get all rows of HTML table
 * @param table Katalon test object represent for HTML table
 * @param outerTagName outer tag name of TR tag, usually is TBODY
 * @return All rows inside HTML table
 */
@Keyword
def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
	WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
	List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
	return selectedRows
}
}