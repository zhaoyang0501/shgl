
package com.pzy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pzy.entity.Grades;
import com.pzy.entity.MsgBoard;
import com.pzy.repository.MsgBoardRepository;

@Service
public class MsgBoardService {
		
		@Autowired
		private MsgBoardRepository msgBoardRepository;
		public MsgBoard findOne(Long  id){
			return msgBoardRepository.findOne(id);
		}
		public void save(MsgBoard msgBoard){
			msgBoardRepository.save(msgBoard);
		}
		public List<MsgBoard> findByGrades(Grades grades){
			List<MsgBoard> list= msgBoardRepository.findByGrades(grades);
			this.addSubMsg(list);
			return list;
		}
		private void addSubMsg( List<MsgBoard> list){
			if(list!=null&&list.size()!=0){
				for(MsgBoard bean:list){
					bean.setSubMsg(msgBoardRepository.findByReplyfor(bean));
				}
			}
		}
		
}