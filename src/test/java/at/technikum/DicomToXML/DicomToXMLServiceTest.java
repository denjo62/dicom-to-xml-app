package at.technikum.DicomToXML;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

public class DicomToXMLServiceTest {
    DicomToXMLService dicomService = new DicomToXMLService();
    @Test
    public void convertToXmlTest() throws DicomConversionException, IOException {

        ClassPathResource resource = new ClassPathResource("files/0003.DCM");
        byte[] fileBytes = resource.getInputStream().readAllBytes();

        String convertedFile = dicomService.convertDcmToXml(fileBytes);
        System.out.println(convertedFile);
    }

}
