package com.ms.library.repository;

import com.ms.library.model.Borrows;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrows, Long>{
}
