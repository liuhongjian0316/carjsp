package work.aijiu.nettytomcat.servlet;

import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;


public abstract class AbstractServlet implements GenericServlet{

    private static final String GET_METHOD = "GET";

    private static final String POST_METHOD = "POST";

    @Override
    public void service(FullHttpRequest request, FullHttpResponse response) throws Exception {
        if (GET_METHOD.equalsIgnoreCase(request.method().toString())){
            this.doGet(request,response);
        }else if (POST_METHOD.equalsIgnoreCase(request.method().toString())){
            this.doPost(request,response);
        }
    }

    protected abstract void doGet(FullHttpRequest request, FullHttpResponse response);

    protected abstract void doPost(FullHttpRequest request, FullHttpResponse response) throws Exception;
}
