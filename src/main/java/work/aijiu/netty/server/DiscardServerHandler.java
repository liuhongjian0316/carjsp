package work.aijiu.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

public class DiscardServerHandler extends SimpleChannelInboundHandler<Object> {


      @Override
      public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
          System.out.println("服务器读取进程"+Thread.currentThread().getName());
          System.out.println("server ctx="+ctx);
          System.out.println("看看channel和pipeline");
          Channel channel = ctx.channel();
          ChannelPipeline pipeline = ctx.pipeline();
          ByteBuf buf = (ByteBuf) msg;
          System.out.println("客户端发送的消息是"+buf.toString(CharsetUtil.UTF_8));
          System.out.println("客户端地址："+channel.remoteAddress());
      }

              @Override
      public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
          cause.printStackTrace();
          ctx.close();
      }
}
