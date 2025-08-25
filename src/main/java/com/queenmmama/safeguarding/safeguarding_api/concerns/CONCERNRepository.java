package com.queenmmama.safeguarding.safeguarding_api.concerns;

import org.springframework.data.repository.ListCrudRepository;
import java.util.List;
import java.util.UUID;

public interface CONCERNRepository extends ListCrudRepository<CONCERN, UUID> {
    List<CONCERN> findByStudentName(String studentName);

}
