package io.github.mariazevedo88.financialjavaapi.test.service.statistic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import io.github.mariazevedo88.financialjavaapi.model.v1.statistic.Statistic;
import io.github.mariazevedo88.financialjavaapi.repository.statistic.StatisticRepository;
import io.github.mariazevedo88.financialjavaapi.service.v1.statistic.StatisticService;

/**
 * Class that implements tests of the StatisticService funcionalities.
 * 
 * @author Mariana Azevedo
 * @since 05/04/2020
 */
@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class StatisticServiceTest {
	
	private static final BigDecimal SUM = BigDecimal.valueOf(500);
	private static final BigDecimal AVG = BigDecimal.valueOf(125);
	private static final BigDecimal MIN = BigDecimal.valueOf(50);
	private static final BigDecimal MAX = BigDecimal.valueOf(200);
	private static final long COUNT = 4L;
	
	@Autowired
	private StatisticService service;

	@MockBean
	private StatisticRepository repository;
	
	/**
	 * Method that test the service that save a Wallet and a WalletItem in the API.
	 * 
	 * @author Mariana Azevedo
	 * @since 24/03/2020
	 */
	@Test
	public void testSave() {
		
		BDDMockito.given(repository.save(Mockito.any(Statistic.class)))
			.willReturn(getMockStatistic());
		Statistic response = service.save(new Statistic());
		
		assertNotNull(response);
		assertEquals(SUM, response.getSum());
		assertEquals(AVG, response.getAvg());
		assertEquals(MIN, response.getMin());
		assertEquals(MAX, response.getMax());
		assertEquals(COUNT, response.getCount());
	}
	
	/**
	 * Method that fill a mock of a Statistic to use as return in the tests.
	 * 
	 * @author Mariana Azevedo
	 * @since 05/04/2020
	 * 
	 * @return <code>Statistic</code> object
	 */
	private Statistic getMockStatistic() {
		
		Statistic statistic = new Statistic(1L, SUM, AVG, MAX, MIN, COUNT);
		return statistic;
	}

}
