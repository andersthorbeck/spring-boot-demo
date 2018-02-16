package no.knowit.chapters.jvm.springbootdemo.client;

import java.io.StringReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;

@Component
public class WeatherClient {

  /** Værvarsel fra Yr, levert av NRK og Meteorologisk institutt. */
  private static final String FORECAST_ENDPOINT_URL =
      "https://www.yr.no/sted/Norway/Oslo/Oslo/Lakkegata/varsel.xml";
  private static final int ONE_SECOND_IN_MS = 1000;
  private static final int ONE_MINUTE_IN_MS = 60 * ONE_SECOND_IN_MS;

  private String forecastXml = null;

  public int fetchForecastTemperature() {
    final String temperatureXPath = "//forecast/tabular/time[1]/temperature/@value";
    return Integer.valueOf(
        new Jaxp13XPathTemplate().evaluateAsString(temperatureXPath, getXmlForecast()));
  }

  private Source getXmlForecast() {
    if (forecastXml == null) {
      forecastXml = fetchXmlForecast();
    }
    return new StreamSource(new StringReader(forecastXml));
  }

  @Scheduled(initialDelay = 10 * ONE_SECOND_IN_MS, fixedRate = 10 * ONE_MINUTE_IN_MS)
  private void regularlyUpdateCachedForecast() {
    forecastXml = fetchXmlForecast();
  }

  private String fetchXmlForecast() {
    return new RestTemplate().getForObject(FORECAST_ENDPOINT_URL, String.class);
  }

}
