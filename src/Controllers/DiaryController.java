package Controllers;

import Exceptions.DiaryAppException;
import Services.DiaryServices;
import Services.DiaryServicesImpl;
import dtos.request.CreateEntryRequest;
import dtos.request.LoginRequest;
import dtos.request.RegisterRequest;

public class DiaryController {
    private static final DiaryServices diaryServices = new DiaryServicesImpl();

    public static String registerUser(RegisterRequest request) {
        try {
            diaryServices.register(request);
            return "registration successful";
        } catch (DiaryAppException e) {
            return e.getMessage();
        }

    }

    public static String login(LoginRequest request) {
        try {
            diaryServices.login(request);
            return "login successful";
        } catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    public static String logout(String username) {
        try {
            diaryServices.logout(username);
            return "logout successful";
        } catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    public String createEntry(CreateEntryRequest createEntryRequest){
        try{
            createEntry(createEntryRequest);
            return "entry created";
        }catch (Exception error){
            return  error.getMessage();
        }
    }

    public String deleteEntry()

}
