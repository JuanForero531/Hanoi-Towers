package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JLabel titleLabel;
    private JPanel levelPanel;
    private JLabel levelLabel;
    private JComboBox<String> levelComboBox;
    public JButton startButton;
    private JTable solutionTable;
    private JScrollPane tableScrollPane;
    private JLabel imageLabel;
    private JLabel developersLabel; // Etiqueta para los nombres de los desarrolladores
    private DefaultTableModel tableModel;

    public View() {
        setTitle("El Desafío de la Torre de Hanoi");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Cambiar color de fondo a azul claro
        getContentPane().setBackground(new Color(176, 224, 230));

        developersLabel = new JLabel("Desarrollado por: Juan José Forero, Samuel Vargas y Sebastian Daza"); // Nombres de los desarrolladores
        developersLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(developersLabel, BorderLayout.NORTH);

        titleLabel = new JLabel("Torre de Hanoi", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(titleLabel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());

        levelPanel = new JPanel();
        levelPanel.setBackground(new Color(70, 130, 180));
        levelPanel.setLayout(new FlowLayout());
        levelLabel = new JLabel("Nivel:");
        levelPanel.add(levelLabel);
        levelComboBox = new JComboBox<>(new String[]{"Principiantes", "Novato", "Intermedio", "Avanzado", "Experto", "Maestro"});
        levelPanel.add(levelComboBox);
        centerPanel.add(levelPanel);

        startButton = new JButton("Iniciar");
        centerPanel.add(startButton);

        add(centerPanel, BorderLayout.CENTER);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Número de Movimiento");
        tableModel.addColumn("Número de Disco");
        tableModel.addColumn("Desde");
        tableModel.addColumn("Hasta");
        solutionTable = new JTable(tableModel);

        // Cambiar color de fondo de los títulos de las tablas a azul oscuro
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(70, 130, 180));
        for (int i = 0; i < solutionTable.getColumnModel().getColumnCount(); i++) {
            solutionTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        tableScrollPane = new JScrollPane(solutionTable);
        add(tableScrollPane, BorderLayout.SOUTH);

        ImageIcon imageIcon = new ImageIcon("src\\view\\hanoi_image.jpeg");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(500, 150, Image.SCALE_SMOOTH);
        imageIcon.setImage(scaledImage);
        imageLabel = new JLabel(imageIcon);
        add(imageLabel, BorderLayout.EAST);

        setVisible(true);
    }

    public void addActionListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }

    public int getSelectedLevelIndex() {
        return levelComboBox.getSelectedIndex();
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}