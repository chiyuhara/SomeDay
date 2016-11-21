package com.someday.chat;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.someday.notice.NoticecommModel;

@Service
public class ChatService implements ChatDao {
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 채팅 목록 출력
	@Override
	public List<ChatModel> chatList(int couple_idx) {
		return sqlSessionTemplate.selectList("chat.chatList", couple_idx);
	}

	// 채팅 쓰기
	@Override
	public int chatSend(ChatModel chatModel) {
		return sqlSessionTemplate.insert("chat.chatSend", chatModel);
	}
}
