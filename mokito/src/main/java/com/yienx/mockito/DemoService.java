package com.yienx.mockito;

/**
 * @Author wangyanbo29
 * @Date 2024/2/20
 * @Description
 */
public class DemoService {
    
    private DemoDao demoDao;

    public DemoService(DemoDao demoDao) {
        this.demoDao = demoDao;
    }

    public int getDemoStatus(){
        return demoDao.getDemoStatus();
    }
}
