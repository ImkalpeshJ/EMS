package com.employee.app.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employee.app.dao.CountryDao;
import com.employee.app.entity.Country;

@Repository
public class CountryDaoImpl implements CountryDao {

	@Autowired
	SessionFactory sf;

	@Override
	public List<Country> getAllCountry() {
		Session session = sf.openSession();
		List<Country> list = null;
		try {
			Criteria criteria = session.createCriteria(Country.class);
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public boolean postCountry(Country country) {
		Session session = null;
		boolean isAdded = false;
		try {
			session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(country);
			transaction.commit();
			isAdded = true;
		} catch (Exception e) {

		} finally {
			if (session != null) {
				session.close();
			}
		}
		return isAdded;
	}

	@Override
	public boolean updateCountry(Country country) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCountry(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Country getCountryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
