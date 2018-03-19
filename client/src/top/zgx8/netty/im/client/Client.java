package top.zgx8.netty.im.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Created by 钟广兴 on 2018/3/19.
 */
public class Client {
    public void run(String host, int port) throws Exception{
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());

            ChannelFuture f = b.connect(host,port).sync();

            String msg = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (!msg.equals("exit;")) {
                System.out.println("请输入消息：");
                msg = br.readLine();
                if (!"".equals(msg)) {
                    if ("1".equals(msg)) {
                        for(int i = 0; i < 1000; i ++) {
                            String m = "SBG"+ System.currentTimeMillis() +System.getProperty("line.separator");
                            ByteBuf bb = Unpooled.copiedBuffer(m.getBytes());
                            f.channel().writeAndFlush(bb);
                        }
                    } else {
                        String m = msg +System.getProperty("line.separator");
                        ByteBuf bb = Unpooled.copiedBuffer(m.getBytes());
                        f.channel().writeAndFlush(bb);
                    }
                }
            }


            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
