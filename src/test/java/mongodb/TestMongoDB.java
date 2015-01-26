package mongodb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import com.yaoxg.mongodb.data.Page;
import com.yaoxg.mongodb.data.dao.inter.UserDao;
import com.yaoxg.mongodb.data.model.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestMongoDB {
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Test
    public void insertAll() {
        List<UserEntity> entityList = new ArrayList<UserEntity>();
        userDao.deleteAll();
        for (int i = 0; i < 10000; i++) {
            UserEntity entity1 = new UserEntity();
            entity1.setId(UUID.randomUUID().toString());
            entity1.setAge(1);
            if (i % 2 == 0) {
                entity1.setName("jacky" + i);
            } else {
                entity1.setName("yaoxg" + i);
            }

            entity1.setBirth(new Date());
            entity1.setPassword("password" + i);
            entity1.setRegionName("北京" + i);
            entity1.setWorks(1);
            entityList.add(entity1);
        }
        userDao.insertAll(entityList);

    }

    @Test
    public void findByKeyValue() {
        List<UserEntity> list = userDao.findByKeyValue("regionName", "北京3");
        if (!CollectionUtils.isEmpty(list)) {
            for (UserEntity e : list) {
                System.out.println("id=" + e.getId() + ", age=" + e.getAge()
                        + ", password=" + e.getPassword() + ", regionName="
                        + e.getRegionName() + ", special="
                        + Arrays.toString(e.getSpecial()) + ", name="
                        + e.getName() + ", birth=" + e.getBirth());
            }
        }
    }

    @Test
    public void findAll() {
        List<UserEntity> list = userDao.findAll();
        if (!CollectionUtils.isEmpty(list)) {
            for (UserEntity e : list) {
                System.out.println("id=" + e.getId() + ", age=" + e.getAge()
                        + ", password=" + e.getPassword() + ", regionName="
                        + e.getRegionName() + ", special="
                        + Arrays.toString(e.getSpecial()) + ", name="
                        + e.getName() + ", birth=" + e.getBirth());
            }
        }
    }

    @Test
    public void deleteByKeyValue() {
        // 匹配多个则删除多个
        userDao.deleteByKeyValue("password", "password0");
        List<UserEntity> list = userDao.findByKeyValue("password", "password0");
        Assert.assertTrue(CollectionUtils.isEmpty(list));
    }

    @Test
    public void findPageList() {
        Page<UserEntity> page = new Page<UserEntity>();
        page.setPageSize(10);
        page.setPageNumber(2);
        Query query = new Query();
        // 如果没有查询条件，则query.addCriteria(new Criteria());
        query.addCriteria(new Criteria("age").is(1));
        page = userDao.findPageList(page, query);
        if (!CollectionUtils.isEmpty(page.getRows())) {
            for (UserEntity e : page.getRows()) {
                System.out.println("id=" + e.getId() + ", age=" + e.getAge()
                        + ", password=" + e.getPassword() + ", regionName="
                        + e.getRegionName() + ", special="
                        + Arrays.toString(e.getSpecial()) + ", name="
                        + e.getName() + ", birth=" + e.getBirth());
            }
        }
    }

    @Test
    public void deleteAll() {
        userDao.deleteAll();
        List<UserEntity> list = userDao.findAll();
        Assert.assertTrue(CollectionUtils.isEmpty(list));
    }
}
