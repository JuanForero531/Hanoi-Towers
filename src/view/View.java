package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Disco;

public class View extends JPanel {
    private Collection<Disco> discosDestino = new LinkedList();
    private Collection<Disco> discosMovibles = new LinkedList();
    private Collection<Disco> discosFijos = new LinkedList();
    private Collection<Disco> discosNoMovibles = new LinkedList();
    private Disco discoSeleccionado = null;
    private int posXOriginal;
    private int posYOriginal;

    public View() {
        this.initDiscosFijos();
        this.initDiscosNoMovibles();
        this.addMouseListeners();
    }

    private void initDiscosFijos() {
        this.discosFijos.add(new Disco(90, 360, Color.DARK_GRAY, 80, 3));
        this.discosFijos.add(new Disco(200, 360, Color.DARK_GRAY, 80, 3));
        this.discosFijos.add(new Disco(320, 360, Color.DARK_GRAY, 80, 3));
    }

    private void initDiscosNoMovibles() {
        this.discosNoMovibles.add(new Disco(125, 110, Color.GRAY, 10, 250));
        this.discosNoMovibles.add(new Disco(235, 110, Color.GRAY, 10, 250));
        this.discosNoMovibles.add(new Disco(355, 110, Color.GRAY, 10, 250));
    }

    private void addMouseListeners() {
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                View.this.handleMousePressed(e);
            }

