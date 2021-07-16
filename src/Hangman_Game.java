
import java.util.Scanner;

public class Hangman_Game {

    //main method
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] game_stat = new int[2];//To display the number of times the user won or lost
        intro();//invoke intro(); method
        while (true) {
            String secret_word = getSecretWord();//invoke getSecretWord(); method and save in secret_word variable
            char[] secret_letter = new char[secret_word.length()];// after user generate correct letter it saves in char
            for (int i = 0; i < secret_letter.length; i++) {
                secret_letter[i] = '_';// to desplay _ _ _ _ 
            }
            boolean result = playOneGame(secret_word, secret_letter);
            if (result) {
                System.out.println("");
                System.out.println("");
                System.out.println("You did it right :) , The word is:  " + secret_word);
                System.out.println("");
                game_stat[0]++;
            } else {
                System.out.println("");
                System.out.println("");
                System.out.println("Wrong :( ,The word was..." + secret_word);
                System.out.println("");
                game_stat[1]++;
            }
            System.out.println("Do you want to play again (y / n):");
            char confirm = input.next().charAt(0);
            if (confirm == 'n' || confirm=='N') {
                getStat(game_stat, 1);
                break;
            }
        }

    }

    //method (1): print intoductory text that apears at the start of the program
    public static void intro() {
        System.out.println("Hangman Game!");
        System.out.println("I will think of a random word.");
        System.out.println("You'll try to guess its letters.");
        System.out.println("Try guess correctly to avoid loses!");

    }
    //method(2)
    public static String getSecretWord() {
        String[] word_list = {"ruby", "python", "java", "fortran", "html", "php"};
        int index_word_list = (int) (Math.random() * word_list.length);// Choose Random from the list above
        return word_list[index_word_list];
    }
    //method(3)
    public static boolean playOneGame(String secret_word, char[] secret_letter) {
        Scanner input = new Scanner(System.in);
        int correct_answer = 0;
        int wrong_answer = 0;
        int trials = 5;
        String wrong_input = "";
        while (trials > 0 && winningState(secret_letter) != secret_word.length()) {
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("You have " + trials + " trials left.");
            System.out.print("Word:	");
            for (int i = 0; i < secret_letter.length; i++) {
                System.out.print(secret_letter[i] + "  ");

            }
            System.out.println("\n");
            System.out.print("Enter your Guess Letter:");
            char choice = input.next().charAt(0);
            choice = Character.toLowerCase(choice);
            boolean guess = correctGuess(secret_word, secret_letter, choice);
            if (guess) {
                correct_answer++;
            } else {
                wrong_answer++;
                trials--;
                wrong_input += choice + " ";
            }
            if (wrong_input.length() > 0) {
                System.out.println("");
                System.out.println("Misses:  " + wrong_input);
            }

        }
        System.out.println("************************************");
        System.out.println("Your Attempt Status: ");
        System.out.println("_____________________");
        System.out.println(" number of wrong guess is = " + wrong_answer);
        System.out.println(" number of correct guesses is = " + correct_answer);
        System.out.println("************************************");
        if (trials > 0) {
            return true;
        } else {
            return false;
        }
    }
    //method(4):to check if the character is letter or not
    public static int winningState(char[] secret_letter) {
        int letter = 0;
        for (int i = 0; i < secret_letter.length; i++) {
            if (Character.isLetter(secret_letter[i])) {
                letter++;
            }
        }
        return letter;
    }
    //method(5): To make sure that the character entered by the user exists within the secret_word
    public static boolean correctGuess(String secret_word, char[] secret_letter, char guessLetter) {
        boolean guess = false;
        for (int i = 0; i < secret_word.length(); i++) {
            if (guessLetter == secret_word.charAt(i)) {
                guess = true;
                secret_letter[i] = guessLetter;
            }
        }
        return guess;
    }
    //method(6): if the user enter no this method print final profile (how many time user play, number of won and number of loss)
    public static void getStat(int[] game_stat, int index) {
        System.out.println("----------------------------------------------------------");
        System.out.println("Your final Profile status:");
        System.out.println("____________________________");
        System.out.println("total number of Hangman games played  = " + (game_stat[0] + game_stat[1]));
        System.out.println("total number of won games  =" + game_stat[0]);
        System.out.println("total number of loss games  =" + game_stat[1]);
    }
}
