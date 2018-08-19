package mobilecompany.services.unit;

import com.mobilecompany.dao.api.*;
import com.mobilecompany.dto.ContractDto;
import com.mobilecompany.entities.Contract;
import com.mobilecompany.services.impl.ContractServiceImpl;
import com.mobilecompany.services.impl.OptionServiceImpl;
import com.mobilecompany.services.impl.TariffServiceImpl;
import com.mobilecompany.services.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jms.core.JmsTemplate;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContractServiceUnitTest {
    @Mock
    private ContractDao contractDao;
    @Mock
    private UserDao userDao;
    @Mock
    private OptionDao optionDao;
    @Mock
    private RoleDao roleDao;
    @Mock
    private TariffDao tariffDao;
    @Mock
    private JmsTemplate jmsTemplate;

    private TariffServiceImpl tariffService;
    private UserServiceImpl userService;
    private OptionServiceImpl optionService;
    private ContractServiceImpl contractService;

    @Before
    public void setUp() {
        tariffService = new TariffServiceImpl(tariffDao, jmsTemplate);
        userService = new UserServiceImpl(userDao, roleDao);
        optionService = new OptionServiceImpl(optionDao, tariffService);
        contractService = new ContractServiceImpl(contractDao, optionDao, optionService, tariffService, userService);
    }

    @Test
    public void findByIdTest() {
        when(contractDao.read(-1)).thenReturn(new Contract());
        ContractDto contract = contractService.getContract(-1);
        assertNotNull(contract);
    }

    @Test
    public void findByPhoneTest() {
        when(contractDao.findByPhoneNumber("test")).thenReturn(new Contract());
        Contract contract = contractService.getContractByPhone("test");
        assertNotNull(contract);
    }
}
