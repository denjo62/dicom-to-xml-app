-- Database: MySQL
-- Description: Create the converted_dicom table

-- Create the table
CREATE TABLE converted_dicom (
                                 ID INT AUTO_INCREMENT PRIMARY KEY,
                                 XML_DATA TEXT,
                                 REQUESTED_TIME TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

