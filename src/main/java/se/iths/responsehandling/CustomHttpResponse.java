package se.iths.responsehandling;

public class CustomHttpResponse {

    private int httpResponseStatusCode;
    private String httpResponseStatus;
    private String httpResponseDescription;

    public CustomHttpResponse() {
    }

    public CustomHttpResponse(int httpResponseStatusCode, String httpResponseStatus, String httpResponseDescription) {
        this.httpResponseStatusCode = httpResponseStatusCode;
        this.httpResponseStatus = httpResponseStatus;
        this.httpResponseDescription = httpResponseDescription;
    }

    public int getHttpResponseStatusCode() {
        return httpResponseStatusCode;
    }

    public void setHttpResponseStatusCode(int httpResponseStatusCode) {
        this.httpResponseStatusCode = httpResponseStatusCode;
    }

    public String getHttpResponseStatus() {
        return httpResponseStatus;
    }

    public void setHttpResponseStatus(String httpResponseStatus) {
        this.httpResponseStatus = httpResponseStatus;
    }

    public String getHttpResponseDescription() {
        return httpResponseDescription;
    }

    public void setHttpResponseDescription(String httpResponseDescription) {
        this.httpResponseDescription = httpResponseDescription;
    }
}
