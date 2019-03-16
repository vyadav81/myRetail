package com.myretail.dao.impl;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.myretail.common.constant.Constant;
import com.myretail.dao.PriceDao;
import com.myretail.model.product.CurrentPrice;

/**
 * Implementation of PriceDao
 * 
 * @author vyadav
 *
 */
@Repository
public class PriceDaoImpl implements PriceDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	Logger logger = LoggerFactory.getLogger(PriceDaoImpl.class);

	/**
	 * to get price of given product Id
	 */
	@Override
	public CurrentPrice findByProductId(Long productId) throws Exception {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where(Constant.ID).is(productId));
			CurrentPrice price = mongoTemplate.findOne(query,
					CurrentPrice.class);
			logger.debug("PriceDaoImpl.findByProductId return price : "
					+ price.toString());
			return price;
		} catch (DataAccessResourceFailureException e) {
			throw new Exception(Constant.CONNECTIONISSUE);
		} catch (Exception ex) {
			throw new Exception(Constant.PRODUCTNOTFOUND);
		}

	}

	/**
	 * To update price of given product Id
	 */
	@Override
	public String updateProductPrice(CurrentPrice cp) throws Exception {

		try {
			Query query = new Query();
			query.addCriteria(Criteria.where(Constant.ID).is(cp.getProductId()));
			CurrentPrice price = mongoTemplate.findOne(query,
					CurrentPrice.class);
			cp.setId(price == null ? new ObjectId() : price.getId());
			mongoTemplate.save(cp);
			logger.debug("PriceDaoImpl.updateProductPrice price update successfully");
			return Constant.SUCCESSSTATUS;
		} catch (DataAccessResourceFailureException e) {
			throw new Exception(Constant.CONNECTIONISSUE);
		} catch (Exception ex) {
			throw new Exception(Constant.UPDATEEROR);
		}
	}
}