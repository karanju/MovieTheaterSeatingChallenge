public class TheaterSeating {

    public TheaterSeating(){             // is this needed?
    }

    public static void main(String [] args){

        TheaterSeatAllocation theaterSeatAllocation = new TheaterSeatAllocation(10,20);

        try{
            theaterSeatAllocation.readInputFile(args[0]);
            theaterSeatAllocation.writeOutputFile(args[1]);
        }
        catch(TicketException e){
            System.out.println(e.getMessageString()+ " ---> "+ e.getExceptionTypes());
        }
    }
}
