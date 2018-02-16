package no.knowit.chapters.jvm.springbootdemo.client;

import java.io.StringReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;

@Component
public class WeatherClient {

  /** Værvarsel fra Yr, levert av NRK og Meteorologisk institutt. */
  private static final String FORECAST_ENDPOINT_URL =
      "https://www.yr.no/sted/Norway/Oslo/Oslo/Lakkegata/varsel.xml";

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

  private String fetchXmlForecast() {
    return new RestTemplate().getForObject(FORECAST_ENDPOINT_URL, String.class);
  }

}
