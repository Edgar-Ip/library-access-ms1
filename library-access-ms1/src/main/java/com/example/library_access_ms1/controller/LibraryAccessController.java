package com.example.library_access_ms1.controller;


import com.example.library_access_ms1.constants.LibraryConstants;
import com.example.library_access_ms1.entity.LibraryUser;
import com.example.library_access_ms1.service.LibraryAccessService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryAccessController {

    private final LibraryAccessService service;

    public LibraryAccessController(LibraryAccessService service) {
        this.service = service;
    }

    @PostMapping("/entry/{userCode}")
    public void enterLibrary(@PathVariable String userCode) {
        service.admitUserIntoLibrary(userCode);
    }

    @PostMapping("/exit/{userCode}")
    public void exitLibrary(@PathVariable String userCode) {
        service.releaseUserFromLibrary(userCode);
    }

    @GetMapping("/users")
    public List<LibraryUser> getUsers(
            @RequestHeader(
                    value = LibraryConstants.HEADER_DELAY_FLAG,
                    required = false
            )
            String delayFlag
    ) {
        boolean applyDelay = "true".equalsIgnoreCase(delayFlag);
        return service.getCurrentUsers(applyDelay);
    }
}
