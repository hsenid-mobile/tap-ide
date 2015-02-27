public interface MainMBean {
    public void addApplication(String type, String urlString);
    public String checkHeartBeat(String msg);
    public void terminate();
}
