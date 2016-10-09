package com.ilovelonely.guoshiqi.dribbbleu.Helper;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.ilovelonely.guoshiqi.dribbbleu.frameWork.OwApplication;

import java.util.List;

import de.greenrobot.daoexample.Customer;
import de.greenrobot.daoexample.CustomerDao;
import de.greenrobot.daoexample.DaoSession;

/**
 * Created by guoshiqi on 16/6/3.
 */
public class DbService {
    private static final String TAG = DbService.class.getSimpleName();
    private static DbService instance;
    private static Context appContext;
    private DaoSession mDaoSession;
    private CustomerDao customerDao;


    private DbService() {
    }

    /**
     * 采用单例模式
     * @param context     上下文
     * @return            dbservice
     */
    public static DbService getInstance(Context context) {
        if (instance == null) {
            instance = new DbService();
            if (appContext == null){
                appContext = context.getApplicationContext();
            }
            instance.mDaoSession = OwApplication.getDaoSession(context);
            instance.customerDao = instance.mDaoSession.getCustomerDao();
        }
        return instance;
    }

    /**
     * 根据用户id,取出用户信息
     * @param id           用户id
     * @return             用户信息
     */
    public Customer loadNote(long id) {
        if(!TextUtils.isEmpty(id + "")) {
            return customerDao.load(id);
        }
        return  null;
    }

    /**
     * 取出所有数据
     * @return      所有数据信息
     */
    public List<Customer> loadAllNote(){
        return customerDao.loadAll();
    }

    /**
     * 生成按id倒排序的列表
     * @return      倒排数据
     */
    public List<Customer> loadAllNoteByOrder()
    {
        return customerDao.queryBuilder().orderDesc(CustomerDao.Properties.Id).list();
    }

    /**
     * 根据查询条件,返回数据列表
     * @param where        条件
     * @param params       参数
     * @return             数据列表
     */
    public List<Customer> queryNote(String where, String... params){
        return customerDao.queryRaw(where, params);
    }


    /**
     * 根据用户信息,插件或修改信息
     * @param user              用户信息
     * @return 插件或修改的用户id
     */
    public long saveNote(Customer user){
        return customerDao.insertOrReplace(user);
    }


    /**
     * 批量插入或修改用户信息
     * @param list      用户信息列表
     */
    public void saveNoteLists(final List<Customer> list){
        if(list == null || list.isEmpty()){
            return;
        }
        customerDao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<list.size(); i++){
                    Customer user = list.get(i);
                    customerDao.insertOrReplace(user);
                }
            }
        });

    }

    /**
     * 删除所有数据
     */
    public void deleteAllNote(){
        customerDao.deleteAll();
    }

    /**
     * 根据id,删除数据
     * @param id      用户id
     */
    public void deleteNote(long id){
        customerDao.deleteByKey(id);
        Log.i(TAG, "delete");
    }

    /**
     * 根据用户类,删除信息
     * @param user    用户信息类
     */
    public void deleteNote(Customer user){
        customerDao.delete(user);
    }
}