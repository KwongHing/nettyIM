package top.zgx8.netty.im.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.nio.channels.SocketChannel;

/**
 * Created by 钟广兴 on 2018/3/19.
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    private final static ChannelGroup channelGroup = new DefaultChannelGroup("channelGroups", GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("新的连接上线：" + ctx.channel().remoteAddress());
        channelGroup.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channelGroup.add(ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        String msg = channelHandlerContext.channel().remoteAddress().toString() + " : " + s + System.getProperty("line.separator");
        for(Channel channel : channelGroup) {
            String m = msg;
            if (!channel.remoteAddress().toString().equals(channelHandlerContext.channel().remoteAddress().toString())) {
            } else {
                m = "your : " + s + System.getProperty("line.separator");
            }
            ByteBuf byteBuf = Unpooled.copiedBuffer(m.getBytes());
            channel.writeAndFlush(byteBuf);
        }
    }
}
