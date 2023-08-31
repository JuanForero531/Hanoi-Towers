package model;
import java.awt.*;
public class Disco {
    private int x;
    private int y;
    private int posXOriginal;
    private int posYOriginal;
    private int posXAnterior;
    private int posYAnterior;

    private Color color;
    private int width;
    private int height;

    public Disco(){

    }
    public Disco(int x, int y, Color color, int width, int height) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.width = width;
        this.height = height;
        this.posXOriginal = x; // Guardar la posición original en X
        this.posYOriginal = y; // Guardar la posición original en Y
    }
    public int getX() {
        return x;
    }
    public int getPosXAnterior() {
        return posXAnterior;
    }
    public int getPosYAnterior() {
        return posYAnterior;
    }
    public int getPosXOriginal() {
        return posXOriginal;
    }

    public int getPosYOriginal() {
        return posYOriginal;
    }
    public void setPosXAnterior(int posXAnterior) {
        this.posXAnterior = posXAnterior;
    }

    public void setPosYAnterior(int posYAnterior) {
        this.posYAnterior = posYAnterior;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setPosicion(int x, int y) {
        posXAnterior = this.x;
        posYAnterior = this.y;
        this.x = x;
        this.y = y;
    }

}

