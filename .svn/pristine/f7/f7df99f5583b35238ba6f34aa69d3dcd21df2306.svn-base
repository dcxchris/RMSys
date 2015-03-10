// Source File Name:   DAOImpl.java

package com.ctgu.dao.impl;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.ctgu.dao.DAO;
import com.ctgu.util.FlipPageInfo;
import com.ctgu.util.ProcParameter;
import com.ctgu.util.ProcUtil;
import com.ctgu.util.StringUtil;

@Repository("DAO")
public class DAOImpl implements DAO
{

    public DAOImpl()
    {
    }

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public void evict(Object beo)
    {
        getSession().evict(beo);
    }

    public void flush()
    {
        getSession().flush();
        getSession().clear();
    }

    public boolean exists(Class clazz, Serializable pk)
    {
        String idName = sessionFactory.getClassMetadata(clazz).getIdentifierPropertyName();
        String className = clazz.getName().substring(clazz.getPackage().getName().length());
        if(className.indexOf(".") == 0)
            className = className.substring(1);
        String hql = (new StringBuilder("select count(t) from ")).append(className).append(" t where ").append(idName).append("=?").toString();
        Query query = getSession().createQuery(hql);
        query.setParameter(0, pk);
        Object o = query.uniqueResult();
        return Integer.valueOf(o.toString()).intValue() > 0;
    }

    public int max(Class clazz, String property)
    {
        String className = clazz.getName().substring(clazz.getPackage().getName().length());
        if(className.indexOf(".") == 0)
            className = className.substring(1);
        String hql = (new StringBuilder("select max(")).append(property).append(") from ").append(className).append(" t").toString();
        Query query = getSession().createQuery(hql);
        Object o = query.uniqueResult();
        return o != null ? Integer.valueOf(o.toString()).intValue() : 0;
    }

    public void delete(Object beo)
    {
        if(beo != null)
            getSession().delete(beo);
    }

    public void delete(Class beoCls, Serializable pk)
    {
        if(pk != null)
        {
            Object beo = getSession().get(beoCls, pk);
            if(beo != null)
                getSession().delete(beo);
        }
    }

    public Serializable save(Object beo)
    {
        Serializable pk = getSession().save(beo);
        return pk;
    }

    public void update(Object beo)
    {
        if(beo != null)
            getSession().update(beo);
    }

    public void savrOrUpdate(Object beo)
    {
        if(beo != null)
            getSession().saveOrUpdate(beo);
    }

    public void merge(Object beo)
    {
        if(beo != null)
        	getSession().merge(beo);
    }

    public int updateBulk(String hql)
    {
        return updateBulk(hql, ((Object []) (null)));
    }

    public int updateBulk(String hql, Object value)
    {
        return updateBulk(hql, new Object[] {
            value
        });
    }

    public int updateBulk(String hql, List values)
    {
        return updateBulk(hql, values.toArray());
    }

    public int updateBulk(String hql, Object values[])
    {
        Query query = getSession().createQuery(hql);
        if(values != null)
        {
            for(int i = 0; i < values.length; i++)
                query.setParameter(i, values[i]);

        }
        return query.executeUpdate();
    }

    public Object find(Class beoCls, Serializable pk)
    {
        return getSession().get(beoCls, pk);
    }

    public Object findFirst(String hql)
    {
        List list = find(hql, 1, 1);
        return list.size() <= 0 ? null : list.get(0);
    }

    public Object findFirst(String hql, Object value)
    {
        List list = find(hql, 1, 1, value);
        return list.size() <= 0 ? null : list.get(0);
    }

    public Object findFirst(String hql, Object values[])
    {
        List list = find(hql, 1, 1, values);
        return list.size() <= 0 ? null : list.get(0);
    }

    public Object findFirst(String hql, List values)
    {
        List list = find(hql, 1, 1, values);
        return list.size() <= 0 ? null : list.get(0);
    }

    public List find(Class clazz)
    {
        List list = getSession().createCriteria(clazz).list();
        return ((List) (list != null ? list : new ArrayList()));
    }

