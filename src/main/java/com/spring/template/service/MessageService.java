package com.spring.template.service;

import com.spring.template.domain.message.dto.MessageRequestDto;
import com.spring.template.domain.message.dto.MessageResponseDto;
import org.springframework.data.domain.Page;

public interface MessageService {
    MessageResponseDto createMessage(MessageRequestDto messageRequestDto);
    MessageResponseDto findMessage(Long id);
    Page<MessageResponseDto> findAllMessage(int page, int size);
    MessageResponseDto updateMessage(Long id, MessageRequestDto messageRequestDto);
    MessageResponseDto deleteMessage(Long id);
}
