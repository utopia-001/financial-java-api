package io.github.mariazevedo88.financialjavaapi.test.controller.statistic;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import io.github.mariazevedo88.financialjavaapi.model.statistic.Statistic;
import io.github.mariazevedo88.financialjavaapi.service.statistic.StatisticService;

/**
 * Class that implements tests of the StatisticController funcionalities
 * 
 * @author Mariana Azevedo
 * @since 05/04/2020
 */
@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class StatisticControllerTest {
	
	private static final Long ID = 1L;
	private static final BigDecimal SUM = BigDecimal.valueOf(500);
	private static final BigDecimal AVG = BigDecimal.valueOf(125);
	private static final BigDecimal MIN = BigDecimal.valueOf(50);
	private static final BigDecimal MAX = BigDecimal.valueOf(200);
	private static final long COUNT = 4L;
	private static final String URL = "/financial/v1/statistics";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StatisticService service;
	
	/**
	 * Method that tests to save an Statistic in the API
	 * 
	 * @author Mariana Azevedo
	 * @since 05/04/2020
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSave() throws Exception {
		
		BDDMockito.given(service.save(Mockito.any(Statistic.class))).willReturn(getMockStatistic());
		
		mockMvc.perform(MockMvcRequestBuilders.get(URL).contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.data.id").value(ID))
		.andExpect(jsonPath("$.data.sum").value(SUM))
		.andExpect(jsonPath("$.data.avg").value(AVG))
		.andExpect(jsonPath("$.data.max").value(MAX))
		.andExpect(jsonPath("$.data.min").value(MIN))
		.andExpect(jsonPath("$.data.count").value(COUNT));
	}
	
	/**
	 * Method that fill a mock Statistic to use as return in the tests.
	 * 
	 * @author Mariana Azevedo
	 * @since 05/04/2020
	 * 
	 * @return <code>Statistic</code> object
	 */
	private Statistic getMockStatistic() {
		
		Statistic statistic = new Statistic();
		statistic.setId(ID);
		statistic.setSum(SUM);
		statistic.setAvg(AVG);
		statistic.setMin(MIN);
		statistic.setMax(MAX);
		statistic.setCount(COUNT);
		
		return statistic;
	}

}