    public List find(String hql)
    {
        return find(hql, ((Object []) (null)));
    }

    public List find(String hql, Object value)
    {
        return find(hql, new Object[] {
            value
        });
    }

    public List find(String hql, Object values[])
    {
        Query query = getSession().createQuery(hql);
        if(values != null)
        {
            for(int i = 0; i < values.length; i++)
                query.setParameter(i, values[i]);

        }
        List list = query.list();
        return ((List) (list != null ? list : new ArrayList()));
    }

    public List find(String hql, List values)
    {
        return find(hql, values.toArray());
    }

    public List find(String hql, int maxResults)
    {
        List list = find(hql, 1, maxResults);
        return list;
    }

    public List find(String hql, int maxResults, Object value)
    {
        List list = find(hql, 1, maxResults, value);
        return list;
    }

    public List find(String hql, int maxResults, Object values[])
    {
        List list = find(hql, 1, maxResults, values);
        return list;
    }

    public List find(String hql, int maxResults, List values)
    {
        List list = find(hql, 1, maxResults, values);
        return list;
    }

    public List find(String hql, int currentPage, int pageSize)
    {
        List list = find(hql, currentPage, pageSize, ((Object []) (null)));
        return list;
    }

    public List find(String hql, int currentPage, int pageSize, Object value)
    {
        List list = find(hql, currentPage, pageSize, new Object[] {
            value
        });
        return list;
    }

    public List find(String hql, int currentPage, int pageSize, List values)
    {
        List list = find(hql, currentPage, pageSize, values.toArray());
        return list;
    }

    public List find(String hql, int currentPage, int pageSize, Object values[])
    {
        Query query = getSession().createQuery(hql);
        if(values != null)
        {
            for(int i = 0; i < values.length; i++)
                query.setParameter(i, values[i]);

        }
        int start;
        if(currentPage <= 0)
            start = 0;
        else
            start = (currentPage - 1) * pageSize;
        query.setFirstResult(start);
        query.setMaxResults(pageSize);
        List list = query.list();
        return ((List) (list != null ? list : new ArrayList()));
    }

    public FlipPageInfo findWithPaging(FlipPageInfo fpi, String hql)
    {
        return findWithPaging(fpi, hql, ((Object []) (null)));
    }

    public FlipPageInfo findWithPaging(FlipPageInfo fpi, String hql, Object value)
    {
        return findWithPaging(fpi, hql, new Object[] {
            value
        });
    }

    public FlipPageInfo findWithPaging(FlipPageInfo fpi, String hql, List values)
    {
        return findWithPaging(fpi, hql, values.toArray());
    }

    public FlipPageInfo findWithPaging(FlipPageInfo fpi, String hql, Object values[])
    {
        String countString = getCountString(hql);
        return findWithPaging(fpi, hql, values, countString, values);
    }

