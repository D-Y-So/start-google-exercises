public class Response {
    private final int statusCode;
    private final String body;
    private final String responseReason;

    private Response(int statusCode, String body, String responseReason) {
        this.statusCode = statusCode;
        this.body = body;
        this.responseReason = responseReason;
    }

    public static Response createNewResponse(int statusCode, String body, String responseReason){
        return new Response(statusCode, body, responseReason);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Response {" +
                "Status Code: " + statusCode +
                ", Reason: " + responseReason +
                ", Body: " + body +
                '}';
    }
}
