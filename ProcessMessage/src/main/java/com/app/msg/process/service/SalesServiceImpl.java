package com.app.msg.process.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.msg.process.domain.Adjustment;
import com.app.msg.process.domain.Product;
import com.app.msg.process.domain.Sale;
import com.app.msg.process.helper.ReportHelper;
import com.app.msg.process.utils.OperationEnum;

/**
 * Sales service class.
 * 
 * @author ricardopalvesjr
 *
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class SalesServiceImpl implements ISalesService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SalesServiceImpl.class);

	private List<Sale> allsales = new ArrayList<>();

	@Autowired
	private MessageSource messageSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.app.msg.process.service.ISalesService#addSale(com.app.msg.process.domain.
	 * Sale)
	 */
	@Override
	public void addSale(Sale sale) {
		this.allsales.add(sale);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.msg.process.service.ISalesService#getNumberAllSales()
	 */
	@Override
	public int getNumberAllSales() {
		return this.allsales.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.msg.process.service.ISalesService#report10Sales(java.util.List)
	 */
	@Override
	public void report10Sales(List<Sale> sales) {
		Map<Product, Integer> productUnits = ReportHelper.getMapProdTotalUnits(sales);

		LOGGER.info(messageSource.getMessage("info.report.canvas", null, null));
		LOGGER.info(messageSource.getMessage("info.report.before.adj", null, null));
		LOGGER.info(productUnits.toString());

		Map<Adjustment, Product> adjProducts = ReportHelper.getMapAdjProduct(sales);

		adjProducts.forEach((k1, v1) -> {
			productUnits.forEach((k2, v2) -> {
				if (k2.equals(v1)) {
					List<Sale> prodList = sales.stream().filter(p -> k2.getType().equals(p.getProduct().getType()))
							.collect(Collectors.toList());

					if (k1.getOperation().equalsIgnoreCase(OperationEnum.ADD.operation())) {
						v2 = OperationEnum.ADD.calculate(v2, k1.getValue() * prodList.size());
					}
					if (k1.getOperation().equalsIgnoreCase(OperationEnum.SUBTRACT.operation())) {
						v2 = OperationEnum.SUBTRACT.calculate(v2, k1.getValue() * prodList.size());
					}
					if (k1.getOperation().equalsIgnoreCase(OperationEnum.MULTIPLY.operation())) {
						v2 = OperationEnum.MULTIPLY.calculate(v2, k1.getValue());
					}

					productUnits.put(k2, v2);
				}
			});
		});

		LOGGER.info(messageSource.getMessage("info.report.canvas", null, null));
		LOGGER.info(messageSource.getMessage("info.report.after.adj", null, null));
		LOGGER.info(productUnits.toString());
		LOGGER.info(messageSource.getMessage("info.report.canvas", null, null));

		Map<Product, Long> productCounts = ReportHelper.getMapProdCounts(sales);

		LOGGER.info(messageSource.getMessage("info.report.number.sales", null, null));
		productCounts.forEach((k1, v1) -> {
			LOGGER.info(messageSource.getMessage("info.report.splitter", null, null));
			LOGGER.info(k1.getType());
			LOGGER.info(v1.toString());
		});
		LOGGER.info(messageSource.getMessage("info.report.canvas", null, null));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.msg.process.service.ISalesService#report50Sales(java.util.List)
	 */
	@Override
	public void report50Sales(List<Sale> sales) {
		Map<Adjustment, Product> adjProducts = ReportHelper.getMapAdjProduct(sales);
		Map<Product, Long> productCounts = ReportHelper.getMapProdCounts(sales);

		LOGGER.info(messageSource.getMessage("info.report.adjustments", null, null));

		productCounts.forEach((k1, v1) -> {
			LOGGER.info(messageSource.getMessage("info.report.splitter", null, null));
			LOGGER.info(k1.toString());
			adjProducts.forEach((k2, v2) -> {
				if (k1.equals(v2) && !k2.getOperation().isEmpty()) {
					LOGGER.info(k2.toString());
				}
			});
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.msg.process.service.ISalesService#getAllsales()
	 */
	@Override
	public List<Sale> getAllsales() {
		return allsales;
	}

}
