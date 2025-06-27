package org.yearup.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
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
    @PutMapping
    public void updateProfile(Authentication auth, @RequestBody Profile profile) {
        String username = auth.getName();
        profileDao.update(username, profile);
    }
}
