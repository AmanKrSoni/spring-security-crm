package com.cvt.security;

import com.cvt.model.Users;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Logger;


public class CustomPermissionEvaluator implements PermissionEvaluator{
    Logger logger= Logger.getLogger(getClass().getName());


    public CustomPermissionEvaluator() {
    logger.info("CustomPermissionEvaluator is called ....");
    }




    @Autowired
    private DataSource dataSource;




    @Override
    public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {

        logger.info(">>> hasPermission() is called ....");

        String con="";
        try {
            con=dataSource.getConnection()!=null?"ESTABLISHED ...":"NOT_ESTABLISHED";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info(">>DATASOURCE :"+con);

        logger.info(">>> Params :"+auth+" ,"+targetDomainObject+" ,"+permission);

        /*final String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();
        return hasPrivilege(auth, targetType, permission.toString().toUpperCase());*/

        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);

        logger.info(">>> JDBC_TEMPLATE :"+jdbcTemplate.toString());



        Object[] args={((UserDetails)auth.getPrincipal()).getUsername(),
                        targetDomainObject.getClass().getName(),
                        permission.toString().toUpperCase()
                        };

        logger.info(">>> Username :"+args[0]);
        logger.info(">>> TargetObject :"+args[1]);
        logger.info(">>> Permission :"+args[2]);

        String sql="select count(*) from permissions p where p.username=? and p.target=? and p.permission=?";
        int count=jdbcTemplate.queryForObject(sql,args,Integer.class);

        logger.info(">>> SQL Count :"+count);


        return count==1 ? true : false;
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable serializable, String targetType, Object permission) throws RuntimeException {
        if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        throw new UnsupportedOperationException("This is not implemented ....");
    }
}
