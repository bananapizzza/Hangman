import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {
    Hangman hangman;

    @BeforeEach
    void initialize(){
        hangman = new Hangman();
    }

    @Test
    void test_initializeGame(){
        hangman.initializeGame();
        assertNotNull(hangman.getWord());
        assertNotNull(hangman.getCurrentStatus());
        assertEquals(8, hangman.getLife());
    }

    @Test
    void test_isRightGuess(){
        hangman.setWord("COMPUTER");
        assertTrue(hangman.isRightGuess('C'));
        assertFalse(hangman.isRightGuess('Z'));
    }

    @Test
    void test_isAllMatched(){
        hangman.setWord("COMPUTER");
        hangman.initializeCurrentStatus();
        hangman.updateCurrentStatus('C');
        hangman.updateCurrentStatus('O');
        hangman.updateCurrentStatus('M');
        hangman.updateCurrentStatus('P');
        hangman.updateCurrentStatus('U');
        hangman.updateCurrentStatus('T');
        hangman.updateCurrentStatus('E');
        hangman.updateCurrentStatus('R');
        assertTrue(hangman.isAllMatched());
    }
}