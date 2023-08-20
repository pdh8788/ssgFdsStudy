package com.example.ssgfdsstudy.controller;

import com.example.ssgfdsstudy.dto.DataObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DataController {

    private final KafkaTemplate<String, DataObject> kafkaTemplate;

    public void sendMessage(DataObject dataObject){
        kafkaTemplate.send("ssgstudy-fds-data", dataObject);
    }

    @PostMapping("/data/collect")
    public String dataCollect(@RequestBody DataObject dataObject){

        String resultCode = "0000";

        // 수집 데이터를 kafka에 전송한다.
        log.info("greeting: {}", dataObject);

        sendMessage(dataObject);

        return resultCode;
    }

}
