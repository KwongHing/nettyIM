package top.zgx8.netty.im.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.channels.SocketChannel;

/**
 * Created by 钟广兴 on 2018/3/19.
 */
public class ClientHandler extends SimpleChannelInboundHandler<SocketChannel> {
    private final String firstMessage;

    public ClientHandler() {
        firstMessage = "Hello Server !";
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ByteBuf b = Unpooled.buffer(firstMessage.length());
        b.writeBytes(firstMessage.getBytes());

        ctx.writeAndFlush(b);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SocketChannel socketChannel) throws Exception {
        System.out.println("123");
    }
}
