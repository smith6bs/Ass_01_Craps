import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Random RND = new Random();
    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args)
    {
        System.out.println("Let's Play Craps");

        boolean playAgain = true;
        while (playAgain) {
            System.out.println("\n-- New Game: First Roll --");
            int[] roll = rollDice();
            int sum = roll[0] + roll[1];
            printRoll(roll[0], roll[1], sum);

            if (sum == 2 || sum == 3 || sum == 12)
            {
                System.out.println("Craps! You crapped out and lost.");
            } else if (sum == 7 || sum == 11)
            {
                System.out.println("Natural! You win!");
            } else {
                int point = sum;
                System.out.println("Point is set to " + point + ".");
                System.out.println("Trying for point...");

                boolean rolling = true;
                while (rolling)
                {
                    roll = rollDice();
                    sum = roll[0] + roll[1];
                    printRoll(roll[0], roll[1], sum);

                    if (sum == point)
                    {
                        System.out.println("Made point and won!");
                        rolling = false;
                    } else if (sum == 7)
                    {
                        System.out.println("Got a seven and lost.");
                        rolling = false;
                    } else {
                        System.out.println("Trying for point...");
                    }
                }
            }

            playAgain = promptYesNo("Do you want to play again? [Y/N]: ");
        }

        System.out.println("Thanks for playing! Goodbye.");

    }

    private static int[] rollDice()
    {
        int die1 = RND.nextInt(6) + 1; // 1..6
        int die2 = RND.nextInt(6) + 1; // 1..6
        return new int[]{die1, die2};
    }

    private static void printRoll(int die1, int die2, int sum)
    {
        System.out.println("Rolled -> Die 1: " + die1 + ", Die 2: " + die2 + " | Sum: " + sum);
    }

    private static boolean promptYesNo(String message)
    {
        while (true)
        {
            System.out.print(message);
            if (!INPUT.hasNextLine()) {
                System.out.println(); // newline
                return false;
            }
            String ans = INPUT.nextLine().trim();
            if (ans.equalsIgnoreCase("Y")) return true;
            if (ans.equalsIgnoreCase("N")) return false;
            // blank or anything else -> reprompt
            System.out.println("Please enter Y or N.");
        }
    }
}
