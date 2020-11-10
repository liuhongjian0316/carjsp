package work.aijiuj;

import java.util.regex.Pattern;

public class Test222 {
    public static void main(String[] args) {
        String regularExpression = "([a-zA-Z]:)?(/[a-zA-Z0-9_-]+)+/?";

        String path = "/asdas/";

        Pattern pattern = Pattern.compile(regularExpression);

        boolean isMatched = Pattern.matches(regularExpression,path);
        System.out.println(path);
        System.out.println(pattern.pattern());
        System.out.println(isMatched);
    }
}
