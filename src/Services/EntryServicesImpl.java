package Services;

import Exceptions.EmptyEntryListException;
import Exceptions.EntryNotFoundException;
import dtos.request.CreateEntryRequest;
import dtos.request.UpdateEntryRequest;
import model.Entry;
import repositories.DiaryRepositoryImpl;
import repositories.EntryRepository;
import repositories.EntryRepositoryImpl;

import java.util.List;

public class EntryServicesImpl implements EntryService{
    private static EntryRepository Repository = new EntryRepositoryImpl();

    @Override
    public void createEntry(CreateEntryRequest request) {
        Entry entry = new Entry();
        entry.setTitle(request.getTitle());
        entry.setBody(request.getBody());
        entry.setAuthor(request.getAuthor().toLowerCase());
        Repository.save(entry);
    }

    @Override
    public void updateEntry(UpdateEntryRequest request) {
        Repository.findById(request.getId());
        Entry entry = Repository.findById(request.getId());
        if (entry == null) throw new NullPointerException("it doesnt exist");
        entry.setTitle(request.getTitle());
        entry.setBody(request.getBody());
        entry.setAuthor(request.getAuthor().toLowerCase());
        //entry.setId(request.getId());
        Repository.save(entry);

    }

    @Override
    public void save(Entry entry) {
    Repository.save(entry);
    }

    @Override
    public void deleteEntry(int id) {
        Entry entry = Repository.findById(id);
        if(entry == null)throw new EntryNotFoundException("Entry Not Found");
        Repository.delete(entry);
    }

    @Override
    public Entry getEntry(int id) {
        Entry entry = Repository.findById(id);
        if(entry == null)throw new EntryNotFoundException("Entry Not Found");
        return entry;
    }

    @Override
    public List<Entry> getEntries(String username) {
      List<Entry> entries = Repository.findByAuthor(username.toLowerCase());
      if(entries.isEmpty())throw new EmptyEntryListException("No Entry Found");
      return entries;
    }

    @Override
    public List<Entry> findAll() {
        return Repository.findAll();
    }

    @Override
    public long count() {
        return Repository.count();
    }


}
