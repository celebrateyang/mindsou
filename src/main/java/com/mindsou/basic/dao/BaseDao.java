package com.mindsou.basic.dao;

import model.Pager;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import model.SystemContext;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

/**
 * Created by 竹庆 on 2016/9/20.
 */
@SuppressWarnings("unchecked")
public class BaseDao<T> implements IBaseDao<T> {

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#add(java.lang.Object)
     */
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Inject
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 创建一个Class的对象来获取泛型的class
     */
    private Class<?> clz;

    public Class<?> getClz() {
        if(clz==null) {
            //获取泛型的Class对象
            clz = ((Class<?>)
                    (((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
        }
        return clz;
    }

    private String initSort(String hql){
        String order = SystemContext.getOrder();
        String sort = SystemContext.getSort();
        if(sort!=null && !"".equals(sort.trim())){
            hql+= " order by "+sort;
            if(!"desc".equals(order)) hql+= " asc";
            else hql+=" desc";
        }
        return hql;
    }
    @SuppressWarnings("rawtypes")
    private void setAlias(Query query,Map<String, Object> alias){
        if(alias!=null){
            Set<String> keys = alias.keySet();
            for(String key:keys){
                Object val = alias.get(key);
                if(val instanceof Collection){
                    query.setParameterList(key, (Collection)val);
                }else {
                    query.setParameter(key, val);
                }
            }
        }
    }

    private void setParameters(Query query,Object[] args){
        if(args!=null&&args.length>0){
            int index = 0;
            for(Object arg:args){
                query.setParameter(index++,arg);
            }
        }
    }

    @Override
    public T add(T t) {
        getSession().save(t);
        return t;
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#update(java.lang.Object)
     */
    @Override
    public void update(T t) {
        getSession().update(t);
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#delete(int)
     */
    @Override
    public void delete(int id) {
        getSession().delete(this.load(id));
    }

	/* (non-Javadoc)
	 * @see com.haobao.user.dao.IBaseDao#load(int)
	 */

    @Override
    public T load(int id) {
        return (T)getSession().load(getClz(), id);
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#list(java.lang.String, java.lang.String[])
     */
    @Override
    public List<T> list(String hql, Object[] args) {

        return this.list(hql, args, null);
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#list(java.lang.String, java.lang.String)
     */
    @Override
    public List<T> list(String hql, Object arg) {
        return this.list(hql, new Object[]{arg});
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#list(java.lang.String)
     */
    @Override
    public List<T> list(String hql) {
        return this.list(hql, null,null);
    }


    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#list(java.lang.String, java.lang.String[], java.util.Map)
     */
    @Override
    public List<T> list(String hql, Object[] args, Map<String, Object> alias) {
        hql = initSort(hql);//设置升降序
        Query query = getSession().createQuery(hql);
        setAlias(query, alias);//设置别名查询参数
        setParameters(query, args);//设置查询参数

        return query.list();
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#list(java.lang.String, java.util.Map)
     */
    @Override
    public List<T> list(String hql, Map<String, Object> alias) {
        return this.list(hql, null, alias);
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#find(java.lang.String, java.lang.String[])
     */
    @Override
    public Pager<T> find(String hql, Object[] args) {
        return this.find(hql, args, null);
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#find(java.lang.String, java.lang.String)
     */
    @Override
    public Pager<T> find(String hql, Object arg) {
        return this.find(hql,new Object[]{arg});
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#find(java.lang.String)
     */
    @Override
    public Pager<T> find(String hql) {
        return this.find(hql, null,null);
    }

    @SuppressWarnings("rawtypes")
    private void setPagers(Query query,Pager pages){
        Integer pageSize = SystemContext.getPageSize();
        Integer pageOffset = SystemContext.getPageOffset();
        if(pageOffset==null || pageOffset==0){
            pageOffset = 0;
        }
        if(pageSize==null || pageSize==0){
            pageSize = 15;
        }
        pages.setOffset(pageOffset);
        pages.setSize(pageSize);
        query.setFirstResult(pageOffset).setMaxResults(pageSize);

    }

    private String getCountSql(String hql,boolean isHql){
        String s1 =  hql.substring(hql.indexOf("from"));
        String countsql = "select count(*) "+s1;
        if(isHql)
        {
            countsql.replaceAll("fetch","");
        }
        return countsql;
    }
    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#find(java.lang.String, java.lang.String[], java.util.Map)
     */
    @Override
    public Pager<T> find(String hql, Object[] args, Map<String, Object> alias) {
        String countSql = getCountSql(hql,true);

        hql = initSort(hql);
        countSql = initSort(countSql);

        Query countQuery = getSession().createQuery(countSql);
        Query query = getSession().createQuery(hql);

        setAlias(query, alias);
        setAlias(countQuery, alias);

        setParameters(query, args);
        setParameters(countQuery, args);

        Pager<T> pages  = new Pager<T>();
        setPagers(query,pages);
        List<T> datas = query.list();
        long total = (Long)countQuery.uniqueResult();
        pages.setDatas(datas);
        pages.setTotal(total);
        return pages;
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#find(java.lang.String, java.util.Map)
     */
    @Override
    public Pager<T> find(String hql, Map<String, Object> alias) {
        return this.find(hql, null, alias);
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#queryObject(java.lang.String, java.lang.String[])
     */
    @Override
    public Object queryObject(String hql, Object[] args) {
        return this.queryObject(hql, args, null);
    }

    @Override
    public Object queryObject(String hql, Object[] args, Map<String, Object> alias) {
        Query query = getSession().createQuery(hql);
        setAlias(query, alias);
        setParameters(query, args);
        return query.uniqueResult();
    }

    @Override
    public Object queryObject(String hql, Map<String, Object> alias) {
        return this.queryObject(hql, null, alias);
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#queryObject(java.lang.String, java.lang.String)
     */
    @Override
    public Object queryObject(String hql, Object arg) {
        return this.queryObject(hql, new Object[]{arg});
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#queryObject(java.lang.String)
     */
    @Override
    public Object queryObject(String hql) {
        return this.queryObject(hql,null,null);
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#updateByHql(java.lang.String, java.lang.String[])
     */
    @Override
    public void updateByHql(String hql, Object[] args) {

        Query query = getSession().createQuery(hql);
        setParameters(query, args);
        query.executeUpdate();
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#updateByHql(java.lang.String, java.lang.String)
     */
    @Override
    public void updateByHql(String hql, Object arg) {
        this.updateByHql(hql,new Object[]{arg});
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#updateByHql(java.lang.String)
     */
    @Override
    public void updateByHql(String hql) {
        Query query = getSession().createQuery(hql);
        query.executeUpdate();
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#listBySql(java.lang.String, java.lang.Object[], java.lang.Class, boolean)
     */
    @Override
    public <N extends Object>List<N> listBySql(String sql, Object[] args, Class<?> clazz, boolean hasEntity) {
//		<N extends Object>List<N> s = new Array<N extends Object>List<N>();
//		s.add("");
//		return s;
        return this.listBySql(sql,args,clazz,null,hasEntity);
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#listBySql(java.lang.String, java.lang.Object, java.lang.Class, boolean)
     */
    @Override
    public <N extends Object>List<N> listBySql(String sql, Object arg, Class<?> clazz, boolean hasEntity) {
        return this.listBySql(sql,new Object[]{arg}, clazz, hasEntity);
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#listBySql(java.lang.String, java.lang.Class, boolean)
     */
    @Override
    public <N extends Object>List<N> listBySql(String sql, Class<?> clazz, boolean hasEntity) {

        return this.listBySql(sql, null,clazz, hasEntity);
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#listBySql(java.lang.String, java.lang.Object[], java.lang.Class, java.util.Map, boolean)
     */
    @Override
    public <N extends Object>List<N> listBySql(String sql, Object[] args, Class<?> clazz, Map<String, Object> alias, boolean hasEntity) {
        sql = initSort(sql);
        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        setAlias(sqlQuery, alias);
        setParameters(sqlQuery, args);
        if(hasEntity){
            sqlQuery.addEntity(clazz);
        }else{
            sqlQuery.setResultTransformer(Transformers.aliasToBean(clazz));
        }
        return sqlQuery.list();
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#listBySql(java.lang.String, java.lang.Class, java.util.Map, boolean)
     */
    @Override
    public <N extends Object>List<N> listBySql(String sql, Class<?> clazz, Map<String, Object> alias, boolean hasEntity) {
        return this.listBySql(sql,null,clazz,alias, hasEntity);
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#findBySql(java.lang.String, java.lang.Object[], java.lang.Class, boolean)
     */
    @Override
    public <N extends Object>Pager<N> findBySql(String sql, Object[] args, Class<?> clazz, boolean hasEntity) {

        return this.findBySql(sql,args, clazz, null, hasEntity);
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#findBySql(java.lang.String, java.lang.Object, java.lang.Class, boolean)
     */
    @Override
    public <N extends Object>Pager<N> findBySql(String sql, Object arg, Class<?> clazz, boolean hasEntity) {
        return this.findBySql(sql,new Object[]{arg}, clazz, hasEntity);
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#findBySql(java.lang.String, java.lang.Class, boolean)
     */
    @Override
    public <N extends Object>Pager<N> findBySql(String sql, Class<?> clazz, boolean hasEntity) {
        return this.findBySql(sql,null, clazz, hasEntity);
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#findBySql(java.lang.String, java.lang.Object[], java.lang.Class, java.util.Map, boolean)
     */
    @Override
    public <N extends Object>Pager<N> findBySql(String sql, Object[] args, Class<?> clazz, Map<String, Object> alias, boolean hasEntity) {
        String countSql = getCountSql(sql,false);
        countSql = initSort(countSql);
        sql = initSort(sql);

        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        SQLQuery countQuery = getSession().createSQLQuery(countSql);

        setAlias(countQuery, alias);
        setAlias(sqlQuery, alias);

        setParameters(countQuery, args);
        setParameters(sqlQuery, args);

        Pager<N> pages =  new Pager<N>();
        setPagers(sqlQuery, pages);

        if(hasEntity){
            sqlQuery.addEntity(clazz);
        }else{
            sqlQuery.setResultTransformer(Transformers.aliasToBean(clazz));
        }
        List<N> datas = sqlQuery.list();
        pages.setDatas(datas);
        long total = ((BigInteger)countQuery.uniqueResult()).longValue();
        pages.setTotal(total);
        return pages;
    }

    /* (non-Javadoc)
     * @see com.haobao.user.dao.IBaseDao#findBySql(java.lang.String, java.lang.Class, java.util.Map, boolean)
     */
    @Override
    public <N extends Object> Pager<N> findBySql(String sql, Class<?> clazz, Map<String, Object> alias, boolean hasEntity) {
        return this.findBySql(sql,null,clazz, alias,hasEntity);
    }



}

