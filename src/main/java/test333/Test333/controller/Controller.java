package test333.Test333.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/returnValue")

public class Controller {
    @GetMapping()
    public int number() {
        return Stream.iterate(1, n -> n + 1)
                .limit(1000000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
    }
}
