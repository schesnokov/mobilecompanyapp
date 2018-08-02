package com.mobilecompany.controllers;

import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.services.api.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/marketing")
public class MarketingStandController {

    private final Set<SseEmitter> emitters = Collections.synchronizedSet(new HashSet<>());
    private TariffService tariffService;

    @Autowired
    public MarketingStandController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @RequestMapping(value = "/stand")
    public List<Map<String, String>> getStandInformation() {
        List<TariffDto> tariffsDtos = tariffService.getAllTariffs();
        List<Map<String, String>> tariffs = new ArrayList<>();
        for (TariffDto tariffDto : tariffsDtos) {
            Map<String, String> item = new HashMap<>();
            item.put("id", String.valueOf(tariffDto.getId()));
            item.put("tariffName", tariffDto.getTariffName());
            item.put("tariffDescription", tariffDto.getTariffDescription());
            item.put("tariffPrice", String.valueOf(tariffDto.getTariffPrice()));
            tariffs.add(item);
        }

        new Timer(true).schedule(new TimerTask() {
            @Override
            public void run() {
                sendNotificationForAllSubscribers();
            }
        }, 10000);
        return tariffs;
    }

    @RequestMapping(value = "/stand/connection")
    public SseEmitter openConnection(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        final SseEmitter emitter= new SseEmitter(20000L);
        emitter.onTimeout(emitter::complete);
        emitter.onCompletion(()-> {
            synchronized (this.emitters) {
                emitters.remove(emitter);
            }
        });
        emitters.add(emitter);
        return emitter;
    }

    @RequestMapping(value = "/stand/update")
    private void sendNotificationForAllSubscribers(){
        synchronized (this.emitters){
            for (SseEmitter emitter:emitters){
                try{
                    emitter.send("update");
                    emitter.complete();
                }
                catch (Exception ignored){
                }
            }
        }
    }

}
