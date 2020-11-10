package work.aijiuj;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test{

}

class test {

    @testA("a")
    public static void main(String[] args) throws Exception {
        Method method = test.class.getMethod("main", String[].class);
        testA testA = method.getAnnotation(testA.class);
        if (testA == null)
            throw new RuntimeException("please add testA");
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(testA);
        Field value = invocationHandler.getClass().getDeclaredField("memberValues");
        value.setAccessible(true);
        Map<String, Object> memberValues = (Map<String, Object>) value.get(invocationHandler);
        String val = (String) memberValues.get("value");
        System.out.println("改变前：" + val);
        val = "b";
        memberValues.put("value", val);
        System.out.println("改变后：" + testA.value());

    }
}