package work.aijiu.common.utils;

import java.util.regex.Pattern;

/**
 * 匹配出合法的url和parms
 */
public class UrlPattern {

    public static boolean UrlFilterUtil(String url){
        String regularExpression = "([a-zA-Z]:)?(/[a-zA-Z0-9_-]+)+/?";
        Pattern pattern = Pattern.compile(regularExpression);
        return Pattern.matches(regularExpression,url);
    }
}
