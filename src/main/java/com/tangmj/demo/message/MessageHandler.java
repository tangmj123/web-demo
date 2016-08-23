package com.tangmj.demo.message;

/**
 * 消息处理接口
 * @author Tangmj
 *
 * @param <M>
 */
public interface MessageHandler<M> {
	
	public void handle(M m);
	
}
