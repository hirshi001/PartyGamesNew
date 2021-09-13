package com.hirshi001.game.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;
import nettypackets.PacketRegistry;
import nettypackets.SidedPacketRegistryContainer;
import nettypackets.packet.Packet;
import nettypackets.packet.PacketHelper;

public class Server {

    public final int port;
    public final SidedPacketRegistryContainer serverRegistries;
    public final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public ChannelFuture future;
    private EventLoopGroup bossGroup, workerGroup;



    public Server(int port) {
        this.port = port;
        this.serverRegistries = new SidedPacketRegistryContainer();
    }

    public void sendPacketToAllConnected(PacketRegistry registry, Packet msg) {
        ByteBuf buf = PacketHelper.toBytes(registry, msg);
        channels.writeAndFlush(buf);
    }

    public void startUp() throws InterruptedException {
        bossGroup = new NioEventLoopGroup(); // (1)
        workerGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap(); // (2)
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class) // (3)
                .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ServerPacketDecoder(serverRegistries, channels));
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

        // Bind and start to accept incoming connections.
        future = b.bind(port).sync(); // (7)
    }

    public void close(){
        channels.close().awaitUninterruptibly();
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

}
