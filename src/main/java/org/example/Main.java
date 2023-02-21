package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(countValidBrackets1("(()"));
        System.out.println(countValidBrackets1(")()())"));
        System.out.println(countValidBrackets1(")(()())"));
        System.out.println(countValidBrackets1(")("));
    }

    private static String countValidBrackets1(String s) {
        if (Objects.isNull(s)) {
            return "0";
        }
        int count = 0;
        StringBuilder sb = new StringBuilder();
        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                deque.push(c);
            } else if (!deque.isEmpty()) {
                deque.pop();
                count += 2;
            }
        }
        String validBracketSequence = removeInvalidParentheses(s).stream()
                .findAny().orElse("");

        if (validBracketSequence.equals("")) {
            return sb.append(count)
                    .toString();
        } else {
            return sb.append(count)
                    .append(" - ")
                    .append(validBracketSequence)
                    .toString();
        }
    }

    private static List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();

        if (s == null) {
            return result;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            s = queue.poll();

            if (isValid(s)) {
                result.add(s);
                found = true;
            }

            if (found) {
                continue;
            }

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '(' && s.charAt(i) != ')') {
                    continue;
                }

                String t = s.substring(0, i) + s.substring(i + 1);

                if (!visited.contains(t)) {
                    queue.add(t);
                    visited.add(t);
                }
            }
        }

        return result;
    }

    private static boolean isValid(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')' && count-- == 0) return false;
        }

        return count == 0;
    }
}

/*
Задача:
Найти валидные круглые скобки.
Обязательно счетчик и правильные скобки нужно выводить.

Пример 1:
               Ввод: "(()"
               Вывод: 2 - ()
Пример 2:
               Ввод: ")()())"
               Вывод: 4 - ()()
Пример 3:
               Ввод: ")(()())"
               Вывод: 6 - (()())
Пример 4:
               Ввод: ")("
               Вывод: 0
 */