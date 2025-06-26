package org.yearup.data;


import org.yearup.models.Profile;

public interface ProfileDao
{
    Profile create(Profile profile);
    Profile getByUserName(String username);
    void update(String username, Profile profile);
}
