package src;

//presa dall'esame 2015 primo appello b
//implementata da me

import java.util.ArrayList;
import java.util.List;
public class ProgressiveAcceptorImpl<X> implements ProgressiveAcceptor<X>{
	
	private ProgressiveFilter<X> filter = null;
	private Aggregator<X> aggregator = null;
	private int size = 0;
	private List<X> list;

	@Override
	public void setProgressiveFilter(final ProgressiveFilter<X> filter) {
		this.filter = filter;		
	}

	@Override
	public void setAggregator(final Aggregator<X> aggregator) {
		this.aggregator = aggregator;		
	}

	@Override
	public void setSize(final int size) {
		if (size < 0){
			throw new IllegalArgumentException();
		}
		list = new ArrayList<X>(size);
		this.size = size;
	}

	@Override
	public boolean accept(final int pos, final X elem) {
		if (this.filter == null || this.aggregator == null || this.size == 0){
			throw new IllegalStateException();
		}
		
		
		if (pos == this.size || pos == this.size - 1 && list.size() == this.size){
			return false;
		}
		
		if (pos > 0){
			if (filter.isNextOK(list.get(pos-1), elem)){	
				final List<X> temp = new ArrayList<>();
				for (int i = 0; i < pos; i++){
					temp.add(list.get(i));
				}
				list.retainAll(temp);
				list.add(pos, elem);
				return true;
			}			
		}
		else { //se aggiungo il primo elemento della lista devo cancellare tutti quelli precedenti
			list.clear();
			list.add(elem);
			return true;
		}
		return false;
	}

	@Override
	public X aggregateAll() {
		X temp = list.get(0);		
		for (int i = 1; i < list.size(); i++) {
			temp = aggregator.aggregate(temp, list.get(i));
		}		
		return temp;
	}

}