    private String getCountString(String hsql)
    {
        String countString = hsql.trim();
        if(countString.indexOf("(") == 0)
            countString = countString.substring(1, countString.length() - 1);
        String charactor = null;
        StringBuilder word = new StringBuilder();
        int parenthesis = 0;
        boolean squotes = false;
        boolean dquotes = false;
        int idx = -1;
        int tableIndex = -1;
        String fromTable = null;
        String fromTable1 = null;
        String fromTable2 = null;
        String fromTable3 = null;
        String fromTable4 = null;
        for(int i = 0; i < countString.length(); i++)
        {
            charactor = countString.substring(i, i + 1);
            if("'".equalsIgnoreCase(charactor))
            {
                if(squotes)
                {
                    squotes = false;
                    word.delete(0, word.length());
                } else
                if(!dquotes)
                    squotes = true;
                continue;
            }
            if("\"".equalsIgnoreCase(charactor))
            {
                if(dquotes)
                {
                    dquotes = false;
                    word.delete(0, word.length());
                } else
                if(!squotes)
                    dquotes = true;
                continue;
            }
            if(squotes || dquotes)
                continue;
            if(!" ".equalsIgnoreCase(charactor) && i == countString.length() - 1)
                word.append(charactor);
            if(" ".equalsIgnoreCase(charactor) || i == countString.length() - 1)
            {
                if(word.length() <= 0)
                    continue;
                if(parenthesis == 0 && "from".equalsIgnoreCase(word.toString()))
                    idx = i - 4;
                else
                if(idx >= 0)
                    if(StringUtil.isEmpty(fromTable1))
                    {
                        fromTable1 = word.toString();
                        if(i == countString.length() - 1)
                            tableIndex = (i - idx) + 1;
                        else
                            tableIndex = i - idx;
                    } else
                    if(StringUtil.isEmpty(fromTable2))
                        fromTable2 = word.toString();
                    else
                    if(StringUtil.isEmpty(fromTable3))
                    {
                        fromTable3 = word.toString();
                    } else
                    {
                        if(!StringUtil.isEmpty(fromTable4))
                            break;
                        fromTable4 = word.toString();
                    }
                word.delete(0, word.length());
                continue;
            }
            if(",".equalsIgnoreCase(charactor))
            {
                if(idx >= 0)
                {
                    if(StringUtil.isEmpty(fromTable1))
                    {
                        fromTable1 = word.toString();
                        tableIndex = i - idx;
                    } else
                    if(StringUtil.isEmpty(fromTable2))
                        fromTable2 = word.toString();
                    else
                    if(StringUtil.isEmpty(fromTable3))
                        fromTable3 = word.toString();
                    else
                    if(StringUtil.isEmpty(fromTable4))
                        fromTable4 = word.toString();
                    break;
                }
                word.delete(0, word.length());
                continue;
            }
            if("(".equalsIgnoreCase(charactor))
            {
                if(parenthesis == 0 && "from".equalsIgnoreCase(word.toString()))
                {
                    idx = i - 4;
                    continue;
                }
                if(StringUtil.isNotEmpty(fromTable1))
                    break;
                parenthesis++;
                word.delete(0, word.length());
                continue;
            }
            if(")".equalsIgnoreCase(charactor))
            {
                if(idx >= 0)
                {
                    if(StringUtil.isEmpty(fromTable1))
                    {
                        fromTable1 = word.toString();
                        tableIndex = i;
                    } else
                    if(StringUtil.isEmpty(fromTable2))
                        fromTable2 = word.toString();
                    else
                    if(StringUtil.isEmpty(fromTable3))
                        fromTable3 = word.toString();
                    else
                    if(StringUtil.isEmpty(fromTable4))
                        fromTable4 = word.toString();
                    break;
                }
                word.delete(0, word.length());
                parenthesis--;
            } else
            {
                word.append(charactor);
            }
        }

        List split = new ArrayList();
        split.add("where");
        split.add("left");
        split.add("right");
        split.add("inner");
        split.add("cross");
        split.add("join");
        split.add("fetch");
        if("as".equalsIgnoreCase(fromTable2))
        {
            fromTable = fromTable3;
            tableIndex = -1;
        } else
        if(fromTable2 == null || split.contains(fromTable2.toLowerCase()) || "order".equalsIgnoreCase(fromTable2) && "by".equalsIgnoreCase(fromTable3) || "group".equalsIgnoreCase(fromTable2) && "by".equalsIgnoreCase(fromTable3))
            fromTable = (new StringBuilder("t_random_")).append(Double.valueOf(Math.random() * 10D).intValue()).toString();
        else
        if(fromTable3 == null && fromTable2 != null)
        {
            fromTable = fromTable2;
            tableIndex = -1;
        } else
        if(split.contains(fromTable3.toLowerCase()) || "order".equalsIgnoreCase(fromTable3) && "by".equalsIgnoreCase(fromTable4) || "group".equalsIgnoreCase(fromTable3) && "by".equalsIgnoreCase(fromTable4))
        {
            fromTable = fromTable2;
            tableIndex = -1;
        } else
        {
            fromTable = (new StringBuilder("t_random_")).append(Double.valueOf(Math.random() * 10D).intValue()).toString();
        }
        countString = countString.substring(idx);
        parenthesis = 0;
        word.delete(0, word.length());
        idx = -2;
        squotes = false;
        dquotes = false;
        if(countString.toLowerCase().replaceAll("[ ]+", " ").contains("order by"))
        {
            for(int i = countString.length() - 1; i >= 0; i--)
            {
                charactor = countString.substring(i, i + 1);
                if("'".equalsIgnoreCase(charactor))
                {
                    if(squotes)
                    {
                        squotes = false;
                        word.delete(0, word.length());
                    } else
                    if(!dquotes)
                        squotes = true;
                    continue;
                }
                if("\"".equalsIgnoreCase(charactor))
                {
                    if(dquotes)
                    {
                        dquotes = false;
                        word.delete(0, word.length());
                    } else
                    if(!squotes)
                        dquotes = true;
                    continue;
                }
                if(squotes || dquotes)
                    continue;
                if(" ".equalsIgnoreCase(charactor))
                {
                    if(word.length() <= 0)
                        continue;
                    if(parenthesis == 0)
                    {
                        if("by".equalsIgnoreCase(word.toString()))
                        {
                            word.insert(0, charactor);
                            continue;
                        }
                        if("order by".equalsIgnoreCase(word.toString()))
                        {
                            idx = i;
                            break;
                        }
                        word.delete(0, word.length());
                    } else
                    {
                        word.delete(0, word.length());
                    }
                    continue;
                }
                if(",".equalsIgnoreCase(charactor))
                {
                    word.delete(0, word.length());
                    continue;
                }
                if("(".equalsIgnoreCase(charactor))
                {
                    word.delete(0, word.length());
                    parenthesis--;
                    continue;
                }
                if(")".equalsIgnoreCase(charactor))
                {
                    if(parenthesis == 0 && "order by".equalsIgnoreCase(word.toString()))
                    {
                        idx = i;
                        break;
                    }
                    parenthesis++;
                } else
                {
                    word.insert(0, charactor);
                }
            }

        }
        if(idx >= 0)
            countString = countString.substring(0, idx);
        if(tableIndex > 0)
            countString = (new StringBuilder(countString)).insert(tableIndex, (new StringBuilder(" ")).append(fromTable).append(" ").toString()).toString();
        return (new StringBuilder("select count(")).append(fromTable).append(") ").append(countString.replace("fetch", "")).toString();
    }

