package model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Hanoi_Tower {
    private Peg pegA;
    private Peg pegB;
    private Peg pegC;
    private int difficultyLevel;

    public Hanoi_Tower(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        pegA = new Peg();
        pegB = new Peg();
        pegC = new Peg();
        initializeDiscs();
    }

    private void initializeDiscs() {
        // Read the number of discs from a text file
        LinkedList<Integer> discNumbers = readDiscNumbers();

        // Get the number of discs based on the difficulty level
        int numberOfDiscs = discNumbers.get(difficultyLevel - 1);

        // Create the discs and stack them on peg A
        for (int i = numberOfDiscs; i > 0; i--) {
            Disc disc = new Disc(i);
            pegA.stackDisc(disc);
        }
    }

    private LinkedList<Integer> readDiscNumbers() {
        LinkedList<Integer> discNumbers = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("discNumber.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int discNumber = Integer.parseInt(line);
                discNumbers.add(discNumber);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return discNumbers;
    }

    public void moveDisc(Peg source, Peg destination) {
        Disc disc = source.unstackDisc();
        destination.stackDisc(disc);
    }

    public boolean isPossibleMove(Peg source, Peg destination) {
        Disc sourceDisc = source.viewTopDisc();
        Disc destinationDisc = destination.viewTopDisc();
        if (sourceDisc == null) {
            return false; // There's no disc on the source peg
        }
        if (destinationDisc == null) {
            return true; // The destination peg is empty, any disc can be moved
        }
        return sourceDisc.getSize() < destinationDisc.getSize();
    }

    public boolean isGameCompleted() {
        return pegA.isEmpty() && pegB.isEmpty();
    }

    public void solveGame() {
        moveDiscs(pegA, pegC, pegB, pegA.viewTopDisc().getSize());
    }

    private void moveDiscs(Peg source, Peg destination, Peg auxiliary, int numberOfDiscs) {
        if (numberOfDiscs > 0) {
            moveDiscs(source, auxiliary, destination, numberOfDiscs - 1);
            moveDisc(source, destination);
            moveDiscs(auxiliary, destination, source, numberOfDiscs - 1);
        }
    }
}