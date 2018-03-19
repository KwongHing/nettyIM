package top.zgx8.netty.im.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by 钟广兴 on 2018/3/19.
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();

        p.addLast("decoder", new StringDecoder());
        p.addLast("encoder", new StringEncoder());
        p.addLast(new ServerHandler());

    }
}
