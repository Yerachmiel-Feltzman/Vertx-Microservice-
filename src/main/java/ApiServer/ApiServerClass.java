package ApiServer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static Utils.CountTags.countValidTagsHtmlString;

public class ApiServerClass extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) {
        /**
         *This method creates a server and exposes an api as "/api/count-valid-tags" to be used from a post request.
         *
         * Default port is 8080, but it is configurable in the configuration files in conf directory.
         */
        // Creates a router object.
        Router router = Router.router(vertx);

        String HelloMessage = "<h1>Hello, in order to use the api you can using cURL as follows:</h1>" +
                "<p>curl -X POST localhost:8082/api/count-valid-tags \\ " +
                "-H \"Content-Type: application/json\" \\" +
                "--data '{\"html\": \"<html><head></head><body><div><div></div></div></body></html>\"}'\n</p>"+
                "<p>And the result would be: {\"validTags\":4} .</p>";

        // Bind "/" to our hello message.
        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response
                    .putHeader("content-type", "text/html")
                    .end(HelloMessage);
        });


        // first of all there is a need to explicitly create a body handler
        router.route("/api*").handler(BodyHandler.create());
        router.post("/api/count-valid-tags").handler(this::returnValidTagsCount);

        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        // Retrieve the port from the configuration,
                        // default to 8080.
                        config().getInteger("http.port", 8080),
                        result -> {
                            if (result.succeeded()) {
                                fut.complete();
                            } else {
                                fut.fail(result.cause());
                            }
                        }
                );
    }

    private void returnValidTagsCount(RoutingContext routingContext) {
        /**
         * Parses the json and if it is the right format returns the count of valid tags.
         *
         * The expect format is:
         * {"html": "some html string}
         *
         * And the response format is:
         * {"validTags": some integer}
         */

        final JsonObject json = routingContext.getBodyAsJson();

        if (json == null) {

            routingContext.response().setStatusCode(400).end("Did you send an empty json or invalid data type?");

        } else {

            final String html = json.getValue("html").toString();

            int validTagsCount = countValidTagsHtmlString(html);

            final Map<String, Integer> validTagsJson = new HashMap<>();
            validTagsJson.put("validTags", validTagsCount);

            routingContext.response()
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodePrettily(validTagsJson));
        }
    }

}
