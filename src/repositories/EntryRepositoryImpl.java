package repositories;

import Exceptions.EmptyEntryListException;
import model.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryImpl implements EntryRepository{
    private static final List<Entry>  entries = new ArrayList<>();

    private int id;
    @Override
    public Entry save(Entry entry) {
        if (entry.getId() > 0) {
            update(entry);
        }
        else if (entry.getId() == 0){
            entry.setId(++id);
        }
        entries.add(entry);

        return entry;

    }

    private void update(Entry entry){
        Entry old = findById(entry.getId());
        entries.remove(old);

    }

    @Override
    public List<Entry> findAll() {

        return entries;
    }

    @Override
    public Entry findById(int id) {
        for (Entry entry : entries) {
            if (entry.getId() == id)
                return entry;
        }
        return null;

    }

    @Override
    public long count() {
        return entries.size();
    }

    @Override
    public List<Entry> findByAuthor(String Author) {
        ArrayList<Entry> usernameEntries = new ArrayList<>();
        for (Entry entry : entries){
            if (entry.getAuthor().equalsIgnoreCase(Author)) usernameEntries.add(entry);
        }
        return usernameEntries;
    }

    public List<Entry> getEntries(String username) {
        List<Entry> userEntries = new ArrayList<>();
        for (Entry entry : entries) {
            if (entry.getAuthor().equalsIgnoreCase(username)) {
                userEntries.add(entry);
            }
        }
        if (userEntries.isEmpty()) throw new EmptyEntryListException("No entry found");
        return userEntries;
    }


    @Override
    public void delete(Entry entry) {
        entries.remove(entry);
    }
}
