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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By


// Specify the absolute path to save the file
File file = new File("C:\\Users\\DELL\\OneDrive\\Desktop\\mail\\Flight_Details_make_my_trip.txt")
file.createNewFile()

// Base URL structure for the search page
String baseUrl = "https://www.makemytrip.com/flight/search?itinerary=BOM-BLR-"
String suffixUrl = "&tripType=O&paxType=A-1_C-0_I-0&intl=false&cabinClass=E&ccde=IN&lang=eng"

// Get the current date
LocalDate today = LocalDate.now()

// Loop for the next 20 days
for (int i = 0; i < 20; i++) {
    // Add i days to the current date
    LocalDate futureDate = today.plusDays(i)
    
    // Format the date as 'dd/MM/yyyy'
    String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).replace("/", "/")

    // Construct the full dynamic URL
    String dynamicUrl = baseUrl + formattedDate + suffixUrl

    // Navigate to the new URL
    WebUI.navigateToUrl(dynamicUrl)
    WebUI.delay(5) // Allow time for the page to load

    // Define the TestObjects for the buttons and the "No Flights Found" error message
    TestObject buttonPrimary = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//span[@class='button buttonPrimary pushRight widthFitContent']")
    TestObject buttonOkay = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//button[@class='button buttonSecondry buttonBig fontSize12 relative' and text()='OKAY, GOT IT!']")
    TestObject noFlightsError = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//p[@class='error-title' and text()='No Flights Found']")

    // Check if the "No Flights Found" message is present
    if (WebUI.verifyElementPresent(noFlightsError, 5, FailureHandling.OPTIONAL)) {
        println("No flights found for date: " + formattedDate + ". Loading the next date.")
        continue // Skip to the next date without proceeding further
    }

    // Check for the presence of the first button and click if present
    if (WebUI.verifyElementPresent(buttonPrimary, 5, FailureHandling.OPTIONAL)) {
        WebUI.click(buttonPrimary)
        WebUI.delay(2)
        println("Clicked on 'buttonPrimary' for date: " + formattedDate)
    } 
    // If the first button is not present, check for the second button and click if present
    else if (WebUI.verifyElementPresent(buttonOkay, 5, FailureHandling.OPTIONAL)) {
        WebUI.click(buttonOkay)
        WebUI.delay(2)
        println("Clicked on 'buttonOkay' for date: " + formattedDate)
    }

    // Continue with data extraction after handling buttons or if none were present
    TestObject listingCard = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//div[@class='listingCard  appendBottom5']")

    // Check if flight listings are present for this date
    if (WebUI.verifyElementPresent(listingCard, 5, FailureHandling.OPTIONAL)) {
        println("Listings found for date: " + formattedDate)
        
        // Get the listing elements
        List<WebElement> listings = WebUiCommonHelper.findWebElements(listingCard, 5)

        // Loop through each listing and extract details
        for (WebElement listing : listings) {
            String flightName = listing.findElement(By.xpath(".//div/p[@class='boldFont blackText airlineName']")).getText()
            String flightID = listing.findElement(By.xpath(".//div/p[@class='fliCode']")).getText()
            String departureTime = listing.findElement(By.xpath(".//div[@class='flexOne timeInfoLeft']/p[@class='appendBottom2 flightTimeInfo']")).getText()
            String arrivalTime = listing.findElement(By.xpath(".//div[@class='flexOne timeInfoRight']/p[@class='appendBottom2 flightTimeInfo']")).getText()
            String totalTime = listing.findElement(By.xpath(".//div[@class='stop-info flexOne']")).getText()
            String price = listing.findElement(By.xpath(".//div[@class='blackText fontSize18 blackFont white-space-no-wrap clusterViewPrice']/span")).getText()
            String departureAirport = listing.findElement(By.xpath(".//div[@class='flexOne timeInfoLeft']/p[@class='blackText']")).getText()
            String arrivalAirport = listing.findElement(By.xpath(".//div[@class='flexOne timeInfoRight']/p[@class='blackText']")).getText()

            // Write the details to the console and the text file
            String offerDetails = "Date: " + formattedDate + "\n" +
                                  "Flight Name: " + flightName + "\n" +
                                  "Flight ID: " + flightID + "\n" +
                                  "Price: " + price + "\n" +
                                  "Departure Airport: " + departureAirport + "\n" +
                                  "Arrival Airport: " + arrivalAirport + "\n" +
                                  "Total Time: " + totalTime + "\n" +
                                  "Departure Time: " + departureTime + "\n" +
                                  "Arrival Time: " + arrivalTime + "\n" +
                                  "--------------------------------------------------------\n"

            println(offerDetails)
            file.append(offerDetails)
        }
    } else {
        println("No listings found for date: " + formattedDate + ". Trying the next date.")
    }
}


//project make my trip flight data automation
//By ADITYA_AGARWAL
//project