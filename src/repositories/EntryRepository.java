package repositories;

import model.Diary;
import model.Entry;

import java.util.List;

public interface EntryRepository {
    Entry save(Entry entry);

    List<Entry>findAll();
    Entry findById(int id);

    long count();
    List<Entry>findByAuthor(String Author);

    void delete(Entry entry);

}
