package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utils.FindFiles;

/**
 *
 * @author Adam
 */
public class MainWindow extends JFrame implements ActionListener {

    JButton[] button = new JButton[8];
    JPanel panel;
    Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize();

    public MainWindow() {
        super("CarApplication");
        panel = new JPanel();
        panel.setBackground(Color.BLACK);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(monitorSize.width, monitorSize.height);
        this.setLocation(0, 0);
        this.add(panel);
        panel.setLayout(new GridLayout(2, button.length / 2));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Font font = new Font("TimesRoman", Font.PLAIN, 14);
        //wczytuje wszystkie przyciski
        for (int i = 0; i < button.length; i++) {
            button[i] = new JButton();
            button[i].setFont(font);
            button[i].setBackground(Color.BLACK);
            button[i].setForeground(Color.WHITE);
            panel.add(button[i]);
        }
        try {
            Image img = ImageIO.read(new File("resources/music.png"));
            Image resizeImg = img.getScaledInstance(button[0].getWidth(), button[0].getHeight(), java.awt.Image.SCALE_SMOOTH);
            button[0].setIcon(new ImageIcon(resizeImg));
        } catch (IOException ex) {
            String text = "Muzyka (Error " + ex + ")";
            button[0].setText(text);
        } finally {
            button[0].setActionCommand("music");
            button[0].addActionListener(this);
        }

        try {
            Image img = ImageIO.read(new File("resources/video.png"));
            Image resizeImg = img.getScaledInstance(button[1].getWidth(), button[1].getHeight(), java.awt.Image.SCALE_SMOOTH);
            button[1].setIcon(new ImageIcon(resizeImg));
        } catch (IOException ex) {
            String text = "Video (Error " + ex + ")";
            button[1].setText(text);
        } finally {
            button[1].setActionCommand("video");
            button[1].addActionListener(this);
        }

        try {
            Image img = ImageIO.read(new File("resources/gMetter.png"));
            Image resizeImg = img.getScaledInstance(button[2].getWidth(), button[2].getHeight(), java.awt.Image.SCALE_SMOOTH);
            button[2].setIcon(new ImageIcon(resizeImg));
        } catch (IOException ex) {
            String text = "Gmetter (Error " + ex + ")";
            button[2].setText(text);
        } finally {
            button[2].setActionCommand("gMetter");
            button[2].addActionListener(this);
        }

        try {
            Image img = ImageIO.read(new File("resources/exit.png"));
            Image resizeImg = img.getScaledInstance(button[7].getWidth(), button[7].getHeight(), java.awt.Image.SCALE_SMOOTH);
            button[7].setIcon(new ImageIcon(resizeImg));
        } catch (IOException ex) {
            String text = "Exit (Error " + ex + ")";
            button[7].setText(text);
        } finally {
            button[7].setActionCommand("exit");
            button[7].addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("music")) {
            new MusicWindow();
        }
        if (e.getActionCommand().equals("video")) {
            JOptionPane.showMessageDialog(this, "Wciśnięto", "OK!", WIDTH, null);
        }

        if (e.getActionCommand().equals("gMetter")) {
            JOptionPane.showMessageDialog(this, "Wciśnięto", "OK!", WIDTH, null);
        }
        if (e.getActionCommand().equals("exit")) {
            this.dispose();
            System.exit(1);
        }
    }

}
