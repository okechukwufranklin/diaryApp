package services;

import Exceptions.IncorrectPasswordException;
import Exceptions.UserAlreadyExistException;
import Services.DiaryServicesImpl;
import Services.EntryServicesImpl;
import dtos.request.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryServiceTest {
    private DiaryServicesImpl diaryServices;
    private EntryServicesImpl entryServices;
    private LoginRequest loginRequest;
    private RegisterRequest registerRequest;
    private RemoveUserRequest removeUserRequest;



    @Test
    public void testDiaryServceRegister(){
        diaryServices = new DiaryServicesImpl();
        registerRequest =new RegisterRequest();
        registerRequest.setUsername("franklin");
        registerRequest.setPassword("1233");
        diaryServices.register(registerRequest);
        assertEquals(1,diaryServices.getNumberOfUsers());

    }

    @Test
    public void testDiaryServiceCanRegisterTwice() {
        diaryServices = new DiaryServicesImpl();
        registerRequest = new RegisterRequest();
        registerRequest.setUsername("franklin");
        registerRequest.setPassword("1233");
        diaryServices.register(registerRequest);

        RegisterRequest registerRequest2 = new RegisterRequest();
        registerRequest2.setUsername("frank");
        registerRequest2.setPassword("1233");
        diaryServices.register(registerRequest2);
        assertEquals(2, diaryServices.getNumberOfUsers());
    }
    @Test
    public void testDiaryService_CannotRegisterTwice() {
        diaryServices = new DiaryServicesImpl();
        registerRequest = new RegisterRequest();
        registerRequest.setUsername("franklin");
        registerRequest.setPassword("1233");
        diaryServices.register(registerRequest);

        RegisterRequest registerRequest2 = new RegisterRequest();
        registerRequest2.setUsername("franklin");
        registerRequest2.setPassword("1233");
        assertThrows(UserAlreadyExistException.class, () -> diaryServices.register(registerRequest2));

    }
    @Test
    public void testDiaryRegisterUserWithEmpty_String_ThrowIllegalArgumentException() {
        diaryServices = new DiaryServicesImpl();
        registerRequest = new RegisterRequest();
        registerRequest.setUsername(" ");
        registerRequest.setPassword(" ");
        assertThrows(IllegalArgumentException.class, () -> diaryServices.register(registerRequest));
        assertEquals(0L, diaryServices.getNumberOfUsers());


    }


    @Test
    public void testToLogin(){
        diaryServices = new DiaryServicesImpl();
        registerRequest =new RegisterRequest();
        registerRequest.setUsername("franklin");
        registerRequest.setPassword("1233");
        diaryServices.register(registerRequest);
        assertEquals(1,diaryServices.getNumberOfUsers());
        loginRequest = new LoginRequest();
        loginRequest.setUsername("franklin");
        loginRequest.setPassword("1233");
        diaryServices.login(loginRequest);
        assertFalse(diaryServices.findDiaryBy("franklin").isLocked());
    }

    @Test
    public void testDiaryUserCanLogOutOFAccount() {
        diaryServices = new DiaryServicesImpl();
        registerRequest =new RegisterRequest();
        registerRequest.setUsername("franklin");
        registerRequest.setPassword("1233");
        diaryServices.register(registerRequest);
        diaryServices.logout("franklin");
        assertTrue(diaryServices.findDiaryBy("franklin").isLocked());
    }
    @Test
    public void testDiaryUserLoginWithIncorrectPassword() {
        diaryServices = new DiaryServicesImpl();
        registerRequest = new RegisterRequest();
        registerRequest.setUsername("franklin");
        registerRequest.setPassword("1233");
        diaryServices.register(registerRequest);
        loginRequest = new LoginRequest();
        loginRequest.setUsername("franklin");
        loginRequest.setPassword("1234");
        assertThrows(IncorrectPasswordException.class, () -> diaryServices.login(loginRequest));
    }

    @Test
    public void testDiaryUserCanDeleteAccount() {
        diaryServices = new DiaryServicesImpl();
        registerRequest = new RegisterRequest();
        registerRequest.setUsername("franklin");
        registerRequest.setPassword("1233");
        diaryServices.register(registerRequest);

        RemoveUserRequest removeUserRequest = new RemoveUserRequest();
        removeUserRequest.setUsername("franklin");
        removeUserRequest.setPassword("1233");

        loginRequest = new LoginRequest();
        loginRequest.setUsername("franklin");
        loginRequest.setPassword("1234");
        diaryServices.removeUser(removeUserRequest);
        assertEquals(0, diaryServices.getNumberOfUsers());
    }


    @Test
    public void testDiaryUserDeleteWithIncorrectPassword_IncorrectPasswordException() {
        diaryServices = new DiaryServicesImpl();
        registerRequest = new RegisterRequest();
        registerRequest.setUsername("franklin");
        registerRequest.setPassword("1233");
        diaryServices.register(registerRequest);
        RemoveUserRequest removeUserRequest = new RemoveUserRequest();
        removeUserRequest.setUsername("franklin");
        removeUserRequest.setPassword("1234567");
        loginRequest = new LoginRequest();
        loginRequest.setUsername("franklin");
        loginRequest.setPassword("1234");
        assertThrows(IncorrectPasswordException.class, () -> diaryServices.removeUser(removeUserRequest));
    }
@Test
    public void testToUpdateEntry_NumberOfEntries() {
        diaryServices = new DiaryServicesImpl();
        registerRequest = new RegisterRequest();
        registerRequest.setUsername("franklin");
        registerRequest.setPassword("1233");
        diaryServices.register(registerRequest);

        loginRequest = new LoginRequest();
        loginRequest.setUsername("franklin");
        loginRequest.setPassword("1233");
        diaryServices.login(loginRequest);

        assertEquals(1, diaryServices.getNumberOfUsers());

        CreateEntryRequest entryRequest = new CreateEntryRequest();
        entryServices = new EntryServicesImpl();
        entryRequest.setTitle("franklin");
        entryRequest.setBody("tired");
        entryRequest.setAuthor("franklin");
        diaryServices.createEntryWith(entryRequest);
        assertEquals(1, diaryServices.count());
        assertEquals(1, entryServices.findAll().get(0).getId());


        assertEquals("franklin", entryServices.getEntries("franklin").getFirst().getTitle());
        assertEquals(1, entryServices.getEntries("franklin").size());

        UpdateEntryRequest updateEntryRequest = new UpdateEntryRequest();
        updateEntryRequest.setTitle("franklin story");
        updateEntryRequest.setBody("New story");
        updateEntryRequest.setAuthor("franklin");
        updateEntryRequest.setId(1);
        diaryServices.updateEntryWith(updateEntryRequest);
        assertEquals("franklin story", diaryServices.getEntriesFor("franklin").getFirst().getTitle());
        assertEquals(1, diaryServices.getEntriesFor("franklin").size());

    }
    @Test
    public void testToDeleteEntry(){
        diaryServices = new DiaryServicesImpl();
        registerRequest = new RegisterRequest();
        registerRequest.setUsername("franklin");
        registerRequest.setPassword("1233");
        diaryServices.register(registerRequest);

        loginRequest = new LoginRequest();
        loginRequest.setUsername("franklin");
        loginRequest.setPassword("1233");
        diaryServices.login(loginRequest);

        assertEquals(1, diaryServices.getNumberOfUsers());

        CreateEntryRequest entryRequest = new CreateEntryRequest();
        entryServices = new EntryServicesImpl();
        entryRequest.setTitle("franklin");
        entryRequest.setBody("tired");
        entryRequest.setAuthor("franklin");
        diaryServices.createEntryWith(entryRequest);
        assertEquals(1, diaryServices.count());
        assertEquals(1, entryServices.findAll().get(0).getId());


        assertEquals("franklin", entryServices.getEntries("franklin").getFirst().getTitle());
        assertEquals(1, entryServices.getEntries("franklin").size());

        DeleteEntryRequest deleteEntryRequest = new DeleteEntryRequest();
        deleteEntryRequest.setUsername("franklin");
        deleteEntryRequest.setPassword("1233");
        deleteEntryRequest.setId(0);
        diaryServices.d

    }
   }
