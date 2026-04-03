package pe.edu.idat.loan_service.dto;

public class GenericResponse<T> {

    private T response;
    private ErrorMessage error;

    public GenericResponse() {
    }

    public GenericResponse(T response, ErrorMessage error) {
        this.response = response;
        this.error = error;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public ErrorMessage getError() {
        return error;
    }

    public void setError(ErrorMessage error) {
        this.error = error;
    }
}