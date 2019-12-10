package servercontroller;

public interface Caller{
	public int PlayerID();
	public String PlayerNamer();
	public boolean UpdateUI(String update);
	public void Start(String inform);
	public void Wait(String inform);
	public boolean Challange(String inform);
	public boolean End(String inform);
	public void SetGoal(String inform);
	public void ConciderChallange(String inform);
}