    public FlipPageInfo findWithPaging(FlipPageInfo fpi, String hql, int totalRecord)
    {
        return findWithPaging(fpi, hql, ((Object []) (null)), totalRecord);
    }

    public FlipPageInfo findWithPaging(FlipPageInfo fpi, String hql, Object value, int totalRecord)
    {
        return findWithPaging(fpi, hql, new Object[] {
            value
        }, totalRecord);
    }

    public FlipPageInfo findWithPaging(FlipPageInfo fpi, String hql, List values, int totalRecord)
    {
        return findWithPaging(fpi, hql, values.toArray(), totalRecord);
    }

    public FlipPageInfo findWithPaging(FlipPageInfo fpi, String hql, Object values[], int totalRecord)
    {
        if(fpi == null)
            fpi = new FlipPageInfo();
        List list = find(hql, fpi.getPage().intValue(), fpi.getPageSize().intValue(), values);
        fpi = new FlipPageInfo(fpi.getPage(), fpi.getPageSize(), Integer.valueOf(totalRecord), list);
        return fpi;
    }

    public FlipPageInfo findWithPaging(FlipPageInfo fpi, String hql, String countString)
    {
        return findWithPaging(fpi, hql, null, countString, null);
    }

    public FlipPageInfo findWithPaging(FlipPageInfo fpi, String hql, String countString, Object value)
    {
        return findWithPaging(fpi, hql, new Object[] {
            value
        }, countString, new Object[] {
            value
        });
    }

