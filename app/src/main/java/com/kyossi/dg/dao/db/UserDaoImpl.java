package com.kyossi.dg.dao.db;

import android.util.Log;

import com.kyossi.dg.R;
import com.kyossi.dg.architecture.custom.DApplicationContext;
import com.kyossi.dg.dao.entities.User;
import com.flys.generictools.dao.daoImpl.GenericDaoImpl;
import com.flys.generictools.dao.db.DatabaseHelper;
import com.j256.ormlite.dao.Dao;

import org.androidannotations.annotations.EBean;

import java.sql.SQLException;

@EBean(scope = EBean.Scope.Singleton)
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

    DatabaseHelper<User, Long> databaseHelper;

    @Override
    public Dao<User, Long> getDao() {
        databaseHelper = new DatabaseHelper(DApplicationContext.getContext(), R.raw.ormlite_config);
        try {
            return (Dao<User, Long>) databaseHelper.getDao(getEntityClassManaged());
        } catch (SQLException e) {
            Log.e(getClass().getSimpleName(), "getting Dao Exception!", e);
        }

        return null;
    }

    @Override
    public void flush() {
        if(databaseHelper.isOpen()){
            databaseHelper.close();
        }
    }

    @Override
    public Class<User> getEntityClassManaged() {
        return User.class;
    }
}
