package work.aijiu.nettytomcat.TestServlet;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import work.aijiu.nettytomcat.annotations.Servlet;
import work.aijiu.nettytomcat.server.GetRequestParms;
import work.aijiu.nettytomcat.servlet.AbstractServlet;

import java.util.Map;

@Servlet(Value = "/login")
public class TestServlet extends AbstractServlet {

    @Override
    public void init() throws Exception {

    }

    @Override
    public void destroy() {

    }

    @Override
    protected void doGet(FullHttpRequest request, FullHttpResponse response) {

    }

    @Override
    protected void doPost(FullHttpRequest request, FullHttpResponse response) throws Exception {
        GetRequestParms getRequestParms = new GetRequestParms(request);
        Map<Object, Object> parse = getRequestParms.parse();
    }

}
