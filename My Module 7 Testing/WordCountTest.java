import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.*;

public class WordCountTest {

    final static String filePath = "input.htm";
    static WordCount wordCount = new WordCount(filePath);

    @Test
    void testFileNotFound() {
        Assertions.assertNotNull(new File(filePath));
    }

    @Test 
    void testMapNotNull() {
        Assertions.assertNotNull(WordCount.map);
    }

    @Test
    void testListNotNull() {
        Assertions.assertNotNull(WordCount.wordObjects);
    }

    @Test
    void testParseInput() {
        Assertions.assertFalse(WordCount.parsedInput.toString().matches("<.*>"));
    }

    @Test
    void getFrequencyOfThe() {
        int expected = 56;
        Assertions.assertEquals(expected, wordCount.getFrequencyByWord("the"));
    }

    @Test
    void getFrequencyOfAnd() {
        int expected = 38;
        Assertions.assertEquals(expected, wordCount.getFrequencyByWord("and"));
    }

    @Test
    void testWordThatDoesNotAppear1() {
        int expected = -1;
        Assertions.assertEquals(expected, wordCount.getFrequencyByWord("font"));
    }

    @Test
    void testWordThatDoesNotAppear2() {
        int expected = -1;
        Assertions.assertEquals(expected, wordCount.getFrequencyByWord("champaigne"));
    }

    @Test
    void testHighestFrequency() {
        int expected = 56;
        int actual = WordCount.wordObjects.get(0).frequency;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testLowestFrequency() {
        int expected = 1;
        int size = WordCount.wordObjects.size();
        int actual = WordCount.wordObjects.get(size - 1).frequency;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testMostFrequentWord() {
        String expected = "the";
        String actual = WordCount.wordObjects.get(0).val;
        Assertions.assertEquals(expected, actual);
    }
}