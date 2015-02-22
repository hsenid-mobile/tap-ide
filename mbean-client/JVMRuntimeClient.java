
import javax.management.*;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;

public class JVMRuntimeClient {
    public static void main(String[] args) throws Exception  {

        if (args == null)  {
        System.out.println("Usage: java JVMRuntimeClient HOST PORT");
    }
    if(args.length < 2)
    {
        System.out.println("Usage: java JVMRuntimeClient HOST PORT");
    }

    try
    {
        JMXServiceURL target = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://0.0.0.0:1617/jmxrmi");
        JMXConnector connector = JMXConnectorFactory.connect(target);
        MBeanServerConnection remote = connector.getMBeanServerConnection();

        ObjectName mbeanName = new ObjectName("hms.ctap.simulator:type=Main");
        MainMBean mbeanProxy = JMX.newMBeanProxy(remote,mbeanName,MainMBean.class,true);

        System.out.println("invoking");
        mbeanProxy.addApplication(args[0],args[1]);
    }
    catch(Exception e)
    {
        System.out.println(e.getMessage());
        System.exit(0);
    }
   }
}
