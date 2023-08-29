package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;
public class Model {
    private LinkedList<Integer> diskNumbers;
    public Model() {
        diskNumbers = loadDiskNumbers();
    }
    private LinkedList<Integer> loadDiskNumbers() {
        LinkedList<Integer> numbers = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\model\\disc_numbers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers;
    }
    public LinkedList<Integer> getDiskNumbers() {
        return diskNumbers;
    }
    public void solveHanoi(int n, Stack<Character> fromRod, Stack<Character> toRod, Stack<Character> auxRod) {
        if (n == 1) {
            char from = fromRod.pop();
            char to = toRod.push(from);
        } else {
            solveHanoi(n - 1, fromRod, auxRod, toRod);
            char from = fromRod.pop();
            char to = toRod.push(from);
            solveHanoi(n - 1, auxRod, toRod, fromRod);
        }
    }
}
