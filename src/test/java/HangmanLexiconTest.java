import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangmanLexiconTest {
    HangmanLexicon hangmanLexicon;

    @BeforeEach
    void initialize(){
        hangmanLexicon = new HangmanLexicon();
    }
//
//    @Test
//    void getWordCount() {
//    }

    @Test
    void getWord() {
        int index = (int)(Math.random()*10);
        assertNotNull(hangmanLexicon.getWord(index));
    }
}