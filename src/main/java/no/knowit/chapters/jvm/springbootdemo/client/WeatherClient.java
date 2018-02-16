package no.knowit.chapters.jvm.springbootdemo.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherClient {

  /** Værvarsel fra Yr, levert av NRK og Meteorologisk institutt. */
  private static final String FORECAST_ENDPOINT_URL =
      "https://www.yr.no/sted/Norway/Oslo/Oslo/Lakkegata/varsel.xml";

  public String fetchXmlForecast() {
    return new RestTemplate().getForObject(FORECAST_ENDPOINT_URL, String.class);
  }

}
