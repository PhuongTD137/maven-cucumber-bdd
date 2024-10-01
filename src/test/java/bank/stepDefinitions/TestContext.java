package bank.stepDefinitions;

public class TestContext {
	private DataContext dataContext;

	public TestContext() {
		dataContext = new DataContext();
	}

	public DataContext getDataContext() {
		return dataContext;
	}
}
