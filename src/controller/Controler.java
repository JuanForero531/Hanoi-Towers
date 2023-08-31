package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.*;

import view.View;

public class Controler {
    private static final String FILE_PATH = "C:\\Users\\Asus\\Downloads\\Interface-Hanoi\\Interface-Hanoi\\src\\controler\\levels.txt";

    public Controler() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static Map<String, Integer> readLevelsFromFile() {
        Map<String, Integer> levels = new HashMap();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Asus\\Downloads\\Interface-Hanoi\\Interface-Hanoi\\src\\controler\\levels.txt"));

            String line;
            try {
                while((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        String level = parts[0].trim();
                        int numDiscos = Integer.parseInt(parts[1].trim());
                        levels.put(level, numDiscos);
                    }
                }
            } catch (Throwable var7) {
                try {
                    reader.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }

                throw var7;
            }

            reader.close();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        return levels;
    }

    private static void createAndShowGUI() {
        View vista = new View();
        JFrame frame = new JFrame("Torres Hanoi");
        JPanel panel = new JPanel();
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Dificultad");
        frame.setDefaultCloseOperation(3);
        Map<String, Integer> levels = readLevelsFromFile();
        Iterator var6 = levels.keySet().iterator();

        while(var6.hasNext()) {
            String level = (String)var6.next();
            JMenuItem levelItem = new JMenuItem(level);
            m1.add(levelItem);
            int numDiscos = (Integer)levels.get(level);
            levelItem.addActionListener((e) -> {
                vista.initDiscos(numDiscos);
                vista.repaint();
            });
        }

        mb.add(m1);
        JTextArea text = new JTextArea("Nombres: Sebastian Daza \n Samuel Vargas \n Juan Jose Forero");
        text.setEditable(false);
        panel.add(text);
        frame.setJMenuBar(mb);
        frame.getContentPane().add("South", panel);
        frame.getContentPane().add(vista);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
