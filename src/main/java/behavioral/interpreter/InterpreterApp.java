package behavioral.interpreter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Stream;

/**
 * Created by Arsen on 5/10/2017
 */
public class InterpreterApp {
    public static void main(String[] args) {
        Stream.of("1 2 3 + -", "1 2 3 + +").forEach(
                in -> {
                    Integer out = Evaluator.evaluate(in).apply();
                    System.out.println(in + " = " + out);
                }
        );
    }
}

class Evaluator {
    static Function<Integer> evaluate(String rawExpression) {
        LinkedList<Function<Integer>> functions = new LinkedList<>();
        Arrays.stream(rawExpression.split(" ")).forEach(
                s -> {
                    switch (s) {
                        case "+": {
                            assert functions.size() >= 2;
                            Function<Integer> right = functions.removeLast();
                            Function<Integer> left = functions.removeLast();
                            functions.add(new Plus(left, right));
                            break;
                        }
                        case "-": {
                            assert functions.size() >= 2;
                            Function<Integer> right = functions.removeLast();
                            Function<Integer> left = functions.removeLast();
                            functions.add(new Minus(left, right));
                            break;
                        }
                        default:
                            functions.add(new Number(Integer.parseInt(s)));
                            break;
                    }
                }
        );
        assert functions.size() == 1;
        return functions.get(0);
    }
}

interface Function<T> {
    T apply();
}

class Number implements Function<Integer> {
    private final int n;
    Number(int n) {
        this.n = n;
    }
    public Integer apply() {
        return n;
    }
}

class Plus implements Function<Integer> {
    private final Function<Integer> left;
    private final Function<Integer> right;
    Plus(Function<Integer> left, Function<Integer> right) {
        this.left = left;
        this.right = right;
    }
    public Integer apply() {
        return left.apply() + right.apply();
    }
}

class Minus implements Function<Integer> {
    private final Function<Integer> left;
    private final Function<Integer> right;
    Minus(Function<Integer> left, Function<Integer> right) {
        this.left = left;
        this.right = right;
    }
    public Integer apply() {
        return left.apply() - right.apply();
    }
}

