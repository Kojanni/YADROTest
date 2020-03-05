import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        for (; ; ) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (correctUseParentheses(line)) {
                System.out.println("Скобочки раставлены ВЕРНО");
            } else {
                System.out.println("Скобочки раставлены НЕ ВЕРНО");
            }
        }
    }
    public static boolean checkParentheses(String statement, String openParentheses) {
        boolean pairParentheses = false;
        String closeParentheses = findClosedParentheses(openParentheses);
        int a = 0;
        int b = 0;
        for (int i = 0; i < statement.length(); i++) {
            if (statement.charAt(i) == openParentheses.charAt(0)) {
                a += 1;
            }
            if (statement.charAt(i) == closeParentheses.charAt(0)) {
                b += 1;
            }
        }
        if (a == b) {
            pairParentheses = true;
        }
        return pairParentheses;
    }

    public static boolean checkAllParentheses(String statement) {
        if (checkParentheses(statement, "(") &&
                checkParentheses(statement, "{")  &&
                checkParentheses(statement, "[")) {
            return true;
        }
        return false;
    }

    public  static String findClosedParentheses (String openParentheses) {
        String closeParentheses = null;
        if (openParentheses.contains("(")) {
            closeParentheses = ")";
        }
        if (openParentheses.contains("[")) {
            closeParentheses = "]";
        }
        if (openParentheses.contains("{")) {
            closeParentheses = "}";
        }
        return closeParentheses;
    }

    public static String findParentheses(String statement, String openParenthese, String closeParenthese) {
        String result = null;
        int openedParenthese = -1;
        int closedParenthese = -1;
        closedParenthese = statement.indexOf(closeParenthese);
        openedParenthese = statement.lastIndexOf(openParenthese);
        if ((openedParenthese == -1) || (closedParenthese == -1)) {
            return null;
        }
        if (openedParenthese < closedParenthese)  {
            result = statement.substring(0, openedParenthese) + statement.substring(closedParenthese + 1);
            if (!checkAllParentheses(result)) {
                result = null;
            }
        } else if (openedParenthese > closedParenthese) {
            closedParenthese = statement.indexOf(closeParenthese);
            openedParenthese = statement.indexOf(openParenthese);
            if (openedParenthese < closedParenthese) {
                result = statement.substring(0, openedParenthese) + statement.substring(closedParenthese + 1);
                if (!checkAllParentheses(result)) {
                    result = null;
                }
            }
        }
        return result;
    }

    public static boolean correctUseParentheses(String statement) {
        boolean correctUse = true;
        if (!checkAllParentheses(statement)) {
            return false;
        }
        if (correctUseOneTypeParentheses(statement, "(") == null) {
            correctUse = false;
        } else if (correctUseOneTypeParentheses(statement, "{") == null) {
            correctUse = false;
        }
        if (correctUseOneTypeParentheses(statement, "[") == null) {
            correctUse = false;
        }
        return correctUse;
    }

    public static String correctUseOneTypeParentheses(String statement, String openParentheses) {
        String closeParentheses = findClosedParentheses(openParentheses);
        while (statement.contains(openParentheses) || statement.contains(closeParentheses)) {
            statement = findParentheses(statement, openParentheses, closeParentheses);
            if (statement == null) {
                break;
            }
        }
        return statement;
    }
}
