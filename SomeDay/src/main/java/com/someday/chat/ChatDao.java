package com.someday.chat;

import java.util.List;

public interface ChatDao {
	
	// ä�� �� ���
	List<ChatModel> chatList(int couple_idx);

	// ä�� �� ����
	int chatSend(ChatModel chatModel);

}
