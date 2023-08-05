package com.mz.ihangbot.keyWord.controller;

import com.mz.ihangbot.common.dto.BasicResponse;
import com.mz.ihangbot.keyWord.dto.ConcernRequestDTO;
import com.mz.ihangbot.keyWord.dto.KeyWordRequestDTO;
import com.mz.ihangbot.keyWord.service.KeyWordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;

@RestController
@RequiredArgsConstructor
@RequestMapping("/keyword")
@Tag(name = "keyword", description = "keyword API")
public class KeyWordController {

    private final BasicResponse basicResponse = new BasicResponse();
    private final KeyWordService keyWordService;

    @PostMapping("/getKeyWord")
    @Operation(summary = "관심사 정보", description = "관심사 정보를 받아옵니다.")
    public ResponseEntity<BasicResponse> getKeyWord(@RequestBody KeyWordRequestDTO requestDTO) throws ParseException {
        String keyword = requestDTO.getKeyword();
        String username = requestDTO.getUsername();
        String count = requestDTO.getCount();
        String date = requestDTO.getDate();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date localDate = formatter.parse(date);

        keyWordService.addKeyWord(keyword, username, Integer.parseInt(count), localDate);

        return basicResponse.noContent();
    }

    @PostMapping("/getConcern")
    @Operation(summary = "주제 정보", description = "주제 정보를 받아옵니다.")
    public ResponseEntity<BasicResponse> getConcern(@RequestBody List<ConcernRequestDTO> requestDTOList) throws ParseException {
        for (ConcernRequestDTO requestDTO : requestDTOList) {
            String category = requestDTO.getCategory();
            String confidence = requestDTO.getConfidence();
            String username = requestDTO.getUsername();
            String date = requestDTO.getDate();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Date localDate = formatter.parse(date);

            keyWordService.addConcern(category, Double.parseDouble(confidence), username, localDate);
        }
        return basicResponse.noContent();
    }
}
