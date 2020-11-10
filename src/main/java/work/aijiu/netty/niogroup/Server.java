package work.aijiu.netty.niogroup;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 服务端
 */
public class Server{

    /**
     * 选择器
     */
    private Selector selector;
    /**
     * 通道
     */
    private ServerSocketChannel listenChannel;
    /**
     * 端口
     */
    private static int port = 6666;

    //初始化
    public Server() {
        try {
            //得到选择器
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            //绑定端口
            listenChannel.socket().bind(new InetSocketAddress(port));
            //设置非阻塞模式
            listenChannel.configureBlocking(false);
            //将channel注册到选择器上
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //监听
    public void listen(){
        try {
            while (true){
                //获取选择器的数量
                int count = selector.select();
                //count>0 说明有时间要处理
                if(count>0){
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        //取出SelectionKey
                        SelectionKey key = iterator.next();
                        //监听accept
                        if(key.isAcceptable()){
                            //创建SocketChannel
                            SocketChannel sc = listenChannel.accept();
                            //异步非阻塞
                            sc.configureBlocking(false);
                            //提示某某某上线
                            System.out.println(sc.getLocalAddress()+"上线了o(*￣▽￣*)ブ");
                        }
                        //通道可读状态
                        if(key.isReadable()){
                            //处理可读状态的事件
                            readDate(key);
                        }
                        //手动从集合中移除当前的Key 防止重复操作
                        iterator.remove();
                    }
                }else {
                    System.out.println("等待客户端上线。。。。。。。。。。。。。");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //发送异常处理
        }
    }
    
    //读取客户端的消息
    public void readDate(SelectionKey key){
        //获取关联的channel
        SocketChannel channel = null;
        try {
            channel = (SocketChannel)key.channel();
            //创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            if (count>0){
                String msg = new String(buffer.array());
                //输出该信息
                System.out.println("from 客户端："+msg);

                //向其他客户端转发消息（去除自己本身）
                sendToOthers(msg,channel);
            }
        } catch (IOException e) {

            try {
                System.out.println(channel.getLocalAddress()+"离线了");
                //取消注册
                key.channel();
                //关闭通道
                channel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } finally {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public void sendToOthers(String msg, SocketChannel self) throws Exception {

        System.out.println("服务器发送消息中");
        for(SelectionKey key:selector.selectedKeys()){
            //通过key 获取相应的通道
            Channel targetChannel = key.channel();
            //将自己排除
            if(targetChannel instanceof SocketChannel && targetChannel !=self){
                //转型
                SocketChannel dest = (SocketChannel)targetChannel;
                //msg储存到buffer中
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                //将buffer 写入通道
                dest.write(buffer);
            }
        }
    }




    public static void main(String[] args) {
        //服务器监听6666端口
        //服务器接受客户端的消息 并实现转发 处理上线离线

        Server  server = new Server();
        server.listen();
    }
}
