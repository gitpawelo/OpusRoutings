package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Controller {

    String path;

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

        path = String.valueOf(selectedFile);

        if (selectedFile != null){

            ocCustIdListView.getItems().addAll(selectedFile.getAbsolutePath());
        }else
            System.out.println("file is not valid");


/*        if (selectedFile != null){
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

*/    }

    public void ButtonCreateJson(ActionEvent event) throws Exception{

        String tpa, jsonName, pathOut, entireFileText;

        tpa = tpaTextField.getText();
        jsonName = jsonTextField.getText();


        entireFileText = new Scanner(new File(path))
                .useDelimiter("\\A").next();

        path = path.replace("\\", "\\\\");

        pathOut = path;
        //replace file name after last backslashes to name from jsonTextField, ie: \\test.txt -> \\newfile.json
        pathOut = pathOut.replace(pathOut.substring(pathOut.lastIndexOf("\\\\")),"\\\\" + jsonName + ".json");

        System.out.println(entireFileText);
        List<String> result = new ArrayList<String>(Arrays.asList(entireFileText.split(";")));

        System.out.println("result: " + result);

        BufferedReader reader = null;
        BufferedWriter writer = null;

        System.out.println(result.get(0));


        try {
            reader = new BufferedReader(new FileReader(path));
            writer = new BufferedWriter(new FileWriter(pathOut));

            String line1;

            writer.write("rarstrfysd\n"
                    + "djfsbfisd\n"
                    + "sdfsdfsdf\n");

            while ((line1 = reader.readLine()) != null) {

                writer.write(line1
                        .replace(line1, "")
                        .concat("tata " + line1 + " mamam " + line1 + " sdsf")

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



    }



}
