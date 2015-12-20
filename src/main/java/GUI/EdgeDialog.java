package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class EdgeDialog extends JDialog implements ActionListener {
    private JPanel panel;
    private JButton add;
    private JComboBox sender, receiver;
    private TextArea money;

    public EdgeDialog(Vector<String> List) {
        JLabel send, receive, pay;
        send = new JLabel("송신자 : ");
        receive = new JLabel("수신자 : ");
        pay = new JLabel("지불 금액 : ");
        sender = new JComboBox(List);
        receiver = new JComboBox(List);
        money = new TextArea();
        add = new JButton("추가");

        panel = new JPanel();
        panel.add(send);
        panel.add(sender);
        panel.add(receive);
        panel.add(receiver);
        panel.add(pay);
        panel.add(money);
        panel.setLayout(new GridLayout(4, 2));
        panel.add(add, 8);

        add.addActionListener(this);

        this.setSize(200, 100);
        this.setModal(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
