package work.aijiu.netty.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFilechannel02 {

    public static void main(String[] args) throws IOException {

        File file = new File("d:\\file01.txt");
        FileInputStream inputStream = new FileInputStream(file);

        FileChannel fileChannel = inputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());

        fileChannel.read(byteBuffer);

        System.out.println(new String(byteBuffer.array()));

        inputStream.close();





    }
}
