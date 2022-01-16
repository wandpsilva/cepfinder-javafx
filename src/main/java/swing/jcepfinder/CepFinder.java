package swing.jcepfinder;

import com.google.gson.Gson;
import javafx.gui.model.Endereco;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class CepFinder {
    private static final int WINDOW_WIDTH = 250;
    private static final int WINDOW_HEIGHT = 300;

    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 30;

    private static final int MARGIN_X = 20;
    private static final int MARGIN_Y = 60;

    private JFrame window;

    private JTextField txtCep;

    private JButton btnFind, btnExit;

    private JLabel lblTitulo;
    private JLabel lblResult;

    public CepFinder() {
        window = new JFrame("CEPFINDER");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocationRelativeTo(null);

        lblTitulo = new JLabel();
        lblTitulo.setBounds(20, 45, 200, 10);
        lblTitulo.setText("Enter a cep number to search:");
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        window.add(lblTitulo);

        lblResult = new JLabel();
        lblResult.setBounds(20, 80, 200, 200);
        lblResult.setFont(new Font("Tahoma", Font.BOLD, 12));
        window.add(lblResult);

        txtCep = new JTextField();
        txtCep.setBounds(MARGIN_X, MARGIN_Y, 200, 30);
        txtCep.setBackground(Color.WHITE);
        txtCep.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        window.add(txtCep);

        btnFind = initBtn("FIND", 20, 100, event -> {
            repaintFont();
            String cep = txtCep.getText();
            cep = cep.replace("-", "");

            URL url;
            try {
                url = new URL("https://ws.apicep.com/cep.json?code=" + cep);
                InputStream is = url.openConnection().getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String resultado = br.readLine();
                Gson json = new Gson();
                Endereco end = json.fromJson(resultado, Endereco.class);
                lblResult.setText("<html><p style=width:100px\">" + end.toString() + "</p></html>");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btnExit = initBtn("EXIT", 110, 100, event -> {
            repaintFont();
            System.exit(0);
        });

        window.setLayout(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    private JButton initBtn(String label, int x, int y, ActionListener event) {
        JButton btn = new JButton(label);
        btn.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        btn.addActionListener(event);
        btn.setFocusable(true);

        window.add(btn);

        return btn;
    }

    private void repaintFont() {
        txtCep.setFont(txtCep.getFont().deriveFont(Font.PLAIN));
    }

    public static void main(String[] args) {
        new CepFinder();
    }
}
