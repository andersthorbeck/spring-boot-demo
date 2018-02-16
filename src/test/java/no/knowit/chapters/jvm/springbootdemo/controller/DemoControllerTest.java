package no.knowit.chapters.jvm.springbootdemo.controller;

import static java.time.ZoneOffset.UTC;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.time.Clock;
import java.time.LocalDateTime;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class DemoControllerTest {

  private Clock fixedClock = Clock.fixed(LocalDateTime.of(2018, 2, 16, 14, 42).toInstant(UTC), UTC);
  DemoController controller = new DemoController(fixedClock);
  MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

  @org.junit.Test
  public void time() throws Exception {
    mockMvc.perform(get("/time")).andExpect(content().string("14:42"));
  }

}
