package no.knowit.chapters.jvm.springbootdemo.controller;

import java.time.Clock;
import java.time.LocalTime;
import no.knowit.chapters.jvm.springbootdemo.client.WeatherClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

  private final Clock clock;
  private final WeatherClient client;

  public DemoController(Clock clock, WeatherClient client) {
    this.clock = clock;
    this.client = client;
  }

  @RequestMapping("/temperature")
  public int temperature() {
    return client.fetchForecastTemperature();
  }

  @RequestMapping("/time")
  public String time() {
    return LocalTime.now(clock).toString();
  }

}
