# Make-My-Trip Flight Data Automation

This project automates the process of retrieving flight data from the Make-My-Trip website using Katalon Studio and Groovy. The automation is designed to collect flight details for the current date and for the next 20 days, bypass CAPTCHA challenges, and efficiently store and distribute the gathered data via email.

## Project Overview

The primary goal of this project is to simplify the collection of flight data for an extended period (21 days in total) from the Make-My-Trip platform. Manual data extraction can be time-consuming and prone to errors, especially with the presence of CAPTCHA challenges. This automation script addresses these challenges by implementing browser configurations to bypass CAPTCHA and ensures seamless data retrieval.

### Key Highlights

- Flight Data Collection: 
  - The script navigates to the Make-My-Trip website and captures flight information, such as departure and arrival times, prices, and availability, for the current day and the subsequent 20 days.
  
- CAPTCHA Bypass: 
  - The project utilizes browser manipulation techniques to bypass CAPTCHA detection, allowing the automation to proceed without manual intervention.

- Data Storage: 
  - Once the flight data is retrieved, it is saved in a structured format in a `.txt` file for basic record-keeping.
  - The data from the `.txt` file is then converted into an Excel file (`.xlsx`) for easier viewing, sorting, and analysis.

- Email Delivery: 
  - Upon successful data retrieval, the script automatically sends an email with both the text file and the Excel file as attachments. This ensures that the user has immediate access to the flight data in multiple formats.

## Automation Flow

1. Initialization: 
   - The browser is launched with special configurations that prevent the automation from being detected as a bot by CAPTCHA mechanisms on the website.

2. Data Retrieval:
   - The automation script navigates through the Make-My-Trip flight search pages, extracting the relevant flight information for each of the 21 consecutive days.

3. Data Processing:
   - The flight data is first recorded in a simple text format for quick reference. 
   - Subsequently, the data is parsed and transformed into a neatly organized Excel sheet, where it can be further analyzed or shared.

4. Email Notification:
   - Once both the text file and Excel file are generated, they are automatically attached to an email and sent to a designated recipient, ensuring that the user receives the data without any manual intervention.

## Features

- Effortless CAPTCHA Bypass:
   - The automation uses browser options to minimize CAPTCHA prompts, ensuring that the data extraction process runs smoothly and without interruptions.

- Automated Flight Data Retrieval:
   - The script covers an extended range of dates, ensuring that the user has a comprehensive view of available flights over a three-week period.

- Data Versatility:
   - The extracted data is provided in both `.txt` and `.xlsx` formats, offering flexibility in how the data is used, stored, or analyzed.

- Seamless Email Integration:
   - Both the raw text file and the more refined Excel file are sent directly to the user's email, simplifying data sharing and access.

## How It Works

- Step 1: The browser is launched with customized options to prevent detection by CAPTCHA systems.
- Step 2: The automation navigates the Make-My-Trip website, retrieving flight data for the current and upcoming 20 days.
- Step 3: The data is stored in two formats: first in a text file, and then in a converted Excel file for easier analysis.
- Step 4: The text and Excel files are automatically emailed to the user, providing instant access to the flight data.

## Installation and Setup

1. Clone the Repository: 
   Download or clone this repository to your local machine.
   
2. Katalon Studio: 
   Open the project in Katalon Studio. Ensure that all necessary libraries and dependencies are configured.

3. Email Configuration: 
   Update the email configuration in the script to include the recipient's email address and any necessary email server settings.

4. Run the Automation: 
   Execute the test case. The script will automatically:
   - Open the browser with CAPTCHA bypass settings.
   - Retrieve flight data for the specified range of dates.
   - Store the data in `.txt` and `.xlsx` formats.
   - Email the files to the user.

## Prerequisites

- Katalon Studio: The automation script is developed and executed in Katalon Studio. Make sure you have Katalon installed and properly configured.
  
- Google Chrome: Ensure that Google Chrome is installed, as the automation script interacts with the website using this browser.
  
- Internet Connection: A stable internet connection is required to access the Make-My-Trip website and send emails.

## Potential Use Cases

- Frequent Travelers: Individuals or travel agents who regularly need to check flight availability and pricing over an extended period.
  
- Data Analysis: Users who want to collect flight data for comparative analysis or trend observation over time.

- Automation Enthusiasts: Developers or testers looking to explore how to handle CAPTCHA challenges and automate data extraction from dynamic websites.


## Conclusion

This project demonstrates the power of automation for retrieving and processing dynamic website data while overcoming challenges such as CAPTCHA. By leveraging Katalon Studio, Groovy scripting, and custom browser settings, this tool offers a comprehensive solution for gathering, organizing, and sharing flight data in an efficient manner.
