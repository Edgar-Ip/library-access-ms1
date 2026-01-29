package com.example.library_access_ms1.repository;

import com.example.library_access_ms1.entity.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryUserRepository extends JpaRepository<LibraryUser, String> {
}
