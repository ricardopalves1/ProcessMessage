package com.app.msg.process.pool;

import java.util.ArrayList;
import java.util.List;

import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.stereotype.Component;

import com.app.msg.process.domain.Sale;
import com.app.msg.process.service.ISalesService;

/**
 * Backlog thread worker reporting class.
 * 
 * @author ricardopalvesjr
 *
 */
@Component
public class BacklogWorker extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger(BacklogWorker.class);
	private volatile int lastReport;
	private volatile boolean shutdown = false;
	private int maxMsgNumber;
	private List<Sale> salesReport = null;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ApplicationContext context;

	@Autowired
	private JmsListenerEndpointRegistry endpointRegistry;

	@Autowired
	private ISalesService salesService;

	@Autowired
	private BrokerService broker;

	/**
	 * @param maxMsgNumber
	 *            the maxMsgNumber to set
	 */
	public void setMaxMsgNumber(int maxMsgNumber) {
		this.maxMsgNumber = maxMsgNumber;
	}

	/**
	 * Method to attempt stopping the Message Broker and the Application.
	 * 
	 * @throws Exception
	 *             Exception in case of failure.
	 */
	private void closedown() throws Exception {
		LOGGER.info(messageSource.getMessage("info.process.stop", null, null));
		broker.stop();

		LOGGER.info(messageSource.getMessage("info.process.halt", null, null));
		SpringApplication.exit(context, () -> 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (!shutdown) {
			int numberSales = salesService.getNumberAllSales();
			if (numberSales == maxMsgNumber) {
				LOGGER.info(messageSource.getMessage("info.process.pause", null, null));
				endpointRegistry.stop();
				shutdown = true;
			}

			if (numberSales % 10 == 0 && numberSales != lastReport) {
				LOGGER.info(messageSource.getMessage("info.message.count", new Object[] { numberSales }, null));

				lastReport = numberSales;
				salesReport = new ArrayList<>();
				salesReport.addAll(salesService.getAllsales());
				salesService.report10Sales(salesReport);
				if (numberSales % 50 == 0) {
					salesService.report50Sales(salesReport);
				}
			}
		}

		try {
			closedown();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
