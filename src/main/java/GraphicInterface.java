import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Administrator on 7/12/2017.
 */
public class GraphicInterface extends JFrame{
    JButton translate;
    JTextArea input, output;

    public GraphicInterface() {
        super("Translate with Ya");
        final JPanel panel = new JPanel();
        input = new JTextArea("Input Field max 10000 symbols", 10, 120);
        input.setLineWrap(true);
        JScrollPane scrollPaneForInput = new JScrollPane(input);
        output = new JTextArea("Output Field", 10, 120);
        output.setLineWrap(true);
        JScrollPane scrollPaneForOutput = new JScrollPane(output);
        translate = new JButton("Translate");
        translate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = input.getText();
                String result = "";
                try {
                    result = result + Translater.connection(text);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                output.setText(result);
            }
        });
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(scrollPaneForInput);
        panel.add(translate);
        panel.add(scrollPaneForOutput);
        setContentPane(panel);
        setSize(1000, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        GraphicInterface graphicInterface = new GraphicInterface();
    }
}
