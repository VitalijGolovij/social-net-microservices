package ru.goloviy.profileservice.service;

import ru.goloviy.profileservice.dto.UserDto;
import ru.goloviy.profileservice.model.User;

import java.util.List;


public interface FriendService {

    public List<UserDto> getFriends(User principalUser);
    public void deleteFriend(User userFrom, User userTo);
    public void inviteToFriend(User userFrom, User userTo);
    public void acceptToFriend(User userFrom, User userTo);
    public void cancelInviteToFriend(User userFrom, User userTo);
    public List<UserDto> getFriendRequests(User principalUser);
}
