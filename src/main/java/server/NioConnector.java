package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class NioConnector {





	public static void main(String[] args) throws IOException {

		Selector channelSelector = Selector.open();
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.bind(new InetSocketAddress(8080));

		serverChannel.register(channelSelector, 
						SelectionKey.OP_ACCEPT);
		
		while(true) {
			int available = channelSelector.select();
			Set<SelectionKey> keys = channelSelector.selectedKeys();
			for(SelectionKey key : keys) {
				if(key.isAcceptable()) {
					SocketChannel channel = ((ServerSocketChannel)key.channel()).accept();
					System.out.println("dedicated port on sever machine opened : " + channel.socket().getPort());
					//register selector to this channel
					channel.register(channelSelector, SelectionKey.OP_READ);
				} else if(key.isReadable()) {
					
				} else if (key.isWritable()) {
					
				}
			}
		}
		
	}
	
	
	private static class Poller implements Runnable {
		
		private Selector channelSelector;

		public Poller(Selector channelSelector) {
			this.channelSelector = channelSelector;
		}
		
		public void run() {
			
		}
	}

}