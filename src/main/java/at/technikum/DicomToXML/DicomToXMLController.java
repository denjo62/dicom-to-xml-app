package at.technikum.DicomToXML;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DicomToXMLController {

    @Autowired
    private DicomToXMLService converter;

    @PostMapping("/convert")
    public String convertDcmToXml(@RequestBody byte[] dcmData) {
        try {
            return converter.convertDcmToXml(dcmData);
        } catch (DicomConversionException e) {
            return "Error: " + e.getMessage();
        }
    }
}
