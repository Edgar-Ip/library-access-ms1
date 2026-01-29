package com.example.library_access_ms1;

import com.example.library_access_ms1.repository.LibraryUserRepository;
import com.example.library_access_ms1.service.LibraryAccessService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class LibraryAccessServiceTest {

    @Mock
    private LibraryUserRepository repository;

    @InjectMocks
    private LibraryAccessService service;

    @Test
    void shouldApplyDelayWhenFlagIsTrue(){
        when(repository.findAll()).thenReturn(Collections.emptyList());

        long start = System.currentTimeMillis();
        service.getCurrentUsers(true);

        long elapsed = System.currentTimeMillis() - start;

        assertTrue(elapsed >= 7000);
    }
}
