package com.spring.template.service.impl;

import com.spring.template.constant.MessageConstant;
import com.spring.template.domain.message.Message;
import com.spring.template.domain.message.dto.MessageRequestDto;
import com.spring.template.domain.message.dto.MessageResponseDto;
import com.spring.template.exception.ResourceNotFoundException;
import com.spring.template.repository.MessageRepository;
import com.spring.template.service.MessageService;
import com.spring.template.util.ModelMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageResponseDto createMessage(MessageRequestDto messageRequestDto) {
        Message newMessage = ModelMapperUtil.convertClass(messageRequestDto, Message.class);
        Message messageSaved = messageRepository.save(newMessage);
        logger.info("Message with id : {} have been created", messageSaved.getId());
        return ModelMapperUtil.convertClass(messageSaved, MessageResponseDto.class);
    }

    @Override
    public MessageResponseDto findMessage(Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageConstant.RESOURCE_NOT_FOUND_MESSAGE,
                        MessageConstant.ENTITY_NAME,
                        MessageConstant.ERROR_KEY
                ));
        logger.info("Message with id : {} have been founded", id);
        return ModelMapperUtil.convertClass(message, MessageResponseDto.class);
    }

    @Override
    public Page<MessageResponseDto> findAllMessage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Message> messagePage = messageRepository.findAll(pageRequest);
        logger.info("{} messages have been founded",messagePage.getSize());
        return messagePage.map(message ->
                ModelMapperUtil.convertClass(message, MessageResponseDto.class));
    }

    @Override
    public MessageResponseDto updateMessage(Long id, MessageRequestDto messageRequestDto) {
        Message existingMessage = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageConstant.RESOURCE_NOT_FOUND_MESSAGE,
                        MessageConstant.ENTITY_NAME,
                        MessageConstant.ERROR_KEY
                ));
        existingMessage.setContent(messageRequestDto.getContent());
        Message updatedMessage = messageRepository.save(existingMessage);
        logger.info("Message with id : {} have been updated", updatedMessage.getId());
        return ModelMapperUtil.convertClass(updatedMessage, MessageResponseDto.class);
    }

    @Override
    public MessageResponseDto deleteMessage(Long id) {
        Message messageToDelete = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageConstant.RESOURCE_NOT_FOUND_MESSAGE,
                        MessageConstant.ENTITY_NAME,
                        MessageConstant.ERROR_KEY
                ));
        messageRepository.delete(messageToDelete);
        logger.info("Message with id : {} have been deleted", messageToDelete.getId());
        return ModelMapperUtil.convertClass(messageToDelete, MessageResponseDto.class);
    }
}
