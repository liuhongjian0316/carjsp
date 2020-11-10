package work.aijiu.netty.nio;

import java.nio.IntBuffer;

public class BasicBuffer {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);

        //向buffer 存入数据
        for (int i = 0; i <intBuffer.limit() ; i++) {
            intBuffer.put(i*2);
        }

        //将buffer 转换 读写切换
        intBuffer.flip();

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
