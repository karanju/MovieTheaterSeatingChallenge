public class TicketException extends Exception {

    public enum Types {
        FileNotReadableException,
        InvalidInputException,
        NoVacantSpaceException,
        FileNotWritable
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
