package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class MainFrame extends JFrame implements ActionListener {
    private JPanel mainPanel, resultPanel, buttonPanel;
    private JButton addVertex, addEdge, show;
    private TextArea result;
    private VertexDialog vertex;
    private EdgeDialog edge;

    public MainFrame() {
        super("채무 관계 그래프");         //  dept relations graph

        mainPanel = new JPanel();
        resultPanel = new JPanel();
        buttonPanel = new JPanel();

        result = new TextArea();

        addVertex = new JButton("인원 추가");
        addEdge = new JButton("채무 관계 추가");
        show = new JButton("결과 출력");

        resultPanel.add(result);

        buttonPanel.add(addVertex);
        buttonPanel.add(addEdge);
        buttonPanel.add(show);
        buttonPanel.setLayout(new GridLayout(1, 3));

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(resultPanel, "Center");
        mainPanel.add(buttonPanel, "South");


        this.add(mainPanel);
        this.setSize(400, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addVertex)) {            //  if addVertex is pressed
            vertex = new VertexDialog();
        } else if (e.getSource().equals(addEdge)) {         //  if addEdge is pressed
            Vector<String> temp = new Vector<String>();     //temporary code. need to get vertex list(Vector)and add it to here.
            edge = new EdgeDialog(temp);
        } else {                                            //   if result is pressed
            //calculate and show result on result(TextArea)
        }
    }
}
