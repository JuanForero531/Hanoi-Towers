package model;
import java.util.Stack;

public class Peg {
    private Stack<Disc> discos;

    public Peg() {
        discos = new Stack<>();
    }

    public void stackDisc(Disc disc) {
        discos.push(disc);
    }

    public Disc unstackDisc() {
        if (!discos.isEmpty()) {
            return discos.pop();
        }
        return null;
    }

    public boolean isEmpty() {
        return discos.isEmpty();
    }

    public Disc viewTopDisc() {
        if (!discos.isEmpty()) {
            return discos.peek();
        }
        return null;
    }
}
