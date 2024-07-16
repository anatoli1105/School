package test333.Test333.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/info")

public class InfoController {
    @Value("${server.port:port}")
    private int port;

    @GetMapping(path = "/port")
    public int port() {
        return port;
    }
}
