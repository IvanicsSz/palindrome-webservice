package io.falcon.assignment.controller;

import io.falcon.assignment.model.Palindrome;
import io.falcon.assignment.model.dto.RestResponseDTO;
import io.falcon.assignment.repository.PalindromeRepository;
import io.falcon.assignment.service.PalindromeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class MessageController {

    private static final String SOCKET_PATH = "/topic/message";
    @Autowired
    private PalindromeRepository palindromeRepository;

    @Autowired
    @Qualifier("palindrome")
    private PalindromeService palindromeService;

    @Autowired
    private SimpMessagingTemplate template;


    @RequestMapping(value = "/contents", method = RequestMethod.GET, produces = "application/json")
    public List<Palindrome> getAllMessages() {
        return palindromeRepository.findAll();
    }


    @RequestMapping(value = "/content/send", method = RequestMethod.POST, produces = "application/json")
    public RestResponseDTO sendMessage(@Valid @RequestBody Palindrome palindrome) {

        palindrome.setPalindromeSize(palindromeService.getLongestPalindrome(palindrome.getUserContent()));
        palindromeRepository.save(palindrome);

        this.template.convertAndSend(SOCKET_PATH, palindrome);

        return RestResponseDTO.buildSuccess();
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponseDTO handleException(MethodArgumentNotValidException exception) {

        String errorMsg = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(exception.getMessage());

        return RestResponseDTO.buildFailed(errorMsg);
    }
}
