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
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import utils.Music;
import utils.SaveData;

/**
 *
 * @author Adam
 */
public class MusicWindow extends JFrame implements ActionListener {

    JList list;
    JScrollPane scrollPane;
    JButton openButton;
    JButton exitButton;
    JButton playButton;
    JButton pauseButton;
    JButton stopButton;
    JPanel panel;
    JPanel selectFilePanel;
    JPanel musicPanel;
    Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize();
    JFileChooser fc = new JFileChooser();

    String directory;
    List<File> listFile;
    Music music;
    SaveData saveDataToXml;

    public MusicWindow() {
        super("Music");
        panel = new JPanel();
        panel.setBackground(Color.BLACK);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(monitorSize.width, monitorSize.height);
        this.setLocation(0, 0);
        this.add(panel);
        panel.setLayout(new GridLayout(2, 1));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Font font = new Font("TimesRoman", Font.PLAIN, 14);
        Font fontList = new Font("TimesRoman", Font.PLAIN, 32);

        selectFilePanel = new JPanel();
        selectFilePanel.setBackground(Color.BLACK);
        selectFilePanel.setLayout(new GridLayout(1, 3));

        openButton = new JButton();
        openButton.setFont(font);
        openButton.setBackground(Color.BLACK);
        openButton.setForeground(Color.WHITE);
        openButton.setActionCommand("open");
        openButton.addActionListener(this);
        selectFilePanel.add(openButton);

        list = new JList();
        list.setFont(fontList);
        list.setBackground(Color.BLACK);
        list.setForeground(Color.WHITE);
        scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(100, Integer.MAX_VALUE));
        scrollPane.setBackground(Color.BLACK);
        scrollPane.setForeground(Color.BLACK);
        scrollPane.getVerticalScrollBar().setBackground(Color.GRAY);
        selectFilePanel.add(scrollPane);

        exitButton = new JButton();
        exitButton.setFont(font);
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.setActionCommand("exit");
        exitButton.addActionListener(this);
        selectFilePanel.add(exitButton);

        panel.add(selectFilePanel);

        musicPanel = new JPanel();
        musicPanel.setBackground(Color.BLACK);
        musicPanel.setLayout(new GridLayout(1, 3));

        playButton = new JButton();
        playButton.setFont(font);
        playButton.setBackground(Color.BLACK);
        playButton.setForeground(Color.WHITE);
        playButton.setActionCommand("play");
        playButton.addActionListener(this);
        musicPanel.add(playButton);

        pauseButton = new JButton();
        pauseButton.setFont(font);
        pauseButton.setBackground(Color.BLACK);
        pauseButton.setForeground(Color.WHITE);
        pauseButton.setActionCommand("pause");
        pauseButton.addActionListener(this);
        musicPanel.add(pauseButton);

        stopButton = new JButton();
        stopButton.setFont(font);
        stopButton.setBackground(Color.BLACK);
        stopButton.setForeground(Color.WHITE);
        stopButton.setActionCommand("stop");
        stopButton.addActionListener(this);
        musicPanel.add(stopButton);
        panel.add(musicPanel);

        setImageOnButton();
        readFile();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("open")) {
            new SelectDevice();
            //open();
        }
        if (e.getActionCommand().equals("play")) {
            if (list.getSelectedIndex() != -1) {
                String name = list.getSelectedValue().toString();
                music = new Music(directory + name);
                music.play();
            }
        }
        if (e.getActionCommand().equals("pause")) {
            music.pausePlay();

        }
        if (e.getActionCommand().equals("stop")) {
            music.stopPlay();

        }
        if (e.getActionCommand().equals("exit")) {
            exit();
        }
    }

    private void setImageOnButton() {
        try {
            Image img = ImageIO.read(new File("resources/exit.png"));
            //Image resizeImg = img.getScaledInstance(exitButton.getWidth(), exitButton.getHeight(), java.awt.Image.SCALE_SMOOTH);
            exitButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            String text = "Exit (Error " + ex + ")";
            exitButton.setText(text);
        }

        try {
            Image img = ImageIO.read(new File("resources/open.png"));
            //Image resizeImg = img.getScaledInstance(openButton.getWidth(), openButton.getHeight(), java.awt.Image.SCALE_SMOOTH);
            openButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            String text = "Open (Error " + ex + ")";
            openButton.setText(text);
        }

        try {
            Image img = ImageIO.read(new File("resources/play.png"));
            //Image resizeImg = img.getScaledInstance(openButton.getWidth(), openButton.getHeight(), java.awt.Image.SCALE_SMOOTH);
            playButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            String text = "Play (Error " + ex + ")";
            playButton.setText(text);
        }

        try {
            Image img = ImageIO.read(new File("resources/pause.png"));
            //Image resizeImg = img.getScaledInstance(openButton.getWidth(), openButton.getHeight(), java.awt.Image.SCALE_SMOOTH);
            pauseButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            String text = "Pause (Error " + ex + ")";
            pauseButton.setText(text);
        }

        try {
            Image img = ImageIO.read(new File("resources/stop.png"));
            //Image resizeImg = img.getScaledInstance(openButton.getWidth(), openButton.getHeight(), java.awt.Image.SCALE_SMOOTH);
            stopButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            String text = "Stop (Error " + ex + ")";
            stopButton.setText(text);
        }
    }

    private void readFile() {
        saveDataToXml = new SaveData();
        try {
            directory = saveDataToXml.readFromFile();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println("Nieudany odczyt pliku");
            ex.printStackTrace();
        }
        setListData(directory);
    }

    private void open() {
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileFilter filter = new FileNameExtensionFilter("MP3", "mp3");
        fc.setFileFilter(filter);
        fc.showOpenDialog(this);
        directory = fc.getCurrentDirectory().getAbsolutePath();
        directory = directory.replaceAll("\\\\", "/");
        directory = directory + "/";
        setListData(directory);
    }

    private void exit() {
        if (directory.isEmpty()) {
        } else {
            saveDataToXml = new SaveData();
            saveDataToXml.setDirectory(directory);
            if (list.getSelectedIndex() != -1) {
                saveDataToXml.setMusicFileName(list.getSelectedValue().toString());
            }
            try {
                saveDataToXml.saveToFile();
            } catch (ParserConfigurationException | TransformerException ex) {
                System.out.println("Nieudany zapis pliku");
                ex.printStackTrace();
            }
        }
        this.dispose();
    }

    private void setListData(String directory) {
        if (directory.isEmpty()) {

        } else {
            fc.setCurrentDirectory(new File(directory));
            File[] filesInDirectory = fc.getCurrentDirectory().listFiles();
            listFile = new ArrayList<>();
            List<String> fileName = new ArrayList<>();
            for (File file : filesInDirectory) {
                if (getExtension(file.getName()).equals("mp3")) {
                    fileName.add(file.getName());
                    listFile.add(file);
                }
            }
            list.setListData(fileName.toArray());
        }
    }

    private String getExtension(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }

}
