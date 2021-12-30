package utils;

import java.util.Comparator;

import model.Ofertable;

public class OrdenadorOfertable implements Comparator<Ofertable> {

	@Override
	public int compare(Ofertable o1, Ofertable o2) {
		if (o1.soyPromocion() && !o2.soyPromocion())
			return -1;
		else if (!o1.soyPromocion() && o2.soyPromocion())
			return 1;
		else if (o1.getCost() > o2.getCost())
			return -1;
		else if (o1.getCost() < o2.getCost())
			return 1;
		else if (o1.getDuration() > o2.getDuration())
			return -1;
		else if (o1.getDuration() < o2.getDuration())
			return 1;
		else
			return 0;
	}

}

