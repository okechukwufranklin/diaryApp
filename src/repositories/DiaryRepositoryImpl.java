package repositories;

import model.Diary;

import java.util.ArrayList;
import java.util.List;

public class DiaryRepositoryImpl implements DiaryRepository{
    private List<Diary> diarylist;
    private int count;
    public DiaryRepositoryImpl(){
        this.diarylist = new ArrayList<>();
    }

    @Override
    public Diary save(Diary diary) {
        this.diarylist.add(diary);
        count++;
        return diary;
    }

    @Override
    public List<Diary> findAll() {
        return new ArrayList<>(diarylist);
    }

    @Override
    public Diary findById(String username) {
        for (Diary diary : diarylist) {
            if (diary.getUsername().equals(username))
                return diary;
        }
        return null;
    }

    @Override
    public long count() {
        return count;
    }

    @Override
    public void delete(String username) {
        findById(username);
        diarylist.remove(username);
        count--;
    }

    @Override
    public void deleteAll(Diary diary) {
        diarylist.clear();
        count =0;
    }
}
