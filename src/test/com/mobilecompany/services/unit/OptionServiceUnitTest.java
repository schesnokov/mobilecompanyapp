package mobilecompany.services.unit;


import com.mobilecompany.dao.api.OptionDao;
import com.mobilecompany.dao.api.TariffDao;
import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.entities.Option;
import com.mobilecompany.services.impl.OptionServiceImpl;
import com.mobilecompany.services.impl.TariffServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jms.core.JmsTemplate;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OptionServiceUnitTest {
    @Mock
    private OptionDao optionDao;
    @Mock
    private TariffDao tariffDao;
    @Mock
    private JmsTemplate jmsTemplate;

    private TariffServiceImpl tariffService;
    private OptionServiceImpl optionService;

    @Before
    public void setUp() {
        tariffService = new TariffServiceImpl(tariffDao, jmsTemplate);
        optionService = new OptionServiceImpl(optionDao, tariffService);
    }

    @Test
    public void findByIdTest() {
        when(optionDao.read(-1)).thenReturn(new Option());
        OptionDto optionDto = optionService.getOption(-1);
        assertNotNull(optionDto);
    }

    @Test
    public void findAllOptions() {
        when(optionDao.findAllOptions()).thenReturn(new ArrayList<>());
        List<OptionDto> optionDtos = optionService.getAllOptions();
        assertNotNull(optionDtos);
    }
}
