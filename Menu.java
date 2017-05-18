/**
 * Created by KhoaFaise on 4/26/17.
 */


import java.util.Scanner;
import java.util.Objects;
public class Menu {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        final int RockPaperScissors = 1;
        final int BlackJack = 2;
        final int ConnectFour = 3;
        final int TicTacToe = 4;

        //prompt the user to pick which game they want to play
        System.out.print("\n(1) Rock, Paper, Scissors");
        System.out.print("\n(2) Blackjack");
        System.out.print("\n(3) Connect Four");
        System.out.print("\n(4) TicTacToe");
        System.out.print("\nPick a number to play a game: ");
        int player = input.nextInt();

        switch (player) {
            case RockPaperScissors:
                RockPaperScissors rockPS = new RockPaperScissors();
                rockPS.main(new String[]{});
                break;
            case BlackJack:
                BlackJack blkJack = new BlackJack();
                blkJack.main(new String[]{});
                break;
            case ConnectFour:
                ConnectFour conFour = new ConnectFour();
                conFour.main(new String[]{});
                break;
            case TicTacToe:
                TicTacToe ticTT = new TicTacToe();
                ticTT.main(new String[]{});
                break;
        }
    }
}

class ConnectFour {
	public static boolean addDisk(char[][] field, int column, char color) {
        if (field[0][column] != ' ')
            return false;
        for (int row = 0; row < 7; ++row) {
            if (field[row][column] != ' ') {
                field[row-1][column] = color;
                return true;
            }
        }
        field[6][column] = color;
        return true;
    }
    public static void printField(char[][] field) {
        for (int row = 0; row < 7; ++row) {
            System.out.print("| ");
            for (int col = 0; col < 7; ++col)
                System.out.print(field[row][col] + "| ");
            System.out.println();
        }
        for (int col = 0; col < 7; ++col)
            System.out.print("---");
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[][] field = new char[7][7];
        for (int i = 0; i < 7; ++i)
            for (int j = 0; j < 7; ++j)
                field[i][j] = ' ';
        printField(field);
        boolean isRed = true;
        while (true) {
            if (isRed)
                System.out.println("Player Red's turn!");
            else
                System.out.println("Player Yellow's turn!");
            System.out.print("Choose a column from 1-7:");
            int column = input.nextInt();
            if (column < 1 || column > 7) {
                System.out.println("Column should be from 1 to 7");
                continue;
            }
            if (!addDisk(field, column - 1, isRed ? 'R' : 'Y')) {
                System.out.println("This column is filled! Choose another one.");
                continue;
            }
            printField(field);
            isRed = !isRed;
        }
    }
}
class BlackJack {
    static int[] deck = {2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11};
    static int [] deck2;
    static boolean playerAce;
    static boolean dealerAce;
    static boolean playerBlackjack;
    static boolean dealerBlackjack;
    static int playerScore;
    static int dealerScore;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        shuffle(deck);
        deck2 = deck;
        draw();
        draw();
        if(playerScore == 21){
            playerBlackjack = true;
            System.out.println("Blackjack!!");
        }
        if(playerBlackjack==true && dealerBlackjack==false){
            System.out.println("You win");
            System.exit(0);
        }
        System.out.println(playerScore);
        choice();
        dealer();
        if(playerScore>dealerScore){
            System.out.println("You win");
        }
        if(playerScore<=dealerScore){
            System.out.println("You lose");
        }




    }


    public static void shuffle(int... deck) {
        for (int i = deck.length - 1; i > 0; i--) {
            int rand = (int) (Math.random() * (i + 1));
            int temp = deck[i];
            deck[i] = deck[rand];
            deck[rand] = temp;
        }
    }
    public static void draw(){
        int range = deck.length;
        int card = (int)(Math.random() * range);
        int drawnCard = deck2[card];
        if (drawnCard >= 2){
            playerScore = drawnCard + playerScore;
        }
        if(drawnCard == 11) {
            playerAce = true;
            System.out.println("You have an ace");
        }
        if(drawnCard == 0) {
            draw();
        }
        deck2[drawnCard] = 0;
        if(playerScore >= 22 && playerAce == true){
            playerScore = playerScore - 10;
            playerAce = false;
        }
        else if(playerScore >=22 && playerAce ==false) {
            System.out.println("Over 21, you lose" + playerScore);
            System.exit(0);
        }
    }

    public static void dealer (){
        dealerDraw();
        dealerDraw();
        if(dealerScore == 21) {
            dealerBlackjack = true;
        }
        if(dealerBlackjack==true && playerBlackjack==true){
            System.out.println("Tie");
            System.exit(0);
        }
        if(dealerBlackjack==true && playerBlackjack==false) {
            System.out.println("Dealer blackjack, you lose");
            System.exit(0);
        }
        dealerChoice();
    }

    public static void dealerDraw () {
        int range = deck.length;
        int card = (int) (Math.random() * range);
        int drawnCard = deck2[card];
        if (drawnCard >= 2) {
            dealerScore = drawnCard + dealerScore;
        }
        if (drawnCard == 11) {
            dealerAce = true;
        }
        if(drawnCard == 0) {
            draw();
        }
        deck2[drawnCard] = 0;
        if(dealerScore >= 22){
            System.out.println("Dealer busts, you win");
            System.exit(0);
        }
    }

    public static void choice() {
        System.out.println("Draw or keep?");
        String input = scanner.nextLine();
        if (Objects.equals(input,"draw")) {
            draw();
            System.out.println(playerScore);
            choice();
        }

    }
    public static void dealerChoice(){
        if(dealerScore>16){
            dealerDraw();
            dealerChoice();
        }
    }

}
class TicTacToe {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {

        final int SIZE = 3;
        char[][] board = new char[SIZE][SIZE];

        resetBoard(board);


