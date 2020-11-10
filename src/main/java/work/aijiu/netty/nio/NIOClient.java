package work.aijiu.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * 客户端
 */
public class NIOClient {
    public static void main(String[] args) throws Exception {

        //得到一个网路通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置非阻塞
        socketChannel.configureBlocking(false);
        //提供服务端的ip 和 port
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",6666);
        //连接服务器
        if(!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("因为连接需要等待时间，客户端不会阻塞，客户端可以干别的事情");
            }
        }
        //连接成功发送数据

        while (true){
            Scanner scanner =new Scanner(System.in);
            String str = scanner.nextLine();
            ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
            socketChannel.write(buffer);
        }
    }
}
