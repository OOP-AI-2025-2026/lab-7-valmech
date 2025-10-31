package ua.opnu;

import java.util.*;
import java.util.function.*;

public class Main {

    static Student[] filterStudents(Student[] list, Predicate<Student> rule) {
        return Arrays.stream(list).filter(rule).toArray(Student[]::new);
    }

    static int[] filterInts(int[] nums, Predicate<Integer> cond1, Predicate<Integer> cond2) {
        return Arrays.stream(nums).filter(i -> cond1.test(i) && cond2.test(i)).toArray();
    }

    static void forEachStudent(Student[] list, Consumer<Student> consumer) {
        for (Student st : list) consumer.accept(st);
    }

    static void conditionalAction(int[] nums, Predicate<Integer> condition, Consumer<Integer> action) {
        for (int n : nums)
            if (condition.test(n))
                action.accept(n);
    }

    static int[] transformArray(int[] nums, Function<Integer, Integer> func) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            res[i] = func.apply(nums[i]);
        return res;
    }

    static String[] stringifyNumbers(int[] nums, Function<Integer, String> func) {
        String[] res = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            res[i] = func.apply(nums[i]);
        return res;
    }

    public static void main(String[] args) {

        System.out.println("Завдання 1:");
        Predicate<Integer> isPrime = n -> {
            if (n < 2) return false;
            for (int i = 2; i <= Math.sqrt(n); i++)
                if (n % i == 0) return false;
            return true;
        };
        int[] numbers = {1, 2, 3, 4, 5, 10, 13, 17, 18};
        for (int n : numbers)
            System.out.printf("%d -> %b%n", n, isPrime.test(n));

        System.out.println("\nЗавдання 2:");
        Student[] students = {
                new Student("Іван", "ІПЗ-21", new int[]{80, 90, 75}),
                new Student("Петро", "ІПЗ-22", new int[]{50, 40, 70}),
                new Student("Олег", "ІПЗ-23", new int[]{60, 60, 60})
        };
        Predicate<Student> hasDebt = s -> {
            for (int mark : s.getMarks())
                if (mark < 60) return true;
            return false;
        };
        Student[] debtors = filterStudents(students, hasDebt);
        for (Student s : debtors)
            System.out.println(s.getName());

        System.out.println("\nЗавдання 3:");
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Predicate<Integer> even = n -> n % 2 == 0;
        Predicate<Integer> greaterThan3 = n -> n > 3;
        System.out.println(Arrays.toString(filterInts(nums, even, greaterThan3)));

        System.out.println("\nЗавдання 4:");
        Consumer<Student> printNameGroup = s -> System.out.println(s.getName() + " " + s.getGroup());
        forEachStudent(students, printNameGroup);

        System.out.println("\nЗавдання 5:");
        Predicate<Integer> evenCheck = n -> n % 2 == 0;
        Consumer<Integer> printEven = n -> System.out.println("Парне: " + n);
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        conditionalAction(arr, evenCheck, printEven);

        System.out.println("\nЗавдання 6:");
        Function<Integer, Integer> powerOfTwo = n -> (int) Math.pow(2, n);
        int[] base = {0, 1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(transformArray(base, powerOfTwo)));

        System.out.println("\nЗавдання 7:");
        Function<Integer, String> numToWord = n -> switch (n) {
            case 0 -> "нуль";
            case 1 -> "один";
            case 2 -> "два";
            case 3 -> "три";
            case 4 -> "чотири";
            case 5 -> "п’ять";
            case 6 -> "шість";
            case 7 -> "сім";
            case 8 -> "вісім";
            case 9 -> "дев’ять";
            default -> "невідомо";
        };
        int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(Arrays.toString(stringifyNumbers(digits, numToWord)));
    }
}

class Student {
    private final String name;
    private final String group;
    private final int[] marks;

    public Student(String name, String group, int[] marks) {
        this.name = name;
        this.group = group;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int[] getMarks() {
        return marks;
    }
}
