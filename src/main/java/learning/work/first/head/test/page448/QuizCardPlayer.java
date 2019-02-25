package learning.work.first.head.test.page448;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by qiong.liu on 2018/11/19.
 */
public class QuizCardPlayer {

    private JTextArea display;
    private JTextArea answer;
    private JFrame frame;
    private ArrayList<QuizCard> cardList;
    private boolean isShowAnswer;
    private int currentCardIndex;
    private QuizCard currentCard;
    private JButton nextButton;

    public static void main(String[] args) {
        QuizCardPlayer quizCardPlayer=new QuizCardPlayer();
        quizCardPlayer.go();
    }

    private void go() {
        JFrame frame=new JFrame("Quiz Card Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel=new JPanel();
        Font bigFont=new Font("sanserif",Font.BOLD,24);
        display=new JTextArea(10,20);
        display.setFont(bigFont);
        display.setEditable(false);
        display.setLineWrap(true);


//        answer=new JTextArea(6,20);
//        answer.setFont(bigFont);
//        answer.setWrapStyleWord(true);
//        answer.setLineWrap(true);

        JScrollPane qScroller=new JScrollPane(display);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton nextButton=new JButton("Show Question");
        mainPanel.add(qScroller);
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextMenuListener());

        JMenuBar menuBar=new JMenuBar();
        JMenu fileMenu=new JMenu("File");
        JMenuItem loadMenuItem=new JMenuItem("Load card set");
        loadMenuItem.addActionListener(new OpenMenuListener());
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setSize(640,500);
        frame.setVisible(true);

    }

    private class NextMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isShowAnswer){
                display.setText("Next Card");
                isShowAnswer=false;
            }else{
                if (currentCardIndex<cardList.size()){
                    showNextCard();
                }else {
                    display.setText("That was last card");
                    nextButton.setEnabled(false);
                }
            }

        }
    }
    private void showNextCard() {
        currentCard=cardList.get(currentCardIndex);
        currentCardIndex++;
        display.setText(currentCard.getQuestion());
        nextButton.setText("Show Answer");
        isShowAnswer=true;
    }

    private class OpenMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileOpen=new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());

        }
    }

    private void loadFile(File file) {
        cardList = new ArrayList<QuizCard>();
        try {
            BufferedReader reader=new BufferedReader(new FileReader(file));
            String line=null;
            while ((line=reader.readLine())!=null){
                makeCard(line);
            }
            reader.close();
        }catch (Exception ex){
            System.out.println("couldn't read the card file");
            ex.printStackTrace();
        }
    }

    private void makeCard(String line) {
        String[] result=line.split("/");
        QuizCard card=new QuizCard(result[0],result[1]);
        cardList.add(card);
        System.out.println("made a card");
    }




}
