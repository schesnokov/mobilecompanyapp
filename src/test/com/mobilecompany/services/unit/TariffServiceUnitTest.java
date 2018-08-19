package mobilecompany.services.unit;

import com.mobilecompany.dao.api.TariffDao;
import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.entities.Tariff;
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
public class TariffServiceUnitTest {
    @Mock
    private TariffDao tariffDao;
    @Mock
    private JmsTemplate jmsTemplate;

    private TariffServiceImpl tariffService;

    @Before
    public void SetUp() {
        tariffService = new TariffServiceImpl(tariffDao, jmsTemplate);
    }

    @Test
    public void getByIdTest(){
        when(tariffDao.read(-1)).thenReturn(new Tariff());
        TariffDto tariffDto = tariffService.getTariff(-1);
        assertNotNull(tariffDto);
    }

    @Test
    public void getAllTariffsTest(){
        when(tariffDao.findAllTariffs()).thenReturn(new ArrayList<>());
        List<TariffDto> tariffDtos = tariffService.getAllTariffs();
        assertNotNull(tariffDtos);
    }
}
