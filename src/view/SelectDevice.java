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
import javax.swing.JPanel;
import utils.FindFiles;

/**
 *
 * @author Adam
 */
public class SelectDevice extends JFrame implements ActionListener {

    JPanel panel;
    JButton systemButton;
    JButton externalDevice1Button;
    JButton externalDevice2Button;
    Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize();

    public SelectDevice() {
        super("Select");
        panel = new JPanel();
        panel.setBackground(Color.BLACK);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(monitorSize.width / 2, monitorSize.height / 2);
        this.setLocation(monitorSize.width / 4, monitorSize.height / 4);
        this.add(panel);
        panel.setLayout(new GridLayout(1, 1));
        Font font = new Font("TimesRoman", Font.PLAIN, 14);

        systemButton = new JButton("system");
        systemButton.setFont(font);
        systemButton.setBackground(Color.BLACK);
        systemButton.setForeground(Color.WHITE);
        systemButton.setActionCommand("system");
        systemButton.addActionListener(this);
        panel.add(systemButton);
        
        externalDevice1Button = new JButton();
        externalDevice1Button.setFont(font);
        externalDevice1Button.setBackground(Color.BLACK);
        externalDevice1Button.setForeground(Color.WHITE);
        externalDevice1Button.setActionCommand("external1");
        externalDevice1Button.addActionListener(this);
        panel.add(externalDevice1Button);
        
        externalDevice2Button = new JButton();
        externalDevice2Button.setFont(font);
        externalDevice2Button.setBackground(Color.BLACK);
        externalDevice2Button.setForeground(Color.WHITE);
        externalDevice2Button.setActionCommand("external2");
        externalDevice2Button.addActionListener(this);
        panel.add(externalDevice2Button);

        setImageOnButton();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("system")) {
            FindFiles ff = new FindFiles();
            ff.find(System.getProperty("user.home"));
        }
        
        if (e.getActionCommand().equals("external1")) {
            FindFiles ff = new FindFiles();
            ff.devices();
        }
        
        if (e.getActionCommand().equals("external2")) {
            
        }
    }
    
    private void setImageOnButton() {
        try {
            Image img = ImageIO.read(new File("resources/open_system.png"));
            //Image resizeImg = img.getScaledInstance(exitButton.getWidth(), exitButton.getHeight(), java.awt.Image.SCALE_SMOOTH);
            systemButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            String text = "Open (Error " + ex + ")";
            systemButton.setText(text);
        }
        
        try {
            Image img = ImageIO.read(new File("resources/open_external.png"));
            //Image resizeImg = img.getScaledInstance(exitButton.getWidth(), exitButton.getHeight(), java.awt.Image.SCALE_SMOOTH);
            externalDevice1Button.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            String text = "Open (Error " + ex + ")";
            externalDevice1Button.setText(text);
        }
        
        try {
            Image img = ImageIO.read(new File("resources/open_external.png"));
            //Image resizeImg = img.getScaledInstance(exitButton.getWidth(), exitButton.getHeight(), java.awt.Image.SCALE_SMOOTH);
            externalDevice2Button.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            String text = "Open (Error " + ex + ")";
            externalDevice2Button.setText(text);
        }
    }
}