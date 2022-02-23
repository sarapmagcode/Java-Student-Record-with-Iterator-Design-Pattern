import java.util.ArrayList;

public class RecordRepository implements Container {

	private ArrayList<String> data;
	
	public RecordRepository(ArrayList<ArrayList<String>> info) {
		data = new ArrayList<String>();
		for (int i = 0; i < (int) info.size(); i++) {
			for (int j = 0; j < (int) info.get(i).size(); j++) {
				data.add(info.get(i).get(j).toString());
			}
		}
	}
	
	@Override
	public Iterator getIterator() {
		return new RecordIterator();
	}
	
	private class RecordIterator implements Iterator {

		int index = 0;
		
		@Override
		public boolean hasNext() {
			if (index < (int) data.size()) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			if (this.hasNext()) {
				return data.get(index++);
			}
			return null;
		}
	}
}
