package com.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.dao.generic.GenericDao;
import com.entity.CarouselDiagram;
@Component
public class CarouselDiagramDao extends GenericDao<CarouselDiagram> {
	public List<CarouselDiagram>getAll(){
		return super.find("status", 1);
	}
}
