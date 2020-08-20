package Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountTags {

    private static int recursiveCounting(int acc, List<String> tagsList) {
        /**
         * Utility function to recursively check how many tags have opening and closing.
         * It does so in a tail recursive style, passing to the next call the accumulator (count until the call)
         * and the tail of the list.
         *
         * @param acc The accumulator. When calling the function, usually the input is acc=0
         * @param tagsList The list of tags
         * @return int The count of valid tags.
         */

        // base case -> check if list is empty
        if (tagsList.isEmpty()) {
            return acc;

        } else {

            // loop one by one using first element
            String firstElement = tagsList.get(0);

            // is it opening tag?
            if (!firstElement.startsWith("/")) {

                // check if there is a closing tag (if there is -> remove start (i=0) and end(first find i) -> count++)
                String expectedClosingTag = "/" + firstElement;

                if (tagsList.contains(expectedClosingTag)) {
                    // increment accumulator counter
                    acc++;

                    // remove closing tag to not match it again
                    int closingTagIndex = tagsList.indexOf(expectedClosingTag); // will return a index since we already checked existence above
                    tagsList.remove(closingTagIndex);
                }
            }

            // remove it anyway (if there is a match or not we remove from list and move forward to find match)
            tagsList.remove(0);
            return recursiveCounting(acc, tagsList);
        }
    }

    public static int countValidTagsHtmlString(String str) {
        /**
         * This method is used to calculate valid tags for an html.
         * For example:
         *  "<html><head></head><body><div><div></div></body></html>"
         *  must return 4.
         *
         * @param str An string representing a html.
         * @return int The count of valid tags (tags that open and close)
         */

        // creates a list of tags
        List<String> tagsList = new ArrayList<String>(Arrays.asList(str.split("<")));

        return recursiveCounting(0, tagsList);
    }
}


