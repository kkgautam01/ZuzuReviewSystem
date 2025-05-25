package org.zuzureviewsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zuzureviewsystem.filehandling.service.FileHandling;
import org.zuzureviewsystem.fileprocessing.dto.HotelReviewDto;
import org.zuzureviewsystem.util.LogMessages;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/reviews")
public class Reviews {
    @Autowired
    FileHandling fileHandling;

    //Process logs by Api
    @PostMapping("/process")
    public ResponseEntity<String> processLogs() {
        fileHandling.process();
        return ResponseEntity.ok(LogMessages.LOG_PROCESSED);
    }

    // TODO : to fetch Logs according to Filters. Extend api according ot the need
    @GetMapping("")
    public ResponseEntity<List<HotelReviewDto>> getLogs(
            @RequestParam(required = false) String providerId,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate
    ) {
        List<HotelReviewDto> hotelReviewDtoList = new ArrayList<>();

        return ResponseEntity.ok(hotelReviewDtoList);
    }
}
