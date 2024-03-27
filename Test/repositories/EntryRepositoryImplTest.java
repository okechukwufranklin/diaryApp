package repositories;
import model.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EntryRepositoryImplTest {
    private Entry entry;
    private EntryRepositoryImpl entryRepo;
    @BeforeEach
    public void SetUp(){
        entryRepo = new EntryRepositoryImpl();
        entryRepo.findAll().clear();
        //entry = new Entry(1,"franklin","franklin","franklin");
    }
    @Test
    public void testToSaveToEntry(){
        entry = new Entry(1,"franklin","franklin","franklin");
        entryRepo.save(entry);
        assertEquals(1,entryRepo.count());
    }
    @Test
    public void testToFindAllEntry(){
        entry = new Entry(1,"franklin","franklin","franklin");
        Entry entry2 = new Entry(2,"franklin","franklin2","franklin");
        Entry entry3 = new Entry(3,"franklin","frank3","franklin");
        entryRepo.save(entry);
        entryRepo.save(entry2);
        entryRepo.save(entry3);
        assertEquals(3,entryRepo.count());
        List<Entry>allEntry = entryRepo.findAll();
        assertEquals(3,allEntry.size());
    }
    @Test
    public void testToFindEntryById(){
        entry = new Entry(1,"franklin","franklin","franklin");
        Entry entry2 = new Entry(2,"franklin2","franklin2","franklin");
        Entry entry3 = new Entry(3,"franklin3","frank3","franklin");
        entryRepo.save(entry);
        entryRepo.save(entry2);
        entryRepo.save(entry3);
        assertEquals(3,entryRepo.count());
        List<Entry>allEntry = entryRepo.findAll();
        assertEquals(3,allEntry.size());
        assertEquals("franklin",entryRepo.findById(1).getTitle());
    }
    @Test
    public void testToDeleteEnty(){
        entry = new Entry(1,"franklin","franklin","franklin");
        Entry entry2 = new Entry(2,"franklin2","franklin2","franklin");
        Entry entry3 = new Entry(3,"franklin3","frank3","franklin");
        entryRepo.save(entry);
        entryRepo.save(entry2);
        entryRepo.save(entry3);
        assertEquals(3,entryRepo.count());
      entryRepo.delete(entry);
      assertEquals(2,entryRepo.count());
    }
    @Test
    public void testToDeleteTwoOutOfThree(){
        entry = new Entry(1,"franklin","franklin","franklin");
        Entry entry2 = new Entry(2,"franklin2","franklin2","franklin");
        Entry entry3 = new Entry(3,"franklin3","frank3","franklin");
        entryRepo.save(entry);
        entryRepo.save(entry2);
        entryRepo.save(entry3);
        assertEquals(3,entryRepo.count());
        entryRepo.delete(entry);
        entryRepo.delete(entry3);
        assertEquals(1,entryRepo.count());
    }


}
