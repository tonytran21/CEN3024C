import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    static Connection connection;

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

    private static void countWords (List<String> words) throws SQLException {
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
                update(word, map.get(word));
            }
            else {
                map.put(word, 1);
                insert(word);
            }
        }
    }

    private static void update (String word, int frequency) throws SQLException {
        String statement = "UPDATE word SET frequency = ? WHERE word=?;";
        PreparedStatement p = connection.prepareStatement(statement);
        p.setInt(1, frequency);
        p.setString(2, word);
        p.executeUpdate();

    }

    private static void insert (String word) throws SQLException {
        String statement = "INSERT INTO word VALUES (?, ?);";
        PreparedStatement p = connection.prepareStatement(statement);
        p.setString(1, word);
        p.setInt(2, 1);
        p.execute();
    }

    private static void createTable() throws SQLException {
        String query = "    CREATE TABLE `WordOccurences`.`word` (\n" +
                "      `word` VARCHAR(25) NOT NULL,\n" +
                "      `frequency` INT NOT NULL,\n" +
                "      PRIMARY KEY (`word`));";
        PreparedStatement p = connection.prepareStatement(query);
        p.execute();
    }

    private static void deleteTable() throws SQLException {
        String query = "DROP TABLE IF EXISTS word";
        connection.prepareStatement(query).execute();
    }

    private static void getAll() throws SQLException {
        String query = "SELECT * FROM word";
        PreparedStatement p = connection.prepareStatement(query);
        ResultSet resultSet = p.executeQuery();
        while (resultSet.next()) {
            String word = resultSet.getString(1);
            int frequency = resultSet.getInt(2);
            System.out.println(word + " -> " + frequency);
        }
    }

    private static void getAllSortByFrequency() throws SQLException {
        String query = "SELECT * FROM word ORDER BY frequency DESC";
        PreparedStatement p = connection.prepareStatement(query);
        ResultSet resultSet = p.executeQuery();
        while (resultSet.next()) {
            String word = resultSet.getString(1);
            int frequency = resultSet.getInt(2);
            System.out.println(word + " -> " + frequency);
        }
    }


    public static void main(String[] args) throws SQLException {
        String db_username = args[0];
        String db_password = args[1];
        String schema = args[2];
        DatabaseConnection db = new DatabaseConnection(db_username, db_password, schema);
        db.connect();
        connection = db.getConnection();
        String filePath = "input.htm";
        deleteTable();
        createTable();
        WordCount wordCount = new WordCount(filePath);
        getAllSortByFrequency();
    }
}
