package repositories;

import model.Diary;

import java.util.List;

public interface DiaryRepository {
    Diary save(Diary diary);
    List<Diary> findAll();
    Diary findById(String username);
    long count();
    void delete(String username);
    void deleteAll(Diary diary);

}
