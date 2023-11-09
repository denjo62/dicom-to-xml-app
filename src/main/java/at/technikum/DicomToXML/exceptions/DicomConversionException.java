package at.technikum.DicomToXML.exceptions;

public class DicomConversionException extends Exception {
    public DicomConversionException(String message) {
        super(message);
    }

    public DicomConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}
