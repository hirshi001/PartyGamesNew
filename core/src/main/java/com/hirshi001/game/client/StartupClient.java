package com.hirshi001.game.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import nettypackets.PacketRegistry;
import nettypackets.SidedPacketRegistryContainer;
import nettypackets.encoderdecoder.PacketDecoder;
import nettypackets.packet.Packet;
import nettypackets.packet.PacketHelper;

public class StartupClient {

    private int port = 8080;
    private String host = "localhost";

    private SocketChannel channel;
    public final SidedPacketRegistryContainer clientRegistries;

    private volatile boolean running = false;

    public StartupClient(String host, int port) {
        this.port = port;
        this.host = host;
        clientRegistries = new SidedPacketRegistryContainer();
    }

    public void sendPacket(PacketRegistry registry, Packet p) {
        channel.writeAndFlush(PacketHelper.toBytes(registry, p));
    }

    public void run() throws Exception {
        if(running) throw new Exception("Client is already running!");
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(
                            new PacketDecoder(clientRegistries)
                    );
                    channel = ch;
                }
            });

            ChannelFuture f = b.connect(host, port).sync();
            running = true;
            try {
                f.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }

}
