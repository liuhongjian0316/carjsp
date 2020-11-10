package work.aijiu.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        //创建一个线程池
        //如果有客户端连接，就创建一个线程


        ExecutorService executorService = Executors.newCachedThreadPool();

        //创建serverSocket
        ServerSocket serverSocket = new ServerSocket(6666);

        while (true){
            //监听等待客户端连接
            final Socket socket = serverSocket.accept();
            System.out.println("有客户端连接了");

            //创建线程
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    hanlder(socket);
                }
            });
        }


    }

    //handler处理通讯
    public static  void hanlder(Socket socket){
        try {
            System.out.println("线程id"+Thread.currentThread().getId()+"------"+"线程name"+Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while(true){
                System.out.println("线程id"+Thread.currentThread().getId()+"------"+"线程name"+Thread.currentThread().getName());
                int read = inputStream.read(bytes);
                if(read != -1){
                    System.out.println(new String(bytes,0,read));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
