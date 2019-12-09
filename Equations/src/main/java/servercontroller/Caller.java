package servercontroller;

public interface Caller{
	public void Player();
	public int PlayerID();
	public boolean UpdateUI(String update);
	public void Start(String inform);
	public void Wait(String inform);
	public boolean Challange(String inform);
	public boolean Win(String inform);
	public boolean Lose(String inform);
	public void SetGoal(String inform);
}
