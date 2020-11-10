package work.aijiu.nettytomcat.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.*;
import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetRequestParms {

    private FullHttpRequest request;

    public GetRequestParms(FullHttpRequest request) {
        this.request = request;
    }


    public Map<Object,Object> parse() throws Exception {
        HttpMethod method = request.method();
        Map<Object, Object> parmMap = new HashMap<>();
        if(HttpMethod.GET == method){
            //获取数据 http://localhost:8080/index?name=zhangsan -> /index>name=zhangsan -> {name=zhangsan}
            QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
            decoder.parameters().entrySet().forEach( entry -> {
                parmMap.put(entry.getKey(), entry.getValue().get(0));
            });
        }else if(HttpMethod.POST == method){
            //接收json数据
            ByteBuf content = request.content();
            String jsonStr = content.toString(CharsetUtil.UTF_8);
            Map<Object,Object> maps = JSON.parseObject(jsonStr,Map.class);
            for (Object key : maps.keySet()){
                parmMap.put(key,maps.get(key));
            }
        }else{
            //先写两个请求的方法其他再说
            System.out.println("不支持");
        }
        return parmMap;
    }
}
