package Utils;

import org.junit.Test;

import java.util.HashMap;

import static Utils.CountTags.countValidTagsHtmlString;
import static org.junit.Assert.assertEquals;

public class CountTagsTest {

    @Test
    public void countValidTagsHtmlStringTest() {
        // inputs and expected outputs
        String html_1 = "<html><head></head><body><div><div></div></body></html>";
        int expectedResult_1 = 4;

        String html_2 = "<html><head></head><body><div><div></div></div></body></html>";
        int expectedResult_2 = 5;

        String html_3 = "<html><head></head><body><div><div></div></div>";
        int expectedResult_3 = 3;

        String html_4 = "<html><head></head><body><div><div></div></div></p></i>";
        int expectedResult_4 = 3;

        String html_5 =  "<html><head></head><body><div><div></div></div></p></i>";
        int expectedResult_5 = 3;

        // real output from function
        int result_1 = countValidTagsHtmlString(html_1);
        int result_2 = countValidTagsHtmlString(html_2);
        int result_3 = countValidTagsHtmlString(html_3);
        int result_4 = countValidTagsHtmlString(html_4);
        int result_5 = countValidTagsHtmlString(html_5);

        // assert tests
        assertEquals(expectedResult_1, result_1);
        assertEquals(expectedResult_2, result_2);
        assertEquals(expectedResult_3, result_3);
        assertEquals(expectedResult_4, result_4);
        assertEquals(expectedResult_5, result_5);

    }

}
