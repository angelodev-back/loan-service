package pe.edu.idat.loan_service.dto;

import java.time.LocalDate;

public class ErrorMessage {

    private Integer statusCode;
    private LocalDate dateError;
    private String message;
    private String description;

    public ErrorMessage() {
    }

    public ErrorMessage(Integer statusCode, LocalDate dateError, String message, String description) {
        this.statusCode = statusCode;
        this.dateError = dateError;
        this.message = message;
        this.description = description;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public LocalDate getDateError() {
        return dateError;
    }

    public void setDateError(LocalDate dateError) {
        this.dateError = dateError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
