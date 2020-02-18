package com.techtrail.commons.auth.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;


import com.techtrail.commons.auth.dao.AuthenticationManagerDao;
import com.techtrail.commons.auth.dto.UserDto;
import com.techtrail.commons.auth.dto.UserEntityDto;
import com.techtrail.commons.auth.model.User;
import com.techtrail.commons.db.dao.GenericDao;


@Repository
@Transactional
public class AuthenticationManagerDaoImpl extends GenericDao implements  AuthenticationManagerDao {

	
	@Override
	public UserDto findUserById(String email, String pswd) {

		Session session = null;
		try {
			session = getSessionFactory().openSession();
			StringBuilder sql = new StringBuilder()
			.append("Select userId,email,mobile,name,password,status,userType FROM user WHERE email= :email AND status IN ('Active', 'active')  ");
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql.toString()).setResultTransformer(Transformers.aliasToBean(UserDto.class));
			query.setParameter("email", email);
			UserDto usersd = (UserDto) query.uniqueResult();
			return usersd;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException();
		} finally {
			GenericDao.close(session);
		}
	}
	
	@Override
	public UserEntityDto findUserEntityDetailsById(Integer userId) {
		Session session = null;
		try {
			session = getSessionFactory().getCurrentSession();
			session.beginTransaction();
			UserEntityDto user = (UserEntityDto) session.createSQLQuery("SELECT u.userid, u.usertype, u.entityid, "
					+ "(SELECT p.entitytype FROM pharmaentity p WHERE p.entityid = u.entityid) AS entitytype "+
						"FROM user u WHERE u.userid=:userid ")
					.setResultTransformer(Transformers
							.aliasToBean(UserEntityDto.class))
					.setInteger("userid", userId).uniqueResult();
			return user;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		} finally {
			GenericDao.close(session);
		}
	}
	
	@Override
	public List<User> findAllUser() {
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			@SuppressWarnings("unchecked")
			List<User> usersd = (List<User>) session.createQuery("FROM User").list();
			return usersd;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException();			
		} finally {
			GenericDao.close(session);
		}
	}
	
	@Override
	public User findUserByEmailId(String email) {
		Session session = null;
		try {
			session = getSessionFactory().getCurrentSession();
			session.beginTransaction();
			User user = (User) session.createQuery("from User where email = :emailId ")
			.setString("emailId", email).uniqueResult();
			return user;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		} finally {
			GenericDao.close(session);
		}
	}
	
	@Override
	public void updateUser(String email, String pswd) {
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			Query query = session.createSQLQuery( "UPDATE user SET password = :password WHERE email = :email" )
			.setParameter("password", pswd)
			.setParameter("email", email);
			query.executeUpdate();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;			
		} finally {
			//GenericDao.close(session);
		}
	}

	@Override
	public User getOtpForUser(String email) {
		if(email == null) 
			return null;
		Session session = null;
		StringBuilder sqlBuilder = new StringBuilder("SELECT otp FROM user WHERE email= '"+ email +"' ");
  
		try {			
			session=getSessionFactory().openSession();		
			
			Query query = session.createSQLQuery(sqlBuilder.toString())
					.addScalar("otp",StringType.INSTANCE);
			
			User details = (User) query.setResultTransformer(Transformers
					.aliasToBean(User.class)).uniqueResult();	
			return details;
			
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;			
		} finally {
			GenericDao.close(session);
		}
		
	}

	@Override
	public String verifyOtpForUserId(String otp, String email) {
		if(otp == null) 
			return null;
		Session session = null;
		StringBuilder sqlBuilder = new StringBuilder("SELECT email FROM user WHERE otp= :otp AND email = :email");
  
		try {			
			session=getSessionFactory().openSession();			
			Query query = session.createSQLQuery(sqlBuilder.toString())
					.addScalar("email",StringType.INSTANCE);
			
			User details = (User) query.setResultTransformer(Transformers
					.aliasToBean(User.class))
					.setString("otp", otp)
					.setString("email", email)
					.uniqueResult();	
			return (details == null ? null : details.getEmail());
			
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;			
		} finally {
			GenericDao.close(session);
		}
		
	}
	
	public void setNewOTP(String email, String otp){
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			Query query = session.createSQLQuery( "UPDATE user SET otp = :otp WHERE email = :email" )
			.setParameter("otp", otp)
			.setParameter("email", email);
			query.executeUpdate();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		} finally {
			//GenericDao.close(session);
		}
	}
	
	//////////
	
	@Override
	public User findUserByMobile(String mobileNo) {
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			User user = (User) session.createQuery("from User where mobile = :mobileNo ")
			.setString("mobileNo", mobileNo).uniqueResult();
			return user;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException();
		} finally {
			//GenericDao.close(session);
		}
	}
	
	public void updateOtpForUser(User employee, int otp) {
		if(employee == null) 
			return;
		Session session = null;
		try {			
			session = getSessionFactory().openSession();
			int status = session.createSQLQuery("UPDATE user SET otp = :otp WHERE userId = :empId ")
					.setInteger("otp", otp)
					.setInteger("empId", employee.getUserId())
					.executeUpdate();
		} catch (Exception ex){
			throw ex;
		} finally {
			//GenericDao.close(session);
		}
	}
	
	public Integer getOtpForEmployee(User employee) {
		if(employee == null) 
			return null;
		Session session = null;
		try {			
			session = getSessionFactory().openSession();
			Integer otp = (Integer) session.createSQLQuery("SELECT otp FROM user WHERE userId = :empId ")
					.setInteger("empId", employee.getUserId())
					.uniqueResult();
			return otp;
		} catch (Exception ex){
			throw ex;
		} finally {
			//GenericDao.close(session);
		}
	}
	
	/*@Override
	public UserEntityDto findUserEntityId(Integer userId) {
		Session session = null;
		try {
			session = getSessionFactory().getCurrentSession();
			session.beginTransaction();
			UserEntityDto user = (UserEntityDto) session.createSQLQuery("SELECT u.entityid, entitytype "
					+ " FROM userentitymap u join pharmaentity"
					+ " pe on u.entityid = pe.entityid where u.userid=:userid ")
					.setResultTransformer(Transformers
							.aliasToBean(UserEntityDto.class))
					.setInteger("userid", userId).uniqueResult();
			return user;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		} finally {
			GenericDao.close(session);
		}
	}*/

}
