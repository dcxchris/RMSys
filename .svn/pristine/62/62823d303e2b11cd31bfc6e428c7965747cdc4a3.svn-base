package com.ctgu.util;

// Source File Name:   FlipPageInfo.java


import java.util.ArrayList;
import java.util.List;

public class FlipPageInfo<T>
{

    public FlipPageInfo()
    {
        this(Integer.valueOf(1), PAGESIZE, Integer.valueOf(0), null);
    }

    public FlipPageInfo(Integer page)
    {
        this(page, PAGESIZE, null, null);
    }

    public FlipPageInfo(Integer page, Integer pageSize)
    {
        this(page, pageSize, null, null);
    }

    public FlipPageInfo(Integer page, Integer totalRecord, List<T> data)
    {
        this(page, PAGESIZE, totalRecord, data);
    }

    public FlipPageInfo(Integer page, Integer pageSize, Integer totalRecord, List<T> data)
    {
        this.page = Integer.valueOf(page != null ? page.intValue() : 1);
        this.pageSize = pageSize != null ? pageSize : PAGESIZE;
        this.totalRecord = Integer.valueOf(totalRecord != null ? totalRecord.intValue() : 0);
        this.data = data;
        if(data == null)
            this.data = new ArrayList();
    }

    public Integer getPage()
    {
        return page;
    }

    public void setPage(Integer page)
    {
        if(page.intValue() <= 0)
            page = Integer.valueOf(1);
        this.page = page;
    }

    public Integer getPageSize()
    {
        if(pageSize.intValue() <= 0)
            pageSize = PAGESIZE;
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        if(pageSize.intValue() <= 0)
            pageSize = PAGESIZE;
        this.pageSize = pageSize;
    }

    public Integer getTotalRecord()
    {
        if(totalRecord.intValue() <= 0)
            totalRecord = Integer.valueOf(0);
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord)
    {
        if(totalRecord.intValue() <= 0)
            totalRecord = Integer.valueOf(0);
        this.totalRecord = totalRecord;
    }

    public List<T> getData()
    {
        if(data == null)
            data = new ArrayList();
        return data;
    }

    public void setData(List<T> data)
    {
        if(data == null)
            this.data = new ArrayList();
        this.data = data;
    }

    public Integer getTotalPage()
    {
        int totalPage = totalRecord.intValue() / pageSize.intValue();
        if(totalRecord.intValue() % pageSize.intValue() != 0)
            totalPage++;
        return Integer.valueOf(totalPage);
    }

    private Integer page;
    private Integer pageSize;
    private Integer totalRecord;
    private Object sum1 = 0;
    private Object sum2 = 0;
    private Object sum3 = 0;
    private Object sum4 = 0;
    private Object sum5 = 0;
    private Object sum6 = 0;
    private List data;
    private static final Integer PAGESIZE = Integer.valueOf(10);
	public Object getSum1() {
		return sum1;
	}

	public void setSum1(Object sum1) {
		this.sum1 = sum1;
	}

	public Object getSum2() {
		return sum2;
	}

	public void setSum2(Object sum2) {
		this.sum2 = sum2;
	}

	public Object getSum3() {
		return sum3;
	}

	public void setSum3(Object sum3) {
		this.sum3 = sum3;
	}

	public Object getSum4() {
		return sum4;
	}

	public void setSum4(Object sum4) {
		this.sum4 = sum4;
	}

	public Object getSum5() {
		return sum5;
	}

	public void setSum5(Object sum5) {
		this.sum5 = sum5;
	}

	public Object getSum6() {
		return sum6;
	}

	public void setSum6(Object sum6) {
		this.sum6 = sum6;
	}
	
    
    

}
