package com.dr.sell.dao;

import com.dr.sell.dateObj.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOneTest() {
        Optional<ProductCategory> byId = productCategoryRepository.findById(1);
        if (byId.isPresent()) {
            ProductCategory productCategoryEntity = byId.get();
            log.info(productCategoryEntity.toString());
        }
    }

    @Test
    @Transactional //测试加事务表示完全回滚 不管成功与否都会回滚
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("xx");
        productCategory.setCategoryType(28821);
        ProductCategory save = productCategoryRepository.save(productCategory);
        Assert.assertNotEquals(null, save);
    }
    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        List<ProductCategory> byCategoryTypeIn = productCategoryRepository.findByCategoryTypeIn(list);
        byCategoryTypeIn.forEach(b -> System.out.println(b.toString()));
    }
}