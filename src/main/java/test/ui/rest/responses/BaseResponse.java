package test.ui.rest.responses;

public class BaseResponse {
    private final String message;
    private final boolean status;

    public BaseResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public boolean getStatus() {
        return status;
    }
}
