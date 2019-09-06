package dasniko.serverless.jug;

import java.util.Collections;
import java.util.Map;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
public class PlainFunction {

    public Map<String, String> greet(Map<String, String> event) {
        return Collections.singletonMap("Greeting", "Hello " + event.get("name"));
    }

}
