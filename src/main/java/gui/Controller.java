package gui;

import com.google.gson.Gson;
import gui.model.Endereco;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnSair;

    @FXML
    private TextField txtCep;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void onBtnBuscarAction() throws IOException {
        Locale.setDefault(Locale.US);
        String cep = txtCep.getText();

        URL url = new URL("https://ws.apicep.com/cep.json?code=" + cep);
        InputStream is = url.openConnection().getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String resultado = br.readLine();

        Gson json = new Gson();
        Endereco end = json.fromJson(resultado, Endereco.class);

        Alerts.showAlert("Endereco: ", null, end.toString(), Alert.AlertType.INFORMATION);
    }

    @FXML
    public void onBtnSairAction() {
        System.exit(0);
    }

    public void validaCampos(String val) {
        if (val.isEmpty()) {
            Alerts.showAlert("Erro", "Campos não preenchidos corretamente",
                    "Você precisa preencher um cep válido!", Alert.AlertType.ERROR);
            txtCep.requestFocus();
        }
    }
}
