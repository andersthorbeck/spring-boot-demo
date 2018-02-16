package no.knowit.chapters.jvm.springbootdemo.controller;

import java.time.LocalTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

  @RequestMapping("/time")
  public String time() {
    return LocalTime.now().toString();
  }

}
