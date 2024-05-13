package assignment.client;

public class SendNotification {
    String message;
    Object body;
    String statusCode;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SendNotification(String message, Object body, String statusCode) {
        this.message = message;
        this.body = body;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "SendNotification{" +
                "body=" + body +
                ", message='" + message + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
