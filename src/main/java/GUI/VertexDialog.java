package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VertexDialog extends JDialog implements ActionListener {
    private TextField vertex;
    private JButton add;
    private JPanel panel;
    private JPanel bPanel;

    public VertexDialog() {
        vertex = new TextField();
        add = new JButton();
        panel = new JPanel();
        bPanel = new JPanel();
        bPanel.setLayout(new GridLayout(1, 3));
        bPanel.add(add, 3);

        panel.setLayout(new BorderLayout());
        panel.add(vertex, "Center");
        panel.add(bPanel, "South");

        this.setSize(200, 100);
        this.setModal(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);

        add.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String object = this.vertex.getText();
    }
}
