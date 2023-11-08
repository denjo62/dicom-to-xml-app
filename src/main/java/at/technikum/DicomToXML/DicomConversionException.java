package at.technikum.DicomToXML;

public class DicomConversionException extends Exception {
    public DicomConversionException(String message) {
        super(message);
    }

    public DicomConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}
