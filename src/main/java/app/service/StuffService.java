package app.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import app.dao.StuffDao;
import app.entity.Stuff;
import app.entity.Stuff.StuffType;

@Named
public class StuffService {
	
	@Inject
	private StuffDao stuffDao;
	
	@Transactional
	public List<Stuff> getStuffList() {	
  		return stuffDao.getStuffList();  		
  	}
	
	@Transactional
	public void remove(Stuff stuff){		
		stuffDao.remove(stuffDao.getStuffByID(stuff.getStuffID()));
	}
	
	@Transactional
	public void addStuff(Stuff stuff){
		stuffDao.addStuff(stuff);
	}
	
	@Transactional
	public void edit(Stuff stuff){		
		Stuff stuffEdit = stuffDao.getStuffByID(stuff.getStuffID());
		stuffEdit.setName(stuff.getName());
		stuffEdit.setPassword(stuff.getPassword());
		stuffEdit.setType(stuff.getType());
		stuffEdit.setLogin(stuff.getLogin());
		stuffEdit.setSecondName(stuff.getSecondName());
		stuffDao.edit(stuffEdit);
	}
	
	public StuffType checkLoginAndPassword(String login, String password){		
		Stuff stuff = stuffDao.getStuffByLogin(login);
		if (stuff == null) return null;
		if (!(stuff.getPassword().equals(password))) return null;
		return stuff.getType();
	}
	
}
