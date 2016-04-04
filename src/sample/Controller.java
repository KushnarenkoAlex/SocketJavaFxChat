package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Optional;

public class Controller {

    private UserHolder userHolder;

    Socket client;

    private BufferedReader in;
    private PrintWriter out;

    @FXML
    private Button button;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField messText;


    @FXML
    private Label nameLabel;

    @FXML
    public void initialize() {
        TextInputDialog dialog = new TextInputDialog("name");
        dialog.setTitle("Name");
        dialog.setHeaderText("Input your name");
        dialog.setContentText("Please enter your name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> userHolder = new UserHolder(name));

        nameLabel.setText(userHolder.getName());
        try {
            client = new Socket("localhost", 8080);

            in = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
            out.write("name:" + userHolder.getName() + '\n');
            out.flush();
            new Daemon().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onClickMethod() {

        String messTextText = messText.getText();
        //DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
        out.write(userHolder.getName() + ":" + messTextText + '\n');
        out.flush();
        messText.clear();
        //textArea.appendText(messTextText + '\n');
        //client.close();

    }

    class Daemon extends Thread {

        public Daemon() {
            setDaemon(true);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String s = in.readLine();
                    if (s != null && !s.isEmpty()) {
                        textArea.appendText(s + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