        System.out.println("[**** [ WELCOME TO THE TIC-TAC-TOE GAME ] ****]\n");
        showBoard(board);


        System.out.print("  Which symbol would you like to choose?, \"x\" or \"o\"? ");
        char userSymbol = sc.next().toLowerCase().charAt(0);
        char compSymbol = (userSymbol == 'x') ? 'o' : 'x';


        System.out.println();
        System.out.print("  Do you want to go first (y/n)? ");
        char ans = sc.next().toLowerCase().charAt(0);

        int turn;
        int remainCount = SIZE * SIZE;


        if (ans == 'y') {
            turn = 0;
            userPlay(board, userSymbol);
        }
        else {
            turn = 1;
            compPlay(board, compSymbol);
        }

        showBoard(board);
        remainCount--;


        boolean done = false;
        int winner = -1;

        while (!done && remainCount > 0) {

            done = isGameWon(board, turn, userSymbol, compSymbol);

            if (done)
                winner = turn;
            else {
                turn = (turn + 1 ) % 2;

                if (turn == 0)
                    userPlay(board, userSymbol);
                else
                    compPlay(board, compSymbol);


                showBoard(board);
                remainCount--;
            }
        }


        if (winner == 0)
            System.out.println("\n**** CONGRATULATIONS, YOU WON!! ****");
        else if (winner == 1)
            System.out.println("\n** YOU LOST..   **");
        else
            System.out.println("\n*** DRAW... ***");

    }

    public static void resetBoard(char[][] brd)
    {
        for (int i = 0; i < brd.length; i++)
            for (int j = 0; j < brd[0].length; j++)
                brd[i][j] = ' ';
    }

    public static void showBoard(char[][] brd)
    {
        int numRow = brd.length;
        int numCol = brd[0].length;

        System.out.println();
        System.out.print("    ");
        for (int i = 0; i < numCol; i++)
            System.out.print(i + "   ");
        System.out.print('\n');

        System.out.println();

        for (int i = 0; i < numRow; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < numCol; j++) {
                if (j != 0)
                    System.out.print("|");
                System.out.print(" " + brd[i][j] + " ");
            }

            System.out.println();

            if (i != (numRow - 1)) {

                System.out.print("   ");
                for (int j = 0; j < numCol; j++) {
                    if (j != 0)
                        System.out.print("+");
                    System.out.print("---");
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void userPlay(char[][] brd, char usym)
    {
        System.out.print("\nEnter the row and column indices: ");
        int rowIndex = sc.nextInt();
        int colIndex = sc.nextInt();

        while (brd[rowIndex][colIndex] != ' ') {
            System.out.print("\n!! The cell is already taken.\nEnter the row and column indices: ");
            rowIndex = sc.nextInt();
            colIndex = sc.nextInt();
        }

        brd[rowIndex][colIndex] = usym;
    }

    public static void compPlay(char[][] brd, char csym)
    {
        for (int i = 0; i < brd.length; i++) {
            for (int j = 0; j < brd[0].length; j++) {
                if (brd[i][j] == ' ') {
                    brd[i][j] = csym;
                    return;
                }
            }
        }
    }

    public static boolean isGameWon(char[][] brd, int turn, char usym, char csym)
    {
        char sym;
        if (turn == 0)
            sym = usym;
        else
            sym = csym;

        int i, j;
        boolean win = false;

        for (i = 0; i < brd.length && !win; i++) {
            for (j = 0; j < brd[0].length; j++) {
                if (brd[i][j] != sym)
                    break;
            }
            if (j == brd[0].length)
                win = true;
        }

        for (j = 0; j < brd[0].length && !win; j++) {
            for (i = 0; i < brd.length; i++) {
                if (brd[i][j] != sym)
                    break;
            }
            if (i == brd.length)
                win = true;
        }


        if (!win) {
            for (i = 0; i < brd.length; i++) {
                if (brd[i][i] != sym)
                    break;
            }
            if (i == brd.length)
                win = true;
        }

        if (!win) {
            for (i = 0; i < brd.length; i++) {
                if (brd[i][brd.length - 1 - i] != sym)
                    break;
            }
            if (i == brd.length)
                win = true;
        }


        return win;
    }
}
class RockPaperScissors {
    public static void main(String [] args){
        //create a scanner
        Scanner user = new Scanner(System.in);
        int computer = (int)(Math.random() * 3);
        final int Rock = 1;
        final int Paper = 2;
        final int Scissors = 3;

        //prompt the user to enter rock, paper, or scissors
        System.out.print("\nPick Rock (1), Paper (2), Scissors (3): ");
        int player = user.nextInt();

        //computer choice
        System.out.print("Computer chooses: " + computer);

        switch (player){
            case Scissors:
                if (computer == Scissors){
                    System.out.println(" Scissors, it's a tie!");
                }//end if
                else{
                    if (computer == Rock){
                        System.out.println(" Rock crushes Scissors, you lose!");
                    }//end if
                    else{
                        System.out.println(" Scissors cut paper, you win!");
                    }//end else
                }//end else
                break;

            case Rock:
                if (computer == Scissors){
                    System.out.println(" Rock crushes scissors, you win!");
                }//end if
                else {
                    if (computer == Rock){
                        System.out.println(" Rock, it's a tie!");
                    }//end if
                    else {
                        System.out.println(" Paper wraps rock, you lose!");
                    }//end else
                }//end else, Rock
                break;

            case Paper:
                if (computer == Scissors){
                    System.out.println(" Scissors cuts paper, you lose!");
                }//end if
                else {
                    if (computer == Rock){
                        System.out.println(" Paper beats rock, you win!");
                    }//end if

                    else {
                        System.out.println(" Paper, tie!");
                    }//end if
                }//end else, paper
                break;

        }
    }


}