package com.someday.chat;

import java.util.List;

public interface ChatDao {
	
	// 채팅 글 목록
	List<ChatModel> chatList(int couple_idx);

	// 채팅 글 쓰기
	int chatSend(ChatModel chatModel);

}
