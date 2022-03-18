import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TheaterSeatAllocation {

    int[] rowStartPointer;
    int numberOfRows;
    int numberOfColumns;
    int seatsRequired;
    int freeSpace;
    int firstAvailableRow;
    int safetyCheckRowIncrementer;
    String result;

    public TheaterSeatAllocation(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.freeSpace = numberOfRows*numberOfColumns;
        this.firstAvailableRow = 0;
        this.safetyCheckRowIncrementer = 2;
        rowStartPointer = new int[numberOfRows];
        Arrays.fill(rowStartPointer,0);
        result = "";
    }

    public void readInputFile(String fileName) throws TicketException{

        try{
            File file = new File(fileName);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String input = myReader.nextLine();
                this.validateInput(input);
                this.assignSeat(input);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            throw new TicketException("Not able to read File",TicketException.Types.InvalidInputException);
        }
    }

    private void assignSeat(String input) throws TicketException{

        if(freeSpace >= seatsRequired){
            allocateSeats();
            result += "\n";
        }
        else{
            throw new TicketException("Not enough space to fit everyone",TicketException.Types.NoVacantSpaceException);
        }
    }

    private void allocateSeats() {

        for(int row = firstAvailableRow; row < numberOfRows; row += safetyCheckRowIncrementer){
            if(numberOfColumns - rowStartPointer[row] >= seatsRequired){
                allocateSeatsToGivenRow(row);
                return;
            }
        }

        if(firstAvailableRow == 0 && safetyCheckRowIncrementer == 2){             // goes on to check even rows
            firstAvailableRow = 1;
            allocateSeats();
        }
        else{
            distributeSeats();                                                    // distribute seats
        }
    }

    private void distributeSeats() {
        int row = firstAvailableRow;
        while(seatsRequired>0){
            int availableSeatsInRow =  numberOfColumns - rowStartPointer[row];
            int seatsAllocated = Math.min(availableSeatsInRow,seatsRequired);        // since seatsRequired might be lesser than seats available
            updateResultString(row,rowStartPointer[row],seatsAllocated);
            rowStartPointer[row]+= seatsAllocated;
            rowStartPointer[row]+= 3;

            if(row == firstAvailableRow && rowStartPointer[row] >= numberOfColumns)                             // updating firstAvailableRow only if the row is filled
                firstAvailableRow++;

            seatsRequired -= seatsAllocated;
            freeSpace -= seatsAllocated;
            row++;
        }
    }

    private void allocateSeatsToGivenRow(int row) {

        updateResultString(row,rowStartPointer[row],seatsRequired);
        rowStartPointer[row] += seatsRequired;
        rowStartPointer[row] += 3;  // adding safety measure
        freeSpace -= (seatsRequired + 3);

        if(row == (numberOfRows - 1) && firstAvailableRow == 1 && safetyCheckRowIncrementer == 2){        // to check all rows next iteration
            firstAvailableRow = 0;
            safetyCheckRowIncrementer = 1;
        }
    }

    private void updateResultString(int row, int start, int seatsRequired) {

        for (int i = start; i < start + seatsRequired; i++) {
            result += (char) ('A' + row);
            result += (i+1) + " ";
        }
    }

    private void validateInput(String input) throws TicketException {

        String[] splitInput = input.split(" ");
        if (splitInput.length == 2 && splitInput[0].toCharArray()[0] == 'R') {
            seatsRequired = Integer.parseInt(splitInput[1]);
            if (seatsRequired <= 0) {
                throw new TicketException("Zero or less tickets: Please enter a valid number of seats ", TicketException.Types.InvalidInputException);
            }
        } else {
            throw new TicketException("Input file is not valid", TicketException.Types.FileNotReadableException);
        }
    }

    public void writeOutputFile(String fileName) throws TicketException {

        try {
            FileWriter output = new FileWriter(fileName);
            output.write(this.result);
            System.out.println("Successfully wrote to the file");
            output.close();
        } catch (IOException e) {
            throw new TicketException("An error occurred during the write to the file.", TicketException.Types.FileNotWritable);  // what if I put io exception directly
        }
    }
}
