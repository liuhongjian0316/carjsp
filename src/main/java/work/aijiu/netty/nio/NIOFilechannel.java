package work.aijiu.netty.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFilechannel {

    public static void main(String[] args) throws IOException {
        String str = "hello";
        //创建输出流
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.txt");

        //通过fileOutputStream 获取对应filechannel
        FileChannel channel = fileOutputStream.getChannel();

        //创建一个缓存区bytebuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //将 str 放到bytebuffer中
        byteBuffer.put(str.getBytes());

        //读写反转
        byteBuffer.flip();

        //bytebuffer数据写入到channel
        channel.write(byteBuffer);

        fileOutputStream.close();






    }
}
