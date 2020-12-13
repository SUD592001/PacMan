import java.util.Scanner;
public class Blackjack {
    public static void main(String[] args) {
        //Declared variables for program to count dealer wins, player wins, tied games and keep track of how many games were played.
        int numberOfDealerWins = 0;
        int numberOfPlayerWins = 0;
        int gameCount = 1;
        int numberOfTieGames = 0;
        boolean continueGame = false;
        Scanner input = new Scanner(System.in);
        P1Random random = new P1Random();
        //While loop used to cycle through games until player exits.
        while (!continueGame) {
            int handNumber = 0;
            boolean blackjack = false;
            int dealtCard;
            int dealerHand;
            System.out.println("START GAME #" + gameCount);
            System.out.println();
            dealtCard = random.nextInt(13) + 1;
            if (dealtCard == 1) {
                System.out.println("Your card is a ACE!");
                handNumber += 1;
            } else if (dealtCard == 11) {
                System.out.println("Your card is a JACK!");
                handNumber += 10;
            } else if (dealtCard == 12) {
                System.out.println("Your card is a QUEEN!");
                handNumber += 10;
            } else if (dealtCard == 13) {
                System.out.println("Your card is a KING!");
                handNumber += 10;
            } else {
                System.out.println("Your card is a " + dealtCard + "!");
                handNumber += dealtCard;
            }
            System.out.println("Your hand is: " + handNumber);
            System.out.println();
            //While loop to show menu after every hand is either dealt or held.
            while (!blackjack) {
                System.out.println("1.  Get another card");
                System.out.println("2.  Hold hand");
                System.out.println("3.  Print statistics");
                System.out.println("4.  Exit");
                System.out.println();
                System.out.print("Choose an option: ");
                int menuSelection = input.nextInt();
                System.out.println();
                if (menuSelection == 1) {
                    dealtCard = random.nextInt(13) + 1;
                    if (dealtCard == 1) {
                        System.out.println("Your card is a ACE!");
                        handNumber += 1;
                    } else if (dealtCard == 11) {
                        System.out.println("Your card is a JACK!");
                        handNumber += 10;
                    } else if (dealtCard == 12) {
                        System.out.println("Your card is a QUEEN!");
                        handNumber += 10;
                    } else if (dealtCard == 13) {
                        System.out.println("Your card is a KING!");
                        handNumber += 10;
                    } else {
                        System.out.println("Your card is a " + dealtCard + "!");
                        handNumber += dealtCard;
                    }
                    //If hand is equal to 21 program declares you win but if hand is greater than 21 program declares you lose.
                    System.out.println("Your hand is: " + handNumber);
                    System.out.println();
                    if (handNumber == 21) {
                        System.out.println("BLACKJACK! You win!");
                        System.out.println();
                        numberOfPlayerWins++;
                        blackjack = true;
                        gameCount++;
                    } else if (handNumber > 21) {
                        System.out.println("You exceeded 21! You lose.");
                        System.out.println();
                        numberOfDealerWins++;
                        blackjack = true;
                        gameCount++;
                    }
                } else if (menuSelection == 2) {
                    dealerHand = random.nextInt(11) + 16;
                    System.out.println("Dealer's hand: " + dealerHand);
                    System.out.println("Your hand is: " + handNumber);
                    System.out.println();
                    //If-else statements for program to decide whether user or dealer wins or there is a tie.
                    if (dealerHand > 21) {
                        System.out.println("You win!");
                        numberOfPlayerWins++;
                        gameCount++;
                    } else if (dealerHand == handNumber) {
                        System.out.println("It's a tie! No one wins!");
                        numberOfTieGames++;
                        gameCount++;
                    } else if (dealerHand > handNumber) {
                        System.out.println("Dealer wins!");
                        numberOfDealerWins++;
                        gameCount++;
                    } else {
                        System.out.println("You win!");
                        gameCount++;
                        numberOfPlayerWins++;
                    }
                    System.out.println();
                    blackjack = true;
                //If user chooses option 3, statistics of game including number of player wins and dealer wins are displayed.
                } else if (menuSelection == 3) {
                    double playerWinPercent = ((double) numberOfPlayerWins / (gameCount - 1))* 100;
                    System.out.println("Number of Player wins: " + numberOfPlayerWins);
                    System.out.println("Number of Dealer wins: " + numberOfDealerWins);
                    System.out.println("Number of tie games: " + numberOfTieGames);
                    System.out.println("Total # of games played is: " + (gameCount - 1));
                    System.out.println("Percentage of Player wins: " + playerWinPercent + "%");
                    System.out.println();
                } else if (menuSelection == 4) {
                    blackjack = true;
                    continueGame = true;
                } else {
                    System.out.println("Invalid input!");
                    System.out.println("Please enter an integer value between 1 and 4.");
                    System.out.println();
                }
            }
            }
        }
    }






