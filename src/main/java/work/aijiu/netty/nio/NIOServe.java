package work.aijiu.netty.nio;

import org.springframework.mail.javamail.InternetAddressEditor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 服务端
 */
public class NIOServe{


    public static void main(String[] args) throws IOException {
        //创建ServerSocketChannel ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //得到一个selector 对象
        Selector selector = Selector.open();

        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        //设置非阻塞
        serverSocketChannel.configureBlocking(false);

        //注册搭配selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while(true){
            if(selector.select(1000)==0){
                //System.out.println("服务器等待1秒，无连接");
                continue;
            }
            //获取
            Set<SelectionKey> keys = selector.selectedKeys();
            //遍历
            Iterator<SelectionKey> keyIterator = keys.iterator();

            while (keyIterator.hasNext()){
                SelectionKey selectionKey = keyIterator.next();
                if(selectionKey.isAcceptable()){
                    //有新客户端来了 创建一个channel
                    //accept 接受与此通道的套接字建立连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功----创建一个socketChannel"+socketChannel.hashCode());
                    //将socketChannel设置非阻塞
                    socketChannel.configureBlocking(false);
                    //将socketChannel注册到selector
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if(selectionKey.isReadable()){
                    //通过key反向获取对应的channel
                    SocketChannel channel = (SocketChannel)selectionKey.channel();
                    //获取该channel 关联的bnuffeer
                    ByteBuffer byteBuffer = (ByteBuffer)selectionKey.attachment();
                    channel.read(byteBuffer);
                    System.out.println("from 客户端"+new String(byteBuffer.array()));
                }
                //手动从集合中移动当前的seletorKey 防止重复操作
                keyIterator.remove();
            }

        }
    }

}
