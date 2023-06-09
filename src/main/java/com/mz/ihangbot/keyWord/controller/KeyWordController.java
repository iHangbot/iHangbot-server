package com.mz.ihangbot.keyWord.controller;

import com.mz.ihangbot.common.dto.BasicResponse;
import com.mz.ihangbot.keyWord.dto.ConcernRequestDTO;
import com.mz.ihangbot.keyWord.dto.KeyWordRequestDTO;
import com.mz.ihangbot.keyWord.service.KeyWordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/keyword")
@Tag(name = "keyword", description = "keyword API")
public class KeyWordController {

    private final BasicResponse basicResponse = new BasicResponse();
    private final KeyWordService keyWordService;

    @PostMapping("/getKeyWord")
    @Operation(summary = "관심사 정보", description = "관심사 정보를 받아옵니다.")
    public ResponseEntity<BasicResponse> getKeyWord(@RequestBody List<KeyWordRequestDTO> requestDTOList) throws ParseException {
        for (KeyWordRequestDTO requestDTO : requestDTOList) {
            String keyword = requestDTO.getKeyword();
            String count = requestDTO.getCount();
            String date = requestDTO.getDate();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Date localDate = formatter.parse(date);

            keyWordService.addKeyWord(keyword, Integer.parseInt(count), localDate);
        }
        return basicResponse.noContent();
    }

    @PostMapping("/getConcern")
    @Operation(summary = "주제 정보", description = "주제 정보를 받아옵니다.")
    public ResponseEntity<BasicResponse> getConcern(@RequestBody List<ConcernRequestDTO> requestDTOList) throws ParseException {
        for (ConcernRequestDTO requestDTO : requestDTOList) {
            String category = requestDTO.getCategory();
            String confidence = requestDTO.getConfidence();
            String date = requestDTO.getDate();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Date localDate = formatter.parse(date);

            keyWordService.addConcern(category, Double.parseDouble(confidence), localDate);
        }
        return basicResponse.noContent();
    }

    @PostMapping("/report")
    @Operation(summary = "관심사 분석 결과 전송", description = "관심사 분석 결과를 전송힙니다.")
    public ResponseEntity<BasicResponse> sendKeyword() {
        return basicResponse.ok(
                keyWordService.getReportData()
        );
    }
}
