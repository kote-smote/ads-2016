import adt.HashTable;
import dataStructures.ClosedBucketHashTable;
import java.util.Scanner;

/**
 * Lab 6 / Problem 2 - Static Routing
 */
public class StaticRouting {

    public static void main(String[] args) {
        HashTable<String, Router> routers = new ClosedBucketHashTable<>(17);
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < N; i++) {
            String interfaceAddress = scanner.nextLine();
            String[] routes = scanner.nextLine().split(",");
            Router router = new Router(interfaceAddress, routes);
            routers.insert(interfaceAddress, router);
        }

        N = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < N; i++) {
            String interfaceAddress = scanner.nextLine();
            String ipAddress = scanner.nextLine();
            Router router = routers.search(interfaceAddress);
            if (router != null && router.hasRoute(ipAddress))
                System.out.println("postoi");
            else
                System.out.println("ne postoi");
        }
    }
}

class Router {
    private String interfaceIP;
    HashTable<IPAddress, String> rootTable;

    public Router(String interfaceIP, String... routes) {
        this.interfaceIP = interfaceIP;
        rootTable = new ClosedBucketHashTable<>(11);
        for (String route : routes)
            rootTable.insert(new IPAddress(route), route);
    }

    public boolean hasRoute(String routeIP) {
        IPAddress ipAddress = new IPAddress(routeIP);
        return rootTable.search(ipAddress) != null;
    }
}

class IPAddress {
    private String address;

    public IPAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        return mainNetwork().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!IPAddress.class.isAssignableFrom(obj.getClass()))
            return false;
        IPAddress other = (IPAddress) obj;
        return this.mainNetwork().equals(other.mainNetwork());
    }

    public String toString() {
        return address;
    }

    private String mainNetwork() {
        int end = address.lastIndexOf(".");
        String mainNetwork = address.substring(0, end);
        return mainNetwork;
    }
}


