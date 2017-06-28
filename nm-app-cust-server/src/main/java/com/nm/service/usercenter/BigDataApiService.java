package com.nm.service.usercenter;


import com.nm.cmd.BigDataHFiveCmd;
import com.nm.model.BigDataHFivemodel;

public interface BigDataApiService {

	BigDataHFivemodel bigDataFromHFive(BigDataHFiveCmd bigDataH5Cmd);

	BigDataHFivemodel bigDataFromzhima(BigDataHFiveCmd bigDataHFiveCmd);

}
