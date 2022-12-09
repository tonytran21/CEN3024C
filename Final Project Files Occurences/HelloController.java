package com.example.module6;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class HelloController {
    @FXML
    private Text resultText;

    @FXML private TextField locationText;

    public void onChooseFile() throws Exception {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select htm file");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.htm", "*.html"));

        File file = fc.showOpenDialog(resultText.getScene().getWindow());
        // set the location textfield with path
        locationText.setText(file.getPath());
        // word objects from main
        List<Main.Word> wordObjects = Main.getWordFrequencies(file);

        StringBuilder text = new StringBuilder();

        for (Main.Word word : wordObjects) { // make one string text
            text.append(word.val).append(" -> ").append(word.frequency).append("\n");
        }
        resultText.setVisible(true);
        resultText.setText(text.toString());
    }

}