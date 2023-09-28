package ru.goloviy.profileservice.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.goloviy.profileservice.convertor.UserConvertor;
import ru.goloviy.profileservice.dto.UserDto;
import ru.goloviy.profileservice.exception.FriendAlreadyException;
import ru.goloviy.profileservice.exception.FriendException;
import ru.goloviy.profileservice.exception.FriendNotFoundException;
import ru.goloviy.profileservice.exception.ImpossibleFriendException;
import ru.goloviy.profileservice.model.User;
import ru.goloviy.profileservice.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)

public class FriendServiceImplTest {
    @Mock
    private UserConvertor userConvertor;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private FriendServiceImpl friendService;
    private User user1;
    private User user2;
    @BeforeEach
    public void initUsers(){
        user1 = new User();
        user1.setId(1l);
        user1.setFriendsRequest(new HashSet<>());
        user1.setFriends(new HashSet<>());

        user2 = new User();
        user2.setFriendsRequest(new HashSet<>());
        user2.setFriends(new HashSet<>());
        user2.setId(2l);
    }

    @Test
    public void successInviteToFriend(){
        Mockito.when(userRepository.save(user1)).thenReturn(user1);
        Assertions.assertDoesNotThrow(() -> friendService.inviteToFriend(user1, user2));
        Assertions.assertEquals(user1.getFriendsRequest(), Set.of(user2));
    }
    @Test
    public void inviteWhoAlreadyFriend(){
        user1.setFriends(Set.of(user2));
        Assertions.assertThrows(FriendAlreadyException.class, () -> friendService.inviteToFriend(user1, user2));
    }
    @Test
    public void inviteYourself(){
        Assertions.assertThrows(ImpossibleFriendException.class, () -> friendService.inviteToFriend(user1, user1));
    }
    @Test
    public void successCancelInviteRequest(){
        user1.getFriendsRequest().add(user2);
        Assertions.assertDoesNotThrow(() ->  friendService.cancelInviteToFriend(user1, user2));
        Assertions.assertEquals(0, user1.getFriendsRequest().size());
    }
    @Test
    public void cancelInviteWhenNotHaveInviteRequest(){
        Assertions.assertThrows(FriendException.class, () -> friendService.cancelInviteToFriend(user1, user2));
    }
    @Test
    public void cancelInviteFromYourself(){
        Assertions.assertThrows(ImpossibleFriendException.class, () -> friendService.cancelInviteToFriend(user1, user1));
    }
    @Test
    public void cancelInviteFromFriend(){
        user1.getFriends().add(user2);
        user2.getFriends().add(user1);
        Assertions.assertThrows(FriendAlreadyException.class, () -> friendService.cancelInviteToFriend(user1, user2));
    }
    @Test
    public void successAcceptToFriend(){
        user1.getFriendsRequest().add(user2);
        Assertions.assertDoesNotThrow(() -> friendService.acceptToFriend(user2, user1));
        Assertions.assertEquals(Set.of(user2), user1.getFriends());
        Assertions.assertEquals(Set.of(user1), user2.getFriends());
        Assertions.assertEquals(0, user1.getFriendsRequest().size());
        Assertions.assertEquals(0, user2.getFriendsRequest().size());
    }
    @Test
    public void acceptToFriendWhenNoFriendRequest(){
        Assertions.assertThrows(FriendException.class, () -> friendService.acceptToFriend(user1, user2));
    }
    @Test
    public void successGetFriends(){
        user1.getFriends().add(user2);
        UserDto expected = new UserDto();
        expected.setId(user2.getId());
        Mockito.when(userConvertor.toUserDto(Set.of(user2))).thenReturn(List.of(expected));
        Assertions.assertEquals(List.of(expected), friendService.getFriends(user1));
    }
    @Test
    public void getEmptyFriendsListDoesNotThrow(){
        Assertions.assertDoesNotThrow(() -> friendService.getFriends(user1));
    }
    @Test
    public void successDeleteFriend(){
        user1.getFriends().add(user2);
        user2.getFriends().add(user1);
        Assertions.assertDoesNotThrow(()-> friendService.deleteFriend(user1, user2));
        Assertions.assertEquals(0, user1.getFriends().size());
        Assertions.assertEquals(0, user2.getFriends().size());
    }
    @Test
    public void deleteWhenNotFriends(){
        Assertions.assertThrows(FriendNotFoundException.class, () -> friendService.deleteFriend(user1, user2));
    }
}
