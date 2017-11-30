import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.ISet;

import java.util.Arrays;
import java.util.Map;

public class Main {

    public static void main(final String[] args) {

        final Config config = new Config();
        config.setLicenseKey("");

        final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);

        final Map<Integer, String> customers = hazelcastInstance.getMap("customers");
        final ISet<Object> customersSet = hazelcastInstance.getSet("customers set");


        customers.put(1, "Joe");
        customers.put(2, "Ali");
        customers.put(3, "Avi");

        customersSet.addAll(Arrays.asList(1, 2, 3));

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress("192.168.1.188");

        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap map = client.getMap("customers");
        ISet set = client.getSet("customers set");



        System.out.println("Map Size:" + map.size());
        System.out.println("Set Size:" + set.size());

        client.shutdown();
        hazelcastInstance.shutdown();

        for (; ; ) {
            break;
        }
    }
}
