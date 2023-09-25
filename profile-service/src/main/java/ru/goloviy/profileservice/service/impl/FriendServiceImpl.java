package ru.goloviy.profileservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.goloviy.profileservice.convertor.UserConvertor;
import ru.goloviy.profileservice.dto.UserDto;
import ru.goloviy.profileservice.exception.FriendAlreadyException;
import ru.goloviy.profileservice.exception.FriendException;
import ru.goloviy.profileservice.exception.FriendNotFoundException;
import ru.goloviy.profileservice.exception.ImpossibleFriendException;
import ru.goloviy.profileservice.model.User;
import ru.goloviy.profileservice.repository.UserRepository;
import ru.goloviy.profileservice.service.FriendService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final UserConvertor dtoConvertor;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void inviteToFriend(User userFrom, User userTo){
        validateFriendRequest(userFrom, userTo);

        if (isAlreadyInvite(userTo, userFrom)){
            acceptToFriend(userFrom, userTo);
            return;
        }
        userFrom.getFriendsRequest().add(userTo);

        userRepository.save(userFrom);
    }

    @Override
    @Transactional
    public void cancelInviteToFriend(User userFrom, User userTo){
        validateFriendRequest(userFrom, userTo);

        if (isAlreadyInvite(userFrom, userTo)){
            userFrom.getFriendsRequest().remove(userTo);
            userRepository.save(userFrom);
        } else {
            throw new FriendException("The friend invitation was not found");
        }
    }

    @Override
    @Transactional
    public void acceptToFriend(User userFrom, User userTo) {
        validateFriendRequest(userFrom, userTo);

        if (!isAlreadyInvite(userTo, userFrom)){
            throw new FriendException("The friend invitation was not found");
        }
        userTo.getFriendsRequest().remove(userFrom);

        userFrom.getFriends().add(userTo);
        userTo.getFriends().add(userFrom);

        userRepository.saveAll(Arrays.asList(userFrom, userTo));
    }

    @Override
    public List<UserDto> getFriends(User principalUser) {
        Set<User> friends = principalUser.getFriends();
        return dtoConvertor.toUserDto(friends);
    }

    @Override
    @Transactional
    public void deleteFriend(User userFrom, User userTo) {
        if (isAlreadyFriend(userFrom, userTo)){
            userFrom.getFriends().remove(userTo);
            userTo.getFriends().remove(userFrom);

            userRepository.saveAll(Arrays.asList(userFrom, userTo));
        } else {
            throw new FriendNotFoundException(userFrom.getId(), userTo.getId());
        }
    }

    @Override
    public List<UserDto> getFriendRequests(User principalUser){
        List<User> friendRequests = userRepository.findByFriendsRequestContaining(principalUser);
        return dtoConvertor.toUserDto(friendRequests);
    }

    private boolean isAlreadyFriend(User userFrom, User userTo){
        return userFrom.getFriends().contains(userTo);
    }

    private boolean isAlreadyInvite(User userFrom, User userTo) {
        return userFrom.getFriendsRequest().contains(userTo);
    }

    private void validateFriendRequest(User userFrom, User userTo){
        if (userFrom.equals(userTo)) {
            throw new ImpossibleFriendException();
        }
        if (isAlreadyFriend(userFrom, userTo)){
            throw new FriendAlreadyException(userFrom.getId(), userTo.getId());
        }
    }
}