            public void mouseReleased(MouseEvent e) {
                View.this.handleMouseReleased();
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                View.this.handleMouseDragged(e);
            }
        });
    }

    private void handleMousePressed(MouseEvent e) {
        if (this.discoSeleccionado != null) {
            this.posXOriginal = this.discoSeleccionado.getX();
            this.posYOriginal = this.discoSeleccionado.getY();
        }

        Iterator var2 = this.discosMovibles.iterator();

        while(var2.hasNext()) {
            Disco modelo = (Disco)var2.next();
            if (modelo.getX() <= e.getX() && e.getX() <= modelo.getX() + modelo.getWidth() && modelo.getY() <= e.getY() && e.getY() <= modelo.getY() + modelo.getHeight()) {
                this.discoSeleccionado = modelo;
                break;
            }
        }

    }

    private void handleMouseReleased() {
        this.discoSeleccionado = null;
        boolean todosEnDestino = true;
        Iterator var2 = this.discosMovibles.iterator();

        while(var2.hasNext()) {
            Disco discoMovible = (Disco)var2.next();
            boolean discoEnDestino = false;
            Iterator var5 = this.discosDestino.iterator();

            while(var5.hasNext()) {
                Disco discoDestino = (Disco)var5.next();
                if (Math.abs(discoMovible.getX() - discoDestino.getX()) <= 6.6) {
                    discoEnDestino = true;
                    break;
                }
            }

            if (!discoEnDestino) {
                todosEnDestino = false;
                break;
            }
        }

        if (todosEnDestino) {
            JOptionPane.showMessageDialog((Component)null, "¡Has ganado!");

        }

    }

    private void handleMouseDragged(MouseEvent e) {
        if (this.discoSeleccionado != null) {
            int newX = e.getX() - this.discoSeleccionado.getWidth() / 2;
            int newY = e.getY() - this.discoSeleccionado.getHeight() / 2;
            newX = Math.max(0, Math.min(this.getWidth() - this.discoSeleccionado.getWidth(), newX));
            newY = Math.max(0, Math.min(this.getHeight() - this.discoSeleccionado.getHeight(), newY));
            boolean colisionFijos = false;
            Iterator var5 = this.discosFijos.iterator();

            Disco colisionMovible;
            while(var5.hasNext()) {
                colisionMovible = (Disco)var5.next();
                if (newX < colisionMovible.getX() + colisionMovible.getWidth() && newX + this.discoSeleccionado.getWidth() > colisionMovible.getX() && newY < colisionMovible.getY() + colisionMovible.getHeight() && newY + this.discoSeleccionado.getHeight() > colisionMovible.getY()) {
                    colisionFijos = true;
                    break;
                }
            }

            boolean colisionMovibles = false;
            colisionMovible = null;
            Iterator var7 = this.discosMovibles.iterator();

            while(var7.hasNext()) {
                Disco discoMovible = (Disco)var7.next();
                if (this.discoSeleccionado != discoMovible && newX < discoMovible.getX() + discoMovible.getWidth() && newX + this.discoSeleccionado.getWidth() > discoMovible.getX() && newY < discoMovible.getY() + discoMovible.getHeight() && newY + this.discoSeleccionado.getHeight() > discoMovible.getY()) {
                    colisionMovibles = true;
                    colisionMovible = discoMovible;
                    break;
                }
            }

            boolean colisionConDiscosMovibles = false;
            Iterator var12 = this.discosMovibles.iterator();

            while(var12.hasNext()) {
                Disco discoMovible = (Disco)var12.next();
                if (this.discoSeleccionado != discoMovible && this.discoSeleccionado.getWidth() > discoMovible.getWidth() && newX < discoMovible.getX() + discoMovible.getWidth() && newX + this.discoSeleccionado.getWidth() > discoMovible.getX() && newY < discoMovible.getY() + discoMovible.getHeight() && newY + this.discoSeleccionado.getHeight() > discoMovible.getY()) {
                    colisionConDiscosMovibles = true;
                    break;
                }
            }

            if (!colisionFijos && !colisionMovibles && !colisionConDiscosMovibles) {
                this.discoSeleccionado.setPosicion(newX, newY);
                this.repaint();
            } else if (colisionConDiscosMovibles && colisionMovible != null && this.discoSeleccionado.getWidth() > colisionMovible.getWidth()) {
                int posXOriginal = this.discoSeleccionado.getPosXOriginal();
                int posYOriginal = this.discoSeleccionado.getPosYOriginal();
                this.discoSeleccionado.setPosicion(posXOriginal, posYOriginal);
                JOptionPane.showMessageDialog((Component)null, "No se puede realizar el movimiento");
                this.repaint();
            }
        }

    }

    public void initDiscos(int numDiscos) {
        this.discosMovibles.clear();
        int baseWidth = 120;
        int baseHeight = 20;
        Color[] colors = new Color[]{Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE, Color.YELLOW, Color.PINK, Color.MAGENTA, Color.CYAN, Color.LIGHT_GRAY, Color.DARK_GRAY};

        int i;
        int discoWidth;
        int discoX;
        int discoY;
        Disco disco;
        for(i = 0; i < numDiscos; ++i) {
            discoWidth = baseWidth - i * (baseWidth / numDiscos);
            discoX = (260 - discoWidth) / 2;
            discoY = 360 - baseHeight - i * baseHeight;
            disco = new Disco(discoX, discoY, colors[i % colors.length], discoWidth, baseHeight);
            this.discosMovibles.add(disco);
        }

        for(i = 0; i < numDiscos; ++i) {
            discoWidth = baseWidth - i * (baseWidth / numDiscos);
            discoX = (717 - discoWidth) / 2;
            discoY = 360 - baseHeight - i * baseHeight;
            disco = new Disco(discoX, discoY, colors[i % colors.length], discoWidth, baseHeight);
            this.discosDestino.add(disco);
            this.repaint();
        }

        this.repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Iterator var2 = this.discosNoMovibles.iterator();

        Disco discoMovible;
        while(var2.hasNext()) {
            discoMovible = (Disco)var2.next();
            g.setColor(discoMovible.getColor());
            g.fillRect(discoMovible.getX(), discoMovible.getY(), discoMovible.getWidth(), discoMovible.getHeight());
        }

        var2 = this.discosFijos.iterator();

        while(var2.hasNext()) {
            discoMovible = (Disco)var2.next();
            g.setColor(discoMovible.getColor());
            g.fillRect(discoMovible.getX(), discoMovible.getY(), discoMovible.getWidth(), discoMovible.getHeight());
        }

        var2 = this.discosMovibles.iterator();

        while(var2.hasNext()) {
            discoMovible = (Disco)var2.next();
            g.setColor(discoMovible.getColor());
            g.fillRect(discoMovible.getX(), discoMovible.getY(), discoMovible.getWidth(), discoMovible.getHeight());
        }

    }
}