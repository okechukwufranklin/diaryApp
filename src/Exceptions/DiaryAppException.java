package Exceptions;

import dtos.request.RegisterRequest;

public class DiaryAppException extends RuntimeException {
    public DiaryAppException(String Message){
        super(Message);
    }
}
