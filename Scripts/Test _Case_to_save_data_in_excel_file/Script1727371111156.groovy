import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.nio.file.Files
import java.nio.file.Paths

// Read the file
File file = new File("C:\\Users\\DELL\\OneDrive\\Desktop\\mail\\Flight_Details_make_my_trip.txt")
List<String> lines = file.readLines()

// Split the file content into individual datasets using the separator
List<List<String>> datasets = []
List<String> currentDataset = []

lines.each { line ->
	if (line.contains("--------------------------------------------------------")) {
		if (currentDataset) {
			datasets.add(currentDataset)
			currentDataset = []
		}
	} else {
		currentDataset.add(line)
	}
}

// Don't forget to add the last dataset
if (currentDataset) {
	datasets.add(currentDataset)
}

// Create a new Excel workbook and sheet
Workbook workbook = new XSSFWorkbook()
Sheet sheet = workbook.createSheet("Flight Details")

// Create header row
Row header = sheet.createRow(0)
header.createCell(0).setCellValue("Date")
header.createCell(1).setCellValue("Flight Name")
header.createCell(2).setCellValue("Flight ID")
header.createCell(3).setCellValue("Price")
header.createCell(4).setCellValue("Departure Airport")
header.createCell(5).setCellValue("Arrival Airport")
header.createCell(6).setCellValue("Total Time")
header.createCell(7).setCellValue("Non-stop/With Stops")
header.createCell(8).setCellValue("Departure Time")
header.createCell(9).setCellValue("Arrival Time")

// Variable to keep track of row number in the Excel file
int rowNum = 1

// Iterate over each dataset and extract the details
datasets.each { dataset ->
	String date = "Unknown"
	String departureTime = "Unknown"
	String arrivalTime = "Unknown"
	String fromLocation = "Unknown"
	String toLocation = "Unknown"
	String flightCarrier = "Unknown"
	String ticketPrice = "Unknown"
	String flightDuration = "Unknown"
	String flightId = "Unknown"
	String nonStopOrWithStops = "Unknown"

	dataset.eachWithIndex { line, index ->
		// Date extraction
		if (index == 0 && line.contains("Date:")) {
			date = line.split(":")[1].trim()
		}

		// Flight carrier extraction
		if (index == 1 && line.contains("Flight Name:")) {
			flightCarrier = line.split(":")[1].trim()
		}

		// Flight ID extraction
		if (index == 2 && line.contains("Flight ID:")) {
			flightId = line.split(":")[1].trim()
		}

		// Ticket price extraction
		if (index == 3 && line.contains("Price:")) {
			ticketPrice = line.split(":")[1].trim().replace("₹", "").replace(",", "")
		}

		// Departure Airport extraction
		if (index == 4 && line.contains("Departure Airport:")) {
			fromLocation = line.split(":")[1].trim()
		}

		// Arrival Airport extraction
		if (index == 5 && line.contains("Arrival Airport:")) {
			toLocation = line.split(":")[1].trim()
		}

		// Total time (flight duration) extraction
		if (index == 6 && line.contains("Total Time:")) {
			flightDuration = line.split(":")[1].trim()
		}

		// Non-stop or with stops extraction
		if (index == 7) {
			nonStopOrWithStops = line.trim()
		}

		// Departure Time extraction
		if (index == 8 && line.contains("Departure Time:")) {
			departureTime = line.split("Departure Time:")[1].trim()
		}

		// Arrival Time extraction
		if (index == 9 && line.contains("Arrival Time:")) {
			arrivalTime = line.split("Arrival Time:")[1].trim()
		}
	}

	// Write the extracted data to the Excel file
	Row row = sheet.createRow(rowNum++)
	row.createCell(0).setCellValue(date)
	row.createCell(1).setCellValue(flightCarrier)
	row.createCell(2).setCellValue(flightId)
	row.createCell(3).setCellValue(ticketPrice)
	row.createCell(4).setCellValue(fromLocation)
	row.createCell(5).setCellValue(toLocation)
	row.createCell(6).setCellValue(flightDuration)
	row.createCell(7).setCellValue(nonStopOrWithStops)
	row.createCell(8).setCellValue(departureTime)
	row.createCell(9).setCellValue(arrivalTime)

	// Write ticket price as a number if it’s numeric
	if (ticketPrice.isDouble()) {
		row.createCell(3).setCellValue(ticketPrice.toDouble()) // Save as numeric value
	} else {
		row.createCell(3).setCellValue(ticketPrice) // Save as text if it’s not numeric
	}
}

// Save the Excel file
FileOutputStream fileOut = new FileOutputStream("C:\\Users\\DELL\\OneDrive\\Desktop\\mail\\Flight_Details_make_my_trip_data_updated.xlsx")
workbook.write(fileOut)
fileOut.close()
workbook.close()

println "All flight details saved to Excel with proper ticket price formatting!"
