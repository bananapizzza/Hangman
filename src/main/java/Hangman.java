import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hangman {
    String word;
    Character[] currentStatus;
    int life;
    boolean isGameFinished;
    Scanner scanner = new Scanner(System.in);
    HangmanLexicon hangmanLexicon = new HangmanLexicon();

    public void startGame() {
        initializeGame();
        while (!isGameFinished) {
            showCurrentStatus();
            char input = getUserInput();
            if (isRightGuess(input)) {
                System.out.println("Right guess! Good job.");
                updateCurrentStatus(input);
                if (isAllMatched()) {
                    System.out.println("You won! The word is "+word);
                    isGameFinished = true;
                }
            } else {
                life--;
                System.out.println("Wrong guess. Your remaining life is "+life);
                if (life == 0) {
                    System.out.println("Game over!");
                    isGameFinished = true;
                }
            }
        }
    }

    void initializeGame() {
        word = getWord();
        initializeCurrentStatus();
        life = 8;
        isGameFinished = false;
    }

    String getWord(){
        int wordCount = hangmanLexicon.getWordCount();
        int index = (int) (Math.random() * wordCount);
        return hangmanLexicon.getWord(index);
    }

    void showCurrentStatus() {
        Stream<Character> charStream = Arrays.stream(currentStatus);
        String s = charStream.map(String::valueOf).collect(Collectors.joining(" "));
        System.out.println("Current status: "+s);
        System.out.println("word: "+word);
    }

    char getUserInput() {
        System.out.println("Enter one character you guess");
        String s = scanner.nextLine();
        if (s.length() > 1) {
            System.out.println("Enter only one character at a time");
            return getUserInput();
        }
        char c = s.toUpperCase().toCharArray()[0];
        if (!Character.isAlphabetic(c)) {
            System.out.println("Enter alphabet only");
            return getUserInput();
        }
        return c;
    }

    boolean isRightGuess(char input) {
        for (char c : word.toCharArray()) {
            if (input == c) {
                return true;
            }
        }
        return false;
    }

    void initializeCurrentStatus(){
        currentStatus = new Character[word.length()];
        for (int i = 0; i < word.length(); i++) {
            currentStatus[i] = '_';
        }
    }

    void updateCurrentStatus(char input) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == input) {
                currentStatus[i] = input;
            }
        }
    }

    boolean isAllMatched(){
        Stream<Character> charStream = Arrays.stream(currentStatus);
        String current = charStream.map(String::valueOf).collect(Collectors.joining());
        return word.equals(current);
    }
}
