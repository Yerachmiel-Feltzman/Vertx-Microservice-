# Vert.x Microservice
Creates a Vert.x server that exposes an api to count valid tags for html.

The following command using cURL is a good example of how to use the api:

```bash
curl -X POST <IP>:8080/api/count-valid-tags \
-H "Content-Type: application/json" \
--data '{"html": "<html><head></head><body><div><div></div></div></body></html>"}'
```

And then the response would be:
```bash
{"validTags":5}
```

The server is built using [Vert.x](https://vertx.io/) framework and is written in Java.





