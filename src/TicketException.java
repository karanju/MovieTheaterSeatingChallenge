public class TicketException extends Exception {

    public enum Types {
        fileNotReadableException,
        invalidInputException,
        noVacantSpaceException,
        maxSizeExceededException,
        fileNotWritable
    }

    private String messageString;
    private Types exceptionTypes;

    public TicketException(String messageString, TicketException.Types exceptionTypes) {
        super();
        this.messageString = messageString;
        this.exceptionTypes = exceptionTypes;
    }

    public String getMessageString() {
        return messageString;
    }

    public Types getExceptionTypes() {
        return exceptionTypes;
    }

}
