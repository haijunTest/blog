package com.mapper;

import com.example.api.api.ApiApplication;
import com.example.api.mapper.MmUserMapper;
import com.example.api.model.MmUser;
import com.example.api.util.Md5;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author haijun
 * @class ${classname}
 * @date 2018/6/9, 18:22
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class MmUserMapperTest {

    @Autowired
    private MmUserMapper mmUserMapper;

    @Test
    public void t1 (){
        MmUser mmUser = mmUserMapper.selectByPrimaryKey(1);
        System.out.print("info ----"+mmUser.getPassword()+"\n");
        log.info("info ----",mmUser.getPassword());
    }

    @Test
    public void testMd5 () throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String userName = "mengmeng";
        String passWord ="941014Meng";
        String encryptedPwd = Md5.getEncryptedPwd(passWord);
        MmUser mmUser = new MmUser();
        mmUser.setUsername(userName);
        mmUser.setPassword(encryptedPwd);
        int insert = mmUserMapper.insert(mmUser);
        if (insert == 1){
            System.out.print("数据存入成功!"+"\n");
        }
    }

    @Test
    public void testMd5Parse () throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String userName = "mengmeng";
        String passWord ="941014Meng";
        String pass = "B47831C9DED08B0B46565D34C235B8355B571F46BB60D37EAE1E2267";
        boolean result = Md5.validPassword(passWord, pass);
        if (result){
            System.out.print("数据验证成功!"+"\n");
        }
    }

    @Test
    public void testV1 (){
        String userName = "haijun";
        MmUser mmUser = new MmUser();
        mmUser.setUsername(userName);
        MmUser info = mmUserMapper.selectOne(mmUser);
        System.out.println("info ---"+info.getUsername()+","+info.getPassword());
    }

    @Test
    public void testV2 (){
        MmUser info = mmUserMapper.getUserInfo("haijun");
        System.out.println("info ---"+info.getUsername()+","+info.getPassword());
    }
}
