package repositories;

import model.Diary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiaryRepositoryImplTest {
    private Diary diary;
    private DiaryRepositoryImpl diaryRepo;
    @BeforeEach
    public void SetUp(){
        diaryRepo = new DiaryRepositoryImpl();
        diary = new Diary("Franklin","Franklinpassword");

    }
    @Test
    public void testToSaveDiary(){
        diaryRepo = new DiaryRepositoryImpl();
        diary = new Diary("Franklin","franklinpassword");
        diaryRepo.save(diary);
        assertEquals(1,diaryRepo.count());
    }
    @Test
    public void testToFidAllDiary(){
        diaryRepo = new DiaryRepositoryImpl();
        diary = new Diary("Franklin","franklinpassword");
        Diary mydiary = new Diary ("hello","1234");
        Diary thirddiary = new Diary("hey","2223");
        diaryRepo.save(mydiary);
        diaryRepo.save(thirddiary);
        diaryRepo.save(diary);
        assertEquals(3,diaryRepo.count());
        List<Diary>allDiary= diaryRepo.findAll();
        assertEquals(3,allDiary.size());

    }
    @Test
    public void testToFindDiaryById(){
        diaryRepo = new DiaryRepositoryImpl();
        diary = new Diary("Franklin","franklinpassword");
        Diary mydiary = new Diary ("hello","1234");
        Diary thirddiary = new Diary("hey","2223");
        diaryRepo.save(mydiary);
        diaryRepo.save(thirddiary);
        diaryRepo.save(diary);
        assertEquals(3,diaryRepo.count());
        assertEquals("Franklin",diaryRepo.findById("Franklin").getUsername());
    }
    @Test
    public void testToDeletDiary(){
        diaryRepo = new DiaryRepositoryImpl();
        diary = new Diary("Franklin","franklinpassword");
        Diary mydiary = new Diary ("hello","1234");
        Diary thirddiary = new Diary("hey","2223");
        diaryRepo.save(mydiary);
        diaryRepo.save(thirddiary);
        diaryRepo.save(diary);
        assertEquals(3,diaryRepo.count());
        diaryRepo.delete("franklin");
        assertEquals(2,diaryRepo.count());
    }
    @Test
    public void testToDeleteTwoOutOfThreeDiary(){
        diaryRepo = new DiaryRepositoryImpl();
        diary = new Diary("Franklin","franklinpassword");
        Diary mydiary = new Diary ("hello","1234");
        Diary thirddiary = new Diary("hey","2223");
        diaryRepo.save(mydiary);
        diaryRepo.save(thirddiary);
        diaryRepo.save(diary);
        assertEquals(3,diaryRepo.count());
        diaryRepo.delete("franklin");
        diaryRepo.delete("hello");
        assertEquals(1,diaryRepo.count());
    }
    @Test
    public void testToDeleteAll(){
        diaryRepo = new DiaryRepositoryImpl();
        diary = new Diary("Franklin","franklinpassword");
        Diary mydiary = new Diary ("hello","1234");
        Diary thirddiary = new Diary("hey","2223");
        diaryRepo.save(mydiary);
        diaryRepo.save(thirddiary);
        diaryRepo.save(diary);
        assertEquals(3,diaryRepo.count());
        diaryRepo.deleteAll(diary);
        assertEquals(0,diaryRepo.count());
    }

}