package br.com.rangood.pdv.rangoodpdvapigatewayserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class EndpointController {

//    @Value("#{${routes}}")
//    private Map<String, String> routing;
//
//    @GetMapping("config")
//    public ResponseEntity getSharedConfigs() {
//        return ResponseEntity.ok(routing);
//    }
}
