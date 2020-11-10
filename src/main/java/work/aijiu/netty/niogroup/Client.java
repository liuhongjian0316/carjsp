package work.aijiu.netty.niogroup;

import org.springframework.expression.spel.ast.Selection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 客户端
 */
public class Client {
    /**
     * 客户端连接服务器
     * 发送信息
     * 接收服务器转发的信息
     */

    /**
     * 客户端地址
     */
    private final String host = "127.0.0.1";
    /**
     * 端口
     */
    private final int port = 6666;
    /**
     * 选择器
     */
    private Selector selector;
    /**
     * 通道
     */
    private SocketChannel socketChannel;
    /**
     * 自己本身的名字
     */
    private String username;

    /**
     * 初始化
     */
    public Client() throws IOException {
        //获取选择器
        selector = Selector.open();
        //连接服务器
        socketChannel = SocketChannel.open(new InetSocketAddress(host,port));
        //设置异步非阻塞
        socketChannel.configureBlocking(false);
        //将channel注册到选择器上
        socketChannel.register(selector, SelectionKey.OP_READ);
        //得到username
        username = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(username + "is ok..........");
    }

    /**
     * 向服务端发送信息
     * @param info
     */
    public void sendInfo(String info){
        info = username + "说：" + info;
        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receive(){

        try {
            int count = selector.select();
            //处理消息
            if(count>0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                //遍历
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if(key.isReadable()){
                        //得到相关信息
                        SocketChannel sc = (SocketChannel)key.channel();
                        //的到buffer
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        sc.read(buffer);
                        //将数据转换成字符串
                        String msg = new String(buffer.array());
                        System.out.println(msg.trim());
                    }
                }
            }else{
                //没有通道
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    public static void main(String[] args) throws Exception {
        //启动客户端
        Client client = new Client();

        //启动线程 读取从服务端转发的消息 3秒一个
        new Thread(){
            @Override
            public void run() {
                while (true){
                    client.receive();
                    try{
                        Thread.sleep(3000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        //发送数据
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String msg = scanner.nextLine();
            client.sendInfo(msg);
        }
    }

}
