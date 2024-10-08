import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//******************************************* from city *******************************************\\

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/one_way_checkbox'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/from_city'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/input_text_box'))

WebUI.setText(findTestObject('Object Repository/input_text_box'), GlobalVariable.from_city)

WebUI.click(findTestObject('Object Repository/select_the_from_city_option'))

//******************************************* to city *******************************************\\

WebUI.delay(2)

//WebUI.click(findTestObject('Object Repository/one_way_checkbox'))

//WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/to_city'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/to_input_text_box'))

WebUI.delay(2)

WebUI.setText(findTestObject('Object Repository/to_input_text_box'), GlobalVariable.to_city)

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/to_city_select'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/date_selected'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/search_button'))



//project make my trip flight data automation
//By ADITYA_AGARWAL
//project