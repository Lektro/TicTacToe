import java.util.*;

public class TicTacToe {

    // Class variables in Array lists to keep track of current positions on the game board.
    // This gets used to check the win conditions for the game.
    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {

        // Creates the game board layout using a 2D char Array
        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'─', '+', '─', '+', '─'},
                {' ', '|', ' ', '|', ' '},
                {'─', '+', '─', '+', '─'},
                {' ', '|', ' ', '|', ' '}};

        // Invoke Method to print the current game board
        printGameBoard(gameBoard);

        // Loops through player and cpu inputs until on of the win condition is met.
        while(true) {

            // Initialises the scanner ready for input.
            Scanner scan = new Scanner(System.in);

            // Prints the prompt to user terminal
            System.out.println("Enter your placement with the number pad.");
            System.out.println("Bottom left equals to 1 and top right equals to 9 (1-9):");

            // Assigns user input to a certain player position in the array.
            int playerPos = scan.nextInt();

            // Checks if the player input can be placed on the game board or if the position is already taken
            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Position taken! Please choose a valid position");
                playerPos = scan.nextInt();
            }

            // Method to place the Player piece once it has been cleared as valid.
            placePiece(gameBoard, playerPos, "Player");
            // Invoke Method to print the current updated game board
            printGameBoard(gameBoard);

            // Checks for winning conditions method result and prints out the correct result of the game if winning conditions have been met.
            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            // uses the random function to pick a number for the cpu. Inject your AI here.
            Random rand = new Random();

            // places the piece for the computer, keeping it within bounds 1 up to 9.
            int cpuPos = rand.nextInt(9) + 1;

            // Checks if the cpu input can be placed on the game board or if the position is already taken
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }

            // Method to place the CPU piece once it has been cleared as valid.
            placePiece(gameBoard, cpuPos, "cpu");

            // Invoke Method to print the current updated game board
            printGameBoard(gameBoard);


            // Checks for winning conditions method result and prints out the correct result of the game if winning conditions have been met.
            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
        }
    }

    // method to print out the entire game board with updated char value's
    public static void printGameBoard(char[][] gameBoard) {

        //prints out the individual chars one by one in the rows of the array using a for loop.
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            //Prints out a new line after each row of chars in the array got printed
            System.out.println();
        }
    }

    // method for placing the pieces on the game board
    public static void placePiece(char[][] gameBoard, int pos, String user) {

        // Declaring first symbol as empty. Once the user gives a valid input this will be switched to the corresponding symbol
        char symbol = ' ';

        // Determine and switches between who is placing a piece or symbol, the player or the computer
        // Assigns the correct symbol
        // Adds the player and CPU position to the correct place in the Array
        if (user.equals("Player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")){
            symbol = 'O';
            cpuPositions.add(pos);
        }

        // Switch case to place the piece or correct symbol in the Array.
        // Using the corresponding number pad value to their position on the game board
        // '7' will place a symbol in the top left corner, 3 will place it in the bottom right corner.
        switch (pos) {
            case 1:
                gameBoard[4][0] = symbol;
                break;
            case 2:
                gameBoard[4][2] = symbol;
                break;
            case 3:
                gameBoard[4][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[0][0] = symbol;
                break;
            case 8:
                gameBoard[0][2] = symbol;
                break;
            case 9:
                gameBoard[0][4] = symbol;
                break;
            default:
                break;
        }

        // Invoke Method to print the current game board
        printGameBoard(gameBoard);
    }

    // Method to check for a winner.
    // Here we are defining the scope of positions that would give a win
    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        // Adding the winning conditions to the winning list
        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(middleRow);
        winning.add(bottomRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        // loops through the winning list to check if either of the players has won the game.
        for (List l : winning) {
            if (playerPositions.containsAll(l)) {
                return "Congrats, you won!";
            } else if (cpuPositions.containsAll(l)) {
                return "CPU WON!";
            } else if (playerPositions.size() + cpuPositions.size() == 9 ) {
                return "Game ended in a Draw!";
            }
        }

        // Empty return unless a winning condition is met.
        return "";
    }
}
