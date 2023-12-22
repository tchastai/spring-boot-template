package com.spring.template.controller;


import com.spring.template.domain.message.dto.MessageRequestDto;
import com.spring.template.domain.message.dto.MessageResponseDto;
import com.spring.template.service.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("")
    public ResponseEntity<MessageResponseDto> createMessage(@RequestBody MessageRequestDto messageRequestDto) {
        return ResponseEntity.ok(messageService.createMessage(messageRequestDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<MessageResponseDto> findMessage(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(messageService.findMessage(id));
    }
    @GetMapping("")
    public ResponseEntity<Page<MessageResponseDto>> findAllMessage(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(messageService.findAllMessage(page, size));
    }
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponseDto> updateMessage(@PathVariable(value = "id") Long id, @RequestBody MessageRequestDto messageRequestDto) {
        return ResponseEntity.ok(messageService.updateMessage(id, messageRequestDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDto> deleteMessage(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(messageService.deleteMessage(id));
    }
}
