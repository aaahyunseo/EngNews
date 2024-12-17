package org.mjulikelion.engnews.controller.ai;

import lombok.RequiredArgsConstructor;
import org.mjulikelion.engnews.authentication.AuthenticatedUser;
import org.mjulikelion.engnews.dto.ai.request.SummarizeDto;
import org.mjulikelion.engnews.dto.ai.response.FeedbackDto;
import org.mjulikelion.engnews.dto.ai.request.TrySummarizeDto;
import org.mjulikelion.engnews.dto.response.ResponseDto;
import org.mjulikelion.engnews.entity.User;
import org.mjulikelion.engnews.service.ai.SummarizeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SummarizeController {

    private final SummarizeService summarizeService;

    @PostMapping("/try-summarize")
    public ResponseEntity<ResponseDto<FeedbackDto>> trySummarize(@AuthenticatedUser User user,@RequestBody TrySummarizeDto trySummarizeDto) {
        FeedbackDto feedback=summarizeService.trySummarize(user,trySummarizeDto);
        return ResponseEntity.ok(ResponseDto.res(HttpStatus.OK, "기사 요약하고 피드백 받기 성공", feedback));
    }

    @PostMapping("/summarize")
    public ResponseEntity<ResponseDto<FeedbackDto>> summarize(@AuthenticatedUser User user, @RequestBody SummarizeDto summarizeDto) {
        FeedbackDto feedback = summarizeService.summarize(user,summarizeDto);
        return ResponseEntity.ok(ResponseDto.res(HttpStatus.OK, "요약해보기 성공", feedback));
    }
}
