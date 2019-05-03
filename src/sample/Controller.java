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

        if (selectedFile != null) {

            ocCustIdListView.getItems().addAll(selectedFile.getAbsolutePath());
        } else
            System.out.println("file is not valid");

    }

    public void ButtonCreateJson(ActionEvent event) throws Exception {

        String tpa, jsonName, pathOut, entireFileText;
        int arraySize;

        tpa = tpaTextField.getText();

        jsonName = jsonTextField.getText();

        entireFileText = new Scanner(new File(path))
                .useDelimiter("\\A").next();

        //replace single '\' to double '\\' to create readable path for BufferedReader/BufferedWriter
        path = path.replace("\\", "\\\\");

        pathOut = path;

        //replace file name after last backslashes to name from jsonTextField, ie: \\test.txt -> \\newfile.json
        pathOut = pathOut.replace(pathOut.substring(pathOut.lastIndexOf("\\\\")), "\\\\" + jsonName + ".json");

        //split values per newline and add it to ArrayList
        List<String> result = new ArrayList<String>(Arrays.asList(entireFileText.split("\n")));

        arraySize = result.size();

        BufferedReader reader = null;
        BufferedWriter writer = null;


        try {
            reader = new BufferedReader(new FileReader(path));
            writer = new BufferedWriter(new FileWriter(pathOut));

            String line;

            writer.write("{\n" +
                    "\n" +
                    "   \"senderLists\": [\n" +
                    "\n" +
                    "      {\n" +
                    "\n" +
                    "        \"id\":   \"allow-all\",\n" +
                    "\n" +
                    "        \"type\": \"blacklist\",\n" +
                    "\n" +
                    "        \"routingIds\": []\n" +
                    "\n" +
                    "      }\n" +
                    "\n" +
                    "   ],\n" +
                    "\n" +
                    "   \"routes\": [\n" +
                    "\n" +
                    "      {\n" +
                    "\n" +
                    "        \"id\": \"idf1.digiInvoice\",\n" +
                    "\n" +
                    "        \"startDate\": \"2018-01-31\",\n" +
                    "\n" +
                    "        \"flowId\": \"digitizing\",\n" +
                    "\n" +
                    "        \"fromListId\": \"allow-all\",\n" +
                    "\n" +
                    "        \"configuredDeliveryChannelId\":\n" +
                    "\n" +
                    "            \"" + tpa + "\",\n" +
                    "\n" +
                    "        \"messageType\": \"invoice\",\n" +
                    "\n" +
                    "        \"messageTypeSub\": \"*\",\n" +
                    "\n" +
                    "        \"enrichement\": {\n" +
                    "\n" +
                    "         \"items\":     []\n" +
                    "\n" +
                    "        }\n" +
                    "\n" +
                    "      },\n" +
                    "\n" +
                    " \n"
            );

            while ((line = reader.readLine()) != null) {

                writer.write(line
                        .replace(line, "")
                        .concat("      { \"parent\": \"idf1.digiInvoice\", \"id\": \"" + line + "\", \"to\": { \"type\": \"OC.CUSTOMER.ID\", \"value\": \"" + line + "\" }},\n")
                        //remove from last line ','
                        .replace(result.get(arraySize - 1) + "\" }},", result.get(arraySize - 1) + "\" }}\n" +
                                "\n" +
                                "   ]\n" +
                                "\n" +
                                "}")

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
