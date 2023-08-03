package com.mz.ihangbot.report.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mz.ihangbot.common.dto.BasicResponse;
import com.mz.ihangbot.common.exception.BusinessException;
import com.mz.ihangbot.common.exception.ErrorCode;
import com.mz.ihangbot.common.exception.InvalidValueException;
import com.mz.ihangbot.keyWord.service.KeyWordService;
import com.mz.ihangbot.member.service.MemberService;
import com.mz.ihangbot.report.dto.SuggestionRequestDTO;
import com.mz.ihangbot.report.service.ReportService;
import com.mz.ihangbot.sentiment.service.SentimentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.mz.ihangbot.common.exception.ErrorCode.NO_REPORT_DATA;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
@Tag(name = "report", description = "report API")
public class ReportController {

    private final BasicResponse basicResponse = new BasicResponse();
    private final ReportService reportService;
    private final MemberService memberService;

    @PostMapping("/{username}")
    @Operation(summary = "레포트 전송", description = "레포트를 전송힙니다.")
    public ResponseEntity<BasicResponse> sendReport(@PathVariable("username") String username) throws JsonProcessingException {
        LocalDateTime createdAt = memberService.findMember(username).getCreatedAt();
        LocalDateTime todayLocalDate = LocalDateTime.now();
        LocalDateTime aWeekAgo = todayLocalDate.minusDays(7);

        if (createdAt.isAfter(aWeekAgo))
            throw new InvalidValueException(ErrorCode.NO_REPORT_DATA);

        return basicResponse.ok(
                reportService.getReportData(username)
        );
    }

    @PostMapping("/getGptData")
    public  ResponseEntity<BasicResponse> getGptData(@RequestBody List<SuggestionRequestDTO> requestDTO) throws JsonProcessingException {
        return basicResponse.ok(
                reportService.getGptData(requestDTO)
        );
    }
}
