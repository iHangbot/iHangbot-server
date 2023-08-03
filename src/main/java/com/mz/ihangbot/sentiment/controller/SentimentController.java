package com.mz.ihangbot.sentiment.controller;

import com.mz.ihangbot.common.dto.BasicResponse;
import com.mz.ihangbot.member.dto.MemberRequestDTO;
import com.mz.ihangbot.sentiment.dto.SentimentRequestDTO;
import com.mz.ihangbot.sentiment.service.SentimentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sentiment")
@Tag(name = "sentiment", description = "sentiment API")
public class SentimentController {

    private final BasicResponse basicResponse = new BasicResponse();
    private final SentimentService sentimentService;

    @PostMapping("/getData")
    @Operation(summary = "감정 분석 정보", description = "감정 분석 정보를 받아옵니다.")
    public ResponseEntity<BasicResponse> getData(@RequestBody List<SentimentRequestDTO> requestDTOList) throws ParseException {
        for (SentimentRequestDTO requestDTO : requestDTOList) {
            String content = requestDTO.getContent();
            String negative = requestDTO.getNegative();
            String positive = requestDTO.getPositive();
            String neutral = requestDTO.getNeutral();
            String username = requestDTO.getUsername();
            String date = requestDTO.getDate();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Date localDate = formatter.parse(date);

            sentimentService.addSentiment(content, Double.parseDouble(negative), Double.parseDouble(positive), Double.parseDouble(neutral), username, localDate);
        }
        return basicResponse.noContent();
    }
}
