package learning.work.first.head.test.page448;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Created by qiong.liu on 2018/11/19.
 */
public class QuizCardBuilder {
    private JTextArea question;
    private JTextArea answer;
    private ArrayList<QuizCard> cardList;
    private JFrame frame;

    public static void main(String[] args) {
        QuizCardBuilder builder=new QuizCardBuilder();
        builder.go();
    }

    private void go() {
        frame=new JFrame("Quiz Card Builder");
        JPanel mainPanel=new JPanel();
        Font bigFont=new Font("sanserif",Font.BOLD,24);
        question=new JTextArea(6,20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(bigFont);

        JScrollPane qScroller=new JScrollPane(question);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        answer=new JTextArea(6,20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(bigFont);

        JScrollPane aScroller=new JScrollPane(answer);
        aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton nextButton=new JButton("Next Card");
        cardList=new ArrayList<QuizCard>();
        JLabel qLabel=new JLabel("Quesion:");
        JLabel aLabel=new JLabel("Answer:");

        mainPanel.add(qLabel);
        mainPanel.add(qScroller);
        mainPanel.add(aLabel);
        mainPanel.add(aScroller);
        mainPanel.add(nextButton);

        nextButton.addActionListener(new NextCardListener());

        JMenuBar menuBar=new JMenuBar();
        JMenu fileMenu=new JMenu("File");
        JMenuItem newMenuItem=new JMenuItem("New");
        JMenuItem saveMenuItem=new JMenuItem("Save");
        newMenuItem.addActionListener(new NewMenuListener());
        saveMenuItem.addActionListener(new SaveMenuListener());

        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setSize(500,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public class NextCardListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            QuizCard card=new QuizCard(question.getText(),answer.getText());
            cardList.add(card);
            clearCard();
        }
    }

    public class SaveMenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            QuizCard card=new QuizCard(question.getText(),answer.getText());
            cardList.add(card);

            JFileChooser fileSave=new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }

    private void saveFile(File file) {
        try {
            BufferedWriter bw=new BufferedWriter(new FileWriter(file));
            for (QuizCard card:cardList){
                bw.write(card.getQuestion()+"/");
                bw.write(card.getAnswer()+"\n");
            }
            bw.close();

        }catch (Exception ex){
            System.out.println("couldn't write the cardList out!");
        }
    }

    public class NewMenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            cardList.clear();
            clearCard();
        }
    }
    private void clearCard() {
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }

}
