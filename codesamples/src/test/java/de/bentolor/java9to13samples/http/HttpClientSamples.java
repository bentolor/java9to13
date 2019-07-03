package de.bentolor.java9to13samples.http;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static java.net.http.WebSocket.NORMAL_CLOSURE;

class HttpClientSamples {

    /**
     * The HTTP API functions asynchronously &amp; synchronously. In asynchronous mode,
     * work is done on the threads supplied by the client's ExecutorService.
     */
    @Test
    void asyncHttp2Client() throws InterruptedException, ExecutionException {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(3))
                .build();

        URI uri = URI.create("https://www.exxcellent.de/");
        HttpRequest request = HttpRequest
                .newBuilder(uri)
                .header("Useragent", "MyDemo")
                .GET()
                .build();

        // start request in background
        var future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);

        // meanwhile: do some other tasks
        var expensiveOperation = Math.pow(12345, 92123);

        // wait for completion of the background tasks
        future.get();
    }

    @Test
    void buildWebsocketClient() throws InterruptedException {
        var wsBuilder = HttpClient.newHttpClient().newWebSocketBuilder();
        var wsFuture = wsBuilder
                .buildAsync(URI.create("wss://echo.websocket.org"),
                        new WebSocket.Listener() {
                            public void onOpen(WebSocket webSocket) {
                            }

                            public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                                return null;
                            }

                            public CompletionStage<?> onBinary(WebSocket webSocket, ByteBuffer data, boolean last) {
                                return null;
                            }

                            public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
                                return null;
                            }

                            public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
                                return null;
                            }

                            public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
                                return null;
                            }

                            public void onError(WebSocket webSocket, Throwable error) {
                            }
                        });
        WebSocket websocket = wsFuture.join();
        websocket.sendPing(ByteBuffer.wrap("Ping from Client".getBytes()));
        websocket.sendText("Hi there!", true);

        websocket.sendClose(NORMAL_CLOSURE, "Goodbye!").join();
    }
}