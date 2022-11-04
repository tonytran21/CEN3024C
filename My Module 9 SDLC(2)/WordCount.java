import java.io.File;
import java.util.*;

public class WordCount {

    static class Word implements Comparable<Word> {
        String val;
        int frequency;

        public Word(String val, int frequency) {
            this.val = val;
            this.frequency = frequency;
        }


        @Override
        public int compareTo(Word o) {
            return Integer.compare(o.frequency, this.frequency);
        }
    }

    static Map<String, Integer> map;
    static List<Word> wordObjects;
    static StringBuilder parsedInput;

    public int getFrequencyByWord(String word) {
        if (map.containsKey(word)) {
            return map.get(word);
        }
        return -1;
    }

    public WordCount(String filePath) {
        try {
            Scanner sc = new Scanner(new File(filePath));
            String openDiv = "<div class=\"chapter\">";
            String endDiv = "<div style='display:block; margin-top:4em'>*** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***</div>";
            StringBuilder theWholeText = new StringBuilder();
            while (sc.hasNext()) {
                String nextLine = sc.nextLine();
                if (!nextLine.equals(openDiv)) {
                    continue;
                }
                while (true) {
                    String nextNextLine = sc.nextLine();
                    if (nextNextLine.equals(endDiv)) {
                        break;
                    }
                    theWholeText.append(nextNextLine);
                }
            }
            String processedText = cleanData(theWholeText.toString());
            parsedInput = new StringBuilder();
            for (char c : processedText.toCharArray()) {
                parsedInput.append(c);
            }
            List<String> finalWords = split(processedText);
            map = new HashMap<>();
            countWords(finalWords);
            wordObjects = new ArrayList<>();
            for (String word : map.keySet()) {
                wordObjects.add(new Word(word, map.get(word)));
            }
            Collections.sort(wordObjects);
            sc.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String cleanData(String text) {
        String htmlTags = "<.*?>";
        text = text.replaceAll(htmlTags, "").trim().replaceAll("&mdash", "");
        StringBuilder res = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c == ' ' || Character.isAlphabetic(c)) {
                res.append(c);
            }
        }
        return res.toString();
    }

    private static List<String> split(String text) {
        String[] words = text.split(" ");
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (word.length() > 0) {
                res.add(word.toLowerCase());
            }
        }
        return res;
    }

    private static void countWords (List<String> words) {
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            }
            else {
                map.put(word, 1);
            }
        }
    }
}
