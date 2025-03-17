package frame;

import java.util.List;

public interface Function {
	public CustomerDTO passInfo(String name);
	public String title();
	public List<String> kinds(List<Integer> list);
	public List<String> genders(List<Integer> list);
}
