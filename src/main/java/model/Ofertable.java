package model;

import java.util.List;
import java.util.Map;

public interface Ofertable {

	public Integer getId();
	
	//public void setId(Integer id);

	public String getName();
	
	public List<String> getNames();
	
	public void setName(String name);

	public Double getCost();
	
	public Double getDuration();
	
	public String getDescription();

	public void setDescription(String description) ;
	
	public String getImage();
	
	public void setImage(String image);
	
	public boolean canHost(int i); //HAY CUPO?

	public void host(int i); //RESTAR CUPO

	public boolean soyPromocion();

	public boolean included(Ofertable oferta); //esta incluida?

	public String toString() ;
	
	public void validate();
	
	public boolean isValid();
	
	public Map<String, String> getErrors();
}
