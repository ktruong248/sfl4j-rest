package api.dao;

import api.domain.LogEntry;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogEntryRepository extends PagingAndSortingRepository<LogEntry, String> {
    List<LogEntry> findBySource(String source);
}
