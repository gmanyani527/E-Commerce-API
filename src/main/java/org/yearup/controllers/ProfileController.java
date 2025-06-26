package org.yearup.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yearup.data.ProfileDao;
import org.yearup.models.Profile;

@RestController
@RequestMapping("/profile")
@CrossOrigin
public class ProfileController {
    private final ProfileDao profileDao;

    public ProfileController(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }
    @GetMapping
    public Profile getProfile(Authentication auth) {
        String username = auth.getName(); // comes from Spring Security
        return profileDao.getByUserName(username);
    }
}
