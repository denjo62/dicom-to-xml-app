package at.technikum.DicomToXML;

import at.technikum.DicomToXML.exceptions.DicomConversionException;
import at.technikum.DicomToXML.validator.DicomValidator;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.io.DicomInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class DicomToXMLService {

    @Autowired
    private DicomValidator dicomValidator = new DicomValidator();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String convertDcmToXml(byte[] dcmData) throws DicomConversionException {
        try {
            if (!dicomValidator.isValidDicomData(dcmData)) {
                throw new DicomConversionException("Invalid DICOM data");
            }

            DicomInputStream dis = new DicomInputStream(new ByteArrayInputStream(dcmData));
            Attributes dcmAttrs = dis.readDataset(-1, -1);
            // Save the XML data to the database
            saveToDatabase(convertAttributesToXml(dcmAttrs));
            return convertAttributesToXml(dcmAttrs);
        } catch (IOException e) {
            throw new DicomConversionException("Error converting DICOM to XML", e);
        }
    }

    private void saveToDatabase(String xmlData) {
        String sql = "INSERT INTO converted_dicom (XML_DATA, REQUESTED_TIME) VALUES (?, NOW())";

        try {
            // Try to insert data into the existing table
            jdbcTemplate.update(sql, xmlData);
        } catch (DataAccessException e) {
            createTableIfNotExists();
            jdbcTemplate.update(sql, xmlData);
        }
    }

    private void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS converted_dicom (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY," +
                "XML_DATA TEXT," +
                "REQUESTED_TIME DATETIME" +
                ")";
        jdbcTemplate.update(createTableSQL);
    }


    private String convertAttributesToXml(Attributes dcmAttrs) {
        StringBuilder xml = new StringBuilder("<dicom>");
        for (int tag : dcmAttrs.tags()) {
            xml.append("<element tag=\"").append(Integer.toHexString(tag)).append("\">")
                    .append(dcmAttrs.getString(tag))
                    .append("</element>");
        }
        xml.append("</dicom>");

        return xml.toString();
    }
}