    public FlipPageInfo findWithPaging(FlipPageInfo fpi, String hql, String countString, Object values[])
    {
        return findWithPaging(fpi, hql, values, countString, values);
    }

    public FlipPageInfo findWithPaging(FlipPageInfo fpi, String hql, String countString, List values)
    {
        return findWithPaging(fpi, hql, values.toArray(), countString, values.toArray());
    }

    public FlipPageInfo findWithPaging(FlipPageInfo fpi, String hql, Object hqlParam[], String countString, Object countParam[])
    {
        if(fpi == null)
            fpi = new FlipPageInfo();
        List list = find(hql, fpi.getPage().intValue(), fpi.getPageSize().intValue(), hqlParam);
        int totalRecord = findInt(countString, countParam);
        fpi = new FlipPageInfo(fpi.getPage(), fpi.getPageSize(), Integer.valueOf(totalRecord), list);
        return fpi;
    }

    public int findInt(String hql)
    {
        return findInt(hql, ((Object []) (null)));
    }

    public int findInt(String hql, Object value)
    {
        return findInt(hql, new Object[] {
            value
        });
    }

    public int findInt(String hql, List values)
    {
        return findInt(hql, values.toArray());
    }

    public int findInt(String hql, Object values[])
    {
        List list = find(hql, 1, 1, values);
        int result = 0;
        if(list.size() > 0 && list.get(0) != null)
            try
            {
                result = Integer.parseInt(list.get(0).toString());
            }
            catch(Exception exception) { }
        return result;
    }

    public List findBySql(String sql,Class clazz)
    {
        return findBySql(sql, null,clazz);
    }

    public List findBySql(String sql, Object values[],Class clazz)
    {
    	
        SQLQuery query = getSession().createSQLQuery(sql);
        if(clazz!=null)
        	query.setResultTransformer(Transformers.aliasToBean(clazz));
        if(values != null)
        {
            for(int i = 0; i < values.length; i++)
                query.setParameter(i, values[i]);

        }
        
        List list = query.list();
        return ((List) (list != null ? list : new ArrayList()));
    }

    public void excuteBySql(String sqls[])
    {
        String as[];
        int j = (as = sqls).length;
        for(int i = 0; i < j; i++)
        {
            String sql = as[i];
            Query query = getSession().createSQLQuery(sql);
            query.executeUpdate();
        }

    }
    
    //******************************************************************//
    public FlipPageInfo findWithPagingBySql(FlipPageInfo fpi, String sql,Class clazz)
    {
        if(fpi == null)
            fpi = new FlipPageInfo();
        List list = findbySql(sql, fpi.getPage().intValue(), fpi.getPageSize().intValue(), null,clazz);
        int totalRecord = totalrecord(sql);
        fpi = new FlipPageInfo(fpi.getPage(), fpi.getPageSize(), Integer.valueOf(totalRecord), list);
        return fpi;
    }
    
    public List findbySql(String sql, int currentPage, int pageSize, Object values[],Class clazz)
    {
    	SQLQuery query = getSession().createSQLQuery(sql);
        if(clazz!=null)
        	query.setResultTransformer(Transformers.aliasToBean(clazz));
        if(values != null)
        {
            for(int i = 0; i < values.length; i++)
                query.setParameter(i, values[i]);

        }
        int start;
        if(currentPage <= 0)
            start = 0;
        else
            start = (currentPage - 1) * pageSize;
        query.setFirstResult(start);
        query.setMaxResults(pageSize);
        List list = query.list();
        return ((List) (list != null ? list : new ArrayList()));
    }
    
    public int totalrecord(String sql) {
		int result =0;
		Query query = getSession().createSQLQuery(sql);
	    List list = query.list();
	    if(list!=null&&list.size()!=0)
	    	result = list.size();
		return result;
	}

    public ResultSet callProc(String sql , List<ProcParameter> param){
    	ProcUtil proc = new ProcUtil(sql,param);
    	getSession().doWork( proc );
    	return proc.getRs();
    }
    
   @Resource
    private SessionFactory sessionFactory;
}
