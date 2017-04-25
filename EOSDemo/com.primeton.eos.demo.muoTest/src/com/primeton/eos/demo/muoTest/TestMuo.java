package com.primeton.eos.demo.muoTest;

import com.eos.data.datacontext.DataContextManager;
import com.eos.data.datacontext.IMUODataContext;
import com.eos.system.annotation.Bizlet;

/** 
 * @author Jin.He
 * @version 2017年3月28日 下午9:52:56
 */
@Bizlet("")
public class TestMuo {
	@Bizlet
	public static void showMuo(String id){
		//获取当前线程中的IMUODataContext
		IMUODataContext muo = DataContextManager.current().getMUODataContext();
		System.out.println(id+"==========="+muo.toString());
	}
}
