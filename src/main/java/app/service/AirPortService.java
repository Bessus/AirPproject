package app.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import app.dao.AirPortDao;
import app.entity.AirPort;



@Named
public class AirPortService {
	@Inject
	private AirPortDao airPortDao;
	
	public AirPortService(){
		
	}
	
	@Transactional
	public void removeAirPort(AirPort airPort){
		airPortDao.removeAirPort(airPort);
	}
	
	@Transactional
    public List<AirPort> getAirPortList() {
		return airPortDao.getAirPortList();
    }
	
	@Transactional
    public AirPort  getAirPortByName(String city) {
		return airPortDao.getAirPortByName(city);
    }
}
