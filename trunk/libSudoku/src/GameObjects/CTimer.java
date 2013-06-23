package GameObjects;

public class CTimer {
	
	
	private static CTimer instance = null;
	
	public static CTimer getInstance() {
		if(instance == null) {
			instance = new CTimer();
		}
		return instance;
	}
	
	// Variables
	private float elapsedTime;
	
	public int hour;
	public int minute;
	public int second;
	
	// Constructor
	public CTimer() {
		// TODO Auto-generated constructor stub
		elapsedTime = 0;
	}
	
	public void resetTimer() {
		elapsedTime = 0;
		hour = minute = second = 0;
	}
	
	public void updateTime(float dt) {
		elapsedTime += dt;
		hour = (int) (elapsedTime/3600);
		minute = (int) (elapsedTime/60 - hour*3600);
		second = (int) (elapsedTime - hour*3600 - minute*60);
	}
	
	public String toString() {
		String timeStr = hour + ":" + minute + ":" + second;
		return timeStr;
	}
}
