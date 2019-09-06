package dasniko.serverless.jug;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Map;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
public class HelloHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Logger logger = LogManager.getLogger(HelloHandler.class);

    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {

        logger.info("Received event: " + event);

        try {

            Map<String, String> body = mapper.readValue(event.getBody(),
                    new TypeReference<Map<String, String>>() {});

            String response = mapper.writeValueAsString(
                    Collections.singletonMap("Greeting", "Hello " + body.get("name"))
            );

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(response);

        } catch (Exception e){
            e.printStackTrace();
            return new APIGatewayProxyResponseEvent().withStatusCode(500).withBody(e.getMessage());
        }

    }
}
