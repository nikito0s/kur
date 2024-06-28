package baranow.polikek.kursach.service;

import baranow.polikek.kursach.exceptions.UsernameAlreadyExistsException;
import baranow.polikek.kursach.model.User;
import baranow.polikek.kursach.model.UserAuthority;
import baranow.polikek.kursach.model.UserRole;
import baranow.polikek.kursach.repository.UserRepository;
import baranow.polikek.kursach.repository.UserRolesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRolesRepository userRolesRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registration_success() {
        String username = "testuser";
        String password = "testpassword";
        String encodedPassword = "encodedPassword";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        userService.registration(username, password);

        verify(userRepository, times(1)).findByUsername(username);
        verify(passwordEncoder, times(1)).encode(password);
        verify(userRepository, times(1)).save(any(User.class));
        verify(userRolesRepository, times(1)).save(any(UserRole.class));
    }

    @Test
    void registration_usernameAlreadyExists() {
        String username = "testuser";
        String password = "testpassword";

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(new User()));

        assertThrows(UsernameAlreadyExistsException.class, () -> {
            userService.registration(username, password);
        });

        verify(userRepository, times(1)).findByUsername(username);
        verify(passwordEncoder, times(0)).encode(password);
        verify(userRepository, times(0)).save(any(User.class));
        verify(userRolesRepository, times(0)).save(any(UserRole.class));
    }

    @Test
    void loadUserByUsername_userFound() {
        String username = "testuser";
        User user = new User();
        user.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void loadUserByUsername_userNotFound() {
        String username = "testuser";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername(username);
        });

        verify(userRepository, times(1)).findByUsername(username);
    }
}
