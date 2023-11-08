package at.technikum.DicomToXML;

import at.technikum.DicomToXML.validator.DicomValidator;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.io.DicomInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class DicomToXMLService {

    @Autowired
    private DicomValidator dicomValidator = new DicomValidator();

    public String convertDcmToXml(byte[] dcmData) throws DicomConversionException {
        try {
            if (!dicomValidator.isValidDicomData(dcmData)) {
                throw new DicomConversionException("Invalid DICOM data");
            }

            DicomInputStream dis = new DicomInputStream(new ByteArrayInputStream(dcmData));
            Attributes dcmAttrs = dis.readDataset(-1, -1);

            return convertAttributesToXml(dcmAttrs);
        } catch (IOException e) {
            throw new DicomConversionException("Error converting DICOM to XML", e);
        }
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