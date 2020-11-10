package work.aijiu.nettytomcat.server;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.MemoryAttribute;
import io.netty.util.CharsetUtil;
import org.springframework.web.bind.annotation.RequestParam;
import work.aijiu.nettytomcat.TestServlet.TestServlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpUtil.is100ContinueExpected;

public class HttpServerHandler extends  SimpleChannelInboundHandler<FullHttpRequest> {


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        //100 Continue
        if (is100ContinueExpected(req)) {
            ctx.write(new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.CONTINUE));
        }
        GetRequestParms getRequestParms = new GetRequestParms(req);
        Map<Object, Object> parse = getRequestParms.parse();

        TestServlet testServlet = new TestServlet();
        String msg = "<html><head><title>测试</title></head><body>hello</body></html>";
        msg += "<spam>数据为"+parse.get("name")+"----"+parse.get("age")+"</span>";
        // 创建http响应
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
        // 设置头信息
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
        testServlet.service(req,response);
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        //判断请求方法的类型
//        if(req.method() == HttpMethod.GET){
//            // 创建http响应
//            FullHttpResponse response = new DefaultFullHttpResponse(
//                    HttpVersion.HTTP_1_1,
//                    HttpResponseStatus.OK,
//                    Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
//            // 设置头信息
//            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
//            testServlet.service(req,response);
//            //获取请求数据
//            GetRequestParms getRequestParms = new GetRequestParms(req);
//            Map<Object, Object> parses= getRequestParms.parse();
//            // 获取请求的uri
//            String uri = req.uri();
//            String msg = "<html><head><title>测试</title></head><body>你请求uri为：" + uri+"</body></html>";
//            msg += "名字："+"<span>"+ parses.get("name") +"</span>";
//            msg += "<p>你个大傻吊</>";
//            // 创建http响应
//            FullHttpResponse response = new DefaultFullHttpResponse(
//                    HttpVersion.HTTP_1_1,
//                    HttpResponseStatus.OK,
//                    Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
//            // 设置头信息
//            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
//            //response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
//            // 将html write到客户端
//            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
//        }
    }
}
