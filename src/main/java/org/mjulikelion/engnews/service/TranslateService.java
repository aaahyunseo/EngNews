package org.mjulikelion.engnews.service;

import lombok.RequiredArgsConstructor;
import org.mjulikelion.engnews.dto.ai.E2kResponseDto;
import org.mjulikelion.engnews.dto.ai.E2kTranslateDto;
import org.mjulikelion.engnews.dto.ai.FeedbackDto;
import org.mjulikelion.engnews.dto.ai.TranslateDto;
import org.mjulikelion.engnews.dto.ai.TryDto;
import org.mjulikelion.engnews.entity.User;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TranslateService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String try_TranslateURL = "http://43.203.141.103:8000/try-translate";
    private final String translateURL = "http://43.203.141.103:8000/translate";
    private final String e2kTranslateURL = "http://43.203.141.103:8000/translate_t5_e2k";

    public FeedbackDto tryTranslate(User user, TryDto tryDto){

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); //Json형식
        headers.add("user", String.valueOf(user.getId()));

        // HTTP 요청 엔터티 생성 (헤더 + 바디)
        HttpEntity<TryDto> entity = new HttpEntity<>(tryDto, headers);

        // POST 요청 전송
        ResponseEntity<String> response = restTemplate.exchange(try_TranslateURL, HttpMethod.POST, entity, String.class);


        FeedbackDto feedback = FeedbackDto.builder()
                .gpt_answer(response.getBody())
                .build();

        return feedback;
    }

    public FeedbackDto translate(User user, TranslateDto translateDto){

        System.out.print(user);
        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); //Json형식
        headers.add("user", String.valueOf(user.getId()));

        // HTTP 요청 엔터티 생성 (헤더 + 바디)
        HttpEntity<TranslateDto> entity = new HttpEntity<>(translateDto, headers);

        // POST 요청 전송
        ResponseEntity<String> response = restTemplate.exchange(translateURL, HttpMethod.POST, entity, String.class);

        FeedbackDto feedback = FeedbackDto.builder()
                .gpt_answer(response.getBody())
                .build();

        return feedback;
    }

    public E2kResponseDto e2kTranslate(User user, E2kTranslateDto e2kTranslateDto) {
        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user", String.valueOf(user.getId()));

        // HTTP 요청 엔터티 생성 (헤더 + 바디)
        HttpEntity<E2kTranslateDto> entity = new HttpEntity<>(e2kTranslateDto, headers);

        // POST 요청 전송
        ResponseEntity<String> response = restTemplate.exchange(e2kTranslateURL, HttpMethod.POST, entity, String.class);

        return E2kResponseDto.builder()
                .answer(response.getBody())
                .build();
    }
}
