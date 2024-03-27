package Services;

import Exceptions.IncorrectPasswordException;
import Exceptions.UserAlreadyExistException;
import Exceptions.UserNotFoundException;
import dtos.request.*;
import model.Diary;
import model.Entry;
import repositories.DiaryRepositoryImpl;
import repositories.EntryRepositoryImpl;

import java.util.List;

public class DiaryServicesImpl implements DiaryServices{
    private DiaryRepositoryImpl diaryRepository = new DiaryRepositoryImpl();
    private EntryService entryService = new EntryServicesImpl();
    //private EntryRepositoryImpl entryRepository = new EntryRepositoryImpl();
    @Override
    public void register(RegisterRequest request) {
    if(request.getUsername().isBlank()||request.getPassword().isBlank()){
        throw new IllegalArgumentException("username or password should not be blank");
    }
    validateUsername(request.getUsername());
    Diary diary = new Diary();
    diary.setUsername(request.getUsername());
    diary.setPassword(request.getPassword());
    diaryRepository.save(diary);
    }

    @Override
    public void login(LoginRequest request) {
    Diary foundDiary;
    try {
        foundDiary = findDiaryBy(request.getUsername().toLowerCase());
    }catch (UserNotFoundException e){
        throw new UserAlreadyExistException("User not found");
    }
    if( PasswordIncorrect(foundDiary, request.getPassword())){
        throw new IncorrectPasswordException("Wrong Password");
    }
    foundDiary.unLockDiary();
    }

    @Override
    public void logout(String username) {
        Diary foundDiary = findDiaryBy(username.toLowerCase());
        foundDiary.lockDiary();
        diaryRepository.save(foundDiary);
    }

    @Override
    public void removeUser(RemoveUserRequest request) {
    Diary findDiary = findDiaryBy(request.getUsername().toLowerCase());
        if(PasswordIncorrect(findDiary, request.getPassword())){
            throw new IncorrectPasswordException("Wrong Password");
        }
        diaryRepository.delete(String.valueOf(findDiary));
    }

    @Override
    public long getNumberOfUsers() {
        return diaryRepository.count();
    }

    @Override
    public long count() {
        return entryService.count();
    }

    public void validateUsername(String username){
       var diary = diaryRepository.findById(username);
       if(diary != null)throw new UserAlreadyExistException("UserName Has Been Taken");
    }
    private  boolean PasswordIncorrect(Diary foundDiary, String Password){
        return !foundDiary.getPassword().equals(Password);
    }
    public Diary findDiaryBy(String UserName){
        Diary foundDiary = diaryRepository.findById(UserName.toLowerCase());
        if (foundDiary == null) throw new UserNotFoundException("User not found");
        return foundDiary;
    }


    public void createEntryWith(CreateEntryRequest request) {
        Diary foundDiary = findDiaryBy(request.getAuthor().toLowerCase());
        checkStateOfDiary(foundDiary);
        entryService.createEntry(request);
    }

    public void updateEntryWith(UpdateEntryRequest request) {
        Diary foundDiary = findDiaryBy(request.getAuthor().toLowerCase());
        checkStateOfDiary(foundDiary);

        entryService.updateEntry(request);
    }

    public List<Entry> getEntriesFor(String username) {
        Diary foundDiary = findDiaryBy(username.toLowerCase());
        checkStateOfDiary(foundDiary);

        return entryService.getEntries(username);
    }
    private void checkStateOfDiary(Diary diary) {
        if (diary.isLocked()) throw new IllegalArgumentException("U have to login.");
    }

}
