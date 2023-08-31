package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JPanel {

    private int contadorMovimientos = 0;
    private JLabel contadorLabel = new JLabel("Movimientos: 0");
    private boolean juegoGanado = false;

 public View() {
    //nuevo
        JLabel nombres = new JLabel("Desarrolladores: Juan José Forero, Samuel Vargas, Sebastian Daza");
        add(nombres, BorderLayout.NORTH);
        contadorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        contadorLabel.setBounds(10, 100, 150, 20);
        add(contadorLabel);
     ...
 }

       public void mouseReleased(MouseEvent e) {
           //nuevo
           if (discoSeleccionado != null) {
                    int distanciaX = Math.abs(e.getX() - 350);
                    if (discoSeleccionado.getColor() == Color.BLUE && distanciaX <= 10) {
                        juegoGanado = true;
                        repaint();
                    }
            }
      }

     public void mousePressed(MouseEvent e) {
             //nuevo
                contadorMovimientos++;
                contadorLabel.setText("Movimientos: " + contadorMovimientos);

         
                for (Disco modelo : discosMovibles) {
                    if (modelo.getX() <= e.getX() && e.getX() <= modelo.getX() + modelo.getWidth() &&
                            modelo.getY() <= e.getY() && e.getY() <= modelo.getY() + modelo.getHeight()) {
                        discoSeleccionado = modelo;
                        break;
                    }
                }
            }

     @Override
    protected void paintComponent(Graphics g) {
        //nuevo
        g.drawString("Movimientos: " + contadorMovimientos, 10, 100);

        super.paintComponent(g);
        for (Disco discoNoMovible : discosNoMovibles) {
            g.setColor(discoNoMovible.getColor());
            g.fillRect(discoNoMovible.getX(), discoNoMovible.getY(), discoNoMovible.getWidth(), discoNoMovible.getHeight());
        }
        for (Disco discoFijo : discosFijos) {
            g.setColor(discoFijo.getColor());
            g.fillRect(discoFijo.getX(), discoFijo.getY(), discoFijo.getWidth(), discoFijo.getHeight());
        }
        for (Disco discoMovible : discosMovibles) {
            g.setColor(discoMovible.getColor());
            g.fillRect(discoMovible.getX(), discoMovible.getY(), discoMovible.getWidth(), discoMovible.getHeight());

            //nuevo
            if (juegoGanado) { 
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 20));  
                g.drawString("¡Has ganado!", 200, 150);
            }
        }
    }

  
}
