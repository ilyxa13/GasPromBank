package ru.glinskiy.gazprombank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.glinskiy.gazprombank.dao.entity.List;

@Repository
public interface ListRepository extends JpaRepository<List, Long> {
}
