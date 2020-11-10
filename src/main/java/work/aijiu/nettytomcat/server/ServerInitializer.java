package work.aijiu.nettytomcat.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        // 请求处理器
        pipeline.addLast(new HttpServerCodec());
        // http 消息聚合器    512*1024为接收的最大contentlength
        pipeline.addLast("httpAggregator",new HttpObjectAggregator(512*1024));
        // 请求处理器
        pipeline.addLast(new HttpServerHandler());

    }
}
