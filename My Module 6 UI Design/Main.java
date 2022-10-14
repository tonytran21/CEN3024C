package com.example.module6;

import java.io.File;
import java.util.*;


public class Main {

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

    private static String cleanData(String text) {
        String htmlTags = "<.*?>";
        text = text.replaceAll(htmlTags, "").trim().replaceAll("&mdash", " ");
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

    private static Map<String, Integer> countWords (List<String> words) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            }
            else {
                map.put(word, 1);
            }
        }
        return map;
    }

    public static List<Word> getWordFrequencies(File file) throws Exception {
        // The path to my file is "input.htm"
        Scanner sc = new Scanner(file);
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
        List<String> finalWords = split(processedText);
        Map<String, Integer> dictionary = countWords(finalWords);
        List<Word> wordObjects = new ArrayList<>();
        for (String w : dictionary.keySet()) {
            Word word = new Word(w, dictionary.get(w));
            wordObjects.add(word);
        }
        Collections.sort(wordObjects);
//        System.out.println("Word Frequencies");
//        System.out.println("----------------");
//        for (Word word : wordObjects) {
//            System.out.println(word.val + " -> " + word.frequency);
//        }
        sc.close();

        return wordObjects;
    }
}
