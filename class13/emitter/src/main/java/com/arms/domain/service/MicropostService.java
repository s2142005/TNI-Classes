package com.arms.domain.service;

import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;
import com.arms.app.home.MicropostCreateForm;
import com.arms.domain.entity.Micropost;

@Service
public class MicropostService extends AppService {
	
	public int createMicropost(MicropostCreateForm micropostCreateForm){
		Date nowDate = Calendar.getInstance().getTime();
		Micropost micropost = new Micropost();
		micropost.setContent(micropostCreateForm.getContent());
		micropost.setUserId(micropostCreateForm.getUserId());
		micropost.setCreated(nowDate);
        		micropost.setUpdated(nowDate);
        		micropostRepository.save(micropost);
		return micropost.getId();
	}
	public void deleteMicropost(int micropostId){
		micropostRepository.delete(micropostId);
	}	

}
