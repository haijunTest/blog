package com.example.api.mapper;

import com.example.api.model.MmUser;
import com.myMapper.MyMapper;

public interface MmUserMapper extends MyMapper<MmUser> {

    MmUser getUserInfo (String userName);
}