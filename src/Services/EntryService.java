package Services;

import dtos.request.CreateEntryRequest;
import dtos.request.UpdateEntryRequest;
import model.Entry;

import java.util.List;

public interface EntryService {
    void createEntry(CreateEntryRequest createEntryRequest);

    void updateEntry(UpdateEntryRequest updateEntryRequest);

    void save(Entry entry);
    void deleteEntry(int id);
    Entry getEntry(int id);
    List<Entry> getEntries(String username);

    List<Entry> findAll();

    long count();
}
