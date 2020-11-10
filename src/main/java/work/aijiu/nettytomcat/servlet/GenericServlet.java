package work.aijiu.nettytomcat.servlet;

import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * servlet
 */
public interface GenericServlet {

    void init() throws Exception;

    void destroy();

    void service(FullHttpRequest request, FullHttpResponse response) throws Exception;

}
