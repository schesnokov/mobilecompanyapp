package mobilecompany.services.unit;

import com.mobilecompany.dao.api.RoleDao;
import com.mobilecompany.dao.api.UserDao;
import com.mobilecompany.dto.UserDto;
import com.mobilecompany.entities.User;
import com.mobilecompany.services.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceUnitTest {

    @Mock
    private UserDao userDao;
    @Mock
    private RoleDao roleDao;

    private UserServiceImpl userService;

    @Before
    public void SetUp(){
        userService = new UserServiceImpl(userDao, roleDao);
    }

    @Test
    public void findById(){
        when(userDao.read(-1)).thenReturn(new User());
        UserDto userDto = userService.getUser(-1);
        assertNotNull(userDto);
    }

    @Test
    public void findByEmail(){
        when(userDao.getByEmail("test")).thenReturn(new User());
        UserDto userDto = userService.findByEmail("test");
        assertNotNull(userDto);
    }

    @Test
    public void findAllUsersTest(){
        when(userDao.findAllUsers()).thenReturn(new ArrayList<>());
        List<UserDto> userDtos = userService.getAllUsers();
        assertNotNull(userDtos);
    }
}
