package io.falcon.assignment.controller;

import io.falcon.assignment.model.Palindrome;
import io.falcon.assignment.model.dto.RestResponseDTO;
import io.falcon.assignment.repository.PalindromeRepository;
import io.falcon.assignment.service.PalindromeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class MessageControllerTest {

    @Mock
    private PalindromeRepository palindromeRepository;

    @Mock
    private PalindromeService palindromeService;

    @Mock
    private SimpMessagingTemplate template;

    @InjectMocks
    private MessageController messageController;

    private MockMvc mockMvc;

    private Palindrome mockPalindrome = new Palindrome();
    private List<Palindrome> mockPalindromes = new ArrayList<>();
    private Date mockDate = new Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
    private String strDate;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();

        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        strDate = dateFormat.format(mockDate);
        mockPalindrome.setUserContent("abrakadabra");
        mockPalindrome.setTimeStamp(mockDate);
        mockPalindromes.add(mockPalindrome);

    }

    @Test
    public void getAllMessages() throws Exception{
        String uri = "/api/contents";

        when(palindromeRepository.findAll()).thenReturn(mockPalindromes);

        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].content", is("abrakadabra")))
                .andExpect(jsonPath("$[0].timestamp", is(strDate)));

        verify(palindromeRepository, times(1)).findAll();
        verifyNoMoreInteractions(palindromeRepository);
    }

    @Test
    public void sendMessageSuccesfully() {
        RestResponseDTO mockRestResponseDTO = RestResponseDTO.buildSuccess();
        RestResponseDTO result = messageController.sendMessage(mockPalindrome);

        verify(palindromeRepository, times(1)).save(mockPalindrome);
        verifyNoMoreInteractions(palindromeRepository);

        verify(palindromeService, times(1)).getLongestPalindrome(mockPalindrome.getUserContent());
        verifyNoMoreInteractions(palindromeService);

        assertEquals(mockRestResponseDTO,result);
    }

}