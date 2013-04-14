package com.example.pc;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import android.util.Log;

public class MessageHandler extends IoHandlerAdapter{

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
		Log.i(MessageHandler.class.getName(), "sessionClosed.");
		MessageListener.reConnect();
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		super.messageReceived(session, message);
		Log.i(MessageHandler.class.getName(), message.toString());
		MainActivity.onMessageReceived(message.toString());
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		super.sessionOpened(session);
		Log.i(MessageHandler.class.getName(), "sessionOpened");
	}

}
