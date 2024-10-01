package bank.stepDefinitions;

import java.util.HashMap;
import java.util.Map;

public class DataContext {
	Map<String, Object> dataContext;

	public DataContext() {
		this.dataContext = new HashMap<>();
	}

	public void setDataContext(Context key, String value) {
		dataContext.put(key.toString(), value);
	}

	public Object getDataContext(Context key) {
		return dataContext.get(key.toString());
	}
}
