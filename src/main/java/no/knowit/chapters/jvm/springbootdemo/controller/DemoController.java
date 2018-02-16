package no.knowit.chapters.jvm.springbootdemo.controller;

import java.time.Clock;
import java.time.LocalTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

  private final Clock clock;

  public DemoController(Clock clock) {
    this.clock = clock;
  }

  @RequestMapping("/time")
  public String time() {
    return LocalTime.now(clock).toString();
  }

}
