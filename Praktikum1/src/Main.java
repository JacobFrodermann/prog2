import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Main {
    static class Game extends JFrame {
        File wordListFile;
        BorderLayout root = new BorderLayout();
        Canvas canvas;
        WordList wordList;
        boolean gameOver = false;
        int guesses = 0;
        JTextField input = new JTextField();
        JButton restartButton = new JButton("Restart");
        JButton chooseCustomWordListButton = new JButton("Eigene wortliste Auswählen");


        void guess(String word) {
            if (word.length() == 0 || gameOver) return;
            char guess = word.charAt(0);
            boolean result = wordList.guessChar(guess);
            if (!result) guesses++;
            if(guesses > 12 || wordList.hadBeenGuessed()) {
                gameOver = true;
            }

            canvas.repaint(); // update display
        }

        Game() {
            super("Hangman");

            // Initialize Word List
            wordList = new WordList();
            wordList.pickRandomWord();
            Assets.load();

            setBounds(0, 0, 800, 500);
            setLayout(root);
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            canvas = new Canvas();
            canvas.setSize(800, 500);
            add(canvas, BorderLayout.CENTER);

            input.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(gameOver) return;
                    guess(input.getText());
                    input.setText("");
                }
            });
            restartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    guesses = 0;
                    if(wordListFile != null){
                        wordList = new WordList(wordListFile);
                    } else {
                        wordList = new WordList();
                    }
                    gameOver = false;
                    canvas.repaint();
                }
            });
            JFrame frame = this;
            chooseCustomWordListButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FileDialog dia = new FileDialog(frame, "Liste Auswählen", FileDialog.LOAD);
                    dia.setDirectory(System.getProperty("user.dir"));
                    dia.setFilenameFilter(new FilenameFilter() {
                                @Override
                                public boolean accept(File dir, String name) {
                                    return name.endsWith(".txt");
                                }
                            });
                    dia.setVisible(true);
                    File file = new File(dia.getFile());
                    wordList = new WordList(file);
                    wordListFile = file;
                    guesses = 0;
                    gameOver = false;
                    canvas.repaint();
                }
            });

            JPanel bottom = new JPanel();
            bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
            bottom.add(input, BorderLayout.WEST);
            bottom.add(chooseCustomWordListButton, BorderLayout.CENTER);
            bottom.add(restartButton, BorderLayout.EAST);


            add(bottom, BorderLayout.SOUTH);

            setVisible(true);
        }

        class Canvas extends JPanel {
            Canvas() {
                super();
            }
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.white);
                g.fillRect(0,0,getWidth(),getHeight());
                g.setFont(g.getFont().deriveFont(30f));

                int i = 0;
                for (char c : wordList.guessedChars) {
                    i++;
                    if (wordList.getWord().toLowerCase().contains(String.valueOf(Character.toLowerCase(c)))) {
                        g.setColor(Color.GREEN);
                    } else {
                        g.setColor(Color.RED);
                    }
                    if(Character.isUpperCase(c)) continue;
                    g.drawString(String.valueOf(c), i * 30, 350);
                }
                g.setColor(Color.BLACK);
                char[] chars = wordList.getMaskedWord().toCharArray();
                for (i=0; i < wordList.getMaskedWord().length(); i++) {
                    // draw underscore under every character
                    g.drawString("_", 440+30*i,205);

                    // draw the guessed word parts
                    if (chars[i] != '_') g.drawString(String.valueOf(chars[i]), 440+30*(i%20),200+(i/20)*60); // next line after 20 chars
                }

                g.drawImage(Assets.hangman[guesses], 0,0, null);

                if (gameOver) {
                    g.setFont(g.getFont().deriveFont(60f));
                    if (wordList.hadBeenGuessed()) {
                        g.setColor(Color.green);
                        g.drawString("Victory", 200, 100);
                    } else {
                        g.setColor(Color.red);
                        g.drawString("Gameover", 150,100);
                    }
                }
            }
        }
    }
    public class Assets {
        public static BufferedImage[] hangman = new BufferedImage[14];
        public static void load() {
            try {
                for (int i = 0; i < hangman.length; i++) {
                    hangman[i] = ImageIO.read(new File("assets/"+(i+1)+".png"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String version = System.getProperty("java.version");
        System.out.println(version);
        new Game();
    }
}
