package com.example.pc;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import android.util.Log;

public class MessageListener extends Thread {

	public static IoSession session;
	private static NioSocketConnector connector;
	private final static String HOST = "192.168.1.101";
	private final static int PORT = 9123;

	@Override
	public void run() {
		super.run();
		Log.i(MessageListener.class.getName(), "message listener start.");
		// Create TCP/IP connector.
		connector = new NioSocketConnector();
		// Set connect timeout.
		connector.setConnectTimeoutMillis(30 * 1000L);

		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new CharsetCodecFactory()));

		// Start communication.
		connector.setHandler(new MessageHandler());
		ConnectFuture cf = connector.connect(new InetSocketAddress(HOST, PORT));
		// Wait for the connection attempt to be finished.
		cf.awaitUninterruptibly();
		session = cf.getSession();

//		cf.getSession().write("abc");
	}

	public static void send(String msg) {
		if (session.isClosing()) {
			Log.i(MessageListener.class.getName(), "session is closed.");
		}
		session.write(msg);
	}

	public static void reConnect() {
		//这里应该启动一个线程监听是否已经连接上，如果没有连接上，则不停地尝试连接。这个实际代码开发的时候一定要记得实现
		connector.connect(new InetSocketAddress(HOST, PORT));
		Log.i(MessageListener.class.getName(), "reConnect...");
	}

}
