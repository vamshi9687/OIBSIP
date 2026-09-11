package numberguessing;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("==========================================");
        System.out.println("           OIBSIP INTERNSHIP");
        System.out.println("==========================================");
        System.out.println("Name  : Vamshi Badavath");
        System.out.println("Track : Java");
        System.out.println("Task  : Task 2 - Number Guessing Game");
        System.out.println("==========================================");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println();
        System.out.println("==========================================");
        System.out.println("          NUMBER GUESSING GAME");
        System.out.println("==========================================");
        int round = 1;
        int roundsWon = 0;
        boolean playAgain = true;

 
        while (playAgain) {

            int secretNumber = random.nextInt(100) + 1;

            int maxAttempts = 7;
            int attempts = 0;
            boolean correct = false;

            System.out.println();
            System.out.println("------------------------------------------");
            System.out.println("Round " + round);
            System.out.println("------------------------------------------");
            System.out.println("I have selected a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts.");

            while (attempts < maxAttempts) {

                System.out.print("\nEnter your guess: ");

                if (!scanner.hasNextInt()) {

                    System.out.println(
                            "Invalid input! Please enter a number.");

                    scanner.next();
                    continue;
                }

                int guess = scanner.nextInt();

                if (guess < 1 || guess > 100) {

                    System.out.println(
                            "Please enter a number between 1 and 100.");

                    continue;
                }

                attempts++;

                System.out.println("Attempt " + attempts
                        + " of " + maxAttempts);


                if (guess > secretNumber) {

                    System.out.println("Too High!");

                } else if (guess < secretNumber) {

                    System.out.println("Too Low!");

                } else {

                    System.out.println("Correct!");
                    System.out.println("Congratulations!");
                    System.out.println("You guessed the number in "
                            + attempts + " attempts.");

                    correct = true;
                    roundsWon++;

                    break;
                }

                System.out.println("Attempts remaining: "
                        + (maxAttempts - attempts));
            }


            if (!correct) {

                System.out.println();
                System.out.println("You Lost!");
                System.out.println("The correct number was: "
                        + secretNumber);
            }

       
            System.out.println();
            System.out.println("==========================================");
            System.out.println("           ROUND " + round + " SUMMARY");
            System.out.println("==========================================");

            if (correct) {

                System.out.println("Result          : Won");
                System.out.println("Attempts        : " + attempts);

            } else {

                System.out.println("Result          : Lost");
                System.out.println("Attempts Used   : " + attempts);
            }

            System.out.println("Rounds Won      : " + roundsWon);
            System.out.println("==========================================");


            System.out.print(
                    "Do you want to play again? (yes/no): ");

            String answer = scanner.next();

            if (answer.equalsIgnoreCase("yes")
                    || answer.equalsIgnoreCase("y")) {

                round++;

            } else {

                playAgain = false;
            }
        }
        System.out.println();
        System.out.println("==========================================");
        System.out.println("          THANK YOU FOR PLAYING!");
        System.out.println("==========================================");
        System.out.println("Player Name     : Vamshi Badavath");
        System.out.println("Track           : Java");
        System.out.println("Task            : Number Guessing Game");
        System.out.println("Total Rounds    : " + round);
        System.out.println("Rounds Won      : " + roundsWon);
        System.out.println("==========================================");

        scanner.close();
    }
}
