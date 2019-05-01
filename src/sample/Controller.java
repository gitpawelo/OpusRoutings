package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.*;

public class Controller {

    @FXML
    private Button browseBtn;

    @FXML
    private Button createJsonBtn;

    @FXML
    private TextField tpaTextField;

    @FXML
    private TextField jsonTextField;

    @FXML
    private ListView ocCustIdListView;


    public void buttonBrowse(ActionEvent event) throws Exception {

        FileChooser fc = new FileChooser();
        File selectedFile = new File(fc.showOpenDialog(null).toString());

        String path = String.valueOf(selectedFile);
        if (selectedFile != null){
//            ocCustIdListView.getSelectionModel().getSelectedItems().addAll(selectedFile.list());
            ocCustIdListView.getItems().addAll(selectedFile.getAbsolutePath());
            BufferedReader reader = null;
            BufferedWriter writer = null;

            String pathOut = "C:\\Users\\Pawel\\Desktop\\output.txt";

            System.out.println("ściezka: " + path);
//            path.replace('', 'D');
            path = path.replace("\\", "\\\\");
            System.out.println("ściezka: " + path);

            try {
                reader = new BufferedReader(new FileReader(path));
                writer = new BufferedWriter(new FileWriter(pathOut));


                String line1;

                while ((line1 = reader.readLine()) != null) {
                    writer.write(line1
                            .replace("WItaj", "Hello")

                    );

                    writer.append("\n");
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            }
        }else
            System.out.println("file is not valid");

    }

    public void ButtonCreateJson(ActionEvent event) throws Exception{

        String tpa;

    tpa = tpaTextField.getCharacters().toString();

        System.out.println("tpa: " + tpa);

    }



}
