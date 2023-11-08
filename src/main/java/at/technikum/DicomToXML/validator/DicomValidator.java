package at.technikum.DicomToXML.validator;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.util.TagUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Component
public class DicomValidator {

    public boolean isValidDicomData(byte[] dicomData) {
        if (dicomData == null || dicomData.length < 132) {
            return false;
        }

        if (dicomData[128] != 'D' || dicomData[129] != 'I' || dicomData[130] != 'C' || dicomData[131] != 'M') {
            return false;
        }

        try {
            DicomInputStream dis = new DicomInputStream(new ByteArrayInputStream(dicomData));
            Attributes dcmAttrs = dis.readDataset(-1, -1);

            // Check for required DICOM tags
            if (!dcmAttrs.contains(Tag.SOPClassUID) ||
                    !dcmAttrs.contains(Tag.SOPInstanceUID) ||
                    !dcmAttrs.contains(Tag.StudyInstanceUID)) {
                return false;
            }

            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
